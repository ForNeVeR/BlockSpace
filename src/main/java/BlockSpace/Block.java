package BlockSpace;

/**
 *
 * @author Hagane <deemson@gmail.com>
 */
public class Block {
    private int relX;
    private int relY;
    private int relZ;

    /**
     * @return relative X coordinate
     */
    public int getRelX() {
        return relX;
    }

    /**
     * @return relative Y coordinate
     */
    public int getRelY() {
        return relY;
    }

    /**
     * @return relative Z coordinate
     */
    public int getRelZ() {
        return relZ;
    }
    
    public Block(int aRelX, int aRelY, int aRelZ)
    {
        relX = aRelX;
        relY = aRelY;
        relZ = aRelZ;
    }
}
