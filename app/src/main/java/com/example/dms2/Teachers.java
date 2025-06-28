package com.example.dms2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
        homeLinks.add(new TeacherItem(11, "Bithi Biswas", "Chief Instructor & Department Head (2nd Shift), Computer Science and Technology", "bithibiswas98@gmail.com", "01749485453"));
        homeLinks.add(new TeacherItem(19, "Md. Mehedi Hasan", "Instructor (Tech), Computer", "mehedi.crux@gmail.com", "01737232587"));
        homeLinks.add(new TeacherItem(27, "Md. Kamal Sowdagar", "Instructor (Tech), Computer", "kamalsowdagar@gmail.com", "01521412326"));
        homeLinks.add(new TeacherItem(66, "Tamanna Halim Shanta", "Junior Instructor (Tech/Computer)", "tamannashanta5@gmail.com", "01767177617"));
        homeLinks.add(new TeacherItem(69, "Shyamananda Pal", "Junior Instructor (Tech), Computer", "shamanando@gmail.com", "01558957892"));
        homeLinks.add(new TeacherItem(70, "Sajib Kumar", "Junior Instructor (Tech/Computer)", "sajib0430@gmail.com", "01730684022"));


        for (TeacherItem item : homeLinks) {
            View viewItem = LayoutInflater.from(this).inflate(R.layout.teacher_card, teacherListLayout, false);

            TextView itemName = viewItem.findViewById(R.id.tcName);
            TextView itemSubject = viewItem.findViewById(R.id.tcDepartment);
            TextView itemEmail = viewItem.findViewById(R.id.tcEmail);
            Button tcBtnCall = viewItem.findViewById(R.id.tcBtnCall);

            tcBtnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + item.number));
                    v.getContext().startActivity(intent);
                }
            });



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
    String number;
    int id;

    TeacherItem(int id, String name, String subject, String email, String number) {
        this.id = id;
        this.subject = subject;
        this.name = name;
        this.email = email;
        this.number = number;
    }


}