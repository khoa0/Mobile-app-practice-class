package com.example.myapp;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;

public class spot_status extends AppCompatActivity {
    ListView list;

    View[] spot = new View[4];

    TextView[] slot = new TextView[4];

    final String host = "c99974ff8b7343ea95758f4c6900543b.s1.eu.hivemq.cloud";
    final String username = "20521472";
    final String password = "Khoa20521472";

    // create an MQTT client
    final Mqtt5BlockingClient client = MqttClient.builder()
            .useMqttVersion5()
            .serverHost(host)
            .serverPort(8883)
            .sslWithDefaultConfig()
            .buildBlocking();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.spot_status_layout);

        spot[0] = findViewById(R.id.spot1);
        spot[1] = findViewById(R.id.spot2);
        spot[2] = findViewById(R.id.spot3);
        spot[3] = findViewById(R.id.spot4);

        for (int i = 0 ; i < 4 ; i++)
        {
            slot[i] = spot[i].findViewById(R.id.spot_status);
            rewite_name_spot(i);
        }

        // connect to HiveMQ Cloud with TLS and username/pw
        client.connectWith()
                .simpleAuth()
                .username(username)
                .password(UTF_8.encode(password))
                .applySimpleAuth()
                .send();

        System.out.println("Connected successfully");

        // subscribe to the topic "my/test/topic"
        String topic = "car_spot/#" ;//+ Integer.toString(spot);
        client.subscribeWith()
                .topicFilter(topic)
                .send();

        // set a callback that is called when a message is received (using the async API style)
        client.toAsync().publishes(ALL, publish -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                System.out.println("Received message: " +
                        publish.getTopic() + " -> " +
                        UTF_8.decode(publish.getPayload().get()));
                update_spot(publish.getTopic().toString(), UTF_8.decode(publish.getPayload().get()).toString());
            }

            // disconnect the client after a message was received
            //client.disconnect();
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.disconnect();
    }

//    public void subcribe_message(int spot){
//        // subscribe to the topic "my/test/topic"
//        String topic = "car_spot/#" ;//+ Integer.toString(spot);
//        client.subscribeWith()
//                .topicFilter(topic)
//                .send();
//
//        // set a callback that is called when a message is received (using the async API style)
//        client.toAsync().publishes(ALL, publish -> {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                System.out.println("Received message: " +
//                        publish.getTopic() + " -> " +
//                        UTF_8.decode(publish.getPayload().get()));
//            }
//
//            // disconnect the client after a message was received
//            //client.disconnect();
//        });
//    }

    public void update_spot(final String topic, final String status) {
        String[] parts = topic.split("/");
        final int num = Integer.parseInt(parts[1]);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Integer.parseInt(status) == 1) {
                    spot[num].setBackgroundResource(R.color.green);
                } else {
                    spot[num].setBackgroundResource(R.color.red);
                }
                String name = "Spot " + parts[1] + " " + status;
                slot[num].setText(name);
            }
        });

    }

    public void rewite_name_spot(int spot) {
        String name = "Spot " + Integer.toString(spot);
        slot[spot].setText(name);
    }
}

