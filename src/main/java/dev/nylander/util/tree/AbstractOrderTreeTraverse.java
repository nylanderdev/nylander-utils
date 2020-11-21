package dev.nylander.util.tree;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Stack;

abstract class AbstractOrderTreeTraverser<NODE extends Node<NODE>> implements TreeTraverser<NODE> {
    private final TraversalStateIncrementer incrementer;
    private final NODE root;
    private final Stack<NodeStatePair<NODE>> traversalStack = new Stack<>();
    private NODE next = null;

    public AbstractOrderTreeTraverser(NODE root, TraversalStateIncrementer incrementer) {
        this.root = root;
        this.incrementer = incrementer;
        setupStack();
    }

    @Override
    public final boolean hasNext() {
        traverseUntilNext();
        return next != null;
    }

    @Override
    public final NODE next() {
        traverseUntilNext();
        if (next == null)
            throw new NoSuchElementException();
        return popNext();
    }

    private void setupStack() {
        traversalStack.push(newNodeStatePair(root));
    }

    private NodeStatePair<NODE> newNodeStatePair(NODE node) {
        return new NodeStatePair<>(node, incrementer.initial());
    }

    private void traverseUntilNext() {
        while (next == null && !traversalStack.isEmpty()) {
            NodeStatePair<NODE> current = traversalStack.peek();
            TraversalState currentState = current.state();
            current.setState(incrementer.increment(currentState));
            handleState(currentState);
        }
    }

    private void handleState(TraversalState state) {
        NodeStatePair<NODE> current = traversalStack.peek();
        switch (state) {
            case LEFT:
                Optional<NODE> optionalLeft = current.node().getLeft();
                pushAsNodeStatePair(optionalLeft);
                break;
            case RIGHT:
                Optional<NODE> optionalRight = current.node().getRight();
                pushAsNodeStatePair(optionalRight);
                break;
            case SELF:
                next = current.node();
                break;
            case POP:
                traversalStack.pop();
                break;
        }
    }

    private void pushAsNodeStatePair(Optional<NODE> possibleNode) {
        possibleNode.ifPresent(node -> traversalStack.push(newNodeStatePair(node)));
    }

    private NODE popNext() {
        NODE poppedNext = next;
        next = null;
        return poppedNext;
    }

    private static class NodeStatePair<NODE extends Node<NODE>> {
        private final NODE node;
        private TraversalState state;

        NodeStatePair(NODE node, TraversalState initialState) {
            this.node = node;
            this.state = initialState;
        }

        public void setState(TraversalState state) {
            this.state = state;
        }

        public TraversalState state() {
            return state;
        }

        public NODE node() {
            return node;
        }

    }
}

