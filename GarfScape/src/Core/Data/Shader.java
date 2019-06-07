package Core.Data;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL20.*;

public class Shader
{

    private static Map<String, String> defines = new HashMap<String, String>();
    private int programID;

    enum ParseStatus
    {
        Vertex,
        Fragment
    }

    public Shader(String shaderFile)
    {
        init();

        String vert = "";
        String frag = "";
        ParseStatus status = ParseStatus.Vertex;

        try
        {
            Scanner scanner = new Scanner(FileLoader.loadFile(shaderFile));
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                if (line.startsWith("@"))
                {
                    if (line.equalsIgnoreCase("@vertex"))
                    {
                        status = ParseStatus.Vertex;
                    }
                    else if (line.equalsIgnoreCase("@fragment"))
                    {
                        status = ParseStatus.Fragment;
                    }
                    else
                    {
                        throw new Exception("invalid shader type '" + line + "'");
                    }
                }
                else
                {
                    switch (status)
                    {
                        case Fragment:
                            frag += line + "\n";
                            break;
                        case Vertex:
                            vert += line + "\n";
                            break;
                    }
                }
            }

            scanner.close();
            compileShader(preprocessShader(vert), preprocessShader(frag));

        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(-1);
        }

    }

    boolean isInit = false;

    private void init()
    {
        if (!isInit)
        {
            defines.put("$_DEF_POSITIONS", "layout (location = 0) in vec3 _IN_POS;");
            defines.put("$_DEF_TEX_COORDS", "layout (location = 1) in vec2 _IN_TEX_COORDS;");
            defines.put("$_DEF_NORMALS", "layout (location = 2) in vec3 _IN_NORMALS;");

            defines.put("$_NORMALS", "_IN_NORMALS");
            defines.put("$_POSITIONS", "_IN_POS");
            defines.put("$_TEX_COORDS", "_IN_TEX_COORDS");

            isInit = true;
        }
    }

    public void bind()
    {
        GL20.glUseProgram(programID);
    }

    public void unBind()
    {
        GL20.glUseProgram(0);
    }

    private void compileShader(String vertexSource, String fragmentSource)
    {
        int vertProgram = createShader(vertexSource, GL20.GL_VERTEX_SHADER);
        int fragProgram = createShader(fragmentSource, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();

        GL20.glAttachShader(programID, vertProgram);
        GL20.glAttachShader(programID, fragProgram);

        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE)
        {
            System.err.println("Program failed to link");
            System.err.println(glGetProgramInfoLog(programID, glGetProgrami(programID, GL_INFO_LOG_LENGTH)));
            System.exit(-1);
        }

        GL20.glValidateProgram(programID);
    }

    private int createShader(String source, int shaderType)
    {
        int shader = 0;
        shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);

        if (shader == 0)
        {
            return 0;
        }

        ARBShaderObjects.glShaderSourceARB(shader, source);
        ARBShaderObjects.glCompileShaderARB(shader);

        if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
        {
            System.err.println("Error creating shader: " + getLogInfo(shader));
            switch (shaderType)
            {
                case GL20.GL_VERTEX_SHADER:
                    System.err.println("Shader type: VERTEX_SHADER");
                    break;
                case GL20.GL_FRAGMENT_SHADER:
                    System.err.println("Shader type: FRAGMENT_SHADER");
                    break;
            }
            System.err.println(source);
            System.exit(-1);
        }

        return shader;
    }

    private static String getLogInfo(int obj)
    {
        return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }

    private String preprocessShader(String shader)
    {
        String[] words = shader.split("(?=\\W)");
        String reconstruct = "";

        for (String word : words)
        {
            if (word.startsWith("$"))
            {
                if (defines.containsKey(word))
                {
                    word = defines.get(word);
                }
                else
                {
                    System.err.println("Unable to find define for '" + word + "'");
                }
            }

            reconstruct += word;
        }

        return reconstruct;
    }
}
