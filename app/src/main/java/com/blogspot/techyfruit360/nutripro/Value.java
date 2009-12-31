package com.blogspot.techyfruit360.nutripro;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class Value extends AppCompatActivity {
    MqttHelper mqttHelper;

    TextView dataReceived,dataReceived2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        dataReceived = (TextView) findViewById(R.id.tt);
        dataReceived2=(TextView)findViewById(R.id.th);
        startMqtt();


      /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/



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
                if(topic.equals("MMoist001"))
                {
                Log.w("Moist001", mqttMessage.toString());
                dataReceived.setText(mqttMessage.toString());
                final int new1 =Integer.parseInt(dataReceived.getText().toString()) ;//data


                final int[] pStatus = {0};
                 final Handler handler = new Handler();
                Resources res = getResources();
                Drawable drawable = res.getDrawable(R.drawable.circular);
                final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
                mProgress.setProgress(0);   // Main Progress
                mProgress.setSecondaryProgress(100); // Secondary Progress
                mProgress.setMax(100); // Maximum Progress
                mProgress.setProgressDrawable(drawable);

                final TextView tv;
                final TextView moist;

                tv = (TextView) findViewById(R.id.tv);
                moist=(TextView)findViewById(R.id.moist);

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        while (pStatus[0] < ((new1*100)/255)) {
                            pStatus[0] += 1;

                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    mProgress.setProgress(pStatus[0]);
                                    tv.setText(pStatus[0] + "%");

                                    if ((new1*100)/255 < 35){
                                        moist.setText("low soil moisture ");
                                        moist.setTextColor(Color.RED);
                                    }
                                    else if (((new1*100)/255) < 60&&((new1*100)/255)>35){
                                        moist.setText("Normal ");

                                    }
                                    else if(((new1*100)/255) >60){
                                        moist.setText("High soil moisture ");
                                    moist.setTextColor(Color.GREEN);}
                                }
                            });
                            try {
                                // Sleep for 200 milliseconds.
                                // Just to display the progress slowly
                                Thread.sleep(10); //thread will take approx 3 seconds to finish
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }}

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
}