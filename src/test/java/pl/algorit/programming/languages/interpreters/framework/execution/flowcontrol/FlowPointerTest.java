package pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol;

import com.google.common.collect.ImmutableList;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class FlowPointerTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void first_null_throwsException() {
        FlowPointer.first(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void first_emptyList_throwsException() {
        FlowPointer.first(Collections.emptyList());
    }

    @Test
    public void first_singletonList_createsFlowPointerAtFirstIndexOnTheFlow() {
        val flow = Collections.singletonList("sas");
        val objectUnderTest = FlowPointer.first(flow);

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(0);
        Assertions.assertThat(objectUnderTest.getFlow()).isEqualTo(flow);
    }

    @Test
    public void first_twoElementList_createsFlowPointerAtFirstIndexOnTheFlow() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.first(flow);

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(0);
        Assertions.assertThat(objectUnderTest.getFlow()).isEqualTo(flow);
    }

    @Test
    public void first_mutableCollection_flowPointerHasOwnWorkingCopyOfTheGivenCollection() {
        val flow = new ArrayList<String>(ImmutableList.of("sas", "bas"));
        val objectUnderTest = FlowPointer.first(flow);
        flow.clear();

        Assertions.assertThat(objectUnderTest.next().getObject()).isEqualTo("bas");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void last_null_throwsException() {
        FlowPointer.last(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void last_emptyList_throwsException() {
        FlowPointer.last(Collections.emptyList());
    }

    @Test
    public void last_singletonList_createsFlowPointerAtLastIndexOnTheFlow() {
        val flow = Collections.singletonList("sas");
        val objectUnderTest = FlowPointer.last(flow);

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(0);
        Assertions.assertThat(objectUnderTest.getFlow()).isEqualTo(flow);
    }

    @Test
    public void last_twoElementList_createsFlowPointerAtLastIndexOnTheFlow() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.last(flow);

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(1);
        Assertions.assertThat(objectUnderTest.getFlow()).isEqualTo(flow);
    }

    @Test
    public void last_mutableCollection_flowPointerHasOwnWorkingCopyOfTheGivenCollection() {
        val flow = new ArrayList<String>(ImmutableList.of("sas", "bas"));
        val objectUnderTest = FlowPointer.last(flow);
        flow.clear();

        Assertions.assertThat(objectUnderTest.previous().getObject()).isEqualTo("sas");
    }

    @Test
    public void getObject_twoElements_returnObjectPointedByPointer() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.last(flow);

        val result = objectUnderTest.getObject();

        Assertions.assertThat(result).isEqualTo("bas");
    }

    @Test
    public void hasNext_pointerOnTheLastPosition_returnsFalse() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.last(flow);

        val result = objectUnderTest.hasNext();

        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void hasNext_pointerOnTheFirstPosition_returnsTrue() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.first(flow);

        val result = objectUnderTest.hasNext();

        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void hasNext_singletonList_returnsFalse() {
        val flow = ImmutableList.of("sas");
        val objectUnderTest = FlowPointer.last(flow);

        val result = objectUnderTest.hasNext();

        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void hasPrevious_pointerOnTheLastPosition_returnsTrue() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.last(flow);

        val result = objectUnderTest.hasPrevious();

        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void hasPrevious_pointerOnTheFirstPosition_returnsFalse() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.first(flow);

        val result = objectUnderTest.hasPrevious();

        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void hasPrevious_singletonList_returnsFalse() {
        val flow = ImmutableList.of("sas");
        val objectUnderTest = FlowPointer.last(flow);

        val result = objectUnderTest.hasPrevious();

        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void next_twoElementsAndCurrentPointerOnTheFirst_movesToLastElement() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.first(flow);

        val result = objectUnderTest.next();

        Assertions.assertThat(result).isEqualTo(FlowPointer.last(flow));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void next_twoElementsAndCurrentPointerOnTheLast_throwsException() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.last(flow);

        objectUnderTest.next();
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void next_singletonList_throwsException() {
        val flow = ImmutableList.of("bas");
        val objectUnderTest = FlowPointer.first(flow);

        objectUnderTest.next();
    }


    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void previous_twoElementsAndCurrentPointerOnTheFirst_throwsException() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.first(flow);

        objectUnderTest.previous();
    }

    @Test
    public void previous_twoElementsAndCurrentPointerOnTheLast_movesToFirstElement() {
        val flow = ImmutableList.of("sas", "bas");
        val objectUnderTest = FlowPointer.last(flow);

        val result = objectUnderTest.previous();

        Assertions.assertThat(result).isEqualTo(FlowPointer.first(flow));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void previous_singletonList_throwsException() {
        val flow = ImmutableList.of("sas");
        val objectUnderTest = FlowPointer.first(flow);

        objectUnderTest.previous();
    }

}