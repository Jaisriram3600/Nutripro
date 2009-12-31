package com.blogspot.techyfruit360.nutripro;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by HP on 8/3/2019.
 */

public class FragmentMaize extends Fragment {
    View view;
    Typeface mt;
    public FragmentMaize() {

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.maize, container, false);
        final Button button=(Button)v.findViewById(R.id.btn);
        final TextView textView =(TextView)v.findViewById(R.id.tv);
        final EditText editText =(EditText)v.findViewById(R.id.ev);
        final TextView textView2 =(TextView)v.findViewById(R.id.tv1);
        final TextView textView3 =(TextView)v.findViewById(R.id.tv2);

        mt= Typeface.createFromAsset(getContext().getAssets(),"font/b.ttf");
        button.setTypeface(mt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = editText.getText().toString();
              int  s = Integer.parseInt(n);
              int   i = 44 * s;
                int j = 38*s;
                int urea = 71*s;
                textView.setText("Add " + i+" KG of DAP");
                textView2.setText("Add " + j+" KG of MOP");
                textView3.setText("Add " + urea+" KG of Urea");
                Intent intent = new Intent(FragmentMaize.this.getActivity(),Main4Activity.class);
                intent.putExtra("3",n);
            //    Intent intent1=new Intent(FragmentMaize.this.getActivity(),Main4Activity.class);
                startActivity(intent);
            }
        });

        return v;
    }



}
