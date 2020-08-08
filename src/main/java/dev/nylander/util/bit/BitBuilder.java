package dev.nylander.util.bit;

public class BitBuilder {
    private final StringBuilder internalChars = new StringBuilder();
    private BitQueue buffer = new BitQueue();

    public void append(char aChar) {
        appendBits(BitUtils.charToBits(aChar));
    }

    public void append(byte aByte) {
        append((char) aByte);
    }

    public void appendBit(boolean bit) {
        buffer.enqueue(bit);
        if (buffer.isFull()) {
            pushBuffer();
        }
    }

    public void appendBits(boolean[] bits) {
        for (boolean bit : bits) {
            appendBit(bit);
        }
    }

    private void pushBuffer() {
        internalChars.append(charFromBuffer());
        resetBuffer();
    }

    private void resetBuffer() {
        buffer = new BitQueue();
    }

    public boolean[] toBits() {
        boolean[] allBits = new boolean[8 * internalChars.length() + buffer.length()];
        char[] chars = internalChars.toString().toCharArray();
        for (int charIndex = 0; charIndex < chars.length; charIndex++) {
            boolean[] bitsOfChar = BitUtils.charToBits(chars[charIndex]);
            System.arraycopy(bitsOfChar, 0, allBits, 8 * charIndex, 8);
        }
        boolean[] bufferBits = buffer.asBits();
        System.arraycopy(bufferBits, 0, allBits, 8 * internalChars.length(), bufferBits.length);
        return allBits;
    }

    private char charFromBuffer() {
        return BitUtils.bitsToChar(buffer.asBits());
    }

}
