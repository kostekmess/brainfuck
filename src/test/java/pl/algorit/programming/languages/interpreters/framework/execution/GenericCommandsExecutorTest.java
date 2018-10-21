package pl.algorit.programming.languages.interpreters.framework.execution;

import lombok.val;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.brainfuck.CommandsBuildUtils;
import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowController;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GenericCommandsExecutorTest {
    GenericCommandsExecutor objectUnderTest;

    @Test
    public void execute_commands_processAllCommands() throws Exception {
        val memoryFake = Mockito.mock(Memory.class);
        val screenWriterFake = Mockito.mock(ScreenWriter.class);
        val flowControllerFake = Mockito.mock(FlowController.class);

        objectUnderTest = new GenericCommandsExecutor(memoryFake, screenWriterFake, flowControllerFake);

        val commandFakes = CommandsBuildUtils.getCommands("+++++++-----.");
        Mockito.when(flowControllerFake.nextPointer(Mockito.any(FlowPointer.class), Mockito.any(FlowAdvice.class)))
                .thenAnswer(invocation -> {
                            val fp = invocation.getArgumentAt(0, FlowPointer.class);
                            return Optional.ofNullable(fp.hasNext() ? fp.next() : null);
                        }
                );

        objectUnderTest.execute(commandFakes);

        commandFakes.forEach(cmd -> {
            verify(cmd, times(1)).modifyMemory(memoryFake);
            verify(cmd, times(1)).useScreen(memoryFake, screenWriterFake);
            verify(cmd, times(1)).adviceOnFlow(memoryFake);
        });
    }
}