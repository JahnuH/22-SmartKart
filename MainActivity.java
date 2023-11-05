package com.example.smartbasket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> productnames, productcosts;
    SimpleAdapter simpleAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productnames = new ArrayList<>();
        productcosts = new ArrayList<>();

        categorizeListView();
        formListView();

    }

    public void categorizeListView() {

        SharedPreferences sharedPreferencesEvents = getSharedPreferences("PRODUCTS_PREFS", MODE_PRIVATE);
        String json = sharedPreferencesEvents.getString("PRODUCTS", null);
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        ArrayList<Product> products = new Gson().fromJson(json, type);

        if ( products != null && products.size() > 0 ) {

            productnames.clear();
            productcosts.clear();

            for (int i = 0; i < products.size(); i++) {

                productnames.add(products.get(i).getProductName());
                productcosts.add(products.get(i).getProductPrice());

            }

        }

    }

    public void formListView() {

        List<HashMap<String,String>> hashMapArrayList = new ArrayList<>();

        for ( int i = 0; i < productnames.size(); i++ ) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("productName", productnames.get(i));
            hashMap.put("productCost", productcosts.get(i));
            hashMapArrayList.add(hashMap);
        }

        String[] from = {"productName", "productCost"};
        int[] to = { R.id.listViewTitle, R.id.listViewDesc };

        listView = findViewById(R.id.listViewProducts);
        simpleAdapter = new SimpleAdapter(getBaseContext(), hashMapArrayList, R.layout.list_item, from, to);
        listView.setAdapter(simpleAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.item1) {
            Intent intent = new Intent(MainActivity.this, ScanningActivity.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.item2) {
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.item3) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }

}
