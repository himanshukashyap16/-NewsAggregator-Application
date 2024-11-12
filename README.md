# NewsAggregator



Setting Up MongoDB on Ubuntu

Install MongoDB:

sudo apt update
sudo apt install -y mongodb-org

Start MongoDB:

sudo systemctl start mongod

Enable MongoDB to start on boot:

sudo systemctl enable mongod

Check MongoDB status:

sudo systemctl status mongod

Access MongoDB shell:

    mongosh

2. Setting Up Redis on Ubuntu

    Install Redis:

sudo apt update
sudo apt install redis-server

Configure Redis (optional):

sudo nano /etc/redis/redis.conf
# Set supervised to systemd
supervised systemd

Start Redis:

sudo systemctl start redis

Enable Redis to start on boot:

sudo systemctl enable redis

Check Redis status:

sudo systemctl status redis

Test Redis:

    redis-cli
    ping  # Should return PONG

3. Setting Up Kafka on Ubuntu

    Install Java (if not installed):

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

**Run the Project**

./gradlew bootRun

