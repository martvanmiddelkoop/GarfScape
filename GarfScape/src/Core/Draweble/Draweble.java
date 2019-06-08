package Core.Draweble;

import Core.Data.Mesh;
import Core.Data.Shader;
import Core.Window;

public abstract class Draweble
{

    protected Shader shader;

    public void setShader(Shader shader)
    {
        this.shader = shader;
    }

    public abstract void draw(Window window);
}
