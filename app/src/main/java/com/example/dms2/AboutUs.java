package com.example.dms2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutUs extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_us);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set clickable external links
        setLink(R.id.about_us_facebook, "https://facebook.com/codemonkmi");
        setLink(R.id.about_us_linkedin, "https://linkedin.com/in/codemonkmi");
        setLink(R.id.about_us_github, "https://github.com/codemonkmi");

        // Enable phone and email auto link if needed
        Linkify.addLinks((TextView) findViewById(R.id.about_us_email), Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks((TextView) findViewById(R.id.about_us_phone), Linkify.PHONE_NUMBERS);
    }

    private void setLink(int viewId, String url) {
        TextView textView = findViewById(viewId);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}