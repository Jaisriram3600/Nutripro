package com.blogspot.techyfruit360.nutripro;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , LocationListener{

    Button btnDeleteUser,btnLogout;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener  authStateListener;
    TextView selectCity, humidity, pressure_field, updatedField,locatio,description,button,temp,weather,locationText,recom;
    private CardView soilcard,moisturecard,cropcard,fertilizercard;
    private Button getLocationBtn;
    LocationManager locationManager;
    Typeface weatherFont,mt;
    String city = "coimbatore";
    int c;
    /* Please Put your API KEY here */
    String OPEN_WEATHER_MAP_API = "b8f8a97894fcce25c479824aff1125bb";
    /* Please Put your API KEY here */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskLoadUp(city);
       Toolbar toolbar =findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        btnDeleteUser =(Button) findViewById(R.id.dlte);
        btnLogout =(Button) findViewById(R.id.logout);

        locatio = (TextView) findViewById(R.id.locatio);
         description = (TextView)findViewById(R.id.description);
         temp=(TextView)findViewById(R.id.temp);
         weather =(TextView)findViewById(R.id.weather);
         updatedField=(TextView)findViewById(R.id.date);
        recom=(TextView)findViewById(R.id.recommednd);
        // Button button1 = findViewById(R.id.btn1);



 soilcard=(CardView)findViewById(R.id.soilcard);
 moisturecard=(CardView)findViewById(R.id.moisturecard);
 cropcard=(CardView)findViewById(R.id.cropcard);
 fertilizercard=(CardView)findViewById(R.id.fertilizercard);

        soilcard.setOnClickListener(this);
        cropcard.setOnClickListener(this);
        moisturecard.setOnClickListener(this);
        fertilizercard.setOnClickListener(this);

        //   String hi[] = {"CHECK YOUR PLANT NUTRITION","CHECK YOUR SOIL NUTRITION"};//eppayum oncreate method la default string irukanum
        ///
        ///  FadingTextView FTV = findViewById(R.id.fading);
        //  FTV.setTexts(hi);
        //  FTV.setTimeout(2, TimeUnit.SECONDS);
        locationText = (TextView)findViewById(R.id.locationText);


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

                getLocation();

        mt=Typeface.createFromAsset(getAssets(),"font/mt.ttf");
        recom.setTypeface(mt);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    startActivity(new Intent(getApplicationContext(), login.class));
                    finish();
                }
            }

        };
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"User deleted",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),signup.class));
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    //    btnLogout.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        firebaseAuth.signOut();
              //  startActivity(new Intent(getApplicationContext(),login.class));
        //        finish();
        //    }
     //   });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
                return true;
        }
return super.onOptionsItemSelected(item);
    }

    public void taskLoadUp(String query) {
        if (com.blogspot.techyfruit360.nutripro.function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }



    class DownloadWeather extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String...args) {
            String xml = com.blogspot.techyfruit360.nutripro.function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +"&units=metric&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();
                    weatherFont = Typeface.createFromAsset(getAssets(), "font/k.ttf");

                    weather.setTypeface(weatherFont);

                  //  locatio.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    description.setText(details.getString("description").toUpperCase(Locale.US));
                    String s =details.getString("description");
                    temp.setText(String.format("%d", main.getInt("temp")) + "°"+"C");
                  //  humidity_field.setText("Humidity: " + main.getString("humidity") + "%");
                  //  pressure_field.setText("Pressure: " + main.getString("pressure") + " hPa");
                 updatedField.setText(df.format(new Date(json.getLong("dt") * 1000)));
                 long num=json.getLong(("dt"));

                 Date date = new java.util.Date(num*1000L);
                 SimpleDateFormat simpleDateFormat= new java.text.SimpleDateFormat("MM");
                 simpleDateFormat.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
                 String newdate =simpleDateFormat.format(date);

                    SimpleDateFormat simpleDateFormat2= new java.text.SimpleDateFormat("dd");
                    simpleDateFormat2.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
                    String newdate2 =simpleDateFormat2.format(date);

                    SimpleDateFormat simpleDateFormat3= new java.text.SimpleDateFormat("yyyy");
                    simpleDateFormat3.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
                    String newdate3 =simpleDateFormat3.format(date);
                 updatedField.setText(newdate2+"-"+newdate+"-"+newdate3);



                    weather.setText(Html.fromHtml(com.blogspot.techyfruit360.nutripro.function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));

                  if(s.equals("clear sky")||s.equals("few clouds"))
                  {
                      recom.setText("Good day for applying pesticides ");

                 }
                  else  if(s.equals("thunderstorm"))
                  {
                      recom.setText("Bad day for applying pesticides");
                  }

                  else  if(s.equals("drizzle")||s.equals("drizzle rain"))
                    {
                        recom.setText("Good weather for seeding ");
                    }


                }

            } catch (JSONException e) {

            }


        }



    }
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.soilcard: i =new Intent(this,soil.class);
            startActivity(i);
                break;
            case R.id.moisturecard :i =new Intent(this,Value.class);
                startActivity(i);
                break;
            case R.id.cropcard: i =new Intent(this,plant.class);
                startActivity(i);
                break;
            case R.id.fertilizercard: i =new Intent(this,Main3Activity.class);
                startActivity(i);
                default:break;

        }


}
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);

        }
        catch(SecurityException e) {

        }
    }

    public void onLocationChanged(Location location) {
        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
        locationText.setVisibility(View.INVISIBLE);

        try {
            double lat=location.getLatitude();
            double lon=location.getLongitude();
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
             city = addresses.get(0).getLocality();
            locatio.setText(city);

            temp.setText(c+"");
            taskLoadUp(city);


        }catch(Exception e)
        {
           e.printStackTrace();
        }

    }


    public void onProviderDisabled(String provider) {
       Toast toast= Toast.makeText(MainActivity.this, " Enable GPS and Internet", Toast.LENGTH_LONG);
       toast.show();


    }


    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast toast= Toast.makeText(MainActivity.this, "GPS turned ON", Toast.LENGTH_LONG);
        toast.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

}


