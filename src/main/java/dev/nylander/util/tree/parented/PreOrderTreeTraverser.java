package dev.nylander.util.tree.parented;

public class PreOrderTreeTraverser<N extends TreeNode<N>> extends AbstractTreeTraverser<N> {
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
