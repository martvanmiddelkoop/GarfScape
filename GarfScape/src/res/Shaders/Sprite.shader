@VERTEX
#version 330 core

$_DEF_POSITIONS
$_DEF_TEX_COORDS

$_DEF_IMAGE
$_DEF_IMAGE_OFFSET
$_DEF_IMAGE_ROWS
$_DEF_IMAGE_WIDTH
$_DEF_IMAGE_HEIGHT

$_DEF_MVP


out vec2 a_texCoord;


void main()
{  
    gl_Position = $_MVP * vec4($_POSITIONS, 1.0);

    //calculate the texture coordinates
    vec2 texScale = vec2(float($_IMAGE_ROWS) / float($_IMAGE_WIDTH), float($_IMAGE_ROWS) / float($_IMAGE_HEIGHT));
    a_texCoord = ($_TEX_COORDS / texScale) + ($_IMAGE_OFFSET / texScale);

}







@FRAGMENT
#version 330 core

$_DEF_IMAGE

out vec4 out_color;
in vec2 a_texCoord;


void main()
{
    out_color = texture($_IMAGE, a_texCoord);
}
