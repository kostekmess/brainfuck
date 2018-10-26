package pl.algorit.programming.languages.interpreters.brainfuck.memory;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class BrainFuckMemoryTest {

    private static final short TEST_BUFFER_SIZE = Short.MAX_VALUE;

    private static final byte FIRST_ASCII_VALUE = 0;
    private static final byte LAST_ASCII_VALUE = Byte.MAX_VALUE;

    private BrainFuckMemory objectUnderTest;

    @Test
    void increment_once_increaseValueAt0indexTo1() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        objectUnderTest.increment();

        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(1)
                .atIndex(0)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void increment_twice_increaseValueAt0indexTo2() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        objectUnderTest.increment();
        objectUnderTest.increment();

        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(2)
                .atIndex(0)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void increment_maxTimes_increaseValueAt0indexToAllowedMAX() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        IntStream.range(0, LAST_ASCII_VALUE).forEach(i -> objectUnderTest.increment());

        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(LAST_ASCII_VALUE)
                .atIndex(0)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void increment_moreThanMax_increaseValueAt0indexToAllowedMAX() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        val moreThanMax = LAST_ASCII_VALUE + 321;
        IntStream.range(0, moreThanMax).forEach(i -> objectUnderTest.increment());

        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(LAST_ASCII_VALUE)
                .atIndex(0)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void increment_onceAfterPointerWasMoved_increaseValueAt1indexTo1() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE, 1);

        objectUnderTest.increment();

        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(1)
                .atIndex(1)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void increment_once_doesNotChangePointerIndex() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        objectUnderTest.increment();

        val expected = 0;

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(expected);
    }

    @Test
    void decrement_once_decreaseValueAt0indexTo1() {
        val initialState = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(2)
                .atIndex(0)
                .build();

        objectUnderTest = new BrainFuckMemory(initialState);

        objectUnderTest.decrement();

        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(1)
                .atIndex(0)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void decrement_twice_decreaseValueAt0indexTo2() {
        val initialState = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(4)
                .atIndex(0)
                .build();

        objectUnderTest = new BrainFuckMemory(initialState);

        objectUnderTest.decrement();
        objectUnderTest.decrement();

        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(2)
                .atIndex(0)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void decrement_maxTimes_decreaseValueAt0indexToAllowedMIN() {
        val initialState = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(LAST_ASCII_VALUE)
                .atIndex(0)
                .build();

        objectUnderTest = new BrainFuckMemory(initialState);

        IntStream.range(0, LAST_ASCII_VALUE).forEach(i -> objectUnderTest.decrement());

        val allowedMin = FIRST_ASCII_VALUE;
        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(allowedMin)
                .atIndex(0)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void decrement_moreThanMax_decreaseValueAt0indexToAllowedMIN() {
        val initialState = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(LAST_ASCII_VALUE)
                .atIndex(0)
                .build();

        objectUnderTest = new BrainFuckMemory(initialState);

        final int moreThanMaxInvocations = LAST_ASCII_VALUE + 321;
        IntStream.range(0, moreThanMaxInvocations).forEach(i -> objectUnderTest.decrement());

        val allowedMin = FIRST_ASCII_VALUE;
        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(allowedMin)
                .atIndex(0)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void decrement_onceAfterPointerWasMoved_decreaseValueAt1indexTo1() {
        val index1 = 1;
        val initialState = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(2)
                .atIndex(index1)
                .build();

        objectUnderTest = new BrainFuckMemory(initialState, index1);

        objectUnderTest.decrement();

        val expected = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(1)
                .atIndex(1)
                .build();

        Assertions.assertThat(objectUnderTest.getBytes()).isEqualTo(expected);
    }

    @Test
    void decrement_once_doesNotChangePointerIndex() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        objectUnderTest.decrement();

        val expected = 0;

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(expected);
    }

    @Test
    void movePointerLeft_from0indexMovedLeft_staysAt0Index() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        objectUnderTest.movePointerLeft();

        val expected = 0;

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(expected);
    }

    @Test
    void movePointerLeft_from2indexMovedLeft_goesTo1index() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE, 2);

        objectUnderTest.movePointerLeft();

        val expected = 1;

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(expected);
    }

    @Test
    void movePointerLeft_fromLastIndexMovedLeftMoreTimesThanBufferSize_goesTo0index() {
        val lastIndex = TEST_BUFFER_SIZE - 1;
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE, lastIndex);

        IntStream.range(0, TEST_BUFFER_SIZE + 500).forEach(value -> objectUnderTest.movePointerLeft());
        objectUnderTest.movePointerLeft();

        val expected = 0;

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(expected);
    }

    @Test
    void movePointerRight_fromLastIndexMovedRight_staysAtLastIndex() {
        val lastIndex = TEST_BUFFER_SIZE - 1;
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE, lastIndex);

        objectUnderTest.movePointerRight();

        val expected = lastIndex;

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(expected);
    }

    @Test
    void movePointerRight_from0indexMovedRight_goesTo1Index() {
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        objectUnderTest.movePointerRight();

        val expected = 1;

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(expected);
    }

    @Test
    void movePointerRight_fromFirstIndexMovedRightMoreTimesThanBufferSize_goesToLastIndex() {
        val lastIndex = TEST_BUFFER_SIZE - 1;
        objectUnderTest = new BrainFuckMemory(TEST_BUFFER_SIZE);

        IntStream.range(0, TEST_BUFFER_SIZE + 500).forEach(value -> objectUnderTest.movePointerRight());

        val expected = lastIndex;

        Assertions.assertThat(objectUnderTest.getPointer()).isEqualTo(expected);
    }

    @Test
    void get_testValueAtTestIndex_returnsTestValueFromTestIndex() {
        val testIndex = 5;
        val testValue = 6;
        val initialState = ByteArrayBuilderUtil.builder(TEST_BUFFER_SIZE)
                .setVal(testValue)
                .atIndex(testIndex)
                .build();

        objectUnderTest = new BrainFuckMemory(initialState, testIndex);

        val result = objectUnderTest.get();

        val expected = (byte) testValue;

        Assertions.assertThat(result).isEqualTo(expected);
    }


}
