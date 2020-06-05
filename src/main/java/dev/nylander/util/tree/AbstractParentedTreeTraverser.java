package dev.nylander.util.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class AbstractParentedTreeTraverser<N extends ParentedTreeNode<N>> implements ParentedTreeTraverser<N> {
    private TraversalMap<N> traversalStates = createTraversalMap();

    @Override
    public final void traverseAll(N node, Consumer<N> action) {
        while (node != null) {
            node = traverse(node, action);
        }
    }

    @Override
    public final void traverseAll(N node, BiConsumer<N, TraversalState> action) {
        while (node != null) {
            node = traverse(node, action);
        }
    }

    @Override
    public final N traverse(N node, Consumer<N> action) {
        return traverse(node, (n, s) -> {
            if (s == TraversalState.ACTION)
                action.accept(n);
        });
    }

    @Override
    public final N traverse(N node, BiConsumer<N, TraversalState> action) {
        N nextNode = null;
        switch (traversalStates.getState(node)) {
            case BEFORE_LEFT:
                nextNode = nodeOrDefaultIfNull(node.getLeftChild(), node);
                break;
            case BEFORE_RIGHT:
                nextNode = nodeOrDefaultIfNull(node.getRightChild(), node);
                break;
            case BEFORE_PARENT:
                nextNode = node.getParent();
                break;
            case ACTION:
                nextNode = node;
                break;
        }
        TraversalState currentState = traversalStates.getState(node);
        action.accept(node, currentState);
        traversalStates.setState(node, incrementTraversalState(currentState));
        return nextNode;
    }

    @Override
    public final void reset() {
        traversalStates = createTraversalMap();
    }

    private N nodeOrDefaultIfNull(N node, N fallback) {
        return node == null ? fallback : node;
    }

    private TraversalMap<N> createTraversalMap() {
        return new TraversalMap<N>(defaultState());
    }

    protected abstract TraversalState incrementTraversalState(TraversalState state);

    protected abstract TraversalState defaultState();

    private static final class TraversalMap<E> {
        private Map<E, TraversalState> internalMap;
        private final TraversalState initialState;

        {
            internalMap = new HashMap<E, TraversalState>();
        }

        TraversalMap(TraversalState initialState) {
            this.initialState = initialState;
        }

        TraversalState getState(E element) {
            return internalMap.getOrDefault(element, initialState);
        }

        void setState(E element, TraversalState state) {
            internalMap.put(element, state);
        }
    }
}


