## Load balancer in Java

This repo is a simple approach to achieve load balancing technique in Java.

### Consistent Hashing [->](./consistent_hash/src/com/vau/consistent_hash)

[wiki reference](https://en.wikipedia.org/wiki/Consistent_hashing)

How to run

`Type the command inside the test folder`

```shell
java -cp ../outputs LoadBalancer.java
 ```

```java
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
```

```output
Server 1 : 127.0.0.4
Server 2 : 127.0.0.1
Server 3 : 127.0.0.4
Server 4 : 127.0.0.1
Server 5 : 127.0.0.1
Server 6 : 127.0.0.1
Server 7 : 127.0.0.4
Server 8 : 127.0.0.1
Server 9 : 127.0.0.1
 ```
