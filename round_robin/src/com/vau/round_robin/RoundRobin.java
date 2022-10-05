package com.vau.round_robin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class RoundRobin {
    private volatile int reqCount;
    private List<INode> nodes = new ArrayList<>();

    public RoundRobin() {
        this(0, Collections.emptyList());
    }

    public RoundRobin(Collection<INode> nodes) {
        this(0, nodes);
    }

    public RoundRobin(int startCount, Collection<INode> nodes) {
        this.reqCount = startCount;
        addToNode(nodes);
    }

    private void addToNode(Collection<INode> nodes) {
        assert nodes != null;

        for (INode node: nodes) {
            this.nodes.add(node);
        }
    }

    public synchronized void addNode(INode node) {
        assert node != null;

        if (this.nodes.contains(node) ) {
            throw new IllegalStateException("This node is already exists");
        }

        boolean isDuplicated = this.nodes.stream()
                .anyMatch(e -> e.getIp() == node.getIp() && e.getPort() == node.getPort());
        if (isDuplicated) {
            throw new IllegalStateException("Node is duplicated");
        }
        this.nodes.add(node);
    }

    public synchronized void removeNode(int index) {
        this.nodes.remove(index);
    }

    public List<INode> getNodes() {
        return this.nodes;
    }

    public synchronized INode getServer() {
        if (this.nodes.isEmpty()) {
            throw new IllegalStateException("Server list is empty");
        }
        int index = reqCount++ % this.nodes.size();
        return this.nodes.get(index);
    }
}
