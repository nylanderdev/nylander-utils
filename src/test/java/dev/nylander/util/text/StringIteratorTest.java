package dev.nylander.util.text;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class StringIteratorTest {
    @Test
    public void noNextTest1() {
        testNoNextException("wowowowowow wow");
    }

    @Test
    public void noNextTest2() {
        testNoNextException("");
    }

    @Test
    public void noNextTest3() {
        testNoNextException("that's wack");
    }

    private void testNoNextException(final String string) {
        StringIterator iterator = new StringIterator(string);
        for (int i = 0; i < string.length(); i++) {
            iterator.next();
        }
        Assert.assertThrows(NoSuchElementException.class, iterator::next);
    }
}
