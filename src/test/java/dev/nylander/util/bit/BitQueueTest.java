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

    @Test
    public void fullTest() {
        BitQueue queue = new BitQueue();
        for (int i = 0; i < 8; i++) {
            queue.enqueue(true);
        }
        Assert.assertTrue(queue.isFull());
    }

    @Test
    public void asBitsTest() {
        BitQueue queue = new BitQueue();
        queue.enqueue(true);
        queue.enqueue(true);
        queue.enqueue(false);
        queue.enqueue(true);
        queue.enqueue(false);
        queue.enqueue(false);
        boolean[] expected = new boolean[]{true, true, false,
                true, false, false};
        Assert.assertArrayEquals(expected, queue.asBits());
    }

    @Test
    public void lengthTest() {
        BitQueue queue = new BitQueue();
        queue.enqueue(true);
        queue.enqueue(true);
        queue.enqueue(false);
        queue.enqueue(true);
        queue.enqueue(false);
        queue.enqueue(false);
        Assert.assertEquals(6, queue.length());
    }

    @Test
    public void dequeueTest() {
        BitQueue queue = new BitQueue();
        boolean[] toEnqueue = new boolean[]{
                false, false, true, true,
                true, false, true, false
        };
        boolean[] dequeued = new boolean[8];
        for (int i = 0; i < 8; i++) {
            queue.enqueue(toEnqueue[i]);
        }
        for (int i = 7; i >= 0; i--) {
            dequeued[i] = queue.dequeue();
        }
        Assert.assertArrayEquals(toEnqueue, dequeued);
    }
}
