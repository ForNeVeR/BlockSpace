package BlockSpace;

/**
 *
 * @author Hagane <deemson@gmail.com>
 */
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Camera 
{
    private float tX,tY,tZ; //translation coords
    private float rP,rY;    //rotation angles (pitch & yaw)
    
    public Camera()
    {
        tX = 0;
        tY = 0;
        tZ = 0;
        rP = 0;
        rY = 0;
    }    
    
    public Camera(float aTX,float aTY,float aTZ)
    {
        tX = aTX;
        tY = aTY;
        tZ = aTZ;
        rP = 0f;
        rY = 0f;
    }
    
    public Camera(float aTX,float aTY,float aTZ,float aRP,float aRY)
    {
        tX = aTX;
        tY = aTY;
        tZ = aTZ;
        rP = aRP;
        rY = aRY;
    }
    
    public void move(float aTX,float aTY,float aTZ)
    {
        tX += aTX;
        tY += aTY;
        tZ += aTZ;
    }
    
    public void rotate(float aRP,float aRY)
    {
        rP += aRP;
        rY += aRY;
    }
    
    public void applyTransform()
    {
        //FIXTHATSHIT
        float centerX = (float) (tX - Math.sin(Math.toRadians(rP))*Math.cos(Math.toRadians(rY+90)));
        float centerY = (float) (tY - Math.sin(Math.toRadians(rP))*Math.sin(Math.toRadians(rY+90)));
        float centerZ = (float) (tZ - Math.cos(Math.toRadians(rP)));
        float upX = (float) (Math.sin(Math.toRadians(rP+90))*Math.cos(Math.toRadians(rY+90)));
        float upY = (float) (Math.sin(Math.toRadians(rP+90))*Math.sin(Math.toRadians(rY+90)));
        float upZ = (float) Math.cos(Math.toRadians(rP+90));
        gluLookAt(tX, tY, tZ, centerX, centerY, centerZ, upX, upY, upZ);
    }
}
