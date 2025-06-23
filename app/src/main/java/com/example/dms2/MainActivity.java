package com.example.dms2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout containerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        containerLayout = findViewById(R.id.container_layout);

        // Example: render 5 components
        for (int i = 0; i < 5; i++) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item, containerLayout, false);

            TextView itemText = itemView.findViewById(R.id.item_text);
            Button itemButton = itemView.findViewById(R.id.item_button);

            itemText.setText("Item #" + (i + 1));
            int finalI = i;
            itemButton.setOnClickListener(v -> {
                Toast.makeText(this, "Clicked item " + (finalI + 1), Toast.LENGTH_SHORT).show();
            });

            containerLayout.addView(itemView);
        }

    }
}