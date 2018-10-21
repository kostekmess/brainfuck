package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.BrainFuckFlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

public class MovePointerRightCommandTest {

    @Test
    public void modifyMemory_memory_movesPointerRight() throws Exception {
        val command = new MovePointerRightCommand();
        val memoryFake = Mockito.mock(Memory.class);
        command.modifyMemory(memoryFake);

        Mockito.verify(memoryFake, Mockito.times(1)).movePointerRight();
        Mockito.verifyNoMoreInteractions(memoryFake);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testModifyMemory_null_throwsException() {
        val command = new MovePointerRightCommand();
        command.modifyMemory(null);
    }

    @Test
    public void adviceOnFlow_memory_advicesToGetNextInstruction() throws Exception {
        val command = new MovePointerRightCommand();
        val memoryFake = Mockito.mock(MemoryReader.class);
        val result = command.adviceOnFlow(memoryFake);

        Assertions.assertThat(result).isEqualTo(BrainFuckFlowAdvice.NEXT_INSTRUCTION);
        Mockito.verifyNoMoreInteractions(memoryFake);
    }

    @Test
    public void useScreen_doesNothing() throws Exception {
        val command = new MovePointerRightCommand();
        val memoryFake = Mockito.mock(MemoryReader.class);
        val screenFake = Mockito.mock(ScreenWriter.class);
        command.useScreen(memoryFake, screenFake);

        Mockito.verifyNoMoreInteractions(memoryFake, screenFake);
    }
}