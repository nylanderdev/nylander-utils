package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitBuilderMixedWriteTest {
    @Test
    public void mixedWriteTest1() {
        BitBuilder builder = new BitBuilder();
        char part1 = 0b11110000;
        boolean part2 = true;
        char part3 = 0b00001111;
        boolean[] expected = new boolean[]{
                true, true, true, true,
                false, false, false, false,
                true,
                false, false, false, false,
                true, true, true, true
        };
        builder.append(part1);
        builder.appendBit(part2);
        builder.append(part3);
        Assert.assertArrayEquals(expected, builder.toBits());
    }

    @Test
    public void mixedWriteTest2() {
        BitBuilder builder = new BitBuilder();
        boolean part1 = true;
        char part2 = 0b11110000;
        char part3 = 0b00001111;
        boolean[] expected = new boolean[]{
                true,
                true, true, true, true,
                false, false, false, false,
                false, false, false, false,
                true, true, true, true
        };
        builder.appendBit(part1);
        builder.append(part2);
        builder.append(part3);
        Assert.assertArrayEquals(expected, builder.toBits());
    }
}
