package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitAssemblerBytePopTest {
    @Test
    public void byteAssemblyTest1() {
        BitAssembler assembler = new BitAssembler();
        byte expected = (byte) 0b11101000;
        assembler.push(new boolean[]
                {true, true, true, false,
                        true, false, false, false}
        );
        Assert.assertEquals(expected, assembler.popByte());
    }

    @Test
    public void byteAssemblyTest2() {
        BitAssembler assembler = new BitAssembler();
        byte expected1 = (byte) 0b11101000;
        byte expected2 = (byte) 0b11101001;
        assembler.push(new boolean[]
                {true, true, true, false,
                        true, false, false, false}
        );
        assembler.push(new boolean[]
                {true, true, true, false,
                        true, false, false, true}
        );
        Assert.assertEquals(expected1, assembler.popByte());
        Assert.assertEquals(expected2, assembler.popByte());
    }

    @Test
    public void byteAssemblyTest3() {
        BitAssembler assembler = new BitAssembler();
        byte expected = (byte) 0b11101000;
        assembler.push(new boolean[]
                {true, true, true, false,
                        true, false, false, false,
                        false, true, false, true}
        );
        Assert.assertEquals(expected, assembler.popByte());
    }

}
