package dev.nylander.util.tree.parented;

public interface TreeNode<N extends TreeNode<N>> {
    N getParent();
    N getLeftChild();
    N getRightChild();
}
