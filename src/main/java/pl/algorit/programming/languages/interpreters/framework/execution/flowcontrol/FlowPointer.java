package pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class FlowPointer<T> {

    private static final int MIN_INDEX = 0;

    private final ImmutableList<T> flow;

    @NonNull
    private final int pointer;

    public T getObject() {
        return flow.get(pointer);
    }

    public FlowPointer<T> next() {
        if (hasNext()) {
            return new FlowPointer<T>(flow, nextIndex());
        }
        throw new IndexOutOfBoundsException();
    }

    public FlowPointer<T> previous() {
        if (hasPrevious()) {
            return new FlowPointer<T>(flow, previousIndex());
        }
        throw new IndexOutOfBoundsException();
    }

    public boolean hasNext() {
        return nextIndex() <= maxIndex();
    }

    public boolean hasPrevious() {
        return previousIndex() >= MIN_INDEX;
    }

    private int nextIndex() {
        return pointer + 1;
    }

    private int previousIndex() {
        return pointer - 1;
    }

    private int maxIndex() {
        return flow.size() - 1;
    }

    @VisibleForTesting
    int getPointer() {
        return pointer;
    }

    @VisibleForTesting
    List<T> getFlow() {
        return flow;
    }

    public static <T> FlowPointer<T> first(Collection<T> flow) {
        Preconditions.checkArgument(flow != null, "flow can't be null");
        Preconditions.checkArgument(!flow.isEmpty(), "No elements found");

        return new FlowPointer<T>(ImmutableList.copyOf(flow), 0);
    }

    public static <T> FlowPointer<T> last(Collection<T> flow) {
        Preconditions.checkArgument(flow != null, "flow can't be null");
        Preconditions.checkArgument(!flow.isEmpty(), "No elements found");
        return new FlowPointer<T>(ImmutableList.copyOf(flow), flow.size() - 1);
    }


}
