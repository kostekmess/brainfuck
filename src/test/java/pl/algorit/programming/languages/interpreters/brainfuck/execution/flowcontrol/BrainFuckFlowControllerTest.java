package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import com.google.common.collect.ImmutableSet;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class BrainFuckFlowControllerTest {

    BrainFuckFlowController objectUnderTest;

    @Test
    public void nextPointer_oneOfTwoControllersEligibleAdvice_returnsAppropriateResult() {
        val pointerFake = FlowPointer.first(Collections.singleton(Mockito.mock(Command.class)));
        val pointer2Fake = FlowPointer.first(Collections.singleton(Mockito.mock(Command.class)));

        val aAdvice = Mockito.mock(FlowAdvice.class);
        val bAdvice = Mockito.mock(FlowAdvice.class);

        val aController = Mockito.mock(BrainFuckFlowPointerController.class);
        when(aController.getAdvice()).thenReturn(aAdvice);
        val aControllerMove = Optional.of(pointerFake);
        when(aController.move(pointerFake)).thenReturn(aControllerMove);

        val bController = Mockito.mock(BrainFuckFlowPointerController.class);
        when(bController.getAdvice()).thenReturn(bAdvice);
        val bControllerMove = Optional.of(pointer2Fake);
        when(bController.move(pointerFake)).thenReturn(bControllerMove);

        val flowPointerControllers = ImmutableSet.of(aController, bController);
        objectUnderTest = new BrainFuckFlowController(flowPointerControllers);

        val result = objectUnderTest.nextPointer(pointerFake, bAdvice);

        Assertions.assertThat(result).isEqualTo(bControllerMove);
    }

    @Test
    public void nextPointer_noAppropriateController_returnsEmptyResult() {
        val pointerFake = FlowPointer.first(Collections.singleton(Mockito.mock(Command.class)));

        val aAdvice = Mockito.mock(FlowAdvice.class);
        val bAdvice = Mockito.mock(FlowAdvice.class);

        val aController = Mockito.mock(BrainFuckFlowPointerController.class);
        when(aController.getAdvice()).thenReturn(aAdvice);
        val aControllerMove = Optional.of(pointerFake);
        when(aController.move(pointerFake)).thenReturn(aControllerMove);

        val flowPointerControllers = ImmutableSet.of(aController);
        objectUnderTest = new BrainFuckFlowController(flowPointerControllers);

        val result = objectUnderTest.nextPointer(pointerFake, bAdvice);

        Assertions.assertThat(result).isEqualTo(Optional.empty());
    }

}