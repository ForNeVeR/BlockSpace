package BlockSpace;

/**
 *
 * @author Hagane <deemson@gmail.com>
 */
import BlockSpace.Util.Quaternion;
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
        Quaternion qpitch = new Quaternion((float)Math.cos(Math.toRadians(rP)/2),
                                              0f,
                                              (float)Math.sin(Math.toRadians(rP)/2),
                                              0f);
        Quaternion qyaw = new Quaternion((float)Math.cos(Math.toRadians(rY)/2),
                                         0f,
                                         0f,
                                         (float)Math.sin(Math.toRadians(rY)/2));
        
        Quaternion rotation = qpitch.multiply(qyaw).normalize();
        
        Quaternion eyeVector = rotation.multiply(new Quaternion(0, 0, 0, -1)).multiply(rotation.conjugate());
        Quaternion upVector = rotation.multiply(new Quaternion(0, 0, 1, 0)).multiply(rotation.conjugate());
        
        gluLookAt(tX, tY, tZ,
                  tX - eyeVector.getX(), tY - eyeVector.getY(), tZ - eyeVector.getZ(),
                  upVector.getX(), upVector.getY(), upVector.getZ());
    }
}
