package dev.nylander.util.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitBuilderSingleWriteTest {
    @Test
    public void singleCharWriteTest1() {
        char charToWrite = 0b10011100;
        boolean[] expected = new boolean[]
                {true, false, false, true,
                        true, true, false, false};
        BitBuilder writer = new BitBuilder();
        writer.append(charToWrite);
        Assert.assertArrayEquals(expected, writer.toBits());
    }

    @Test
    public void singleCharWriteTest2() {
        char charToWrite =  0b00011100;
        boolean[] expected = new boolean[]
                {false, false, false, true,
                        true, true, false, false};
        BitBuilder writer = new BitBuilder();
        writer.append(charToWrite);
        Assert.assertArrayEquals(expected, writer.toBits());
    }

    @Test
    public void singleByteWriteTest1() {
        byte byteToWrite = 0b00011100;
        boolean[] expected = new boolean[]
                {false, false, false, true,
                        true, true, false, false};
        BitBuilder writer = new BitBuilder();
        writer.append(byteToWrite);
        Assert.assertArrayEquals(expected, writer.toBits());
    }

    @Test
    public void singleByteWriteTest2() {
        byte byteToWrite = (byte) 0b10011100;
        boolean[] expected = new boolean[]
                {true, false, false, true,
                        true, true, false, false};
        BitBuilder writer = new BitBuilder();
        writer.append(byteToWrite);
        Assert.assertArrayEquals(expected, writer.toBits());
    }

    @Test
    public void singleByteWriteTest3() {
        byte byteToWrite = (byte) 0b11111111;
        boolean[] expected = new boolean[]
                {true, true, true, true,
                        true, true, true, true};
        BitBuilder writer = new BitBuilder();
        writer.append(byteToWrite);
        Assert.assertArrayEquals(expected, writer.toBits());
    }
}