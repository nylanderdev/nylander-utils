package dev.nylander.util.tree;

import java.util.Optional;

public interface Node<NODE extends Node<NODE>> {
    Optional<NODE> getLeft();
    Optional<NODE> getRight();
}
