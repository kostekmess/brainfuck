package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;

public enum BrainFuckFlowAdvice implements FlowAdvice {
    NEXT_INSTRUCTION,
    JUMP_TO_LOOP_START,
    JUMP_TO_LOOP_END;
}
