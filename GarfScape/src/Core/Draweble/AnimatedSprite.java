package Core.Draweble;

import Core.Data.Shader;
import Core.Data.Texture;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite
{//TODO IMPLEMENT THIS

    int index = 0;
    ArrayList<Texture> textureArray;

    public AnimatedSprite(Shader shader, ArrayList<Texture> textureArray)
    {
        super(textureArray.get(0));
        this.textureArray = textureArray;
    }

    @Override
    public void setTexture(Texture texture)
    {
        this.texture = textureArray.get(textureArray.indexOf(texture));
    }

    public void step()
    {
        if (index >= textureArray.size() - 1)
        {
            this.texture = textureArray.get(index++);
        }
        else
        {
            index = 0;
            this.texture = textureArray.get(index);
        }
    }

}
