package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

import static pl.algorit.programming.languages.interpreters.brainfuck.CommandsBuildUtils.getCommands;

public class JumpToLoopStartFlowPointerControllerTest {

    private JumpToLoopStartFlowPointerController objectUnderTest;

    @Test
    public void move_iteratorOnTheLoopEnd1_movesIteratorToTheLoopStart() {
        String givenCommand = "[]";
        int expectedLoopStartIndex = 0;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd2_movesIteratorToTheLoopStart() {
        String givenCommand = "[+]";
        int expectedLoopStartIndex = 0;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd3_movesIteratorToTheLoopStart() {
        String givenCommand = "+++[-.<>.+]";
        int expectedLoopStartIndex = 3;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd4_movesIteratorToTheLoopStart() {
        String givenCommand = "[[]]";
        int expectedLoopStartIndex = 0;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd5_movesIteratorToTheLoopStart() {
        String givenCommand = "[[+-+<>]]";
        int expectedLoopStartIndex = 0;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd6_movesIteratorToTheLoopStart() {
        String givenCommand = "[[+-+<>][]]";
        int expectedLoopStartIndex = 0;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd7_movesIteratorToTheLoopStart() {
        String givenCommand = "[[+-+<>][R]]";
        int expectedLoopStartIndex = 0;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd8_movesIteratorToTheLoopStart() {
        String givenCommand = "--[[+-+<>][R][---]]";
        int expectedLoopStartIndex = 2;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd9_movesIteratorToTheLoopStart() {
        String givenCommand = "--[[+-";
        int expectedLoopStartIndex = 3;
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd10_movesIteratorToTheLoopStart() {
        int expectedLoopStartIndex = 2;
        String givenCommand = "--[[[[asdadsas[a]aaaad[]]]]]";
        testJumpingFromTheEndToStart(givenCommand, expectedLoopStartIndex);
    }

    @Test
    public void move_iteratorOnTheLoopEnd11_doesntFindStart() {
        String givenCommand = "--]]+-";
        testStartNotFound(givenCommand);
    }

    @Test
    public void move_iteratorOnTheLoopEnd12_doesntFindStart() {
        String givenCommand = "]";
        testStartNotFound(givenCommand);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void move_null_throwsException() {
        //when
        val moved = objectUnderTest.move(null);

        //then
        Assertions.assertThat(moved.isPresent()).isFalse();
    }

    private void testStartNotFound(String givenCommand) {
        //given
        objectUnderTest = new JumpToLoopStartFlowPointerController();

        val list = getCommands(givenCommand);

        val end = FlowPointer.last(list);
        //when
        val moved = objectUnderTest.move(end);

        //then
        Assertions.assertThat(moved.isPresent()).isFalse();
    }

    private void testJumpingFromTheEndToStart(String givenCommand, int expectedLoopStartIndex) {
        //given
        objectUnderTest = new JumpToLoopStartFlowPointerController();

        val list = getCommands(givenCommand);
        val last = FlowPointer.last(list);

        //when
        val moved = objectUnderTest.move(last)
                .orElseThrow(() -> new IllegalStateException("move expected"));

        //then
        val sa = new SoftAssertions();
        sa.assertThat(moved.getObject().getSymbol()).isEqualTo('[');
        sa.assertThat(moved.getObject()).isEqualTo(list.get(expectedLoopStartIndex));
        sa.assertAll();
    }
}