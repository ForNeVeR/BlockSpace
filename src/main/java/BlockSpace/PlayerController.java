package BlockSpace;

/**
 *
 * @author Hagane <deemson@gmail.com>
 */
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
public class PlayerController implements IController
{
    private static float MOVE_SPEED = 0.01f;
    private static float ROTATE_SPEED = 0.1f;
       
    private Camera camera;
    public PlayerController(Camera aCamera)
    {
        camera = aCamera;
        Mouse.setGrabbed(true);
    }
    
    @Override
    public void update()
    {
        int mouse_dx = Mouse.getDX();
        int mouse_dy = Mouse.getDY();
        
        getCamera().rotate(mouse_dx*ROTATE_SPEED, mouse_dy*ROTATE_SPEED);
        
        float forward_spd = 0f;
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) forward_spd += MOVE_SPEED;
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) forward_spd -= MOVE_SPEED;
        getCamera().move(0, 0, forward_spd);
        getCamera().applyTransform();
    }

    /**
     * @return the camera
     */
    public Camera getCamera() {
        return camera;
    }
}
