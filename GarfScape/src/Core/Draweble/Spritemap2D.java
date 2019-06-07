package Core.Draweble;

import Core.Data.Sprite;
import Core.Window;

public class Spritemap2D extends Draweble2D
{
    
    Sprite[] sprites;
    int width;
    
    public Spritemap2D(int width, int height)
    {
        this.width = width;
        sprites = new Sprite[width * height];
    }
    
    public void loadSpritesFromFile(String filename)
    {
        
    }

    public void setSprite(int x, int y, Sprite sprite)
    {
        sprites[width * y + x] = sprite;
    }
    
    
    //je moet dit runnen voordat hij kan drawen
    public void update()
    {//doet dit aan een batch
        
    }
    
    public Sprite getSprite(int x, int y)
    {
        return sprites[y * width + x];
    }
    
    @Override
    void draw(Window window)
    {
    }

}
