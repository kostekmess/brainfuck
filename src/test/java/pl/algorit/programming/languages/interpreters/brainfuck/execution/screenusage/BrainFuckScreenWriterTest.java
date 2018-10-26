package pl.algorit.programming.languages.interpreters.brainfuck.execution.screenusage;

import lombok.val;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.io.PrintStream;

public class BrainFuckScreenWriterTest {
    BrainFuckScreenWriter objectUnderTest;

    @Test
    public void writeln_aaaString_invokesPrintlnOnceWithGivenString() {
        val fake = Mockito.mock(PrintStream.class);
        objectUnderTest = new BrainFuckScreenWriter(fake);

        val value = 'a';
        objectUnderTest.write(value);

        Mockito.verify(fake, Mockito.times(1)).println(value);
    }
}