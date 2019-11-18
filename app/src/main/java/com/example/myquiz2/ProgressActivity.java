package com.example.myquiz2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressActivity extends AppCompatActivity {
    Button btnNext;
    SeekBar myseekbar;
    String checkboxes;
    String range;
    TextView tv1, tvChange;
    int progressChangedValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
          checkboxes = getIntent().getExtras().getString("checkboxes");
          range = getIntent().getExtras().getString("range");
        Toast.makeText(ProgressActivity.this, "Checkboxes = "+checkboxes+"\nselect name = "+range+"\nTotal Questions = "+progressChangedValue, Toast.LENGTH_SHORT).show();

        btnNext = (Button)findViewById(R.id.btnNext);
        myseekbar = (SeekBar)findViewById(R.id.myseekbar);
        tv1 = (TextView)findViewById(R.id.tv1);
        tvChange = (TextView)findViewById(R.id.tvChange);

        myseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                tvChange.setText(progressChangedValue+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(ProgressActivity.this, "Total Questions = "+progressChangedValue, Toast.LENGTH_SHORT).show();
                tv1.setText(progressChangedValue+"/"+seekBar.getMax());
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProgressActivity.this, MathsActivity.class);
                i.putExtra("checkboxes", checkboxes);
                i.putExtra("range", range);
                i.putExtra("totalQuestion", String.valueOf(progressChangedValue));
                startActivity(i);
            }
        });


    }
}
