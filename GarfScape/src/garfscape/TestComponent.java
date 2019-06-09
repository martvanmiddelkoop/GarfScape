package garfscape;

import Core.Component;
import Core.Entity;
import Core.Event.KeyboardEvent;
import Core.Event.MouseMoveEvent;
import Core.RenderContext;
import org.lwjgl.glfw.GLFW;

public class TestComponent extends Component
{

    private float tElapsed = 0;
    private float radius = 50;
    private float speed = 10;

    @Override
    public void update(RenderContext ctx, Entity e)
    {
        KeyboardEvent ev = ctx.getInput().getKey(GLFW.GLFW_KEY_SPACE);
        if (ev != null)
        {
            tElapsed += ctx.getDeltaTime();
            e.getTransform().getRotation().z += ctx.getDeltaTime() * 360;
            ctx.getInput().discardKeyEvent(ev);
            
            MouseMoveEvent me = ctx.getInput().getMouseMove();
            
            if(me != null)
            {
                e.getTransform().getPosition().x = me.getMouseX();
                e.getTransform().getPosition().y = me.getMouseY();
                ctx.getInput().discardMouseMove();
            }
        }
    }
}
