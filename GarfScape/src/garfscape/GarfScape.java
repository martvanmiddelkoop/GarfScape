/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garfscape;

import Core.Data.*;
import Core.Window;
import org.joml.Vector4f;

public class GarfScape
{

    public static void main(String[] args)
    {
        Window window = new Window("Garfscape", 1280, 720);
        window.setClearColor(new Vector4f(0.1f, 0.1f, 0.2f, 1));

        float[] positions =
        {

            -0.5f, 0.5f, 0f, // Left top         ID: 0
            -0.5f, -0.5f, 0f, // Left bottom      ID: 1
            0.5f, -0.5f, 0f, // Right bottom     ID: 2
            0.5f, 0.5f, 0f  // Right left       ID: 3
        };

        float[] textureCoords =
        {
            0, 0,
            0, 1,
            1, 1,
            1, 0
        };

        int[] indicies =
        {
            0, 1, 2,
            0, 2, 3,
        };

        Shader shader = new Shader("Shaders/Sprite.shader");
        
        TextureAtlas atlas = new TextureAtlas("Img/TestAtlas.png", 2);
        Texture texture = new Texture(atlas, 1, 0, 1, 1);

        Mesh mesh = new Mesh(positions, textureCoords, indicies);

        while (window.isActive())
        {
            window.prepare();

            shader.bind();
            texture.bind(shader);

            mesh.draw();

            window.update();

        }

        window.destroy();
    }

}
