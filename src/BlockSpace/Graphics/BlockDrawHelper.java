package BlockSpace.Graphics;

/**
 *
 * @author Hagane <deemson@gmail.com>
 */

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class BlockDrawHelper {
    
    private static float vertices[] = {-0.5f, -0.5f, -0.5f, 1.0f,
                                        0.5f, -0.5f, -0.5f, 1.0f,
                                        0.5f,  0.5f, -0.5f, 1.0f,
                                       -0.5f,  0.5f, -0.5f, 1.0f,
                                       -0.5f, -0.5f,  0.5f, 1.0f,
                                        0.5f, -0.5f,  0.5f, 1.0f,
                                        0.5f,  0.5f,  0.5f, 1.0f,
                                       -0.5f,  0.5f,  0.5f, 1.0f};
    
    private static float colors[] = {1.0f, 0.0f, 0.0f, 1.0f,
                                     0.0f, 1.0f, 0.0f, 1.0f,
                                     0.0f, 0.0f, 1.0f, 1.0f,
                                     1.0f, 1.0f, 1.0f, 1.0f,
                                     0.0f, 1.0f, 1.0f, 1.0f,
                                     1.0f, 0.0f, 1.0f, 1.0f,
                                     1.0f, 1.0f, 0.0f, 1.0f,
                                     0.0f, 0.0f, 0.0f, 1.0f};
    
    private static byte indices[] = {0,1,2,3,
                                     0,4,5,1,
                                     0,3,7,4,
                                     1,5,6,2,
                                     2,6,7,3,
                                     4,7,6,5};
    private static int vaoID;
    private static int vboID;
    private static int vboiID;
    private static int vbocID;
    
    private static Shader shader;
    
    public static void Init()
    {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertexBuffer.put(vertices);
        vertexBuffer.flip();
        
        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorBuffer.put(colors);
        colorBuffer.flip();
        
        ByteBuffer indexBuffer = BufferUtils.createByteBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();
               
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);
        
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);       
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        vbocID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbocID);
        glBufferData(GL_ARRAY_BUFFER,colorBuffer,GL_STATIC_DRAW);
        glVertexAttribPointer(1,4,GL_FLOAT,false,0,0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glBindVertexArray(0);
        vboiID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        
        shader = new Shader("shaders/vertex.glsl","shaders/fragment.glsl");
        shader.setAttribute(0, "in_Position");
        shader.setAttribute(1, "in_Color");
    }
    
    public static void DeInit()
    {        
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboID);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboiID);
        
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoID);
    }
    
    public static void DrawBlock(int relX, int relY, int relZ)
    {
        glPushMatrix();
        glTranslated(relX, relY, relZ);
        glBindVertexArray(vaoID);
        
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        FloatBuffer projMatrix = BufferUtils.createFloatBuffer(16);
        FloatBuffer modelviewMatrix = BufferUtils.createFloatBuffer(16);
        glGetFloat(GL_PROJECTION_MATRIX, projMatrix);
        glGetFloat(GL_MODELVIEW_MATRIX, modelviewMatrix);
        
        shader.bind();
        shader.setUniformMatrix4("projection", projMatrix);
        shader.setUniformMatrix4("modelview", modelviewMatrix);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiID);
        glDrawElements(GL_QUADS, indices.length, GL_UNSIGNED_BYTE, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
        glPopMatrix();
    }
}
