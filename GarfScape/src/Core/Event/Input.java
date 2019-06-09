package Core.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.glfw.GLFW;



public class Input 
{
    Map<Integer, KeyboardEvent> keyEvents = new HashMap<>();
    ArrayList<Event> discardedEvents = new ArrayList<>();
    ArrayList<Event> currentDiscardedEvents = new ArrayList<>();
    
    MouseMoveEvent mouseMoveEvent = null;
    double lastX = 0, lastY = 0;
    
    public Input(long glfwWindow)
    {
        GLFW.glfwSetKeyCallback(glfwWindow, (window, key, scancode, action, mods) ->
        {
            if(action == GLFW.GLFW_PRESS)
            {
                keyEvents.put(key, new KeyboardEvent(key));
            }
            else if (action == GLFW.GLFW_RELEASE)
            {
                keyEvents.remove(key);
            }
        });
        
        GLFW.glfwSetCursorPosCallback(glfwWindow, (window, xpos, ypos) ->
        {
            int[] ww = new int[1];
            int[] wh = new int[1];
            GLFW.glfwGetWindowSize(window, ww, wh);
            double wHeight = wh[0];
            ypos = wHeight - ypos;
            mouseMoveEvent = new MouseMoveEvent((float)xpos, (float)ypos, (float)lastX - (float)xpos, (float)lastY - (float)ypos);
            
            lastX = xpos;
            lastY = ypos;
        });
    }
    
    //clears the discarded keys
    //call this at the end of the frame
    public void reset()
    {
        discardedEvents.clear();
    }
    
    //call this at the end of the layer update
    public void pushLayer()
    {
        discardedEvents.addAll(currentDiscardedEvents);
        currentDiscardedEvents.clear();
    }
    
    public void discardKeyEvent(KeyboardEvent e)
    {
        currentDiscardedEvents.add(e);
    }
    
    public KeyboardEvent getKey(int code)
    {
        
        KeyboardEvent e = keyEvents.get(code);
        
        if(e == null)
        {
            return null;
        }
        else if(discardedEvents.contains(e))
        {
            return null;
        }
        else
        {
            return e;
        }
    }
    
    public void discardMouseMove()
    {
        currentDiscardedEvents.add(mouseMoveEvent);
    }
    
    public MouseMoveEvent getMouseMove()
    {
        if(discardedEvents.contains(mouseMoveEvent))
        {
            return null;
        }
        else
        {
            return mouseMoveEvent;
        }
    }
    
    
}
