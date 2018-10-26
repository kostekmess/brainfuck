package pl.algorit.programming.languages.interpreters.brainfuck.memory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import pl.algorit.programming.languages.interpreters.framework.memory.Memory;

public class BrainFuckMemory implements Memory {

    private static final int MIN_PTR = 0;
    private static final int MIN_VAL = 0;
    private static final byte MAX_VAL = Byte.MAX_VALUE;
    private byte[] bytes;
    private int pointer;

    public BrainFuckMemory(short size) {
        Preconditions.checkArgument(size > 0, "size must be > 0");
        bytes = new byte[size];
    }

    @VisibleForTesting
    BrainFuckMemory(short size, int pointer) {
        this(size);
        this.pointer = pointer;
    }

    @VisibleForTesting
    BrainFuckMemory(byte[] bytes, int pointer) {
        this.bytes = bytes;
        this.pointer = pointer;
    }

    @VisibleForTesting
    BrainFuckMemory(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void increment() {
        if (bytes[pointer] < MAX_VAL)
            bytes[pointer]++;
    }

    @Override
    public void decrement() {
        if (bytes[pointer] > MIN_VAL)
            bytes[pointer]--;
    }

    @Override
    public void movePointerRight() {
        if (pointer < bytes.length - 1)
            pointer++;
    }

    @Override
    public void movePointerLeft() {
        if (pointer > MIN_PTR)
            pointer--;
    }

    @Override
    public byte get() {
        return bytes[pointer];
    }

    @VisibleForTesting
    byte[] getBytes() {
        return bytes;
    }

    @VisibleForTesting
    int getPointer() {
        return pointer;
    }

}
