package Core;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import org.joml.Vector4f;

import java.nio.*;

public class Window
{

    private long windowPointer;
    private Vector4f clearColor = new Vector4f(0, 0, 0, 1);
    private int width, height;

    public Window(String name, int width, int height)
    {
        this.width = width;
        this.height = height;

        if (!glfwInit())
        {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        else
        {
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

            windowPointer = glfwCreateWindow(width, height, "BlueFeet", NULL, NULL);
            if (windowPointer == NULL)
            {
                throw new RuntimeException("Failed to create GLFW window");
            }
            else
            {
                try (MemoryStack stack = stackPush())
                {
                    IntBuffer pWidth = stack.mallocInt(1);
                    IntBuffer pHeight = stack.mallocInt(1);

                    glfwGetWindowSize(windowPointer, pWidth, pHeight);

                    GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

                    glfwSetWindowPos(
                            windowPointer,
                            (vidmode.width() - pWidth.get(0)) / 2,
                            (vidmode.height() - pHeight.get(0)) / 2
                    );
                }

                setTitle(name);

                glfwMakeContextCurrent(windowPointer);
                glfwSwapInterval(1);

                glfwShowWindow(windowPointer);
                GL.createCapabilities();

                glEnable(GL_DEPTH_TEST);

                glfwSetWindowSizeCallback(windowPointer, (window, w, h) ->
                {
                    glViewport(0, 0, w, h);
                    this.width = w;
                    this.height = h;
                });
            }
        }

    }

    public void setTitle(String title)
    {
        glfwSetWindowTitle(windowPointer, title);
    }

    public void destroy()
    {
        glfwDestroyWindow(windowPointer);
    }

    public void prepare()
    {
        glfwMakeContextCurrent(windowPointer);

        glfwPollEvents();

        glClearColor(clearColor.x, clearColor.y, clearColor.z, clearColor.w);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void update()
    {
        glfwSwapBuffers(windowPointer);
    }

    public boolean isActive()
    {
        return !glfwWindowShouldClose(windowPointer);
    }

    public void setClearColor(Vector4f color)
    {
        this.clearColor = color;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
