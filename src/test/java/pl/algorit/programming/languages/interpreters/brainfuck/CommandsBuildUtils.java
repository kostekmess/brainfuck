package pl.algorit.programming.languages.interpreters.brainfuck;

import lombok.val;
import org.mockito.Mockito;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;

import java.util.List;
import java.util.stream.Collectors;

public class CommandsBuildUtils {
    public static List<Command> getCommands(String symbols) {
        return symbols.chars()
                .mapToObj(c -> {
                    val fake = Mockito.mock(Command.class);
                    Mockito.when(fake.getSymbol()).thenReturn((char) c);
                    return fake;
                })
                .collect(Collectors.toList());
    }

}
