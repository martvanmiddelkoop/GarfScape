/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garfscape;

import Core.Data.*;
import Core.Draweble.Sprite2D;
import Core.Window;
import org.joml.Vector4f;

public class GarfScape
{

    public static void main(String[] args)
    {
        Window window = new Window("Garfscape", 1280, 720);
        window.setClearColor(new Vector4f(0.1f, 0.1f, 0.2f, 1));

        Shader shader = new Shader("Shaders/Sprite.shader");

        TextureAtlas atlas = new TextureAtlas("Img/TestAtlas.png", 2);
        Texture texture = new Texture(atlas, 1, 1, 1, 1);

        Sprite2D sprite = new Sprite2D();
        sprite.setShader(shader);
        sprite.setTexture(texture);
        sprite.getTransform().getPosition().x += 100;
        sprite.getTransform().getPosition().y += 100;
        sprite.setWidth(100);
        sprite.setHeight(100);

        while (window.isActive())
        {
            window.prepare();

            sprite.draw(window);

            window.update();

        }

        window.destroy();
    }

}
