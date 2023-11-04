package com.example.smartbasket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TertiaryActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3;
    Button button1, button2, button3;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tertiary);

        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        editText3 = findViewById(R.id.edittext3);

        button1 = findViewById(R.id.insert);
        button2 = findViewById(R.id.update);
        button3 = findViewById(R.id.delete);
        Button button4 = findViewById(R.id.view);

        DB = new DBHelper(this);

        button1.setOnClickListener(view -> {
            String e1 = editText1.getText().toString();
            String e2 = editText2.getText().toString();
            String e3 = editText3.getText().toString();
            Boolean checkinsertdata = DB.insertuserdata(e1,e2,e3);
            if(checkinsertdata) {
                Toast.makeText(TertiaryActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TertiaryActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(view -> {
            String e1 = editText1.getText().toString();
            String e2 = editText2.getText().toString();
            String e3 = editText3.getText().toString();
            Boolean checkupdatedata = DB.updateuserdata(e1,e2,e3);
            if(checkupdatedata) {
                Toast.makeText(TertiaryActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TertiaryActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(view -> {
            String e1 = editText1.getText().toString();
            Boolean checkdeletedata = DB.deletedata(e1);
            if(checkdeletedata) {
                Toast.makeText(TertiaryActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TertiaryActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(view -> {
            Cursor res = DB.getdata();
            if(res.getCount()==0) {
                Toast.makeText(TertiaryActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()) {
                buffer.append("Name : " + res.getString(0) + "\n");
                buffer.append("Contact : " + res.getString(1) + "\n");
                buffer.append("Date of Birth : " + res.getString(2) + "\n\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(TertiaryActivity.this);
            builder.setCancelable(true);
            builder.setTitle("User Entries");
            builder.setMessage(buffer.toString());
            builder.show();

        });

        Button doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(v -> {
            Toast.makeText(TertiaryActivity.this, "Done Button", Toast.LENGTH_SHORT).show();
        });

    }

}
