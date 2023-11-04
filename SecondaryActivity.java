package com.example.smartbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(v -> {

            try {
                Connection connection = connectionclass();
                if (connection != null) {
                    String query = "Select * from retail_detail";
                    Statement st = connection.createStatement();
                    ResultSet resultSet = st.executeQuery(query);
                    while (resultSet.next()) {
                        //textView.setText(resultSet.getString(2));
                        System.out.println("--> " + resultSet.getString(2));
                    }
                }
            } catch (Exception exception) {
                Log.e("Error ", "MainActivity: " + exception.getMessage());
            }

        });
    }

    @SuppressLint("NewApi")
    public Connection connectionclass() {

        Connection connection = null;
        String ip = "10.7.74.55";
        String port = "1433";
        String username = "sa";
        String password = "Blackp1nk!";
        String database = "master";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //String ConnectionURL ="jdbc:sqlserver://ASUSF15/chand;databaseName=PRODUCT_INVENTORY;integratedSecurity=true";
            String ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + database + ";user=" + username + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (Exception exception) {
            Log.e("Error ", "ConnectionClass: " + exception.getMessage());
        }

        return connection;

    }

}
