package mqtt_client;

import com.amazonaws.services.iot.client.*;

public class JavaDemoMQTTV3 {

    private static final String CLIENT_ID = "";
    private static final String AWS_IOT_ENDPOINT = "";

    private static final String A_KEY = "";
    private static final String SECURITY_KEY = "";

    public static void main(String[] args) throws AWSIotException, InterruptedException {

        // Create connection to AWS IoT
        AWSIotMqttClient client = new AWSIotMqttClient(
                AWS_IOT_ENDPOINT,
                CLIENT_ID,
                A_KEY,
                SECURITY_KEY
        );

        // Connect to AWS IoT
        client.connect();
        System.out.println("Connected to AWS IoT Core");

        // Subscribe to a topic
        String topic = "iot/topic";
        AWSIotTopic topicListener = new AWSIotTopic(topic, AWSIotQos.QOS0) {
            @Override
            public void onMessage(AWSIotMessage message) {
                System.out.println("Received: " + message.getStringPayload());
            }
        };
        client.subscribe(topicListener);
        System.out.println("Subscribed to topic: " + topic);

        // Publish a test message
        AWSIotMessage message = new AWSIotMessage(topic, AWSIotQos.QOS0, "Hello from Java MQTT!");
        client.publish(message);
        System.out.println("Message published");

        // Keep the connection open
        Thread.sleep(5000);
        client.disconnect();
    }
}
