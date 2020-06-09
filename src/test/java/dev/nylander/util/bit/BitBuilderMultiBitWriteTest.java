package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitBuilderMultiBitWriteTest {
    @Test
    public void multiBitWriteTest1() {
        BitBuilder builder = new BitBuilder();
        boolean[] expected = new boolean[]{
                true, true, false, true,
                true, false, true, true,
                true, false, false, false,
                true, false, false, false,
                false, true, false, true
        };
        builder.appendBits(expected);
        Assert.assertArrayEquals(expected, builder.toBits());
    }
}
