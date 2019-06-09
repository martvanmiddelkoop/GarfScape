/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garfscape;

import Core.Data.*;
import Core.Draweble.*;
import Core.*;
import Core.ECS.*;
import Core.Math.Transform2D;

public class GarfScape
{

    public static void main(String[] args)
    {
        RenderContext ctx = new RenderContext(new Window("GarfScape", 1280, 720));

        TextureAtlas atlas = new TextureAtlas("Img/TestAtlas.png", 2);
        Shader spriteShader = new Shader("Shader/Sprite.shader");
        Sprite sprite = new Sprite(new Texture(atlas, 0, 0, 1, 1), spriteShader);

        Transform2D t = new Transform2D();
        t.getScale().x = 100;
        t.getScale().y = 100;

        Entity parent = new Entity(t);
        parent.addComponent(new TestComponent());

        Entity popke = parent.createChild(new Transform2D());

        popke.addComponent(new DrawebleComponent(sprite));

        ctx.add(parent, LayerIndex.Scene);

        ctx.start();
        while (ctx.update());
    }

}
