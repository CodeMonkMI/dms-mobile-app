package com.example.dms2;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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

                    Intent intent = null;
                    int id = item.getId();
                    Class<?> targetActivity = null;

                    if (id == 0) {
                        targetActivity = MainActivity.class;
                    } else if (id == 1) {
                        targetActivity = Teachers.class;
                    } else if (id == 2) {
                        targetActivity = Notices.class;
                    } else if (id == 3) {
                        targetActivity = Results.class;
                    } else if (id == 4) {
                        targetActivity = BookList.class;
                    } else if (id == 5) {
                        targetActivity = ContactUs.class;
                    }

                    drawerLayout.closeDrawer(GravityCompat.START);

                    if (targetActivity != null) {
                        // Check if we're already in that activity
                        if (!BaseActivity.this.getClass().equals(targetActivity)) {
                            intent = new Intent(BaseActivity.this, targetActivity);
                            startActivity(intent);
                        }
                    }






                }
            });

            itemTitle.setText(item.getTitle());
            itemText.setText(item.getText());
            itemIcon.setImageResource(item.getIcon());

            navDrawerLayout.addView(viewItem);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            SpannableString spanString = new SpannableString(menuItem.getTitle());
            spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)), 0, spanString.length(), 0);
            menuItem.setTitle(spanString);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_menu_about_us) {

            Intent intent = new Intent(BaseActivity.this, AboutUs.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
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
