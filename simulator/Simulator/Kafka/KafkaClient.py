import json
from kafka import KafkaProducer, KafkaConsumer

KAFKA_BOOTSTRAP_SERVERS = ['localhost:9092']

KAFKA_TOPIC = 'rawsamples'

kafka_producer = KafkaProducer(
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVERS,
    client_id="raw-producer",
    max_request_size=2147483647
)

KAFKA_RESULTS_TOPIC = 'cleangens'

kafka_consumer = KafkaConsumer(KAFKA_RESULTS_TOPIC, bootstrap_servers=KAFKA_BOOTSTRAP_SERVERS)


class KafkaClient:
    @staticmethod
    def consume_kafka_clean_gens():
        for message in kafka_consumer:
            message = message.value.decode('utf-8')
            data = json.loads(message)
            yield data

    @staticmethod
    def send_str_to_decoder(input_string, session_id):
        data_to_send = json.dumps({"data": input_string, "id": session_id}).encode('utf-8')
        kafka_producer.send(KAFKA_TOPIC, value=data_to_send)
        print(data_to_send)

    @staticmethod
    def send_end_of_stream(session_id):
        data_to_send = json.dumps({"data": "END_OF_STREAM", "id": session_id}).encode('utf-8')
        kafka_producer.send(KAFKA_TOPIC, value=data_to_send)
        print(data_to_send)
