package pl.algorit.programming.languages.interpreters.framework.execution;

import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowController;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GenericCommandsExecutor implements CommandsExecutor {

    private final Memory memory;
    private final ScreenWriter screenWriter;
    private final FlowController<Command> flowController;

    @Override
    public void execute(List<Command> commands) {
        process(commands);
    }

    private void process(final List<Command> commands) {
        if (commands.isEmpty()) {
            return;
        }

        var flowPointer = Optional.of(FlowPointer.first(commands));
        while (flowPointer.isPresent()) {
            flowPointer = nextFlowStep(flowPointer.get());
        }
    }

    private Optional<FlowPointer<Command>> nextFlowStep(FlowPointer<Command> flowPointer) {
        val command = flowPointer.getObject();

        command.modifyMemory(memory);
        command.useScreen(memory, screenWriter);

        val flowAdvice = command.adviceOnFlow(memory);
        return flowController.nextPointer(flowPointer, flowAdvice);
    }
}
