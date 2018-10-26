package pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol;

import pl.algorit.programming.languages.interpreters.framework.memory.MemoryReader;

public interface FlowAdviser {
    FlowAdvice adviceOnFlow(MemoryReader memory);
}
