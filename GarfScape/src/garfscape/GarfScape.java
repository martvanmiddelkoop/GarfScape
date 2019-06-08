/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garfscape;

import Core.Data.*;
import Core.Draweble.Sprite2D;
import Core.*;
import org.joml.Vector4f;

public class GarfScape
{

    public static void main(String[] args)
    {
        RenderContext ctx = new RenderContext(new Window("GarfScape", 1280, 720));
        
        TextureAtlas atlas = new TextureAtlas("Img/TestAtlas.png", 2);
        Shader spriteShader = new Shader("Shader/Sprite.shader");
        
        Sprite2D sprite = new Sprite2D(new Texture(atlas, 0, 0, 1, 1), spriteShader);
        sprite.getTransform().getPosition().x += 100;
        sprite.getTransform().getPosition().y += 100;
        
        ctx.add(sprite, LayerIndex.Scene);
        
        
        while(ctx.update());
    }

}
