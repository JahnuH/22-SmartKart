package com.example.smartbasket;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ManagementActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4, editText5;
    Button button1, button2, button3;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        editText3 = findViewById(R.id.edittext3);
        editText4 = findViewById(R.id.edittext4);
        editText5 = findViewById(R.id.edittext5);

        button1 = findViewById(R.id.insert);
        button2 = findViewById(R.id.update);
        button3 = findViewById(R.id.delete);
        Button button4 = findViewById(R.id.view);

        DB = new DBHelper(this);

        button1.setOnClickListener(view -> {
            String e1 = editText1.getText().toString();
            String e2 = editText2.getText().toString();
            String e3 = editText3.getText().toString();
            String e4 = editText4.getText().toString();
            String e5 = editText5.getText().toString();
            Boolean checkinsertdata = DB.insertuserdata(e1,e2,e3,e4,e5);
            if(checkinsertdata) {
                Toast.makeText(ManagementActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ManagementActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(view -> {
            String e1 = editText1.getText().toString();
            String e2 = editText2.getText().toString();
            String e3 = editText3.getText().toString();
            String e4 = editText4.getText().toString();
            String e5 = editText5.getText().toString();
            Boolean checkupdatedata = DB.updateuserdata(e1,e2,e3,e4,e5);
            if(checkupdatedata) {
                Toast.makeText(ManagementActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ManagementActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(view -> {
            String e1 = editText1.getText().toString();
            Boolean checkdeletedata = DB.deletedata(e1);
            if(checkdeletedata) {
                Toast.makeText(ManagementActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ManagementActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(view -> {
            Cursor res = DB.getdata();
            if(res.getCount()==0) {
                Toast.makeText(ManagementActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()) {
                buffer.append("Barcode: " + res.getString(0) + "\n");
                buffer.append("Product Name: " + res.getString(1) + "\n");
                buffer.append("Product Price: Rs. " + res.getString(2) + "\n");
                buffer.append("Product Category: " + res.getString(3) + "\n");
                buffer.append("Description: " + res.getString(4) + "\n\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(ManagementActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Product Database");
            builder.setMessage(buffer.toString());
            builder.show();

        });

        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(v -> {
            Intent intent = new Intent(ManagementActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

}
