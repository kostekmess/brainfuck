package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.BrainFuckFlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

@AllArgsConstructor
class PrintCommand implements Command {
    @Getter
    private final char symbol = '.';

    @Override
    public void useScreen(MemoryReader memoryReader, ScreenWriter screenWriter) {
        screenWriter.write((char) memoryReader.get());
    }

    @Override
    public void modifyMemory(Memory memory) {
        //by default do nothing
    }

    @Override
    public FlowAdvice adviceOnFlow(MemoryReader memoryReader) {
        return BrainFuckFlowAdvice.NEXT_INSTRUCTION;
    }
}
