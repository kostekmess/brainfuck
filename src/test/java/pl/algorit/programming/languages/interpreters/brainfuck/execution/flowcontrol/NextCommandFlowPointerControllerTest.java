package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import com.google.common.collect.ImmutableList;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

public class NextCommandFlowPointerControllerTest {

    private NextCommandFlowPointerController objectUnderTest;

    @Test
    public void move_threeElements_movesIteratorToTheThirdElement() {
        objectUnderTest = new NextCommandFlowPointerController();

        val cmdMock1 = Mockito.mock(Command.class);
        val cmdMock2 = Mockito.mock(Command.class);
        val cmdMock3 = Mockito.mock(Command.class);

        val commands = ImmutableList.<Command>of(cmdMock1, cmdMock2, cmdMock3);

        val move0 = FlowPointer.first(commands);
        val move1 = objectUnderTest.move(move0).orElseThrow(IllegalStateException::new);
        val move2 = objectUnderTest.move(move1).orElseThrow(IllegalStateException::new);

        Assertions.assertThat(move2.getObject()).isEqualTo(cmdMock3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void move_null_throwsException() {
        objectUnderTest = new NextCommandFlowPointerController();

        val move1 = objectUnderTest.move(null);
    }
}