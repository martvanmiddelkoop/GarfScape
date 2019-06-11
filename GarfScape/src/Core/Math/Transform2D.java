package Core.Math;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform2D extends Transform
{

    @Override
    public Matrix4f getMVP(Transform camera, float windowWidth, float windowHeight)
    {//TODO: DIT OPTIMIZEN

        Matrix4f model = new Matrix4f()
                .translate(position)
                .rotateZ((float) Math.toRadians(rotation.z))
                .scale(scale);

        Matrix4f proj = new Matrix4f().ortho(-(windowWidth / 2), windowWidth / 2, -(windowHeight / 2), windowHeight / 2, -1.0f, 1000);

        Matrix4f cameraTransformation = new Matrix4f();
        cameraTransformation.identity()
                .rotateX((float) Math.toRadians(-camera.getRotation().x))
                .rotateY((float) Math.toRadians(-camera.getRotation().y))
                .rotateZ((float) Math.toRadians(-camera.getRotation().z))
                .translate(-(camera.getPosition().x), -(camera.getPosition().y), -camera.getPosition().z);

        return new Matrix4f(proj).mulAffine(cameraTransformation).mulAffine(model);

    }

    public Transform2D()
    {
    }

    //public Transform2D(Camera)
    public Transform2D(Transform2D other)
    {
        position = new Vector3f(other.position);
        rotation = new Vector3f(other.rotation);
        scale = new Vector3f(other.scale);
    }

    @Override
    public Transform clone()
    {
        return new Transform2D(this);
    }
}
