package edu.ewubd.cse489_2019_1_60_202;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvEvents;
    private ArrayList<Event> events;
    private CustomEventAdapter adapter;
    public static int counter = 0;

    Button btnNew, btnHistory, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(MainActivity.counter+" @MainActivity-onCreate()");

        loadData();

        String[] keys = {"action", "id", "semester"};
        String[] values = {"restore", "20191260202", "2023-1"};
        httpRequest(keys, values);

        lvEvents = findViewById(R.id.listEvents);
        btnNew = findViewById(R.id.btnNew);

        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Event e = (Event) adapterView.getItemAtPosition(pos);

                String key = e.name + '-' + e.phone;

                Intent i = new Intent(MainActivity.this, CreateEventActivity.class);
                i.putExtra("value", key);
                startActivity(i);
                finish();
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, CreateEventActivity.class);
                i.putExtra("value", "");
                startActivity(i);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.counter+ "@MainActivity- button exit was pressed");
                MainActivity.counter++;
                finish();

            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.counter+ "@MainActivity- button history was pressed");
                MainActivity.counter++;
            }
        });
    }

    private void loadData() {
        events = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        //events = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("YOUR_SEPARATOR");

            String name = fieldValues[0];
            String place = fieldValues[1];
            String eventType = fieldValues[2];
            String dateTime = fieldValues[3];
            String capacity = fieldValues[4];
            String budget = fieldValues[5];
            String email = fieldValues[6];
            String phone = fieldValues[7];
            String description = fieldValues[8];

            Event e = new Event(key, name, "", dateTime, "", "", "", "", "", eventType);
            events.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, R.layout.row_event, events);
        lvEvents.setAdapter(adapter);

        // handle the click on an event-list item
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //String item = (String) parent.getItemAtPosition(position);
                Event selectedEvent = events.get(position);
                System.out.println(position);
                Intent i = new Intent(MainActivity.this, CreateEventActivity.class);
                i.putExtra("key", selectedEvent.key);
                i.putExtra("name", selectedEvent.name);
                i.putExtra("place", selectedEvent.place);
                i.putExtra("type", selectedEvent.eventType);
                i.putExtra("dateTime", selectedEvent.datetime);
                i.putExtra("capacity", selectedEvent.capacity);
                i.putExtra("budget", selectedEvent.budget);
                i.putExtra("email", selectedEvent.email);
                i.putExtra("phone", selectedEvent.phone);
                i.putExtra("description", selectedEvent.description);
                startActivity(i);
            }
        });
        // handle the long-click on an event-list item---check
        lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Event selectedEvent = events.get(position);
                String message = "Do you want to delete event - " + selectedEvent.name + " ?";
                showDialog(message, "Delete Event", selectedEvent.key, selectedEvent);
                return true;
            }
        });
    }
    private void showDialog(String message, String name, String key, Event event) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(name);
        builder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Util.getInstance().deleteByKey(MainActivity.this, event.key);
                        dialog.cancel();
                        //initializeCustomEventList();
                        events.remove(event);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(MainActivity.counter+" @MainActivity-onStart()");
        MainActivity.counter++;

    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(MainActivity.counter+" @MainActivity-onResume()");
        MainActivity.counter++;

        events.clear();

        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");

        while(rows.moveToNext()){
            String key = rows.getString(0);     // returns the first column out of the 2 columns
            String value = rows.getString(1);

            String[] subStrings = value.split("-----");
            String name = subStrings[0];
            String place = subStrings[1];
            String type = subStrings[2];
            String dateTime = subStrings[3];
            String capacity = subStrings[4];
            String budget = subStrings[5];
            String email = subStrings[6];
            String phone = subStrings[7];
            String description = subStrings[8];

            events.add(new Event(key, name, place, type, dateTime, capacity, budget, email, phone, description));

            String keys[] = {"action", "id", "semester"};
            String values[] = {"restore", "2019160202", "2023-1"};
            httpRequest(keys, values);

        }
        adapter = new CustomEventAdapter(this,  R.layout.row_event, events);
        lvEvents.setAdapter(adapter);
        // sets the list inside ListView in xml
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(MainActivity.counter+" @MainActivity-onPause()");
        MainActivity.counter++;
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(MainActivity.counter+" @MainActivity-onStop()");
        MainActivity.counter++;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println(MainActivity.counter+" @MainActivity-onRestart()");
        MainActivity.counter++;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(MainActivity.counter+" @MainActivity-onDestroy()");
        MainActivity.counter++;
    }

        @SuppressLint("StaticFieldLeak")
    private void httpRequest(final String keys[],final String values[]){
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                for (int i=0; i<keys.length; i++){
                    params.add(new BasicNameValuePair(keys[i],values[i]));
                }
                String url= "https://www.muthosoft.com/univ/cse489/index.php";
                String data="";
                try {
                    data=JSONParser.getInstance().makeHttpRequest(url,"POST",params);
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            protected void onPostExecute(String data){
                if(data!=null){
                    System.out.println(data);
                    updateEventListByServerData(data);
                    Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
    public void updateEventListByServerData(String data) {
        try {
            JSONObject jo = new JSONObject(data);
            if (jo.has("events")) {
                events.clear();
                JSONArray ja = jo.getJSONArray("events");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject event = ja.getJSONObject(i);
                    String eventKey = event.getString("e_key");
                    String eventValue = event.getString("e_value");
                    // split eventValue to show in event list
                    String[] fieldValues = eventValue.split("YOUR_SEPARATOR");
                    String name = fieldValues[0];
                    String place = fieldValues[1];
                    String eventType = fieldValues[2];
                    String dateTime = fieldValues[3];
                    String capacity = fieldValues[4];
                    String budget = fieldValues[5];
                    String email = fieldValues[6];
                    String phone = fieldValues[7];
                    String description = fieldValues[8];
                    Event e = new Event(eventKey, name, "", dateTime, "", "", "", "", "", eventType);
                    events.add(e);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
    }
}