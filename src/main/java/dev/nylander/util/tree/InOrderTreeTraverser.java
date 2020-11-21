package dev.nylander.util.tree;

public class InOrderTreeTraverser<NODE extends Node<NODE>> extends AbstractOrderTreeTraverse<NODE> {
    public InOrderTreeTraverser(NODE root) {
        super(root, new InOrderTraversalStateIncrementer());
    }
}

class InOrderTraversalStateIncrementer implements TraversalStateIncrementer {
    @Override
    public TraversalState initial() {
        return TraversalState.LEFT;
    }

    @Override
    public TraversalState increment(TraversalState state) {
        switch (state) {
            case LEFT:
                return TraversalState.SELF;
            case SELF:
                return TraversalState.RIGHT;
            case RIGHT:
            default:
                return TraversalState.POP;
        }
    }

}