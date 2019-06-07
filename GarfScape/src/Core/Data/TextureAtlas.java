package Core.Data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL12;
import static org.lwjgl.opengl.GL13.*;

public class TextureAtlas
{

    private int imageID;
    private int rows;
    private int index;

    public TextureAtlas(String imageName, int rows)
    {
        loadImage(imageName);
        this.rows = rows;

    }

    public int getID()
    {
        return imageID;
    }

    public int getRows()
    {
        return rows;
    }

    public int getIndex()
    {
        return index;
    }

    private static int count = 0;

    public void loadImage(String imageName)
    {

        try
        {

            BufferedImage image = ImageIO.read(FileLoader.loadFile(imageName));
            ByteBuffer buffer = loadImage(image);

            glActiveTexture(GL_TEXTURE0 + count);

            int textureID = GL11.glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            glBindTexture(GL_TEXTURE_2D, 0);

            index = count;
            count++;
            this.imageID = textureID;
        }
        catch (IOException ex)
        {
            System.err.println("Failed to load image '" + imageName + "'");
        }

    }

    private static ByteBuffer loadImage(BufferedImage image)
    {

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4); //4 for RGBA, 3 for RGB

        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }

        buffer.flip();

        return buffer;
    }
}
