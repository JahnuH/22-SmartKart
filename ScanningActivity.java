package com.example.smartbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ScanningActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    String barcode;
    DBHelper DB;

    TextView textViewName, textViewPrice, textViewCategory, textViewDescription;

    String name, price, category, description;

    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);

        textViewName = findViewById(R.id.name);
        textViewPrice = findViewById(R.id.price);
        textViewCategory = findViewById(R.id.category);
        textViewDescription = findViewById(R.id.description);

        textViewName.setText("Scan Barcode");
        textViewPrice.setText("");
        textViewCategory.setText("");
        textViewDescription.setText("");

        SharedPreferences sharedPrefs = getSharedPreferences("PRODUCTS_PREFS", MODE_PRIVATE);
        String json = sharedPrefs.getString("PRODUCTS", null);
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        products = new Gson().fromJson(json, type);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(view -> {

            products.add(new Product(barcode, name, price, category, description));
            SharedPreferences sharedPrefsEvents = getSharedPreferences("PRODUCTS_PREFS", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefsEvents.edit();
            editor.putString("PRODUCTS", new Gson().toJson(products)).apply();

            Intent intent = new Intent(ScanningActivity.this, MainActivity.class);
            startActivity(intent);

        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(ScanningActivity.this, MainActivity.class);
            startActivity(intent);
        });

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
            barcode = result.getText();
            DB = new DBHelper(this);
            Cursor res = DB.getdata();
            if(res.getCount()==0) {
                Toast.makeText(ScanningActivity.this, "Item not Found", Toast.LENGTH_SHORT).show();
                return;
            }
            while(res.moveToNext()) {

                if(res.getString(0).equals(barcode)) {
                    textViewName.setText(res.getString(1));
                    textViewPrice.setText("Price: Rs. " + res.getString(2));
                    textViewCategory.setText("Category: " + res.getString(3));
                    textViewDescription.setText("Description: " + res.getString(4));

                    name = res.getString(1);
                    price = res.getString(2);
                    category = res.getString(3);
                    description = res.getString(4);

                    break;

                }

            }

        }));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

}
