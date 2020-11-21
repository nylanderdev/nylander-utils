package dev.nylander.util.tree;

interface DataNode<DATATYPE> extends Node<DataNode<DATATYPE>> {
    DATATYPE getData();
}
