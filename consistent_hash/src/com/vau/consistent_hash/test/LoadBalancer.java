package com.vau.consistent_hash.test;

import com.vau.consistent_hash.INode;
import com.vau.consistent_hash.Node;
import com.vau.consistent_hash.RouteTable;
import com.vau.consistent_hash.VirtualNode;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class LoadBalancer {
    public static void main(String[] args) {
        Node node = new Node("127.0.0.1", "80");
        List<INode> nodes = Arrays.asList(node, new Node("127.0.0.2", "80"),
                new Node("127.0.0.3", "80"), new Node("127.0.0.4", "80"),
                new VirtualNode(node, 0),
                new VirtualNode(node, 1),
                new VirtualNode(node, 2),
                new VirtualNode(node, 3));
        RouteTable<INode> table = new RouteTable<>(nodes);

        ExecutorService executor = Executors.newFixedThreadPool(15);
        // get random server
        for (int i = 0; i < 25; ++i) {
            if (i == 15) {
                table.removeNode(node);
            }
            executor.submit(new Runner(i, table));
        }
        executor.shutdown();
    }

    static class Runner implements Runnable {
        private int index;
        private RouteTable<INode> table;

        public Runner(int index, RouteTable<INode> table) {
            this.index = index;
            this.table = table;
        }

        @Override
        public void run() {
            System.out.println("Server " + index + " : " + table.getNode("192.1.4." + index).getIp());
        }
    }
}
