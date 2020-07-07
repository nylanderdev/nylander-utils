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
}
