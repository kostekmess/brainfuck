package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

import java.util.Optional;

public class NextCommandFlowPointerController extends AbstractFlowPointerController {

    @Override
    public Optional<FlowPointer<Command>> move(FlowPointer<Command> current) {
        return next(current);
    }

    @Override
    public FlowAdvice getAdvice() {
        return BrainFuckFlowAdvice.NEXT_INSTRUCTION;
    }
}
