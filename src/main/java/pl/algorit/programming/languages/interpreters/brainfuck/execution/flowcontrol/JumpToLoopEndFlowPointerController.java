package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import lombok.NonNull;
import lombok.val;
import lombok.var;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

import java.util.ArrayDeque;
import java.util.Optional;

public class JumpToLoopEndFlowPointerController extends JumpToFlowPointerController {

    public Optional<FlowPointer<Command>> move(@NonNull FlowPointer<Command> current) {
        val stack = new ArrayDeque<Character>();

        var next = next(current);

        while (next.isPresent()) {
            val found = processFinding(stack, next.get(), LOOP_START, LOOP_END);
            if (found) {
                return next;
            }
            next = next(next.get());
        }

        return Optional.empty();
    }

    @Override
    public FlowAdvice getAdvice() {
        return BrainFuckFlowAdvice.JUMP_TO_LOOP_END;
    }
}
