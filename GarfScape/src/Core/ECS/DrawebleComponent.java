package Core.ECS;

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
    public void update(RenderContext ctx, Entity entity)
    {
        draweble.draw(entity.getWorldTransform(), ctx.getWindow().getWidth(), ctx.getWindow().getHeight());
    }
}
