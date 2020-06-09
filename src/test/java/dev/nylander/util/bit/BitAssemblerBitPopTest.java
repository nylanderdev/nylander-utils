package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitAssemblerBitPopTest {

    @Test
    public void bitPopTest1() {
        BitAssembler assembler = new BitAssembler();
        boolean expected1 = false;
        boolean expected2 = true;
        assembler.push(new boolean[]{expected1, expected2});
        Assert.assertEquals(expected1, assembler.popBit());
        Assert.assertEquals(expected2, assembler.popBit());
    }

    @Test
    public void bitPopTest2() {
        testBitPops(new boolean[]
                {true, false, true, true,
                        false, false, true, false}
        );
    }

    public void testBitPops(boolean[] expectedBits) {
        BitAssembler assembler = new BitAssembler();
        assembler.push(expectedBits);
        for (int i = 0; i < expectedBits.length; i++) {
            Assert.assertEquals(expectedBits[i], assembler.popBit());
        }
    }
}
