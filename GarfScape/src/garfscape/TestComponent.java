package garfscape;

import Core.Component;
import Core.Entity;
import Core.RenderContext;



public class TestComponent extends Component
{
    
    private float tElapsed = 0;
    private float radius = 50;
    private float speed = 10;
    
    @Override
    public void update(RenderContext ctx, Entity e)
    {
        tElapsed += ctx.getDeltaTime();
        
        e.getTransform().getPosition().x = ctx.getWindow().getWidth() / 2 + radius / 2 + (float)Math.cos(tElapsed * speed) * radius;
        e.getTransform().getPosition().y = ctx.getWindow().getHeight() / 2 + radius / 2 + (float)Math.sin(tElapsed * speed) * radius;
        e.getTransform().getRotation().z += ctx.getDeltaTime() * 360;
    }
}
