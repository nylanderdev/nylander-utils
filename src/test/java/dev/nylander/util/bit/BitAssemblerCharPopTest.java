package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitAssemblerCharPopTest {

    @Test
    public void charAssemblyTest1() {
        BitAssembler assembler = new BitAssembler();
        char expected = 0b11101000;
        assembler.push(new boolean[]
                {true, true, true, false,
                        true, false, false, false}
        );
        Assert.assertEquals(expected, assembler.popChar());
    }

    @Test
    public void charAssemblyTest2() {
        BitAssembler assembler = new BitAssembler();
        char expected1 = 0b11101000;
        char expected2 = 0b11101001;
        assembler.push(new boolean[]
                {true, true, true, false,
                        true, false, false, false}
        );
        assembler.push(new boolean[]
                {true, true, true, false,
                        true, false, false, true}
        );
        Assert.assertEquals(expected1, assembler.popChar());
        Assert.assertEquals(expected2, assembler.popChar());
    }

    @Test
    public void charAssemblyTest3() {
        BitAssembler assembler = new BitAssembler();
        char expected = 0b11101000;
        assembler.push(new boolean[]
                {true, true, true, false,
                        true, false, false, false,
                        false, true, false, true}
        );
        Assert.assertEquals(expected, assembler.popChar());
    }
}
