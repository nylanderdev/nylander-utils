package dev.nylander.util.bit;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BitAssembler {
    private final Queue<Character> internalChars = new ArrayDeque<>();
    private final boolean[] topBuffer = new boolean[8];
    private int topBufferLength = 0;
    private final boolean[] bottomBuffer = new boolean[8];
    private int bottomBufferLength = 0;

    public void push(boolean bit) {
        topBuffer[topBufferLength++] = bit;
        if (topBufferLength == 8) {
            pushTopBuffer();
        }
    }

    public void push(boolean[] bits) {
        for (boolean bit : bits) {
            push(bit);
        }
    }

    public void push(char aChar) {
        push(BitUtil.charToBits(aChar));
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
        if (bottomBufferLength > 0) {
            return popBitFromBottomBuffer();
        } else {
            if (internalChars.size() > 0) {
                loadBottomBuffer();
                return popBitFromBottomBuffer();
            } else {
                return popBitFromTopBuffer();
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
        return BitUtil.bitsToChar(popBits(8));
    }

    public int availableBytes() {
        return availableBits() / 8;
    }

    public int availableBits() {
        return bottomBufferLength + 8 * internalChars.size() + topBufferLength;
    }

    private void loadBottomBuffer() {
        char internalChar = internalChars.remove();
        boolean[] bitsToLoad = BitUtil.charToBits(internalChar);
        System.arraycopy(bitsToLoad, 0, bottomBuffer, 0, bitsToLoad.length);
        bottomBufferLength = 8;
    }

    private boolean popBitFromTopBuffer() {
        boolean poppedBit = topBuffer[0];
        shiftTopBuffer();
        return poppedBit;
    }

    private void shiftTopBuffer() {
        topBufferLength--;
        if (topBufferLength >= 0) {
            System.arraycopy(topBuffer, 1, topBuffer, 0, topBufferLength);
        }
    }

    private boolean popBitFromBottomBuffer() {
        boolean poppedBit = bottomBuffer[0];
        shiftBottomBuffer();
        return poppedBit;
    }

    private void shiftBottomBuffer() {
        bottomBufferLength--;
        if (bottomBufferLength >= 0) {
            System.arraycopy(bottomBuffer, 1, bottomBuffer, 0, bottomBufferLength);
        }
    }

    private void pushTopBuffer() {
        internalChars.add(charFromTopBuffer());
        resetTopBuffer();
    }

    private void resetTopBuffer() {
        topBufferLength = 0;
        Arrays.fill(topBuffer, false);
    }

    private char charFromTopBuffer() {
        return BitUtil.bitsToChar(topBuffer);
    }
}