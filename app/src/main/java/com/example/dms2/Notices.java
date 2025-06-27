package com.example.dms2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Notices extends BaseActivity {
    LinearLayout noticesListLayout;
    List<NoticeItem> noticeItems = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notices);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        noticesListLayout = findViewById(R.id.notices_list_layout);
        noticeItems.add(new NoticeItem(1, "Faculty Meeting - June 20", "normal", "Monthly faculty meeting to discuss curriculum updates.", "administrative", "2025-06-14"));
        noticeItems.add(new NoticeItem(2, "Semester Final Exam Schedule", "urgent", "Final exam routine has been published. Check department board.", "academic", "2025-06-18"));
        noticeItems.add(new NoticeItem(3, "Library Orientation Session", "info", "New students are requested to attend the library orientation.", "student affairs", "2025-06-20"));
        noticeItems.add(new NoticeItem(4, "National Mourning Day", "important", "Observance of National Mourning Day on August 15.", "event", "2025-06-22"));
        noticeItems.add(new NoticeItem(5, "Assignment Submission Deadline", "warning", "Deadline for OOP lab assignment is approaching.", "academic", "2025-06-25"));
        noticeItems.add(new NoticeItem(6, "Blood Donation Camp", "info", "Join the voluntary blood donation camp at campus.", "health", "2025-06-26"));
        noticeItems.add(new NoticeItem(7, "Software Engineering Workshop", "highlight", "A day-long workshop by industry experts on software engineering trends.", "workshop", "2025-06-28"));
        noticeItems.add(new NoticeItem(8, "Class Suspension Notice", "alert", "All classes will be suspended on account of department seminar.", "administrative", "2025-07-01"));
        noticeItems.add(new NoticeItem(9, "Internship Opportunity - Grameenphone", "career", "Eligible students can apply for internship at Grameenphone ICT.", "career", "2025-07-05"));
        noticeItems.add(new NoticeItem(10, "Campus Cleanliness Drive", "volunteer", "Join the department’s effort to make our campus clean and green.", "social", "2025-07-10"));

        for (NoticeItem item : noticeItems) {
            View viewItem = LayoutInflater.from(this).inflate(R.layout.notices_card, noticesListLayout, false);

            TextView itemTitle = viewItem.findViewById(R.id.notice_title);
            TextView itemShortDec = viewItem.findViewById(R.id.notice_description);
            TextView itemTag = viewItem.findViewById(R.id.notice_tag);
            TextView itemCategoryDate = viewItem.findViewById(R.id.notice_category_date);

            itemTitle.setText(item.title);
            itemTag.setText(item.tag);
            itemShortDec.setText(item.shortDec);
            itemCategoryDate.setText("Category: " + item.category + " Date: " + item.date);


            noticesListLayout.addView(viewItem);


        }

    }
}


class NoticeItem {
    int id;
    String title;
    String tag;
    String shortDec;
    String category;
    String date;

    NoticeItem(int Id, String titleStr, String tagStr, String shortDecStr, String categoryStr, String dateStr) {
        id = Id;
        tag = tagStr;
        shortDec = shortDecStr;
        category = categoryStr;
        date = dateStr;
        title = titleStr;
    }

}

