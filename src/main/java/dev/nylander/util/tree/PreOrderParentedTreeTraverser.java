package dev.nylander.util.tree;

public class PreOrderParentedTreeTraverser<N extends ParentedTreeNode<N>> extends AbstractParentedTreeTraverser<N> {
    @Override
    protected TraversalState incrementTraversalState(TraversalState state) {
        switch (state) {
            case ACTION:
                return TraversalState.BEFORE_LEFT;
            case BEFORE_LEFT:
                return TraversalState.BEFORE_RIGHT;
            case BEFORE_RIGHT:
                return TraversalState.BEFORE_PARENT;
            default:
                return TraversalState.UNDEFINED;
        }
    }

    @Override
    protected TraversalState defaultState() {
        return TraversalState.ACTION;
    }
}
