package com.example.dms2;

import android.content.Intent;
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





public class BookList extends AppCompatActivity {

    LinearLayout semesterListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        semesterListLayout = findViewById(R.id.semesterListLayout);


        for (int i =1;i<=8;i++){
            View viewItem = LayoutInflater.from(this).inflate(R.layout.semester_item_card, semesterListLayout, false);
            TextView semesterName = viewItem.findViewById(R.id.semesterItemName);

            semesterName.setText("Semester " + i);

            int finalI = i;
            viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BookList.this, SemesterBooks.class);
                    intent.putExtra("id", finalI);

                    startActivity(intent);
                }
            });

            semesterListLayout.addView(viewItem);

        }

    }



}



