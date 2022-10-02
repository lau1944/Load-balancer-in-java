package com.vau.consistent_hash;

import java.util.*;

/**
 * Consistent hash table
 */
public final class RouteTable<T extends INode> {
    private final SortedMap<Long, T> ring = new TreeMap<>();
    private IHash hashFunc;

    public RouteTable() {
        this(Collections.emptyList(), new Md5Hash());
    }

    public RouteTable(Collection<T> nodes) {
        this(nodes, new Md5Hash());
    }

    public RouteTable(Collection<T> nodes, IHash hashFunc) {
        this.hashFunc = hashFunc;

        initRing(nodes);
    }

    public INode getNode(String key) {
        assert key != null;

        long hashed = this.hashFunc.doHash(key);
        SortedMap<Long, T> tailMap = ring.tailMap(hashed);
        long hashVal = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        INode node = ring.get(hashVal);
        return node instanceof VirtualNode ? ((VirtualNode) node).getNode() : node;
    }

    public void addNode(T node) {
        assert node != null;

        String key = node.getKey();
        long hashed = this.hashFunc.doHash(key);
        if (ring.containsKey(hashed)) {
            throw new IllegalStateException("Contains duplicate key");
        }
        ring.put(hashed, node);
    }

    public T removeNode(String key) {
        assert key != null;

        if (!ring.containsKey(key)) {
            throw new IllegalArgumentException("Does not contain value related to this key");
        }

        return ring.remove(key);
    }

    private void initRing(Collection<T> nodes) {
        if (Objects.isNull(nodes)) { throw new NullPointerException(); }

        for (INode node: nodes) {
            addNode((T) node);
        }
    }
}
