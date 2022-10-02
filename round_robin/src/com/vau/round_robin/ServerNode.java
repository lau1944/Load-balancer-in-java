package com.vau.round_robin;

public class ServerNode implements INode {
    private String ip;
    private String port;

    public ServerNode(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public String getPort() {
        return port;
    }
}
