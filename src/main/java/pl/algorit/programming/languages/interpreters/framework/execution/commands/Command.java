package pl.algorit.programming.languages.interpreters.framework.execution.commands;

import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdvice;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowAdviser;
import pl.algorit.programming.languages.interpreters.framework.execution.memorymodification.MemoryModifier;
import pl.algorit.programming.languages.interpreters.framework.execution.screenusage.ScreenWriterUser;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

public interface Command extends MemoryModifier, FlowAdviser, ScreenWriterUser {

    char getSymbol();

    @Override
    default void modifyMemory(Memory memory) {
        throw new IllegalStateException();
    }

    @Override
    default FlowAdvice adviceOnFlow(MemoryReader memoryReader) {
        throw new IllegalStateException();
    }

    @Override
    default void useScreen(MemoryReader memoryReader, ScreenWriter screenWriter) {
        throw new IllegalStateException();
    }
}
