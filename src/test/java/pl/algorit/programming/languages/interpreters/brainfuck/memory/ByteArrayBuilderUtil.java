package pl.algorit.programming.languages.interpreters.brainfuck.memory;

class ByteArrayBuilderUtil {
    private final byte[] bytes;
    private final ArrayValBuilderUtil arrayValBuilderUtil;
    private byte currentValue;

    private ByteArrayBuilderUtil(byte[] bytes) {
        this.bytes = bytes;
        this.arrayValBuilderUtil = new ArrayValBuilderUtil();
    }

    ArrayValBuilderUtil setVal(int value) {
        currentValue = toByte(value);
        return arrayValBuilderUtil;
    }

    byte[] build() {
        return bytes;
    }

    private byte toByte(int i) {
        return (byte) i;
    }

    static ByteArrayBuilderUtil builder(short size) {
        return new ByteArrayBuilderUtil(new byte[size]);
    }

    class ArrayValBuilderUtil {
        ByteArrayBuilderUtil atIndex(int index) {
            bytes[index] = currentValue;
            return ByteArrayBuilderUtil.this;
        }
    }
}
