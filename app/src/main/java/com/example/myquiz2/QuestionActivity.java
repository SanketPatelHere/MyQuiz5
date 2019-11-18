package com.example.myquiz2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rangeupto0to10, rangeupto10to20, rangeupto20to30, rangeupto30to40, rangeupto40to50;
    Button btnsubmit, btnclear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        rg = (RadioGroup)findViewById(R.id.rg);
        rangeupto0to10 = (RadioButton)findViewById(R.id.rangeupto0to10);
        rangeupto10to20 = (RadioButton)findViewById(R.id.rangeupto10to20);
        rangeupto20to30 = (RadioButton)findViewById(R.id.rangeupto20to30);
        rangeupto30to40 = (RadioButton)findViewById(R.id.rangeupto30to40);
        rangeupto40to50 = (RadioButton)findViewById(R.id.rangeupto40to50);
        btnsubmit = (Button)findViewById(R.id.btnsubmit);
        btnclear = (Button)findViewById(R.id.btnclear);
        final String checkboxes = getIntent().getExtras().getString("checkboxes");
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb =(RadioButton)rg.findViewById(rg.getCheckedRadioButtonId());
                Toast.makeText(QuestionActivity.this, "Checkboxes = "+checkboxes+"\nselect name = "+rb.getText(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(QuestionActivity.this, ProgressActivity.class);
                i.putExtra("checkboxes",checkboxes);
                i.putExtra("range",rb.getText());
                startActivity(i);
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg.clearCheck();
            }
        });



    }
}
