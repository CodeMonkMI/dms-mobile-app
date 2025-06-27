package com.example.dms2;

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

public class Teachers extends BaseActivity {
    LinearLayout teacherListLayout;
    List<TeacherItem> homeLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teachers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        teacherListLayout = findViewById(R.id.teacher_list_layout);

        homeLinks.add(new TeacherItem(1, "Dr. Anisur Rahman", "Computer Science", "anisur.rahman@univbd.edu"));
        homeLinks.add(new TeacherItem(2, "Prof. Farhana Haque", "Electrical Engineering", "farhana.haque@univbd.edu"));
        homeLinks.add(new TeacherItem(3, "Md. Saiful Islam", "Software Engineering", "saiful.islam@univbd.edu"));
        homeLinks.add(new TeacherItem(4, "Dr. Nusrat Jahan", "Information Technology", "nusrat.jahan@univbd.edu"));
        homeLinks.add(new TeacherItem(5, "Engr. Rakibul Hasan", "Computer Networks", "rakibul.hasan@univbd.edu"));
        homeLinks.add(new TeacherItem(6, "Dr. Mahmudul Hasan", "Artificial Intelligence", "mahmudul.hasan@univbd.edu"));
        homeLinks.add(new TeacherItem(7, "Prof. Selina Akter", "Data Science", "selina.akter@univbd.edu"));
        homeLinks.add(new TeacherItem(8, "Md. Arif Hossain", "Cyber Security", "arif.hossain@univbd.edu"));
        homeLinks.add(new TeacherItem(9, "Dr. Tanzina Karim", "Database Systems", "tanzina.karim@univbd.edu"));
        homeLinks.add(new TeacherItem(10, "Engr. Kazi Tamim", "Mobile Application Development", "kazi.tamim@univbd.edu"));


        for (TeacherItem item : homeLinks) {
            View viewItem = LayoutInflater.from(this).inflate(R.layout.teacher_card, teacherListLayout, false);

            TextView itemName = viewItem.findViewById(R.id.tcName);
            TextView itemSubject = viewItem.findViewById(R.id.tcDepartment);
            TextView itemEmail = viewItem.findViewById(R.id.tcEmail);
            TextView itemBtnProfile = viewItem.findViewById(R.id.tcBtnViewProfile);

            itemName.setText(item.name);
            itemSubject.setText(item.subject);
            itemEmail.setText(item.email);

            teacherListLayout.addView(viewItem);


        }
    }
}


class TeacherItem {
    String name;
    String subject;
    String email;
    int id;

    TeacherItem(int idData, String nameStr, String subjectStr, String emailStr) {
        id = idData;
        subject = subjectStr;
        name = nameStr;
        email = emailStr;
    }


}