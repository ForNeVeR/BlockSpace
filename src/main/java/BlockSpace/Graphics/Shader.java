package BlockSpace.Graphics;

/**
 *
 * @author Hagane <deemson@gmail.com>
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import javax.print.DocFlavor;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.util.glu.GLU.*;

public class Shader {
    
    private int programID;
    private int vertexShader;
    private int fragmentShader;
    
    private static int loadShader(String path, int type)
    {
        StringBuilder source = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null)
            {
                source.append(line).append("\n");
            }
            reader.close();
            System.out.println(source);
        }
        catch (FileNotFoundException ex)
        {
            System.err.printf("Shader source not found: %s\n", path);
            System.exit(-1);
        }
        catch (IOException ex)
        {
            System.err.printf("Could not read shader source: %s\n", path);
            System.exit(-1);
        }
        
        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, source);
        glCompileShader(shaderID);
        return shaderID;
    }
    
    public Shader(String vsPath, String fsPath)
    {
        programID = glCreateProgram();
        vertexShader = loadShader(vsPath, GL_VERTEX_SHADER);
        fragmentShader = loadShader(fsPath, GL_FRAGMENT_SHADER);
        glAttachShader(programID, vertexShader);
        glAttachShader(programID, fragmentShader);
        glLinkProgram(programID);
    }
    
    public void setAttribute(int attribID, String attribName)
    {
        glBindAttribLocation(programID, attribID, attribName);
    }
    
    public void setUniform4(String name, FloatBuffer vec4)
    {
        int uniformLocation = glGetUniformLocation(programID, name);
        glUniform4(uniformLocation, vec4);
    }
    
    public void setUniformMatrix4(String name, FloatBuffer mat4)
    {
        int uniformLocation = glGetUniformLocation(programID, name);
        glUniformMatrix4(uniformLocation, false, mat4);
    }
    
    public void bind()
    {
        glValidateProgram(programID);
        int glError = glGetError();
        if(glError != GL_NO_ERROR)
        {
            System.err.printf("OpenGL Error %x: %s\n",glError,gluErrorString(glError));
            System.exit(-1);
        }
        glUseProgram(programID);
    }
    
    public void clear()
    {
    //    glUseProgram(0);
        glDetachShader(programID, vertexShader);
        glDetachShader(programID, fragmentShader);
        
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        glDeleteProgram(programID);
    }
}
