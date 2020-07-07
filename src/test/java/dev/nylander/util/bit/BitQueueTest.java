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
    public void enqueueBitTest() {
        BitQueue buffer = new BitQueue();
        buffer.enqueue(true);
        Assert.assertTrue(buffer.bitAt(0));
    }
}
