package Core;

import Core.Draweble.Draweble;
import Core.ECS.Entity;
import Core.Math.Transform;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class RenderContext
{

    Window window;
    ArrayList<Entity>[] layers = (ArrayList<Entity>[]) new ArrayList[LayerIndex.values().length];
    //Transform camera = new Transform();
    
    public RenderContext(Window window)
    {
        this.window = window;
        
        for (int layer = 0; layer < layers.length; layer++)
        {
            layers[layer] = new ArrayList<>();
        }
    }

    public boolean update()
    {
        window.prepare();
        
        if(!window.isActive())
        {
            return false;
        }
        
        for (int layer = 0; layer < layers.length; layer++)
        {
            GL11.glClear(GL_DEPTH_BUFFER_BIT);

            for (int i = 0; i < layers[layer].size(); i++)
            {
                layers[layer].get(i).update(this);
            }
        }
        
        window.update();
        
        return true;
    }

    public Window getWindow()
    {
        return window;
    }
    
    public void add(Entity entity, LayerIndex index)
    {
        layers[index.ordinal()].add(entity);
    }

    public void remove(Entity entity)
    {
        for (int layer = 0; layer < layers.length; layer++)
        {
            if (layers[layer].remove(entity))
            {
                break;
            }
        }
    }
}
