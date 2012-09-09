package BlockSpace;

/**
 *
 * @author Hagane <deemson@gmail.com>
 */
import BlockSpace.Graphics.BlockDrawHelper;
import java.util.LinkedList;
import java.util.List;

public class BlockGroup {
    private List<Block> blocks;
    private float absX;
    private float absY;
    private float absZ;

    /**
     * @return group's absolute X coordinate
     */
    public float getAbsX() {
        return absX;
    }

    /**
     * @return group's absolute Y coordinate
     */
    public float getAbsY() {
        return absY;
    }

    /**
     * @return group's absolute Z coordinate
     */
    public float getAbsZ() {
        return absZ;
    }
    
    public BlockGroup(float aAbsX, float aAbsY, float aAbsZ)
    {
        blocks = new LinkedList<>();
        absX = aAbsX;
        absY = aAbsY;
        absZ = aAbsZ;
    }
    
    public void addBlock(int aRelX, int aRelY, int aRelZ)
    {
        blocks.add(new Block(aRelX,aRelY,aRelZ));
    }
    
    public void draw()
    {
        for(Block b : blocks) 
        {
            BlockDrawHelper.DrawBlock(b.getRelX(), b.getRelY(), b.getRelZ());
        }
    }
    
}
