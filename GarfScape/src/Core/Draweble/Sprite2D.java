package Core.Draweble;

import Core.Data.Mesh;
import Core.Data.Shader;
import Core.Data.Texture;
import Core.Math.Transform;
import Core.Window;

public class Sprite2D extends Draweble2D
{

    private Texture texture;

    public Sprite2D(Texture texture, Shader shader)
    {
        this.texture = texture;
        this.shader = shader;

        setWidth(100);
        setHeight(100);
    }

    @Override
    public void draw(float windowW, float windowH)
    {
        //bind
        shader.bind();
        getTexture().bind(shader);

        //set data
        shader.setUniformMat4("_MVP", transform.getMVP(windowW, windowH));

        //draw
        pixelMesh.draw();

        //unbind
        getTexture().unBind();
        shader.unBind();

    }

    public Texture getTexture()
    {
        return texture;
    }

    public void setTexture(Texture texture)
    {
        this.texture = texture;
    }

    /**
     * sets the x component of the scale
     */
    public void setWidth(float width)
    {
        this.transform.getScale().x = width;
    }

    /**
     * sets the y component of the scale
     */
    public void setHeight(float height)
    {
        this.transform.getScale().y = height;
    }

    /**
     * gets the x component of the scale
     */
    public float getWidth()
    {
        return transform.getScale().x;
    }

    /**
     * gets the y component of the scale
     */
    public float getHeight()
    {
        return transform.getScale().y;
    }

    private static final Mesh pixelMesh = new Mesh(
            new float[]
            {

                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f
            },
            new float[]
            {
                0, 0,
                0, 1,
                1, 1,
                1, 0
            },
            new int[]
            {
                0, 1, 2,
                0, 2, 3,
            });

}
