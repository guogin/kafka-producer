# Kafka Producer
## Prerequisites
* JDK 11
* Apache Kafka 3.1.0

## How to Build
Clone the code and then run:
```shell
$ ./gradlew build
```

## How to Run
First start ZooKeeper service:
```shell
# Start the ZooKeeper service
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```
Next, start Kafka broker service:
```shell
$ bin/kafka-server-start.sh config/server.properties
```
Then, run this application:
```shell
$ ./gradlew bootRun
```

## How to Send Messages
Use an http client and send message like below:
```http request
POST http://localhost:8080/api/send
Accept: application/json
Content-Type: text/plain

hello, this is a test message
```
If you have cURL you could do this in terminal:
```shell
$ curl -X POST --location "http://localhost:8080/api/send" \
    -H "Accept: application/json" \
    -H "Content-Type: text/plain" \
    -d "hello, this is a test message"
```
