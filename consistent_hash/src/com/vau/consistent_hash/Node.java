package com.vau.consistent_hash;

/**
 * Template class for node
 */
public class Node implements INode {
    private String ip;
    private String port;
    private String key;

    public Node(String ip) {
        this(ip, "");
    }

    public Node(String ip, String port) {
        assert ip != null;

        this.ip = ip;
        this.port = port;

        String key = this.ip + this.port;
        setKey(key);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getKey() {
        return this.key;
    }

    public String getIp() { return this.ip; }

    public String getPort() { return this.port; }
}