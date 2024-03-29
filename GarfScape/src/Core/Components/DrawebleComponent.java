package Core.Components;

import Core.Component;
import Core.Entity;
import Core.Draweble.Draweble;
import Core.RenderContext;



public class DrawebleComponent extends Component
{
    private Draweble draweble;
    
    public DrawebleComponent(Draweble draweble)
    {
        this.draweble = draweble;
        
    }
    
    @Override
    public void draw(RenderContext ctx, Entity entity)
    {
        draweble.draw(entity.getWorldTransform(), ctx.getCamera().getWorldTransform(), ctx.getWindow().getWidth(), ctx.getWindow().getHeight());
    }
}
