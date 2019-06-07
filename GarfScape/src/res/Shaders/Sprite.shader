@VERTEX
#version 330 core

$_DEF_POSITIONS


void main()
{  
    gl_Position = vec4($_POSITIONS, 1.0);
}

@FRAGMENT
#version 330 core

out vec4 out_color;

void main()
{
        out_color = vec4(0.0, 1.0, 1.0, 1.0);
}
