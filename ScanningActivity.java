package com.example.smartbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;

public class ScanningActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    String barcode;
    DBHelper DB;

    TextView textViewName, textViewPrice, textViewCategory, textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        scan();

        textViewName = findViewById(R.id.name);
        textViewPrice = findViewById(R.id.price);
        textViewCategory = findViewById(R.id.category);
        textViewDescription = findViewById(R.id.description);

        textViewName.setText("Scan Barcode");
        textViewPrice.setText("");
        textViewCategory.setText("");
        textViewDescription.setText("");

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(view -> {
            Intent intent = new Intent(ScanningActivity.this, MainActivity.class);
            intent.putExtra("message", "HelloWorld");
            startActivity(intent);
        });

        button2.setOnClickListener(view -> {
            Intent intent = new Intent(ScanningActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

    public void scan() {
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
