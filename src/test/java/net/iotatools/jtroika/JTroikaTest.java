package net.iotatools.jtroika;

import org.junit.Assert;
import org.junit.Test;

public class JTroikaTest {

    private static final int[] vector1Hash = new int[]{
            0,0,2,0,0,0,2,0,2,1,0,2,2,2,0,2,0,1,0,0,1,2,2,0,1,1,1,
            0,0,1,1,1,0,0,0,0,1,0,0,0,2,0,2,2,2,1,1,2,2,1,1,0,2,1,
            1,0,0,2,1,1,0,1,2,0,2,1,0,1,1,0,1,1,0,1,2,0,1,0,1,2,0,
            1,2,1,0,2,0,2,0,1,0,1,1,1,0,0,2,2,1,1,1,0,2,0,2,2,1,2,
            0,0,1,2,2,2,1,0,2,0,2,0,2,1,0,0,2,0,0,0,2,0,1,2,2,0,0,
            2,1,1,2,2,0,0,2,1,2,0,2,0,0,1,2,0,0,1,0,1,0,2,0,1,2,2,
            1,2,0,0,0,1,0,1,1,2,0,1,0,1,0,2,1,1,2,0,0,2,1,0,0,2,1,
            0,2,0,0,0,0,0,2,1,0,0,1,2,0,2,0,0,1,1,2,2,0,0,2,2,1,0,
            2,2,1,1,1,0,0,2,1,1,1,0,0,0,0,0,1,2,1,2,2,2,2,0,0,0,2};

    private static final int[] vector2Hash = new int[]{
            2,0,2,0,0,2,1,1,1,1,1,0,1,2,0,0,1,1,1,0,1,2,2,1,2,2,2,
            1,2,0,0,2,2,1,1,1,0,1,2,2,0,1,2,0,2,1,2,1,2,1,2,0,1,0,
            0,0,0,0,1,0,2,0,2,0,2,1,2,2,2,0,1,0,2,1,2,1,2,1,2,1,0,
            2,1,0,2,0,1,1,1,2,2,2,1,1,1,1,0,1,0,0,0,2,1,0,0,1,2,1,
            1,1,0,0,0,1,1,2,1,2,1,2,0,0,0,2,2,2,1,2,1,2,0,2,0,0,2,
            2,1,0,0,0,2,2,2,0,2,2,0,2,2,2,2,1,0,0,2,2,1,0,1,2,1,1,
            2,0,0,1,1,1,2,1,2,1,0,2,2,0,1,1,2,0,2,2,1,1,0,2,1,1,2,
            0,2,0,0,1,1,1,0,2,0,0,0,0,2,1,0,1,2,2,1,1,0,2,2,2,1,1,
            0,0,2,1,1,2,2,0,0,2,1,2,0,1,2,2,1,1,2,0,2,2,1,2,1,1,1};

    private static final int[] vector3Hash = new int[]{
            1,2,0,2,2,0,1,2,1,2,1,2,0,2,0,2,1,1,0,1,2,2,0,2,2,2,1,
            1,2,1,2,1,2,2,2,1,2,1,1,0,2,2,1,1,2,2,2,2,2,0,1,2,1,2,
            0,0,1,2,2,1,0,1,1,2,0,2,2,1,1,0,2,0,0,2,0,0,0,0,2,0,0,
            1,0,0,0,1,2,0,2,1,2,2,2,0,1,1,2,1,1,1,1,1,2,0,2,2,1,0,
            1,0,2,2,0,2,2,1,1,1,2,0,1,0,2,2,1,1,2,2,2,0,0,0,0,0,2,
            2,1,0,2,0,2,1,2,1,0,0,1,2,2,1,0,1,0,0,2,2,0,0,1,1,0,1,
            0,2,1,0,1,0,0,0,0,0,2,1,2,2,1,0,1,1,2,2,0,0,0,2,1,0,0,
            0,1,2,2,2,1,0,2,0,0,1,0,1,1,2,0,0,1,2,2,2,0,2,0,1,1,2,
            1,0,0,2,1,1,0,2,0,2,2,1,1,2,1,1,0,1,1,0,1,1,0,2,2,1,2};

    @Test
    //check that the test vector from reference documentation is correctly hashed
    //see : https://www.cyber-crypt.com/wp-content/uploads/2018/12/20181221.iota_.troika-reference.v1.0.1.pdf (page 15)
    public void vector1Test(){
        Assert.assertArrayEquals(vector1Hash,JTroika.hashAsTrits(new int[]{0}));
    }

    @Test
    //check that the test vector from reference documentation is correctly hashed
    //see : https://www.cyber-crypt.com/wp-content/uploads/2018/12/20181221.iota_.troika-reference.v1.0.1.pdf (page 15)
    public void vector2Test(){
        Assert.assertArrayEquals(vector2Hash,JTroika.hashAsTrits(new int[]{0,0}));
    }

    @Test
    //check that the test vector from reference documentation is correctly hashed
    //see : https://www.cyber-crypt.com/wp-content/uploads/2018/12/20181221.iota_.troika-reference.v1.0.1.pdf (page 15)
    public void vector3Test(){
        Assert.assertArrayEquals(vector3Hash,JTroika.hashAsTrits(new int[]{
                1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2
        }));
    }

    @Test
    public void invalidTryteTest(){
        try{
            JTroika.hash("A123");
            Assert.fail("Should have thrown IllegalArgumentException");
        }catch (IllegalArgumentException e){
            //expected
        }
    }

}
