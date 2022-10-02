# Load balancer in Java

This repo is a simple approach to achieve load balancing technique in Java.

## Reference

[Consistent Hash](#consistent-hashing)

[Round robins](#round-robin)

### Consistent Hashing

[navigation](./consistent_hash/src/com/vau/consistent_hash)

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

### Round robin 

[navigation](./round_robin/src/com/vau/round_robin)

[wiki reference](https://www.nginx.com/resources/glossary/round-robin-load-balancing/#:~:text=What%20Is%20Round%2DRobin%20Load,to%20each%20server%20in%20turn.)

How to run

`Type the command inside the test folder`

```shell
java -cp ../outputs LoadBalancer.java
 ```

```java
public final class LoadBalancer {
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
```

```output
Server 4 now is: 127.0.0.1
Server 9 now is: 127.0.0.2
Server 8 now is: 127.0.0.1
Server 2 now is: 127.0.0.3
Server 5 now is: 127.0.0.2
Server 3 now is: 127.0.0.4
Server 7 now is: 127.0.0.4
Server 0 now is: 127.0.0.1
Server 6 now is: 127.0.0.3
Server 10 now is: 127.0.0.3
Server 11 now is: 127.0.0.4
Server 1 now is: 127.0.0.2

 ```

