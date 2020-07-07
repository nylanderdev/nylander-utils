package dev.nylander.util.bit;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BitAssembler {
    private final Queue<Character> internalChars = new ArrayDeque<>();
    private BitQueue topBuffer = new BitQueue();
    private BitQueue bottomBuffer = new BitQueue();


    public void push(boolean bit) {
        topBuffer.enqueue(bit);
        if (topBuffer.isFull()) {
            pushTopBuffer();
        }
    }

    public void push(boolean[] bits) {
        for (boolean bit : bits) {
            push(bit);
        }
    }

    public void push(char aChar) {
        push(BitUtils.charToBits(aChar));
    }

    public void push(byte aByte) {
        push((char) aByte);
    }

    public void push(byte[] bytes) {
        for (byte aByte : bytes) {
            push(aByte);
        }
    }

    public void push(char[] chars) {
        for (char aChar : chars) {
            push(aChar);
        }
    }

    public boolean popBit() {
        if (availableBits() <= 0) {
            throw new IllegalStateException("BitAssembler is empty.");
        }
        if (bottomBuffer.length() > 0) {
            return bottomBuffer.dequeue();
        } else {
            if (internalChars.size() > 0) {
                loadBottomBuffer();
                return bottomBuffer.dequeue();
            } else {
                return topBuffer.dequeue();
            }
        }
    }

    public boolean[] popBits(int bitCount) {
        if (availableBits() < bitCount) {
            throw new IllegalArgumentException("Less than " + bitCount + " bits in the BitAssembler.");
        }
        boolean[] poppedBits = new boolean[bitCount];
        for (int i = 0; i < bitCount; i++) {
            poppedBits[i] = popBit();
        }
        return poppedBits;
    }

    public byte popByte() {
        return (byte) popChar();
    }

    public char popChar() {
        if (availableBytes() < 1) {
            throw new IllegalStateException("Less than eight bits in the BitAssembler.");
        }
        return BitUtils.bitsToChar(popBits(8));
    }

    public int availableBytes() {
        return availableBits() / 8;
    }

    public int availableBits() {
        return bottomBuffer.length() + 8 * internalChars.size() + topBuffer.length();
    }

    private void loadBottomBuffer() {
        char internalChar = internalChars.remove();
        boolean[] bitsToLoad = BitUtils.charToBits(internalChar);
        for (boolean bit : bitsToLoad) {
            bottomBuffer.enqueue(bit);
        }
    }

    private void pushTopBuffer() {
        internalChars.add(charFromTopBuffer());
        resetTopBuffer();
    }

    private void resetTopBuffer() {
        topBuffer = new BitQueue();
    }

    private char charFromTopBuffer() {
        return BitUtils.bitsToChar(topBuffer.asBits());
    }
}