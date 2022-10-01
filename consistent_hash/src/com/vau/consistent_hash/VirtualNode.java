package com.vau.consistent_hash;

public class VirtualNode implements INode {
    private Node physicalNode;
    private String key;
    private int virtualIndex;

    public VirtualNode(Node physicalNode, int virtualIndex) {
        this.physicalNode = physicalNode;
        this.virtualIndex = virtualIndex;
        this.key = virtualIndex + "-" + physicalNode.getIp() + physicalNode.getPort();
    }

    public Node getNode() {
        return this.physicalNode;
    }

    @Override
    public String getIp() {
        return this.physicalNode.getIp();
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getPort() {
        return this.physicalNode.getPort();
    }
}
