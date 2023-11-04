package com.example.smartbasket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondaryActivity extends AppCompatActivity {

    String name,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        Intent intent = getIntent();
        String barcode = intent.getStringExtra("key");

        Button button = findViewById(R.id.button);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);

        //textView1.setText(name);
        //textView2.setText(price);

        button.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, TertiaryActivity.class);
            intent1.putExtra("key1", barcode); // Pass your parameter here
            startActivity(intent1);

        });
    }

}
