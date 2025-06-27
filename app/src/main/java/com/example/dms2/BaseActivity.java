package com.example.dms2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    protected Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base); // Base layout with Drawer

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        setupDynamicDrawerMenu();
    }

    // Insert child layout into FrameLayout
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        if (frameLayout != null) {
            getLayoutInflater().inflate(layoutResID, frameLayout, true);
        } else {
            super.setContentView(layoutResID);
        }
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setupDynamicDrawerMenu() {

        View headerView = navigationView.getHeaderView(0);

        LinearLayout navDrawerLayout = headerView.findViewById(R.id.dynamic_menu_container);
        List<NavDrawerItem> navMenuItems = new ArrayList<>();


        navMenuItems.add(new NavDrawerItem(0, "Home", "Application Start", R.drawable.ic_trending_up));
        navMenuItems.add(new NavDrawerItem(1, "Teachers", "Faculty and staff management", R.drawable.ic_trending_up));
        navMenuItems.add(new NavDrawerItem(2, "Notices", "Department  announcements", R.drawable.ic_trending_up));
        navMenuItems.add(new NavDrawerItem(3, "Results", "Academic results adn grades", R.drawable.ic_trending_up));
        navMenuItems.add(new NavDrawerItem(4, "Book List", "Course material and resources", R.drawable.ic_trending_up));
        navMenuItems.add(new NavDrawerItem(5, "Contact Us", "Department directory", R.drawable.ic_trending_up));

        for (NavDrawerItem item : navMenuItems) {
            View viewItem = LayoutInflater.from(this).inflate(R.layout.nav_drawer_menu_item, navDrawerLayout, false);

            TextView itemTitle = viewItem.findViewById(R.id.nav_drawer_item_title);
            TextView itemText = viewItem.findViewById(R.id.nav_drawer_item_text);
            ImageView itemIcon = viewItem.findViewById(R.id.nav_drawer_item_icon);

            viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent;
                    int id = item.getId();
                    if (id == 0) {
                        intent = new Intent(BaseActivity.this, MainActivity.class);
                    } else if (id == 1) {
                        intent = new Intent(BaseActivity.this, Teachers.class);
                    } else if (id == 2) {
                        intent = new Intent(BaseActivity.this, Notices.class);
                    } else if (id == 3) {
                        intent = new Intent(BaseActivity.this, Results.class);
                    } else if (id == 4) {
                        intent = new Intent(BaseActivity.this, BookList.class);
                    } else if (id == 5) {
                        intent = new Intent(BaseActivity.this, ContactUs.class);
                    } else {
                        return;
                    }

                    drawerLayout.closeDrawer(GravityCompat.START);
                    startActivity(intent);

                }
            });

            itemTitle.setText(item.getTitle());
            itemText.setText(item.getText());
            itemIcon.setImageResource(item.getIcon());

            navDrawerLayout.addView(viewItem);

        }
    }

}


class NavDrawerItem {
    private int id;
    private String title;
    private String text;
    private int resId;

    public NavDrawerItem(int id, String title, String text, int icon) {
        this.id = id;
        this.title = title;
        this.resId = icon;
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

    public int getIcon() {
        return resId;
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
