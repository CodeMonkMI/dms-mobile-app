package com.example.dms2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;




import androidx.core.view.GravityCompat;


public class MainActivity extends BaseActivity {
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



        // inflate dynamic data
        counterLayout = findViewById(R.id.main_counter_layout);

        counterList.add(new StatItem('1', "Total Faculty", "6"));
        counterList.add(new StatItem('2', "Total Students", "300+"));
        counterList.add(new StatItem('3', "Active Courses", "53"));
        counterList.add(new StatItem('5', "Upcoming Events", "3+"));


        for (StatItem item : counterList) {
            View viewItem = LayoutInflater.from(this).inflate(R.layout.counter_card, counterLayout, false);

            TextView itemLabel = viewItem.findViewById(R.id.counter_card_label);
            TextView itemNumber = viewItem.findViewById(R.id.counter_card_number);

            itemLabel.setText(item.getLabel());
            itemNumber.setText(item.getValue());

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


class StatItem {
    private int id;
    private String label;
    private String value;

    public StatItem(int id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    // Optional: toString for debugging
    @Override
    public String toString() {
        return "StatItem{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}