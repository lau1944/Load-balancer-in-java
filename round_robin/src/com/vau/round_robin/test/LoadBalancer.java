package com.vau.round_robin.test;

import com.vau.round_robin.INode;
import com.vau.round_robin.RoundRobin;
import com.vau.round_robin.ServerNode;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadBalancer {
    public static void main(String[] args) {
        List<INode> nodes = Arrays.asList(new ServerNode("127.0.0.1", "80"), new ServerNode("127.0.0.2", "80"),
                new ServerNode("127.0.0.3", "80"), new ServerNode("127.0.0.4", "80"));
        RoundRobin roundRobin = new RoundRobin(0, nodes);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 12; ++i) {
            executorService.submit(new Request(roundRobin, i));
        }
        executorService.shutdown();
    }

    static class Request implements Runnable {
        private RoundRobin robin;
        private int index;

        public Request(RoundRobin robin, int index) {
            this.robin = robin;
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("Server " + index + " now is: " + robin.getServer().getIp());
        }
    }
}
