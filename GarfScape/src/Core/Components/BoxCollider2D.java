package Core.Components;

import Core.Component;
import Core.Entity;
import Core.Math.Transform;
import Core.Math.Transform2D;
import Core.RenderContext;
import java.util.ArrayList;



public class BoxCollider2D extends Component
{
    
    private static ArrayList<BoxCollider2D> colliders = new ArrayList<>();
    
    public BoxCollider2D()
    {
        colliders.add(this);
    }
    
    public static ArrayList<BoxCollider2D> getColliders()
    {
        return colliders;
    }
    
    Transform t = new Transform2D();
    @Override
    public void update(RenderContext ctx, Entity e)
    {
        t = e.getWorldTransform();
    }
    
    public Transform getTransform()
    {
        return t;
    }
    
    
}
