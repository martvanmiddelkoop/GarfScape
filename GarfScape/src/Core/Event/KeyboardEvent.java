package Core.Event;



public class KeyboardEvent extends Event
{

    private final int code;
    
    
    public KeyboardEvent(int code)
    {
        this.code = code;
    }
    
    public int getCode()
    {
        return code;
    }
}
