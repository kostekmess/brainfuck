package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.BrainFuckFlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

public class LoopStartCommandTest {

    @Test
    public void adviceOnFlow_byteIsNot0_advicesToGetNextInstruction() throws Exception {
        val command = new LoopStartCommand();
        val memoryFake = Mockito.mock(Memory.class);
        Mockito.when(memoryFake.get()).thenReturn((byte) 4);
        val result = command.adviceOnFlow(memoryFake);

        Assertions.assertThat(result).isEqualTo(BrainFuckFlowAdvice.NEXT_INSTRUCTION);

        Mockito.verify(memoryFake, Mockito.times(1)).get();
        Mockito.verifyNoMoreInteractions(memoryFake);
    }

    @Test
    public void adviceOnFlow_byteIs0_advicesToGoBackToLoopEnd() throws Exception {
        val command = new LoopStartCommand();
        val memoryFake = Mockito.mock(Memory.class);
        Mockito.when(memoryFake.get()).thenReturn((byte) 0);
        val result = command.adviceOnFlow(memoryFake);

        Assertions.assertThat(result).isEqualTo(BrainFuckFlowAdvice.JUMP_TO_LOOP_END);

        Mockito.verify(memoryFake, Mockito.times(1)).get();
        Mockito.verifyNoMoreInteractions(memoryFake);
    }


    @Test
    public void useScreen_doesNothing() throws Exception {
        val command = new LoopStartCommand();
        val memoryFake = Mockito.mock(MemoryReader.class);
        val screenFake = Mockito.mock(ScreenWriter.class);
        command.useScreen(memoryFake, screenFake);

        Mockito.verifyNoMoreInteractions(memoryFake, screenFake);
    }

    @Test
    public void modifyMemory_doesNothing() {
        val command = new LoopStartCommand();
        val memoryFake = Mockito.mock(Memory.class);
        command.modifyMemory(memoryFake);

        Mockito.verifyNoMoreInteractions(memoryFake);
    }
}