package pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol;

import java.util.Optional;

public interface FlowController<T> {

    Optional<FlowPointer<T>> nextPointer(FlowPointer<T> current, FlowAdvice advice);

}
