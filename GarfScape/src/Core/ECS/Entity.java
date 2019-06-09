package Core.ECS;

import Core.Math.Transform;
import Core.RenderContext;
import java.util.ArrayList;

public final class Entity
{

    Transform transform;
    Entity parent = null;
    ArrayList<Component> components = new ArrayList<>();
    ArrayList<Entity> children = new ArrayList<>();

    /**
     * @param transform the transform that will be passed to the entity >>>THIS IS PASSED BY VALUE<<<
     */
    public Entity(Transform transform)
    {
        this.transform = transform.clone();
    }

    private Entity(Entity parent, Transform transform)
    {
        this.parent = parent;
        this.transform = transform.clone();
    }

    public Transform getWorldTransform()
    {

        if (parent == null)
        {
            return transform;
        }

        return transform.sum(parent.getTransform());
    }
    
    public boolean isChild()
    {
        return parent != null;
    }

    public Transform getTransform()
    {
        return transform;
    }

    public void update(RenderContext ctx)
    {
        for (int i = 0; i < children.size(); i++)
        {
            children.get(i).update(ctx);
        }

        for (int i = 0; i < components.size(); i++)
        {
            components.get(i).update(ctx, this);
        }
    }

    public Entity createChild(Transform transform)
    {
        Entity child = new Entity(this, transform);
        children.add(child);
        return child;
    }

    public void addComponent(Component component)
    {
        components.add(component);
    }
}
