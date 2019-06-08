package Core.Draweble;

import Core.Math.Transform2D;



public abstract class Draweble2D extends Draweble
{
    protected Transform2D transform = new Transform2D();
    
    public Transform2D getTransform()
    {
        return transform;
    }
}
