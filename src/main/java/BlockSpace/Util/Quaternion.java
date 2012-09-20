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
        w = aW;
        x = aX;
        y = aY;
        z = aZ;
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

    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + Float.floatToIntBits(this.w);
        hash = 97 * hash + Float.floatToIntBits(this.x);
        hash = 97 * hash + Float.floatToIntBits(this.y);
        hash = 97 * hash + Float.floatToIntBits(this.z);
        return hash;
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
        return new Quaternion(w,-x,-y,-z);
    }

    public float abs()
    {
        return (float) Math.sqrt(w*w+x*x+y*y+z*z);
    }

    public Quaternion multiply(float scalar)
    {
        return new Quaternion(scalar*w,scalar*x,scalar*y,scalar*z);
    }

    public Quaternion multiply(Quaternion other)
    {
        float nW = this.getW()*other.getW() - this.getX()*other.getX() 
                - this.getY()*other.getY() - this.getZ()*other.getZ();
        
        float nX = this.getW()*other.getX() + this.getX()*other.getW() 
                - this.getY()*other.getZ() + this.getZ()*other.getY();
        
        float nY = this.getW()*other.getY() + this.getX()*other.getZ() 
                + this.getY()*other.getW() - this.getZ()*other.getX();
        
        float nZ = this.getW()*other.getZ() - this.getX()*other.getY() 
                + this.getY()*other.getX() + this.getZ()*other.getW();
        
        return new Quaternion(nW, nX, nY, nZ);
    }

    public Quaternion normalize()
    {
        float length = this.abs();
        return new Quaternion(w/length,x/length,y/length,z/length);
    }
    
}
