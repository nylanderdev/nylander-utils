package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitAssemblerInsufficientBitsTest {

    @Test
    public void insufficientByteAssemblyTest1() {
        BitAssembler assembler = new BitAssembler();
        assembler.push(new boolean[7]);
        Assert.assertThrows(IllegalStateException.class, assembler::popByte);
    }

    @Test
    public void insufficientByteAssemblyTest2() {
        BitAssembler assembler = new BitAssembler();
        assembler.push(new boolean[15]);
        assembler.popByte();
        Assert.assertThrows(IllegalStateException.class, assembler::popByte);
    }

}
