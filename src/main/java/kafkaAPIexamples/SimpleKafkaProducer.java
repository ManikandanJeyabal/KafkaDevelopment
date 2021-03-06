package kafkaAPIexamples;

import kafka.common.KafkaException;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class SimpleKafkaProducer {
    // Topic Name Available at Producer level - Must be private
    private static final String topic = "SimpleKafkaProducerExample";

    // Run methods to trigger the Job
    private static void run() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            for (int i = 0; i <= 100; i++) {
                RecordMetadata rmData = producer.send(new ProducerRecord<>(topic, Integer.toString(i), Integer.toString(i))).get();
                System.out.printf("record %s Inserted to Topic Successfully\n", i);
                System.out.println("Partition No: " + rmData.partition() + " Offset Info: " + rmData.offset());
            }
        } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        producer.close();
    }

    public static void main(String[] args){
        // Object creation
        SimpleKafkaProducer producer = new SimpleKafkaProducer();
        try{
            producer.run(); //calling the Run method to Execute the Code
        } catch (KafkaException e){
            System.out.println(e.getMessage()); // Get the Exception and log in Screen.
        }
    }

}
