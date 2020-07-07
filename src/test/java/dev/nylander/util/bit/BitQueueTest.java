package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitQueueTest {
    @Test
    public void emptyBitQueueTest() {
        BitQueue buffer = new BitQueue();
        Assert.assertThrows(IndexOutOfBoundsException.class,
                () -> buffer.bitAt(0));
    }

    @Test
    public void bitPushTest() {
        BitQueue buffer = new BitQueue();
        buffer.pushBit(true);
        Assert.assertTrue(buffer.bitAt(0));
    }
}
