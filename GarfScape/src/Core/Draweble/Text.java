package Core.Draweble;

import Core.Data.Mesh;
import Core.Data.Shader;
import Core.Data.Texture;
import Core.Data.TextureAtlas;
import Core.Math.Transform;
import org.joml.Vector2f;

public class Text extends Draweble
{

    private String text = "";

    private static TextureAtlas fontAtlas = new TextureAtlas("Img/_Font.png", 10);
    private static Shader fontShader = new Shader("Shader/Font.shader");
    private static float spaceMultiplier = 0.5f;
    private static Texture[] font = new Texture[128];

    static
    {
        font[0] = new Texture(fontAtlas, 7, 9, 1, 1);
        font[(int) 'A'] = new Texture(fontAtlas, 3, 3, 1, 1);
        font[(int) 'B'] = new Texture(fontAtlas, 4, 3, 1, 1);
        font[(int) 'C'] = new Texture(fontAtlas, 5, 3, 1, 1);
        font[(int) 'D'] = new Texture(fontAtlas, 6, 3, 1, 1);
        font[(int) 'E'] = new Texture(fontAtlas, 7, 3, 1, 1);
        font[(int) 'F'] = new Texture(fontAtlas, 8, 3, 1, 1);
        font[(int) 'G'] = new Texture(fontAtlas, 9, 3, 1, 1);
        font[(int) 'H'] = new Texture(fontAtlas, 0, 4, 1, 1);
        font[(int) 'I'] = new Texture(fontAtlas, 1, 4, 1, 1);
        font[(int) 'J'] = new Texture(fontAtlas, 2, 4, 1, 1);
        font[(int) 'K'] = new Texture(fontAtlas, 3, 4, 1, 1);
        font[(int) 'L'] = new Texture(fontAtlas, 4, 4, 1, 1);
        font[(int) 'M'] = new Texture(fontAtlas, 5, 4, 1, 1);
        font[(int) 'N'] = new Texture(fontAtlas, 6, 4, 1, 1);
        font[(int) 'O'] = new Texture(fontAtlas, 7, 4, 1, 1);
        font[(int) 'P'] = new Texture(fontAtlas, 8, 4, 1, 1);
        font[(int) 'Q'] = new Texture(fontAtlas, 9, 4, 1, 1);
        font[(int) 'R'] = new Texture(fontAtlas, 0, 5, 1, 1);
        font[(int) 'S'] = new Texture(fontAtlas, 1, 5, 1, 1);
        font[(int) 'T'] = new Texture(fontAtlas, 2, 5, 1, 1);
        font[(int) 'U'] = new Texture(fontAtlas, 3, 5, 1, 1);
        font[(int) 'V'] = new Texture(fontAtlas, 4, 5, 1, 1);
        font[(int) 'W'] = new Texture(fontAtlas, 5, 5, 1, 1);
        font[(int) 'X'] = new Texture(fontAtlas, 6, 5, 1, 1);
        font[(int) 'Y'] = new Texture(fontAtlas, 7, 5, 1, 1);
        font[(int) 'Z'] = new Texture(fontAtlas, 8, 5, 1, 1);
        
        font[(int) '1'] = new Texture(fontAtlas, 7, 1, 1, 1);
        font[(int) '2'] = new Texture(fontAtlas, 8, 1, 1, 1);
        font[(int) '3'] = new Texture(fontAtlas, 9, 1, 1, 1);
        font[(int) '4'] = new Texture(fontAtlas, 0, 2, 1, 1);
        font[(int) '5'] = new Texture(fontAtlas, 1, 2, 1, 1);
        font[(int) '6'] = new Texture(fontAtlas, 2, 2, 1, 1);
        font[(int) '7'] = new Texture(fontAtlas, 3, 2, 1, 1);
        font[(int) '8'] = new Texture(fontAtlas, 4, 2, 1, 1);
        font[(int) '9'] = new Texture(fontAtlas, 5, 2, 1, 1);
    }

    public Text()
    {
        this.shader = fontShader;
    }
    
    public Text(String text)
    {
        this.shader = fontShader;
        this.text = text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return this.text;
    }

    @Override
    public void draw(Transform transform, Transform camera, float windowW, float windowH)
    {
        shader.bind();
        Transform t = transform.clone();

        for (int i = 0; i < text.length(); i++)
        {
            if (text.charAt(i) != '\n')
            {
                try
                {
                    font[(int) text.charAt(i)].bind(shader);
                }
                catch (NullPointerException e)
                {
                    font[0].bind(shader);
                }

                shader.setUniformMat4("_MVP", t.getMVP(camera, windowW, windowH));
                pixelMesh.draw();
                t.getPosition().x += t.getScale().x * spaceMultiplier;
            }
            else
            {
                t.getPosition().x = transform.getPosition().x;
                t.getPosition().y -= t.getScale().y * spaceMultiplier;
            }
        }

        shader.unBind();
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
