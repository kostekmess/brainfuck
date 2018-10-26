package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import lombok.NonNull;
import lombok.val;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowController;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BrainFuckFlowController implements FlowController<Command> {

    private final Map<FlowAdvice, BrainFuckFlowPointerController> flowAdviceControllers;

    public BrainFuckFlowController(@NonNull Set<BrainFuckFlowPointerController> flowPointerControllers) {
        this.flowAdviceControllers = flowPointerControllers.stream()
                .collect(Collectors.toMap(
                        BrainFuckFlowPointerController::getAdvice,
                        Function.identity()));
    }

    @Override
    public Optional<FlowPointer<Command>> nextPointer(FlowPointer<Command> current, FlowAdvice advice) {
        val controller = flowAdviceControllers.get(advice);
        return controller != null
                ? controller.move(current)
                : Optional.empty();
    }

}
