package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitAssemblerBitCountTest {

    @Test
    public void bitCountTest1() {
        testBitCount(9838);
    }

    @Test
    public void bitCountTest2() {
        testBitCount(345);
    }

    @Test
    public void bitCountTest3() {
        testBitCount(23);
    }

    @Test
    public void bitCountTest4() {
        testBitCount(0);
    }

    @Test
    public void bitCountTest5() {
        testBitCount(1);
    }

    private void testBitCount(final int expectedBitCount) {
        BitAssembler assembler = new BitAssembler();
        boolean[] bits = new boolean[expectedBitCount];
        assembler.push(bits);
        Assert.assertEquals(expectedBitCount, assembler.availableBits());
    }
}
