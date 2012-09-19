package BlockSpace.Util;

/**
 *
 * @author Savichev_DV
 */
public class Quaternion
{
    private float w;
    private float x;
    private float y;
    private float z;

    public Quaternion(float aW, float aX, float aY, float aZ)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * @return scalar component
     */
    public float getW()
    {
        return w;
    }

    /**
     * @return X component of vector
     */
    public float getX()
    {
        return x;
    }

    /**
     * @return Y component of vector
     */
    public float getY()
    {
        return y;
    }

    /**
     * @return Z component of vector
     */
    public float getZ()
    {
        return z;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass())
        {
            return false;
        }
        else
        {
            Quaternion other = (Quaternion) obj;
            if (this.getW() == other.getW()
                && this.getX() == other.getX()
                && this.getY() == other.getY()
                && this.getZ() == other.getZ())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public Quaternion add(Quaternion addend)
    {
        float nW = this.getW() + addend.getW();
        float nX = this.getX() + addend.getX();
        float nY = this.getY() + addend.getY();
        float nZ = this.getZ() + addend.getZ();
        return new Quaternion(nW, nX, nY, nZ);
    }

    public Quaternion conjugate()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public float abs()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Quaternion multiply(float scalar)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Quaternion multiply(Quaternion other)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Quaternion normalize()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
