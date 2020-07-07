package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitBufferTest {
    @Test
    public void emptyBitBufferTest() {
        BitBuffer buffer = new BitBuffer();
        Assert.assertThrows(IndexOutOfBoundsException.class,
                () -> buffer.bitAt(0));
    }

    @Test
    public void bitPushTest() {
        BitBuffer buffer = new BitBuffer();
        buffer.pushBit(true);
        Assert.assertTrue(buffer.bitAt(0));
    }
}
