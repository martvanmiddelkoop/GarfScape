package Core.Math;

import org.joml.Vector3f;

public class Transform
{

    protected Vector3f position = new Vector3f(0, 0, 0);
    protected Vector3f rotation = new Vector3f();
    protected Vector3f scale = new Vector3f(1, 1, 1);

    public Vector3f getPosition()
    {
        return position;
    }

    public Vector3f getRotation()
    {
        return rotation;
    }

    public Vector3f getScale()
    {
        return scale;
    }
}
