package BlockSpace;

/**
 *
 * @author Hagane <deemson@gmail.com>
 */

import BlockSpace.Graphics.BlockDrawHelper;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.util.glu.GLU.*;

public class Main {
    public static void main (String args[])
    {
        Main m = new Main();
        m.start();
    }
    
    public void start()
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(1024, 768));
            Display.create();
        }
        catch (LWJGLException ex)
        {
            ex.printStackTrace();
            System.exit(0);
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(90.0f, ((float)1024)/((float)768), 0.1f, 1000.0f);
        glMatrixMode(GL_MODELVIEW);
        
        BlockDrawHelper.Init();
        
        while (!Display.isCloseRequested())
        {
            glRotated(0.01, 0.3, 0.4, 0.5);
            glTranslated(0.0001, 0.0001, -0.001);
            glClearColor(0.4f, 0.6f, 0.9f, 0f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            BlockDrawHelper.DrawBlock(0, 0, 0);
            BlockDrawHelper.DrawBlock(1, 0, 0);
            BlockDrawHelper.DrawBlock(0, 1, 0);
            BlockDrawHelper.DrawBlock(0, 0, 1);
            
            Display.update();
        }
        
        BlockDrawHelper.DeInit();
        Display.destroy();
    }
}
