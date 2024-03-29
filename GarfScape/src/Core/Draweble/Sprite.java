package Core.Draweble;

import Core.Data.Mesh;
import Core.Data.Shader;
import Core.Data.Texture;
import Core.Math.Transform;
import Core.Window;
public class Sprite extends Draweble
{

    protected Texture texture;
    private static Shader spriteShader = new Shader("Shader/Sprite.shader");

    public Sprite(Texture texture)
    {
        this.texture = texture;
        this.shader = spriteShader;
    }

    @Override
    public void draw(Transform transform, Transform camera, float windowW, float windowH)
    {
        //bind
        shader.bind();
        getTexture().bind(shader);

        //set data
        shader.setUniformMat4("_MVP", transform.getMVP(camera, windowW, windowH));

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
