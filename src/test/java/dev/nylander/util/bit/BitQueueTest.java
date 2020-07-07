package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitQueueTest {
    @Test
    public void emptyBitQueueTest() {
        BitQueue queue = new BitQueue();
        Assert.assertThrows(IndexOutOfBoundsException.class,
                () -> queue.bitAt(0));
    }

    @Test
    public void enqueueBitTest() {
        BitQueue queue = new BitQueue();
        queue.enqueue(true);
        Assert.assertTrue(queue.bitAt(0));
    }

    @Test
    public void enqueueWhenFullTest() {
        BitQueue queue = new BitQueue();
        for (int i = 0; i < 8; i++) {
            queue.enqueue(true);
        }
        Assert.assertThrows(IllegalStateException.class,
                () -> queue.enqueue(true));
    }
}
