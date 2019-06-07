/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garfscape;

import Core.Data.Mesh;
import Core.Data.Shader;
import Core.Window;

public class GarfScape
{

    public static void main(String[] args)
    {
        Window window = new Window("Garfscape", 1280, 720);

        float[] positions =
        {
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
        };

        int[] indicies =
        {
            0, 1, 2
        };
        
        Shader shader = new Shader("Shaders/Sprite.shader");

        Mesh mesh = new Mesh(positions, indicies);
        while (window.isActive())
        {
            window.prepare();
            
            shader.bind();
            mesh.draw();
            shader.unBind();

            window.update();

        }

        window.destroy();
    }

}
