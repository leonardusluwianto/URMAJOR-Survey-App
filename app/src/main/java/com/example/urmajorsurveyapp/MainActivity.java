package com.example.urmajorsurveyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText answer1, answer2, answer3, answer4, answer5;
    private Button btnSubmit;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        answer5 = findViewById(R.id.answer5);
        btnSubmit = findViewById(R.id.btn_submit);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Submitting data...");

        btnSubmit.setOnClickListener(v -> {
            if ((answer1.getText().length() > 0) && (answer2.getText().length() > 0) && (answer3.getText().length() > 0) && (answer4.getText().length() > 0) && (answer5.getText().length() > 0)) {
                submitData(answer1.getText().toString(), answer2.getText().toString(), answer3.getText().toString(), answer4.getText().toString(), answer5.getText().toString());
            }
            else {
                Toast.makeText(getApplicationContext(), "Silakan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resetData() {
        answer1.setText(null);
        answer2.setText(null);
        answer3.setText(null);
        answer4.setText(null);
        answer5.setText(null);
    }

    private void submitData(String answer1, String answer2, String answer3, String answer4, String answer5) {
        Map<String, Object> map = new HashMap<>();
        map.put("answer1", answer1);
        map.put("answer2", answer2);
        map.put("answer3", answer3);
        map.put("answer4", answer4);
        map.put("answer5", answer5);

        progressDialog.show();

        Call<Void> call = retrofitInterface.executeSubmit(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Jawaban Anda telah tercatat.", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                resetData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//
//        db.collection("responses")
//                .add(response)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getApplicationContext(), "Jawaban Anda telah tercatat.", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                    }
//                });
    }
}