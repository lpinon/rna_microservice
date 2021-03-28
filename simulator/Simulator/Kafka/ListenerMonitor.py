from Simulator.Kafka.KafkaClient import KafkaClient


class KafkaListenerMonitor:
    @staticmethod
    def listen_clean_gens():
        for gen in KafkaClient.consume_kafka_clean_gens():
            yield gen


if __name__ == '__main__':
    for gen in KafkaListenerMonitor.listen_clean_gens():
        print(gen)
