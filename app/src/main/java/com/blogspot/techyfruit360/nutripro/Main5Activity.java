package com.blogspot.techyfruit360.nutripro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Main5Activity extends AppCompatActivity {
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        TextView tv1=(TextView)findViewById(R.id.tv1);
        TextView tv2=(TextView)findViewById(R.id.tv2);
        TextView tv3=(TextView)findViewById(R.id.tv3);
        TextView tv4=(TextView)findViewById(R.id.tv4);
        TextView tv5=(TextView)findViewById(R.id.tv5);
        TextView tv6=(TextView)findViewById(R.id.tv6);
        TextView tv7=(TextView)findViewById(R.id.tv7);
        TextView tv8=(TextView)findViewById(R.id.tv8);
        TextView tv9=(TextView)findViewById(R.id.tv9);
        TextView tv10=(TextView)findViewById(R.id.tv10);
        TextView tv11=(TextView)findViewById(R.id.tv11);
        TextView tv12=(TextView)findViewById(R.id.tv12);
        Intent intent =this.getIntent();

        String string =intent.getStringExtra("2");





        int s = Integer.parseInt(string);
        int  i = 20 * s;//Rice calculation
        int  j = 20*s;
        int   k  = 59*s;
        double o= (k/2);


        tv1.setText(""+i+"kg");
        tv2.setText(""+j+"kg");
        tv3.setText("--------");
        tv4.setText("--------");
        tv5.setText("--------");
        tv6.setText(new DecimalFormat("##.##").format(o)+"kg");
        tv7.setText("--------");
        tv8.setText("--------");
        tv9.setText(new DecimalFormat("##.##").format(o)+"kg");
        tv10.setText(""+i+"kg");
        tv11.setText(""+j+"kg");
        tv12.setText(""+k+"kg");


    }
}
