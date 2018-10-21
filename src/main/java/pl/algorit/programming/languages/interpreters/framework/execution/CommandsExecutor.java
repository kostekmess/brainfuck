package pl.algorit.programming.languages.interpreters.framework.execution;

import pl.algorit.programming.languages.interpreters.framework.execution.commands.Command;

import java.util.List;

public interface CommandsExecutor {

    void execute(List<Command> commands);
}
