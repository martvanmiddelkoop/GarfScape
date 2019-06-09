package Core.Math;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class Transform
{

    protected Vector3f position = new Vector3f(0, 0, 0);
    protected Vector3f rotation = new Vector3f();
    protected Vector3f scale = new Vector3f(1, 1, 1);

    public abstract Matrix4f getMVP(float windowWidth, float windowHeight);
    public abstract Transform clone();
    
    public Transform sum(Transform right)
    {
        Transform t = clone();
        t.position.add(right.position);
        t.rotation.add(right.rotation);
        t.scale.add(right.scale);
        return t;
    }
    

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
