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

        
        Text text = new Text();
        text.setText("COCK AND BALL\nTORTURE");
        
        
        Entity test = new Entity(new Transform2D());
        test.getTransform().getScale().x = 100;
        test.getTransform().getScale().y = 100;

        test.addComponent(new DrawebleComponent(text));
        test.addComponent(new TestComponent());

        ctx.add(test, LayerIndex.Scene);

        ctx.start();
        while (ctx.update());
    }

}
