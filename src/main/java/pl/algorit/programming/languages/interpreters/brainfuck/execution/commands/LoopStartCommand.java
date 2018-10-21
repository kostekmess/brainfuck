package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.BrainFuckFlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

@AllArgsConstructor
class LoopStartCommand implements BrainFuckFlowCommand {
    @Getter
    private final char symbol = '[';

    @Override
    public FlowAdvice adviceOnFlow(MemoryReader memoryReader) {
        return memoryReader.get() == 0
                ? BrainFuckFlowAdvice.JUMP_TO_LOOP_END
                : BrainFuckFlowAdvice.NEXT_INSTRUCTION;
    }
}
