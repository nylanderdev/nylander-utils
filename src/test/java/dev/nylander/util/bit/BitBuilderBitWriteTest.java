package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitBuilderBitWriteTest {
    @Test
    public void bitWriteTest1() {
        testBitWrite(new boolean[]{});
    }

    @Test
    public void bitWriteTest2() {
        testBitWrite(new boolean[]{true});
    }

    @Test
    public void bitWriteTest3() {
        testBitWrite(new boolean[]{true, true, false, true});
    }

    private void testBitWrite(boolean[] bits) {
        BitBuilder builder = new BitBuilder();
        for (boolean bit : bits) {
            builder.appendBit(bit);
        }
        Assert.assertArrayEquals(bits, builder.toBits());
    }
}
