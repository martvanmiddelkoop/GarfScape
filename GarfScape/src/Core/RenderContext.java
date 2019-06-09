package Core;

import Core.Draweble.Draweble;
import Core.Event.Input;
import Core.Math.Transform;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class RenderContext
{

    Window window;
    ArrayList<Entity>[] layers = (ArrayList<Entity>[]) new ArrayList[LayerIndex.values().length];
    Input input;
    //Transform camera = new Transform();

    public RenderContext(Window window)
    {
        this.window = window;
        input = new Input(window.getPointer());

        for (int layer = 0; layer < layers.length; layer++)
        {
            layers[layer] = new ArrayList<>();
        }
    }

    private Instant start;
    double lastDeltaTime = 0;
    
    public Input getInput()
    {
        return input;
    }

    public void start()
    {
        start = Instant.now();
    }

    public void stop()
    {
        start = null;
    }

    public boolean update()
    {
        Instant finish = Instant.now();
        lastDeltaTime = (double) (Duration.between(start, finish).toNanos()) / 1000000000.0;
        start = Instant.now();

        window.prepare();

        if (!window.isActive())
        {
            return false;
        }

        for (int layer = layers.length - 1; layer >= 0; layer--)
        {

            for (int i = 0; i < layers[layer].size(); i++)
            {
                layers[layer].get(i).update(this);
            }
            
            input.pushLayer();
        }

        for (int layer = 0; layer < layers.length; layer++)
        {
            GL11.glClear(GL_DEPTH_BUFFER_BIT);

            for (int i = 0; i < layers[layer].size(); i++)
            {
                layers[layer].get(i).draw(this);
            }
        }

        window.update();
        input.reset();

        return true;
    }

    public Window getWindow()
    {
        return window;
    }

    public double getDeltaTime()
    {
        return lastDeltaTime;
    }

    public void add(Entity entity, LayerIndex index)
    {
        if (!entity.isChild())
        {
            layers[index.ordinal()].add(entity);
        }
        else
        {
            System.err.println("Cannot add child entity to the render context");
        }
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
