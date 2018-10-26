package pl.algorit.programming.languages.interpreters.framework.execution.screenusage;

import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;
import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

public interface ScreenWriterUser {

    void useScreen(MemoryReader memoryReader, ScreenWriter screenWriter);
}
