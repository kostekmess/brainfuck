package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

interface BrainFuckFlowCommand extends Command {

    @Override
    default void modifyMemory(Memory memory) {
        //by default do nothing
    }

    @Override
    default void useScreen(MemoryReader memoryReader, ScreenWriter screenWriter) {
        //by default do nothing
    }

}
