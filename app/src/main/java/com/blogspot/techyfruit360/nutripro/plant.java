
package com.blogspot.techyfruit360.nutripro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.TimeUnit;

public class plant extends AppCompatActivity {
    MqttHelper mqttHelper;
    TextView dataReceived,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

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
                if (topic.equals("JJ7")) { //Log.w("Mois", mqttMessage.toString());
                    dataReceived=(TextView)findViewById(R.id.textView5);
                    dataReceived.setText(mqttMessage.toString());

                    final int new1 = Integer.parseInt(dataReceived.getText().toString());//data

                    data = (TextView) findViewById(R.id.textView2);
                    data.setText(new1);

                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

}

//public void on(View view1) {
           // EditText editText=findViewById(R.id.et);
          //  FadingTextView FTV = findViewById(R.id.fading);  Use if needed

           // String hi = editText.getText().toString();

          //  FTV.setTexts(new String[]{"" + hi});
         //   FTV.setTimeout(2,TimeUnit.SECONDS);
       // }
  //   <EditText
 //   android:id="@+id/et"
 //   android:layout_width="wrap_content"

   // android:layout_height="wrap_content"
 //   android:layout_above="@+id/button1"
 //   android:layout_centerHorizontal="true"
   // android:layout_marginBottom="17dp"
          //  />

    // <Button
   // android:id="@+id/button1"
   // android:layout_width="wrap_content"
   // android:layout_height="wrap_content"
   // android:layout_alignParentBottom="true"
  //  android:layout_centerHorizontal="true"
  //  android:layout_marginBottom="137dp"
   // android:onClick="on"
    //android:text="Enter" />


