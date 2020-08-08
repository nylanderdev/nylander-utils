package dev.nylander.util.bit;

import java.util.ArrayDeque;
import java.util.Queue;

class CharBitQueue {
    private final Queue<Character> internalChars = new ArrayDeque<>();
    private final BitQueue bottomBuffer = new BitQueue();

    public void enqueue(char aChar) {
        internalChars.add(aChar);
    }

    public boolean dequeueBit() {
        if (availableBits() <= 0) {
            throw new IllegalStateException("CharBitQueue is empty.");
        }
        if (bottomBuffer.length() <= 0) {
            loadBottomBuffer();
        }
        return bottomBuffer.dequeue();
    }

    public int availableBits() {
        return bottomBuffer.length() + internalChars.size() * 8;
    }

    private void loadBottomBuffer() {
        char internalChar = internalChars.remove();
        boolean[] bitsToLoad = BitUtils.charToBits(internalChar);
        for (boolean bit : bitsToLoad) {
            bottomBuffer.enqueue(bit);
        }
    }
}
