package dev.nylander.util.tree;

import java.util.Optional;

class ArrayTree<T> {
    private final T[] array;

    public ArrayTree(T[] array) {
        this.array = array;
    }

    DataNode<T> getRoot() {
        return new ArrayNode(0);
    }

    private class ArrayNode implements DataNode<T> {
        private final int index;

        private ArrayNode(int index) {
            this.index = index;
        }

        @Override
        public T getData() {
            return array[index];
        }

        @Override
        public Optional<DataNode<T>> getLeft() {
            int leftIndex = 2 * (index + 1) - 1;
            if (leftIndex >= array.length || array[leftIndex] == null)
                return Optional.empty();
            else return Optional.of(new ArrayNode(leftIndex));
        }

        @Override
        public Optional<DataNode<T>> getRight() {
            int rightIndex = 2 * (index + 1);
            if (rightIndex >= array.length || array[rightIndex] == null)
                return Optional.empty();
            else return Optional.of(new ArrayNode(rightIndex));
        }

    }
}
