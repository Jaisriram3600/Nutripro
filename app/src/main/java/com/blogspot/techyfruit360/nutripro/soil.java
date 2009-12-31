package com.blogspot.techyfruit360.nutripro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class soil extends AppCompatActivity {
    MqttHelper mqttHelper;
    TextView dataReceived,data,tv5,tv6,tv7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil);
        startMqtt();
    }
    private void startMqtt() {
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                if(topic.equals("SoilR"))
                { //Log.w("Mois", mqttMessage.toString());
                    dataReceived=(TextView)findViewById(R.id.textView);
                    tv5=(TextView)findViewById(R.id.textView5);


                    dataReceived.setText(mqttMessage.toString());
                    final String Red =(dataReceived.getText().toString()) ;//data


                    tv5.setText(Red);

                }
               else if(topic.equals("SoilG"))
                { //Log.w("Mois", mqttMessage.toString());
                    dataReceived=(TextView)findViewById(R.id.textView);
                    dataReceived.setText(mqttMessage.toString());
                    final String Green =(dataReceived.getText().toString()) ;//data
                    tv6=(TextView)findViewById(R.id.textView6);
                   tv6.setText(Green);

                }
               else if(topic.equals("SoilB"))
                { //Log.w("Mois", mqttMessage.toString());
                    dataReceived=(TextView)findViewById(R.id.textView);
                    dataReceived.setText(mqttMessage.toString());
                    final String Blue =(dataReceived.getText().toString()) ;//data

                    tv7 = (TextView) findViewById(R.id.textView7);
                    tv7.setText(Blue);

                }}

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
}


