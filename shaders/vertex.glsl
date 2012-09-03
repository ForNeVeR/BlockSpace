#version 150 core
in vec4 in_Position;
in vec4 in_Color;

uniform mat4 projection;
uniform mat4 modelview;

out vec4 pass_Color;

void main(void)
{
    gl_Position = projection * modelview * in_Position;
    pass_Color = in_Color;
}