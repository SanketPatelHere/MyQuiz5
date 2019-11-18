package com.example.myquiz2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView tvresult;
    Button btnhome;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvresult = (TextView)findViewById(R.id.tvresult);
        btnhome = (Button) findViewById(R.id.btnhome);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        float result = Float.parseFloat(getIntent().getExtras().getString("result"));
        float totalQuestions = Float.parseFloat(getIntent().getExtras().getString("totalQuestions"));
        float wrong = totalQuestions-result;
        //Toast.makeText(ResultActivity.this, "Right Answers = "+MathsActivity.myscore, Toast.LENGTH_SHORT).show();
        //Toast.makeText(ResultActivity.this, "Right Answers = "+result, Toast.LENGTH_SHORT).show();
        Toast.makeText(ResultActivity.this, "Right Answers = "+result+"\nWrong Answers = "+wrong, Toast.LENGTH_SHORT).show();
        //tvresult.setText("Right Answers = "+result);
        tvresult.setText("Total Question = "+totalQuestions+"\nRight Answers = "+result+"\nWrong Answers = "+wrong);

        //int rate = (result/totalQuestions) * 100;
        float d = (result/totalQuestions) * 100;
        //float d =  (Float.parseFloat(String.valueOf(result/totalQuestions))) * 100;

        Log.i("My Right = ",result+"");
        Log.i("My Total Questions = ",totalQuestions+"");
        Log.i("My Rating = ",d+"");
        this.ratingBar.setRating((result/totalQuestions)*5);
        Log.i("My Get Rating = ",ratingBar.getRating()+"");

        String rating = "Rating is = "+ratingBar.getRating();
        Toast.makeText(ResultActivity.this, rating, Toast.LENGTH_SHORT).show();
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);
                MathsActivity.myscore = 0;
                //result = 0;
            }
        });
    }
}
