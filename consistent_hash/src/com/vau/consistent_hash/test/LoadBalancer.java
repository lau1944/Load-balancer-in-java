package com.vau.consistent_hash.test;

import com.vau.consistent_hash.INode;
import com.vau.consistent_hash.Node;
import com.vau.consistent_hash.RouteTable;

import java.util.List;

public final class LoadBalancer {
    public static final void main(String[] args) {
        List<INode> nodes = List.of(new INode[]{
            new Node("127.0.0.1", "80"), new Node("127.0.0.2", "80"),
                new Node("127.0.0.3", "80"), new Node("127.0.0.4", "80")

        });
        RouteTable table = new RouteTable(nodes);

        // get random server
        for (int i = 1; i < 10; ++i) {
            System.out.println("Server " + i + " : " + table.getNode("192.1.4." + i).getIp());
        }
    }
}
