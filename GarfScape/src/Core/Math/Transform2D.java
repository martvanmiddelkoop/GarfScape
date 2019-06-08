package Core.Math;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform2D
{

    private Vector3f position = new Vector3f(0, 0, 0);
    private float rotation = 0;
    private Vector3f scale = new Vector3f(1, 1, 1);

    public Matrix4f getMVP(float windowWidth, float windowHeight)
    {//TODO: DIT OPTIMIZEN

        Matrix4f model = new Matrix4f()
                .translate(position)
                .rotateZ((float)Math.toRadians(rotation))
                .scale(scale);

        Matrix4f proj = new Matrix4f().ortho(0, windowWidth, 0, windowHeight, -1.0f, 1000);

        return new Matrix4f(proj).mulAffine(model);

    }

    public Vector3f getPosition()
    {
        return position;
    }

    public float getRotation()
    {
        return rotation;
    }

    public Vector3f getScale()
    {
        return scale;
    }
}
