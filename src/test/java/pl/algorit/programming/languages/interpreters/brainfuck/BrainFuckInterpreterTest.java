package pl.algorit.programming.languages.interpreters.brainfuck;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.commands.BrainFuckCommandsFactory;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.commands.BrainFuckCommandsPredicate;
import pl.algorit.programming.languages.interpreters.framework.execution.GenericCommandsExecutor;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;

import java.util.List;

import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BrainFuckInterpreterTest {
    BrainFuckInterpreter objectUnderTest;

    @Test
    public void interpret_4commands_properlyInterpretsAllCommands() {
        val predicate = Mockito.mock(BrainFuckCommandsPredicate.class);
        when(predicate.test(anyChar())).thenReturn(true);
        when(predicate.test('e')).thenReturn(false);

        val factory = Mockito.mock(BrainFuckCommandsFactory.class);
        val tCommand = Mockito.mock(Command.class);
        val sCommand = Mockito.mock(Command.class);

        when(factory.create('t')).thenReturn(tCommand);
        when(factory.create('s')).thenReturn(sCommand);

        val executor = Mockito.mock(GenericCommandsExecutor.class);

        objectUnderTest = new BrainFuckInterpreter(executor, factory, predicate);
        objectUnderTest.interpret("test");

        verify(factory, times(3)).create(anyChar());
        verify(predicate, times(4)).test(anyChar());

        val argument = ArgumentCaptor.forClass(List.class);
        verify(executor, times(1)).execute(argument.capture());

        Assertions.assertThat(argument.getValue()).containsExactly(tCommand, sCommand, tCommand);
    }
}