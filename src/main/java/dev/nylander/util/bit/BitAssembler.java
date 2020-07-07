package dev.nylander.util.bit;

public class BitAssembler {
    private BitQueue topBuffer = new BitQueue();
    private CharBitQueue charBuffer = new CharBitQueue();

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

    public boolean popBit() {
        if (availableBits() < 1)
            throw new IllegalArgumentException("BitAssembler is empty.");
        if (charBuffer.availableBits() > 0)
            return charBuffer.dequeueBit();
        else return topBuffer.dequeue();
    }

    public byte popByte() {
        return (byte) popChar();
    }

    public char popChar() {
        if (availableBytes() < 1)
            throw new IllegalStateException("Less than eight bits in the BitAssembler.");
        return BitUtils.bitsToChar(popBits(8));
    }

    public int availableBytes() {
        return availableBits() / 8;
    }

    public int availableBits() {
        return charBuffer.availableBits() + topBuffer.length();
    }

    private void pushTopBuffer() {
        charBuffer.enqueue(charFromTopBuffer());
        resetTopBuffer();
    }

    private void resetTopBuffer() {
        topBuffer = new BitQueue();
    }

    private char charFromTopBuffer() {
        return BitUtils.bitsToChar(topBuffer.asBits());
    }
}