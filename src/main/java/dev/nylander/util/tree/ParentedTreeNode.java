package dev.nylander.util.tree;

public interface ParentedTreeNode<N extends ParentedTreeNode<N>> {
    N getParent();
    N getLeftChild();
    N getRightChild();
}
