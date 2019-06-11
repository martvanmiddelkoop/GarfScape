package Core.Draweble;

import Core.Data.Shader;
import Core.Math.Transform;

public abstract class Draweble
{

    protected Shader shader;

    public void setShader(Shader shader)
    {
        this.shader = shader;
    }

    public abstract void draw(Transform transform, Transform camera, float windowW, float windowH);
}
