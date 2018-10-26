package pl.algorit.programming.languages.interpreters.brainfuck;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.val;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.commands.BrainFuckCommandsFactory;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.commands.BrainFuckCommandsPredicate;
import pl.algorit.programming.languages.interpreters.framework.Interpreter;
import pl.algorit.programming.languages.interpreters.framework.execution.GenericCommandsExecutor;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class BrainFuckInterpreter implements Interpreter {

    private final GenericCommandsExecutor genericCommandsExecutor;
    private final BrainFuckCommandsFactory commandsFactory;
    private final BrainFuckCommandsPredicate commandsPredicate;

    @Override
    public void interpret(@NonNull String commandsExpression) {
        val commands = IntStream.range(0, commandsExpression.length())
                .mapToObj(commandsExpression::charAt)
                .filter(commandsPredicate)
                .map(commandsFactory::create)
                .collect(Collectors.toList());

        genericCommandsExecutor.execute(commands);
    }

}
