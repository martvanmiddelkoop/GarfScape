package Core.Math;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform2D extends Transform
{

    public Matrix4f getMVP(float windowWidth, float windowHeight)
    {//TODO: DIT OPTIMIZEN

        Matrix4f model = new Matrix4f()
                .translate(position)
                .rotateZ((float)Math.toRadians(rotation.z))
                .scale(scale);

        Matrix4f proj = new Matrix4f().ortho(0, windowWidth, 0, windowHeight, -1.0f, 1000);

        return new Matrix4f(proj).mulAffine(model);

    }
}
