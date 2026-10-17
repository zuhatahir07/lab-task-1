
package com.example.uolhelpdesk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String EXTRA_NAME =
            "com.example.uolhelpdesk.EXTRA_NAME";

    EditText etName;
    Button btnWebsite, btnCall, btnMap, btnRequest;

    public static void openIntent(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context,
                    "No app found for this action",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnWebsite = findViewById(R.id.btnWebsite);
        btnCall = findViewById(R.id.btnCall);
        btnMap = findViewById(R.id.btnMap);
        btnRequest = findViewById(R.id.btnRequest);

        // Visit Website button
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.uol.edu.pk"));

                openIntent(MainActivity.this, intent);
            }
        });

        // Call Help Desk button
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:04212345678"));

                openIntent(MainActivity.this, intent);
            }
        });

        // Find Campus button
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=University+of+Lahore"));

                openIntent(MainActivity.this, intent);
            }
        });

        // Send a Request button
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();

                if (name.isEmpty()) {
                    etName.setError("Please enter your name");
                    etName.requestFocus();
                    return;
                }

                Intent intent = new Intent(
                        MainActivity.this,
                        RequestActivity.class);

                intent.putExtra(EXTRA_NAME, name);
                startActivity(intent);
            }
        });
    }
}
