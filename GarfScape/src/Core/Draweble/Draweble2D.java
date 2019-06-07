package Core.Draweble;

import org.joml.Vector2f;



public abstract class Draweble2D extends Draweble
{
    protected Vector2f position = new Vector2f();
    private float rotation = 0;
    
    public Vector2f getPosition()
    {
        return position;
    }

    public float getRotation()
    {
        return rotation;
    }

    public void setRotation(float rotation)
    {
        this.rotation = rotation;
    }
}
