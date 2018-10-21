package pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.framework.execution.flowcontrol.FlowPointer;

import static pl.algorit.programming.languages.interpreters.brainfuck.CommandsBuildUtils.getCommands;

public class JumpToLoopEndFlowPointerControllerTest {

    private JumpToLoopEndFlowPointerController objectUnderTest;

    @Test
    public void move_iteratorInTheLoop1_movesIteratorToTheLoopEnd() {
        String givenCommand = "[]";
        int expectedLoopEndIndex = givenCommand.length() - 1;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop2_movesIteratorToTheLoopEnd() {
        String givenCommand = "[+]";
        int expectedLoopEndIndex = givenCommand.length() - 1;

        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop3_movesIteratorToTheLoopEnd() {
        String givenCommand = "[-.<>.+]+";
        int expectedLoopEndIndex = givenCommand.length() - 2;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop4_movesIteratorToTheLoopEnd() {
        String givenCommand = "[[]]";
        int expectedLoopEndIndex = givenCommand.length() - 1;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop5_movesIteratorToTheLoopEnd() {
        String givenCommand = "[[+-+<>]]";
        int expectedLoopEndIndex = givenCommand.length() - 1;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop6_movesIteratorToTheLoopEnd() {
        String givenCommand = "[[+-+<>][]]";
        int expectedLoopEndIndex = givenCommand.length() - 1;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop7_movesIteratorToTheLoopEnd() {
        String givenCommand = "[[+-+<>][R]]";
        int expectedLoopEndIndex = givenCommand.length() - 1;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop8_movesIteratorToTheLoopEnd() {
        String givenCommand = "[[+-+<>][R][---]]++";
        int expectedLoopEndIndex = givenCommand.length() - 3;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop9_movesIteratorToTheLoopEnd() {
        String givenCommand = "--]]+-";
        int expectedLoopEndIndex = 2;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop10_movesIteratorToTheLoopEnd() {
        String givenCommand = "[[[[asdadsas[a]aaaad[]]]]]--";
        int expectedLoopEndIndex = givenCommand.length() - 3;
        testJumpingFromTheStartToEnd(givenCommand, expectedLoopEndIndex);
    }

    @Test
    public void move_iteratorInTheLoop11_doesntFindEnd() {
        String givenCommand = "--[[+-";
        testEndNotFound(givenCommand);
    }

    @Test
    public void move_iteratorInTheLoop12_doesntFindEnd() {
        String givenCommand = "[";
        testEndNotFound(givenCommand);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void move_null_throwsException() {
        //when
        val moved = objectUnderTest.move(null);

        //then
        Assertions.assertThat(moved.isPresent()).isFalse();
    }

    private void testEndNotFound(String givenCommand) {
        //given
        objectUnderTest = new JumpToLoopEndFlowPointerController();

        val list = getCommands(givenCommand);
        val end = FlowPointer.first(list);

        //when
        val moved = objectUnderTest.move(end);

        //then
        Assertions.assertThat(moved.isPresent()).isFalse();
    }

    private void testJumpingFromTheStartToEnd(String givenCommand, int expectedLoopEndIndex) {
        //given
        objectUnderTest = new JumpToLoopEndFlowPointerController();

        val list = getCommands(givenCommand);
        val first = FlowPointer.first(list);

        //when
        val moved = objectUnderTest.move(first)
                .orElseThrow(() -> new IllegalStateException("move expected"));

        //then
        val sa = new SoftAssertions();
        sa.assertThat(moved.getObject().getSymbol()).isEqualTo(']');
        sa.assertThat(moved.getObject()).isEqualTo(list.get(expectedLoopEndIndex));
        sa.assertAll();
    }
}