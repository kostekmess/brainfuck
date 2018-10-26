package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.BrainFuckFlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

@Test
public class PrintCommandTest {

    @Test
    public void useScreen_memory_getsValueFromMemoryAndWritesToScreen() throws Exception {
        val command = new PrintCommand();
        val memoryFake = Mockito.mock(MemoryReader.class);
        val screenFake = Mockito.mock(ScreenWriter.class);
        command.useScreen(memoryFake, screenFake);

        Mockito.verify(memoryFake, Mockito.times(1)).get();
        Mockito.verify(screenFake, Mockito.times(1)).write(Mockito.anyChar());
        Mockito.verifyNoMoreInteractions(memoryFake, screenFake);
    }

    @Test
    public void modifyMemory_doesNothing() throws Exception {
        val command = new PrintCommand();
        val memoryFake = Mockito.mock(Memory.class);
        command.modifyMemory(memoryFake);

        Mockito.verifyNoMoreInteractions(memoryFake);
    }

    @Test
    public void adviceOnFlow_memory_advicesToGetNextInstruction() throws Exception {
        val command = new PrintCommand();
        val memoryFake = Mockito.mock(MemoryReader.class);
        val result = command.adviceOnFlow(memoryFake);

        Assertions.assertThat(result).isEqualTo(BrainFuckFlowAdvice.NEXT_INSTRUCTION);
        Mockito.verifyNoMoreInteractions(memoryFake);
    }
}