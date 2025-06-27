package com.example.dms2;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


public class SemesterBooks extends BaseActivity {
    private static final String TAG = "SemesterBooksActivity";
    LinearLayout semesterBookListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_semester_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        if (!intent.hasExtra("id")) return;
        int semesterId = intent.getIntExtra("id", '1');
        String jsonString = loadJSONFromAsset("cst_courses_json.json");
        if (jsonString != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, List<Course>>>() {
            }.getType();
            Map<String, List<Course>> allSemesters = gson.fromJson(jsonString, type);

            Log.v(TAG, String.valueOf(semesterId));
            semesterBookListLayout = findViewById(R.id.semesterBookListLayout);

            // Example: get all courses from semester 3
            List<Course> semesterCourses = allSemesters.get(String.valueOf(semesterId));
            assert semesterCourses != null;
            if (semesterCourses.isEmpty()) return;
            for (Course course : semesterCourses) {
                View viewItem = LayoutInflater.from(this).inflate(R.layout.book_item_card, semesterBookListLayout, false);

                TextView bookTitle = viewItem.findViewById(R.id.bookTitle);
                TextView bookCode = viewItem.findViewById(R.id.bookCode);
                TextView bookCredit = viewItem.findViewById(R.id.bookCredit);

                TextView bookTC = viewItem.findViewById(R.id.bookTC);
                TextView bookTF = viewItem.findViewById(R.id.bookTF);
                TextView bookTT = viewItem.findViewById(R.id.bookTT);
//
                TextView bookPC = viewItem.findViewById(R.id.bookPC);
                TextView bookPF = viewItem.findViewById(R.id.bookPF);
                TextView bookPT = viewItem.findViewById(R.id.bookPT);





                bookTitle.setText(course.getName());
                bookCode.setText("Code: " + course.getCode());
                bookCredit.setText(course.getCredit() + " Credit");


                Log.i("SemesterBooksActivityCourse", String.valueOf(course.toString()));
//                bookTC.setText(" - Continuous: " + course.getCode());
//                bookTC.setText(course.getPracticalAssessment().getContinuous());
//                bookTF.setText(course.getPracticalAssessment().getFinalExam());
//                bookTT.setText(course.getPracticalAssessment().getTotal());

//                bookPC.setText(course.getTheoryAssessment().getContinuous());
//                bookPF.setText(course.getTheoryAssessment().getFinalExam());
//                bookPT.setText(course.getTheoryAssessment().getTotal());


                semesterBookListLayout.addView(viewItem);
            }
        }
    }

    private String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}

class Course {
    private String code;
    private String name;
    private int theory;
    private int practical;
    private int credit;
    private Assessment theoryAssessment;
    private Assessment practicalAssessment;
    private int grandTotal;

    // Getters & Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTheory() {
        return theory;
    }

    public void setTheory(int theory) {
        this.theory = theory;
    }

    public int getPractical() {
        return practical;
    }

    public void setPractical(int practical) {
        this.practical = practical;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Assessment getTheoryAssessment() {
        return theoryAssessment;
    }

    public void setTheoryAssessment(Assessment theoryAssessment) {
        this.theoryAssessment = theoryAssessment;
    }

    public Assessment getPracticalAssessment() {
        return practicalAssessment;
    }

    public void setPracticalAssessment(Assessment practicalAssessment) {
        this.practicalAssessment = practicalAssessment;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }
}


class Assessment {
    private int continuous;
    private int finalExam; // Use a custom key in Gson mapping
    private int total;

    // Getters & Setters
    public int getContinuous() {
        return continuous;
    }

    public void setContinuous(int continuous) {
        this.continuous = continuous;
    }

    public int getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(int finalExam) {
        this.finalExam = finalExam;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
