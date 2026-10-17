package com.example.uolhelpdesk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RequestActivity extends Activity {

    TextView tvGreeting;
    EditText etPhone, etLink;
    Button btnCallNumber, btnOpenLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        tvGreeting = findViewById(R.id.tvGreeting);
        etPhone = findViewById(R.id.etPhone);
        etLink = findViewById(R.id.etLink);
        btnCallNumber = findViewById(R.id.btnCallNumber);
        btnOpenLink = findViewById(R.id.btnOpenLink);

        String name = getIntent().getStringExtra(
                MainActivity.EXTRA_NAME);

        if (name == null || name.trim().isEmpty()) {
            name = "Student";
        }

        tvGreeting.setText("Hello, " + name
                + "! How can we help you today?");

        btnCallNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etPhone.getText()
                        .toString().trim();

                if (!number.matches("03[0-9]{9}")) {
                    etPhone.setError(
                            "Enter 11 digits starting with 03");
                    etPhone.requestFocus();
                    return;
                }

                Intent intent = new Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:" + number));

                MainActivity.openIntent(RequestActivity.this,
                        intent);
            }
        });

        btnOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = etLink.getText()
                        .toString().trim();

                if (link.isEmpty()) {
                    etLink.setError("Please enter a link");
                    etLink.requestFocus();
                    return;
                }

                if (!link.startsWith("http://")
                        && !link.startsWith("https://")) {
                    link = "https://" + link;
                }

                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(link));

                MainActivity.openIntent(RequestActivity.this,
                        intent);
            }
        });
    }
}
