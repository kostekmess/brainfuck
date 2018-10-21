package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.BrainFuckFlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

interface BrainFuckMemoryCommand extends Command {

    @Override
    default FlowAdvice adviceOnFlow(MemoryReader memoryReader) {
        return BrainFuckFlowAdvice.NEXT_INSTRUCTION;
    }

    @Override
    default void useScreen(MemoryReader memoryReader, ScreenWriter screenWriter) {
        //by default do nothing
    }

}
