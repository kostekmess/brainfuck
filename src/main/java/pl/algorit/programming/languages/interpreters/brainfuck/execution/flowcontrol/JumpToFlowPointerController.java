package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

import java.util.ArrayDeque;

abstract class JumpToFlowPointerController extends AbstractFlowPointerController {
    static final char LOOP_END = ']';
    static final char LOOP_START = '[';

    boolean processFinding(ArrayDeque<Character> stack, FlowPointer<Command> current, char nestedLoopSymbol, char searchedElementSymbol) {
        if (current.getObject().getSymbol() == searchedElementSymbol) {
            if (stack.isEmpty()) {
                return true;
            } else {
                stack.pop();
            }
        }

        if (current.getObject().getSymbol() == nestedLoopSymbol) {
            stack.push(current.getObject().getSymbol());
        }
        return false;
    }
}
