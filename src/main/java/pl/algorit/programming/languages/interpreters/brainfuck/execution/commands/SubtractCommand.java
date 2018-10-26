package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;

@AllArgsConstructor
class SubtractCommand implements BrainFuckMemoryCommand {
    @Getter
    private final char symbol = '-';

    @Override
    public void modifyMemory(@NonNull Memory memory) {
        memory.decrement();
    }
}
