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
import Core.Components.BoxCollider2D;
import Core.Components.RigidBody2D;
import Core.Math.Transform2D;
import org.joml.Vector4f;

public class GarfScape
{

    public static void main(String[] args)
    {
        RenderContext ctx = new RenderContext(new Window("GarfScape", 1280, 720));
        ctx.getWindow().setClearColor(new Vector4f(0.1f, 0.1f, 0.2f, 1.0f));
        
        TextureAtlas atlas = new TextureAtlas("Img/TestAtlas.png", 2);
        
        Transform2D t = new Transform2D();
        t.getScale().x = 100;
        t.getScale().y = 100;
        t.getPosition().y = 360;
        t.getPosition().x = 360;
        
        Transform2D help = new Transform2D();
        
        
        Entity entity = new Entity(t);
        
        entity.addComponent(new DrawebleComponent(new Sprite(new Texture(atlas, 1, 0, 1, 1))));
        entity.addComponent(new RigidBody2D());
        
        Entity collider = new Entity(t);
        collider.getTransform().getPosition().y -= 200;
        collider.addComponent(new BoxCollider2D());
        collider.addComponent(new DrawebleComponent(new Sprite(new Texture(atlas, 0, 0, 1, 1))));
        
        ctx.setCamera(new Entity(t));
        
        ctx.add(entity, LayerIndex.Scene);
        ctx.add(collider, LayerIndex.Scene);
        
        
        
        ctx.start();
        while (ctx.update());
    }

}
