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
            appendBits(BitUtils.charToBits(aChar));
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
            boolean[] bitsOfChar = BitUtils.charToBits(chars[charIndex]);
            System.arraycopy(bitsOfChar, 0, allBits, 8 * charIndex, 8);
        }
        System.arraycopy(buffer, 0, allBits, 8 * internalChars.length(), bufferLength);
        return allBits;
    }

    private char charFromBuffer() {
        return BitUtils.bitsToChar(buffer);
    }

}
