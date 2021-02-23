Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
props.put("schema.registry.url", schemaUrl);
String topic = "customerContacts";
int wait = 500;
Producer<String, Customer> producer = new KafkaProducer<String, Customer>(props);

while (true) {
    Customer customer = CustomerGenerator.getNext();
    System.out.println("Generated customer " +
    customer.toString());
    ProducerRecord<String, Customer> record =
    new ProducerRecord<>(topic, customer.getId(), customer);
    producer.send(record);
}