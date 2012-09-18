
package BlockSpace.Util.Test;

import junit.framework.TestCase;
import BlockSpace.Util.Quaternion;
/**
 *
 * @author Savichev_DV
 */
public class QuaternionTest extends TestCase
{
    
    public QuaternionTest(String testName)
    {
        super(testName);
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    
    public void testCreate()
    {
        Quaternion quat = new Quaternion (1f,0f,0f,0f);
        assertNotNull(quat);
    }
    
    public void testEquals()
    {
        Quaternion quat1 = new Quaternion (1f,0f,0f,0f);
        Quaternion quat2 = new Quaternion (1f,0f,0f,0f);
        Quaternion quat3 = new Quaternion (0f,0f,0f,0f);
        assertTrue(quat1.equals(quat2));
        assertFalse(quat1.equals(quat3));
    }
    
    public void testAdd()
    {
        Quaternion augend = new Quaternion (1f,0f,0f,0f);
        Quaternion addend = new Quaternion (1f,1f,1f,1f);
        Quaternion result = new Quaternion (2f,1f,1f,1f);
        assertEquals(result, augend.add(addend));
    }
    
    public void testConjugate()
    {
        Quaternion quat = new Quaternion(1f,1f,1f,1f);
        Quaternion conjugate = new Quaternion(1f,-1f,-1f,-1f);
        assertEquals(conjugate, quat.conjugate());
    }
    
    public void testAbs()
    {
        Quaternion quat = new Quaternion(1f,1f,1f,1f);
        assertEquals(2f, quat.abs());
    }
    
    public void testMultiply()
    {
        Quaternion quat1 = new Quaternion(1f,1f,1f,1f);
        Quaternion quat2 = new Quaternion(1f,1f,1f,1f);
        float scalar = 2f;
        Quaternion result1 = new Quaternion(2f,2f,2f,2f);
        Quaternion result2 = new Quaternion(-2f,2f,2f,2f);
        assertEquals(result1, quat1.multiply(scalar));
        assertEquals(result2, quat1.multiply(quat2));
    }
    
    public void testNormalize()
    {
        Quaternion quat = new Quaternion(1f,1f,1f,1f);
        Quaternion normal = new Quaternion(0.5f,0.5f,0.5f,0.5f);
        assertEquals(normal, quat.normalize());
    }
}
