package dev.nylander.util.tree;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface ParentedTreeTraverser<N extends ParentedTreeNode<N>> {
    enum TraversalState {BEFORE_LEFT, BEFORE_RIGHT, BEFORE_PARENT, ACTION, UNDEFINED}

    void traverseAll(N node, Consumer<N> action);

    void traverseAll(N node, BiConsumer<N, TraversalState> action);

    N traverse(N node, Consumer<N> action);

    N traverse(N node, BiConsumer<N, TraversalState> action);

    void reset();
}
