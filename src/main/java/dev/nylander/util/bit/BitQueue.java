package dev.nylander.util.bit;

public class BitQueue {
    private boolean[] internalBits = new boolean[8];
    private int length;

    public boolean bitAt(int index) {
        if (index >= length)
            throw new ArrayIndexOutOfBoundsException(index);
        return internalBits[index];
    }

    public void enqueue(boolean bit) {
        if (isFull())
            throw new IllegalStateException("BitQueue is full");
        internalBits[length++] = bit;
    }

    public boolean[] asBits() {
        boolean[] copy = new boolean[length];
        System.arraycopy(internalBits, 0, copy, 0, copy.length);
        return copy;
    }

    public boolean isFull() {
        return length >= 8;
    }
}
