package dev.nylander.util.bit;

class BitUtil {

    public static char bitsToChar(boolean[] eightBits) {
        if (eightBits.length != 8) {
            throw new IllegalArgumentException("eightBits must have a length of exactly 8.");
        }
        char bitChar = 0;
        for (int i = 0; i < eightBits.length; i++) {
            if (eightBits[eightBits.length - 1 - i]) {
                bitChar |= (1 << i);
            }
        }
        return bitChar;
    }

    public static boolean[] charToBits(char aChar) {
        final int sizeOfByte = 8;
        boolean[] bitArray = new boolean[sizeOfByte];
        for (int i = 0; i < sizeOfByte; i++) {
            bitArray[sizeOfByte - 1 - i] = ((aChar >> i) & 1) == 1;
        }
        return bitArray;
    }
}
