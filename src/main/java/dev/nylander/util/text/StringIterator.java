package dev.nylander.util.text;

import java.util.Iterator;

public class StringIterator implements Iterator<Character> {
    private final Character[] charactersOfString;
    private int nextIndex = 0;

    public StringIterator(String string) {
        char[] primitiveArray = string.toCharArray();
        charactersOfString = new Character[primitiveArray.length];
        for (int i = 0; i < primitiveArray.length; i++) {
            charactersOfString[i] = primitiveArray[i];
        }
    }

    public boolean hasNext() {
        return nextIndex < charactersOfString.length;
    }

    public Character next() {
        return charactersOfString[nextIndex++];
    }

    public Character peek() {
        return charactersOfString[nextIndex];
    }
}
