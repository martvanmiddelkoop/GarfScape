package Core.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Mesh
{

    private int VAO = GL30.glGenVertexArrays();
    private ArrayList<Integer> vbos = new ArrayList<Integer>();
    private int EBO;

    private int vertexCount;

    public Mesh(float[] positions, float[] textureCoords, float[] normals, int[] indicies)
    {
        storeData(0, 3, positions);
        storeData(1, 2, textureCoords);
        storeData(2, 3, normals);
        storeIdicies(indicies);

    }

    public Mesh(float[] positions, int[] indicies)
    {
        storeData(0, 3, positions);
        storeIdicies(indicies);
    }

    public Mesh(String fileName)
    {
        FileReader fr = null;

        try
        {
            fr = new FileReader(FileLoader.loadFile(fileName));

            BufferedReader reader = new BufferedReader(fr);
            String line;

            List<Vector3f> verticies = new ArrayList();
            List<Vector2f> textures = new ArrayList();
            List<Vector3f> normals = new ArrayList();
            List<Integer> indicies = new ArrayList();

            float[] verticiesArr = null;
            float[] texturesArr = null;
            float[] normalsArr = null;
            int[] indiciesArr = null;

            try
            {
                while (true)
                {
                    line = reader.readLine();
                    String[] currentLine = line.split(" ");

                    if (line.startsWith("v "))
                    {
                        Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                        verticies.add(vertex);
                    }
                    else if (line.startsWith("vt "))
                    {
                        Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
                        textures.add(texture);
                    }
                    else if (line.startsWith("vn"))
                    {
                        Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                        normals.add(normal);
                    }
                    else if (line.startsWith("f "))
                    {
                        texturesArr = new float[verticies.size() * 2];
                        normalsArr = new float[verticies.size() * 3];
                        break;
                    }
                }

                while (line != null)
                {
                    if (!line.startsWith("f "))
                    {
                        line = reader.readLine();
                        continue;
                    }

                    String[] currentLine = line.split(" ");
                    String[] vertex1 = currentLine[1].split("/");
                    String[] vertex2 = currentLine[2].split("/");
                    String[] vertex3 = currentLine[3].split("/");

                    processVertex(vertex1, indicies, textures, normals, texturesArr, normalsArr);
                    processVertex(vertex2, indicies, textures, normals, texturesArr, normalsArr);
                    processVertex(vertex3, indicies, textures, normals, texturesArr, normalsArr);

                    line = reader.readLine();
                }
                reader.close();
            }
            catch (IOException ex)
            {
                System.err.println(ex.toString());
            }

            verticiesArr = new float[verticies.size() * 3];
            indiciesArr = new int[indicies.size()];

            int vertexPointer = 0;
            for (Vector3f vertex : verticies)
            {
                verticiesArr[vertexPointer++] = vertex.x;
                verticiesArr[vertexPointer++] = vertex.y;
                verticiesArr[vertexPointer++] = vertex.z;
            }

            for (int i = 0; i < indicies.size(); i++)
            {
                indiciesArr[i] = indicies.get(i);
            }

            storeIdicies(indiciesArr);
            storeData(0, 3, verticiesArr);
            storeData(1, 2, texturesArr);

            System.out.println("Loaded mesh '" + fileName + "' (" + indiciesArr.length + " verticies)");
        }
        catch (Exception ex)
        {
            System.err.println(ex.toString());
        }

    }

    private static void processVertex(String[] vertexData, List<Integer> indicies, List<Vector2f> textures, List<Vector3f> normals, float[] textureArray, float[] normalArray)
    {
        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        indicies.add(currentVertexPointer);
        Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
        textureArray[currentVertexPointer * 2] = currentTex.x;
        textureArray[currentVertexPointer * 2 + 1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
        normalArray[currentVertexPointer * 3 + 0] = currentNorm.x;
        normalArray[currentVertexPointer * 3 + 1] = currentNorm.y;
        normalArray[currentVertexPointer * 3 + 2] = currentNorm.z;

    }

    public void storeData(int index, int dimentions, float[] data)
    {
        bind();

        int VBO = GL15.glGenBuffers();
        //bind
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL20.glEnableVertexAttribArray(index);

        //store
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, dimentions, GL11.GL_FLOAT, false, 0, 0);

        //unbind
        GL20.glDisableVertexAttribArray(index);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        vbos.add(VBO);

        unBind();
    }

    public void storeIdicies(int[] indicies)
    {
        bind();

        vertexCount = indicies.length;
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(vertexCount);
        indicesBuffer.put(indicies);
        indicesBuffer.flip();

        EBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, EBO);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);

        unBind();
    }

    protected void finalize() throws Throwable
    {
        destruct();
    }

    private void destruct()
    {
        if (VAO != 0)
        {
            GL30.glDeleteVertexArrays(VAO);
        }

        if (EBO != 0)
        {
            GL15.glDeleteBuffers(EBO);
        }

        for (int i = 0; i < vbos.size(); i++)
        {
            GL30.glDeleteBuffers(vbos.get(i));
        }
    }

    private void bind()
    {
        GL30.glBindVertexArray(VAO);
    }

    private void unBind()
    {
        GL30.glBindVertexArray(0);
    }

    public void draw()
    {
        bind();

        for (int i = 0; i < vbos.size(); i++)
        {
            GL20.glEnableVertexAttribArray(i);
        }

        GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);

        for (int i = 0; i < vbos.size(); i++)
        {
            GL20.glDisableVertexAttribArray(i);
        }

        unBind();
    }
}