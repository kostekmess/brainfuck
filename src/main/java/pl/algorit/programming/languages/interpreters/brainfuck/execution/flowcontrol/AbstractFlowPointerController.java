package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import lombok.NonNull;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointerController;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

import java.util.Optional;

abstract class AbstractFlowPointerController implements BrainFuckFlowPointerController {

    Optional<FlowPointer<Command>> next(@NonNull FlowPointer<Command> current) {
        return current.hasNext()
                ? Optional.of(current.next())
                : Optional.empty();
    }

    Optional<FlowPointer<Command>> previous(@NonNull FlowPointer<Command> current) {
        return current.hasPrevious()
                ? Optional.of(current.previous())
                : Optional.empty();
    }
}
