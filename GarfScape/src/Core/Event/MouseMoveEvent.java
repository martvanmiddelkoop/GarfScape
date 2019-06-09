package Core.Event;



public class MouseMoveEvent extends Event
{
    private float mouseX;
    private float mouseY;
    private float deltaX;
    private float deltaY;

    public MouseMoveEvent(float mouseX, float mouseY, float deltaX, float deltaY)
    {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public float getMouseX()
    {
        return mouseX;
    }

    public float getMouseY()
    {
        return mouseY;
    }

    public float getDeltaX()
    {
        return deltaX;
    }

    public float getDeltaY()
    {
        return deltaY;
    }
    
    
}
