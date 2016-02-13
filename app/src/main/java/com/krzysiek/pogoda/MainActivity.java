package com.krzysiek.pogoda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] cities = { "Dortmund", "Lille", "Dubai" };
        ListView city_list = (ListView) findViewById(R.id.listView1);
        Adapter adapter_listy = new Adapter(this, cities);
        city_list.setAdapter(adapter_listy);

        city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int pos, long arg3) {

                Intent startActivityCustomList = new Intent(MainActivity.this, ShowWeather.class);
                startActivityCustomList.putExtra("city", cities[pos]);
                startActivity(startActivityCustomList);
            }
        });
    }
}