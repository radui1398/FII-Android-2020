package com.example.lab1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String FULL_NAME = "Radu Ionut";
    private Product[] products;
    private int currentProduct = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("--- onCreate ----");

        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            currentProduct = savedInstanceState.getInt("currentProduct");
        }

        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.listView);

        products = new Product[]{
                new Product("Mountain Bike", "Bike for hard terrain"),
                new Product("BMX", "Bike for tricks."),
                new Product("City Bike", "Bike for a city ride.")
        };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < products.length; ++i) {
            list.add(products[i].getName());
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        if (currentProduct != -1) {
            final String item = (String) listview.getItemAtPosition(currentProduct);
            TextView description = (TextView) findViewById(R.id.textView);
            description.setText(products[currentProduct].getDescription());
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                TextView description = (TextView) findViewById(R.id.textView);
                currentProduct = position;
                description.setText(products[position].getDescription());
            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.login:
                Intent intent = new Intent(this, LoginActivity.class);
                String user_name = "Radu Ionut";
                intent.putExtra("USER_NAME", user_name);
                startActivity(intent);
                return true;
            case R.id.get_help:
                Toast.makeText(getApplicationContext(), "Launching SMS app.", Toast.LENGTH_LONG).show();
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:0760125125"));

                startActivity(sendIntent);
                return true;
            case R.id.sensors:
                Intent sensors_intent = new Intent(this, Sensors.class);
                startActivity(sensors_intent);
                return true;
            case R.id.my_location:
                Intent location = new Intent(this, Location.class);
                startActivity(location);
                return true;
            case R.id.app_settings:
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                startActivity(settings_intent);
                return true;
            case R.id.log_out:
                SharedPreferences prefs = getSharedPreferences("USER", MODE_PRIVATE);
                final String display_name = prefs.getString("NAME", "");//"No name defined" is the default value.

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Are you sure you want to log out?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Thank you " + display_name + "!", Toast.LENGTH_LONG).show();

                                SharedPreferences.Editor editor = getSharedPreferences("USER", MODE_PRIVATE).edit();
                                editor.putString("NAME","");
                                editor.apply();

                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class Product {
        private String name;
        private String description;

        public Product(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);
        icicle.putInt("currentProduct", currentProduct);
    }

    @Override
    protected void onStart() {
        System.out.println("--- onStart ----");
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("--- onResume ----");

        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("--- onPause ----");
        super.onPause();
    }

    @Override
    protected void onStop() {
        System.out.println("--- onStop ----");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("--- onDestroy ----");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        System.out.println("--- onRestart ----");
        super.onRestart();
    }


}