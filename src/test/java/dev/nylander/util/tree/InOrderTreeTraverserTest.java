package dev.nylander.util.tree;

import org.junit.Assert;
import org.junit.Test;

public class InOrderTreeTraverserTest {
    @Test
    public void inOrderTraversalFidelityTest1() {
        ArrayTree<Character> tree = new ArrayTree<>(new Character[]{
                'D', 'B', 'F', 'A', 'C', 'E', 'G'
        });
        InOrderTreeTraverser<DataNode<Character>> traverser = new InOrderTreeTraverser<>(tree.getRoot());
        StringBuilder stringBuilder = new StringBuilder();
        traverser.forEachRemaining(node -> stringBuilder.append(node.getData()));
        Assert.assertEquals("ABCDEFG", stringBuilder.toString());
    }
}
