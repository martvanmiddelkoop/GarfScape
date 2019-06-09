/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garfscape;

import Core.Components.DrawebleComponent;
import Core.Entity;
import Core.Data.*;
import Core.Draweble.*;
import Core.*;
import Core.Math.Transform2D;
import org.joml.Vector4f;

public class GarfScape
{

    public static void main(String[] args)
    {
        RenderContext ctx = new RenderContext(new Window("GarfScape", 1280, 720));
        ctx.getWindow().setClearColor(new Vector4f(0.1f, 0.1f, 0.2f, 1.0f));
        
        Transform2D t = new Transform2D();
        t.getScale().x = 100;
        t.getScale().y = 100;
        t.getPosition().y = 360;
        
        
        
        
        Entity e1 = new Entity(t);
        Entity e2 = new Entity(t);
        e1.getTransform().getPosition().x = 100;
        e2.getTransform().getPosition().x = 300;
        
        e1.addComponent(new DrawebleComponent(new Text("1")));
        e2.addComponent(new DrawebleComponent(new Text("2")));
        
        e1.addComponent(new TestComponent());
        e2.addComponent(new TestComponent());
        
        
        
       
        ctx.add(e1, LayerIndex.Scene);
        ctx.add(e2, LayerIndex.UI);
        
        ctx.start();
        while (ctx.update());
    }

}
