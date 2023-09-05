public interface BinaryOp {
    int apply(int i, int j);
}

class Adder implements BinaryOp {
    public int apply(int i, int j) {
        return i + j;
    }
}

class Multiplier implements BinaryOp {
    public int apply(int i, int j) {
        return i * j;
    }
}