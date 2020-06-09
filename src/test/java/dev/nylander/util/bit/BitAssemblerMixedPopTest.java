package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitAssemblerMixedPopTest {
    @Test
    public void mixedPopTest1() {
        BitAssembler assembler = new BitAssembler();
        boolean part1 = true;
        byte part2 = 0b01111110;
        assembler.push(part1);
        assembler.push(part2);
        byte expected1 = (byte) 0b10111111;
        boolean expected2 = false;
        Assert.assertEquals(expected1, assembler.popByte());
        Assert.assertEquals(expected2, assembler.popBit());
    }
}
