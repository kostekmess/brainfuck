package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import com.google.common.collect.ImmutableSet;
import lombok.AllArgsConstructor;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.CommandsFactory;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BrainFuckCommandsFactory implements CommandsFactory {

    private static final Set<Command> COMMANDS = ImmutableSet.<Command>builder()
            .add(new AddCommand())
            .add(new SubtractCommand())
            .add(new MovePointerRightCommand())
            .add(new MovePointerLeftCommand())
            .add(new PrintCommand())
            .add(new LoopStartCommand())
            .add(new LoopEndCommand())
            .build();

    static final Map<Character, Command> COMMANDS_BY_SYMBOL = COMMANDS.stream()
            .collect(Collectors.toMap(
                    Command::getSymbol,
                    Function.identity()
            ));

    @Override
    public Command create(Character c) {
        return Optional.ofNullable(COMMANDS_BY_SYMBOL.get(c))
                .orElseThrow(() -> new IllegalStateException(String.format("%s character not recognized as command", c)));
    }

}
