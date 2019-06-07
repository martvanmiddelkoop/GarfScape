package Core.Data;

import static org.lwjgl.opengl.GL13.*;

public class Texture
{

    TextureAtlas atlas;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Texture(TextureAtlas atlas, int x, int y, int width, int height)
    {
        this.atlas = atlas;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void bind(Shader shader)
    {
        glBindTexture(GL_TEXTURE_2D, atlas.getID());

        shader.bind();
        shader.setUniformI("_IMAGE", atlas.getIndex());
        shader.setUniformVec2("_IMAGE_OFFSET", x, y);
        shader.setUniformI("_IMAGE_ROWS", atlas.getRows());
        shader.setUniformI("_IMAGE_WIDTH", width);
        shader.setUniformI("_IMAGE_HEIGHT", height);
    }

    public void unBind()
    {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

}
