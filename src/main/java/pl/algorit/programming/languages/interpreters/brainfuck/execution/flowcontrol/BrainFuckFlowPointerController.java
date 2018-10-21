package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointerController;

public interface BrainFuckFlowPointerController extends FlowPointerController<Command> {

    FlowAdvice getAdvice();

}
