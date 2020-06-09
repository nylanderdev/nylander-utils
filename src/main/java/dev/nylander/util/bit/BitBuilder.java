package dev.nylander.util.bit;

import java.util.Arrays;

public class BitBuilder {
    private final StringBuilder internalChars = new StringBuilder();
    private final boolean[] buffer = new boolean[8];
    private int bufferLength = 0;

    public void append(char aChar) {
        if (bufferLength == 0) {
            internalChars.append(aChar);
        } else {
            appendBits(charToBits(aChar));
        }
    }

    public void append(byte aByte) {
        append((char) aByte);
    }

    public void appendBit(boolean bit) {
        buffer[bufferLength++] = bit;
        if (bufferLength == 8) {
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
        Arrays.fill(buffer, false);
        bufferLength = 0;
    }

    public boolean[] toBits() {
        boolean[] allBits = new boolean[8 * internalChars.length() + bufferLength];
        char[] chars = internalChars.toString().toCharArray();
        for (int charIndex = 0; charIndex < chars.length; charIndex++) {
            boolean[] bitsOfChar = charToBits(chars[charIndex]);
            System.arraycopy(bitsOfChar, 0, allBits, 8 * charIndex, 8);
        }
        System.arraycopy(buffer, 0, allBits, 8 * internalChars.length(), bufferLength);
        return allBits;
    }

    private char charFromBuffer() {
        char bufferChar = 0;
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[buffer.length - 1 - i]) {
                bufferChar |= (1 << i);
            }
        }
        return bufferChar;
    }

    private static boolean[] charToBits(final char aChar) {
        final int sizeOfByte = 8;
        boolean[] bitArray = new boolean[sizeOfByte];
        for (int i = 0; i < sizeOfByte; i++) {
            bitArray[sizeOfByte - 1 - i] = ((aChar >> i) & 1) == 1;
        }
        return bitArray;
    }

}
