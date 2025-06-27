package com.example.dms2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import androidx.annotation.NonNull;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;


import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Results extends BaseActivity {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText inputField = findViewById(R.id.result_roll_input);
        Button submitButton = findViewById(R.id.result_submit_btn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll = inputField.getText().toString().trim();
                if (!roll.isEmpty()) {
                    fetchSemesterResults(roll);
                }

            }
        });
    }

    private void fetchSemesterResults(String roll) {
        String url = "https://btebresultszone.com/api/results/individual?roll=" + roll +
                "&exam=DIPLOMA+IN+ENGINEERING&regulation=2022";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    ApiResponse apiResponse = gson.fromJson(json, ApiResponse.class);

                    runOnUiThread(() -> {
                        LinearLayout result_table_layout = findViewById(R.id.result_table_layout);
                        result_table_layout.removeAllViews(); // optional: clear previous results

                        for (SemesterResult semester : apiResponse.semester_results) {
                            for (ExamResult result : semester.exam_results) {
                                View viewItem = LayoutInflater.from(Results.this)
                                        .inflate(R.layout.semester_result_item, result_table_layout, false);

                                TextView name = viewItem.findViewById(R.id.semester_result_name);
                                TextView date = viewItem.findViewById(R.id.semester_result_date);
                                TextView gpa = viewItem.findViewById(R.id.semester_result_gpa);

                                name.setText(semester.semester + " Semester");
                                date.setText("Publish date: " + result.date.substring(0, 10)); // cleaner
                                gpa.setText("GPA: " + result.gpa);
                                if(result.gpa == 0.0) {
                                    gpa.setTextColor(Color.parseColor("#FF0000"));
                                    name.setTextColor(Color.parseColor("#FF0000"));
                                    date.setTextColor(Color.parseColor("#FF0000"));
                                }

                                result_table_layout.addView(viewItem);
                            }
                        }
                    });
                }
            }

        });
    }

    // ============ Models =============
    public static class ApiResponse {
        @SerializedName("semester_results")
        public List<SemesterResult> semester_results;
    }

    public static class SemesterResult {
        public int semester;
        @SerializedName("exam_results")
        public List<ExamResult> exam_results;
    }

    public static class ExamResult {
        public double gpa;
        public String date;
    }
}

