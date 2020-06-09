package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitBuilderMultiWriteTest {
    @Test
    public void multiCharWriteTest1() {
        char char1 = 0b11001100;
        char char2 = 0b10101010;
        boolean[] expected = new boolean[]{
                true, true, false, false,
                true, true, false, false,
                true, false, true, false,
                true, false, true, false
        };
        BitBuilder builder = new BitBuilder();
        builder.append(char1);
        builder.append(char2);
        Assert.assertArrayEquals(expected, builder.toBits());
    }

    @Test
    public void multiCharWriteTest2() {
        char char1 = 0b11001100;
        char char2 = 0b10101010;
        char char3 = 0b00101011;
        boolean[] expected = new boolean[]{
                true, true, false, false,
                true, true, false, false,
                true, false, true, false,
                true, false, true, false,
                false, false, true, false,
                true, false, true, true
        };
        BitBuilder builder = new BitBuilder();
        builder.append(char1);
        builder.append(char2);
        builder.append(char3);
        Assert.assertArrayEquals(expected, builder.toBits());
    }
}
