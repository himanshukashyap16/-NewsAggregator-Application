# NewsAggregator


# FrontEnd Repo

https://gitlab.com/himanshukashyap1122/frontend-for-newsaggregator


#  Tech Stack for News Aggregator
1. Backend (Server-side)
a. Java

    Reason: Used for implementing backend logic, managing Kafka producers/consumers, and handling HTTP requests and responses.

b. Spring Boot

    Reason: A framework that simplifies the creation of Java-based microservices. You used it to manage APIs and integrate with other components like MongoDB and Kafka.

c. Apache Kafka

    Reason: Used for real-time data streaming and messaging between components. Kafka manages the flow of news articles between producers, processing streams, and consumers.

    Kafka Topics:
        news-aggregator-input-topic: For input news data.
        news-aggregator-output-topic: For processed output.

d. MongoDB

    Reason: A NoSQL database used for storing processed news articles. It helps in efficiently storing and retrieving news data.

    Integration: MongoDB is used for storing processed news data and enabling efficient querying.

e. Redis

    Reason: Used for caching to speed up read operations and reduce load times. It helps with storing frequently accessed data like popular news articles or metadata.

    Integration: Caching the results of frequently searched news articles.



# Setting Up MongoDB on Ubuntu

##  Install MongoDB:

sudo apt update
sudo apt install -y mongodb-org

##  Start MongoDB:

sudo systemctl start mongod

##  Enable MongoDB to start on boot:

sudo systemctl enable mongod

##  Check MongoDB status:

sudo systemctl status mongod

## Access MongoDB shell:

    mongosh

# Setting Up Redis on Ubuntu

## Install Redis:

sudo apt update
sudo apt install redis-server

##  Configure Redis (optional):

sudo nano /etc/redis/redis.conf
## Set supervised to systemd
supervised systemd

Start Redis:

sudo systemctl start redis

Enable Redis to start on boot:

sudo systemctl enable redis

Check Redis status:

sudo systemctl status redis

## Test Redis:

    redis-cli
    ping  # Should return PONG

# Setting Up Kafka on Ubuntu

## Install Java (if not installed):

sudo apt install openjdk-11-jdk

Download and extract Kafka:

wget https://downloads.apache.org/kafka/{version}/kafka_{version}.tgz
tar -xzf kafka_{version}.tgz
cd kafka_{version}

Start Zookeeper:

bin/zookeeper-server-start.sh config/zookeeper.properties

Start Kafka Broker:

bin/kafka-server-start.sh config/server.properties

Create Topics:

bin/kafka-topics.sh --create --topic news-aggregator-input-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
bin/kafka-topics.sh --create --topic news-aggregator-output-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 
1

# Run the Project 

./gradlew bootRun

