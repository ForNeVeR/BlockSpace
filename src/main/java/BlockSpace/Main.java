package BlockSpace;

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

/**
 * Main program class.
 *
 * @author Hagane <deemson@gmail.com>
 * @author von Never
 */
public class Main {
	private static final int FPS = 60;

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
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);
        glClearColor(0.4f, 0.6f, 0.9f, 0f);
        
        IController ctrl = new PlayerController(new Camera());
        
        BlockDrawHelper.Init();
        BlockGroup bg = new BlockGroup(0f, 0f, -5f);
        bg.addBlock(0, 0, 0);
        bg.addBlock(1, 0, 0);
        bg.addBlock(0, 1, 0);
        bg.addBlock(0, 0, 1);
        bg.addBlock(0, 1, 1);
        bg.addBlock(1, 0, 1);
        bg.addBlock(1, 1, 0);
        bg.addBlock(1, 1, 1);
        
        float rotation = 0.0f;
        
        while (!Display.isCloseRequested())
        {
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            gluPerspective(90.0f, ((float)1024)/((float)768), 0.1f, 1000.0f);
            ctrl.update();
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            //rotation += 0.05f;
            glTranslatef(bg.getAbsX(), bg.getAbsY(), bg.getAbsZ());
            //glRotatef(rotation, 1, 0, 0);
            //glRotatef(rotation, 0, 1, 0);
            //glRotatef(rotation, 0, 0, 1);
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            bg.draw();
            
            Display.update();
			Display.sync(FPS);
        }
        
        BlockDrawHelper.DeInit();
        Display.destroy();
    }
}
