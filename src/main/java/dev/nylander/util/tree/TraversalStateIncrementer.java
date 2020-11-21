package dev.nylander.util.tree;

interface TraversalStateIncrementer {
    TraversalState initial();
    TraversalState increment(TraversalState state);
}
