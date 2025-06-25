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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout counterLayout;
    LinearLayout homeLinksLayout;
    List<StatItem> counterList = new ArrayList<>();
    List<HomeLinkItem> homeLinks = new ArrayList<>();

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

        counterLayout = findViewById(R.id.main_counter_layout);

        counterList.add(new StatItem('1', "Total Faculty", "45"));
        counterList.add(new StatItem('2', "Total Students", "1250"));
        counterList.add(new StatItem('3', "Active Courses", "78"));
        counterList.add(new StatItem('4', "Pending Notices", "4"));
        counterList.add(new StatItem('5', "Upcoming Events", "8"));


        for (StatItem item : counterList) {
            View viewItem = LayoutInflater.from(this).inflate(R.layout.counter_card, counterLayout, false);

            TextView itemLabel = viewItem.findViewById(R.id.counter_card_label);
            TextView itemNumber = viewItem.findViewById(R.id.counter_card_number);

            itemLabel.setText(item.getLabel());
            itemNumber.setText(item.getValue());

            counterLayout.addView(viewItem);


        }

        homeLinksLayout = findViewById(R.id.main_links_layout);

        homeLinks.add(new HomeLinkItem('1', "Total Faculty", "45"));
        homeLinks.add(new HomeLinkItem('2', "Total Students", "1250"));
        homeLinks.add(new HomeLinkItem('3', "Active Courses", "78"));
        homeLinks.add(new HomeLinkItem('4', "Pending Notices", "4"));
        homeLinks.add(new HomeLinkItem('5', "Upcoming Events", "8"));


        for (HomeLinkItem item : homeLinks) {
            View viewItem = LayoutInflater.from(this).inflate(R.layout.home_link_item, homeLinksLayout, false);

            TextView itemLabel = viewItem.findViewById(R.id.home_link_title);
            TextView itemNumber = viewItem.findViewById(R.id.home_link_text);

            itemLabel.setText(item.getTitle());
            itemNumber.setText(item.getText());

            counterLayout.addView(viewItem);


        }


    }
}





class HomeLinkItem {
    private int id;
    private String title;
    private String text;

    public HomeLinkItem(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    // Optional: toString for debugging
    @Override
    public String toString() {
        return "StatItem{" +
                "id=" + id +
                ", label='" + title + '\'' +
                ", value='" + text + '\'' +
                '}';
    }
}

