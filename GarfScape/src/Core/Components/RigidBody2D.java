package Core.Components;

import Core.Component;
import Core.Entity;
import Core.Math.Transform;
import Core.RenderContext;
import java.util.ArrayList;
import org.joml.Vector2f;

public class RigidBody2D extends Component
{

    Vector2f velocity = new Vector2f();
    float mass = 0.001f;

    public void update(RenderContext ctx, Entity e)
    {
        velocity.y -= 100.0f * ctx.getDeltaTime();

        ArrayList<BoxCollider2D> colliders = BoxCollider2D.getColliders();

        for (int i = 0; i < colliders.size(); i++)
        {
            Transform other = colliders.get(i).getTransform();

            if (e.getTransform().getPosition().y - e.getTransform().getScale().y / 2 < other.getPosition().y + other.getScale().y / 2
                    && e.getTransform().getPosition().y + e.getTransform().getScale().y / 2 > other.getPosition().y - other.getScale().y / 2)
            {// 
                float yDif = e.getTransform().getPosition().y - other.getPosition().y - e.getTransform().getScale().y / 2 - other.getScale().y / 2;
                float dir = 0;
                if(yDif > 0)
                {
                    dir = -1;
                }
                else
                {
                    dir = 1;
                }
                velocity.y = 1 * dir;
                e.getTransform().getPosition().y += 0.1 * dir;
            }
            
            if (e.getTransform().getPosition().x - e.getTransform().getScale().x / 2 < other.getPosition().x + other.getScale().x / 2
                    && e.getTransform().getPosition().x + e.getTransform().getScale().x / 2 > other.getPosition().x - other.getScale().x / 2)
            {

            }

            e.getTransform().getPosition().y += velocity.y * ctx.getDeltaTime();
            e.getTransform().getPosition().x += velocity.x * ctx.getDeltaTime();
        }
    }
}
