package pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol;

import java.util.Optional;

public interface FlowPointerController<T> {

    Optional<FlowPointer<T>> move(FlowPointer<T> current);

}
