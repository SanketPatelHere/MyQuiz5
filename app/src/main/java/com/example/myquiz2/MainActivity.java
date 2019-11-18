package com.example.myquiz2;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    CheckBox chkAdd, chkSub, chkMul, chkDiv;
    Button btnNext;
    ArrayList<String> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chkAdd = (CheckBox)findViewById(R.id.chkAdd);
        chkSub = (CheckBox)findViewById(R.id.chkSub);
        chkMul = (CheckBox)findViewById(R.id.chkMul);
        chkMul = (CheckBox)findViewById(R.id.chkMul);
        chkDiv = (CheckBox)findViewById(R.id.chkDiv);
        btnNext = (Button)findViewById(R.id.btnNext);
        lst = new ArrayList<>();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QuestionActivity.class);
                i.putExtra("checkboxes",lst.toString());
                startActivity(i);
            }
        });
        Log.i("My Checkboxes = ",lst+"");
    }
    public void onCheckboxClicked(View view)
    {
        boolean checked =((CheckBox)view).isChecked();
        switch (view.getId())
        {
            case R.id.chkAdd:
                if(checked)
                {
                    Toast.makeText(this, "Checked = "+chkAdd.getText().toString(), Toast.LENGTH_SHORT).show();
                    lst.add(chkAdd.getText().toString());
                    Collections.sort(lst);
                }
                else
                {
                    lst.remove(chkAdd.getText().toString());
                    Collections.sort(lst);

                }
                break;
            case R.id.chkSub:
                if(checked)
                {
                    Toast.makeText(this, "Checked = "+chkSub.getText().toString(), Toast.LENGTH_SHORT).show();
                    lst.add(chkSub.getText().toString());
                    Collections.sort(lst);
                }
                else
                {
                    lst.remove(chkSub.getText().toString());
                    Collections.sort(lst);
                }
                break;
            case R.id.chkMul:
                if(checked)
                {
                    Toast.makeText(this, "Checked = "+chkMul.getText().toString(), Toast.LENGTH_SHORT).show();
                    lst.add(chkMul.getText().toString());
                    Collections.sort(lst);
                }
                else
                {
                    lst.remove(chkMul.getText().toString());
                    Collections.sort(lst);
                }
                break;
            case R.id.chkDiv:
                if(checked)
                {
                    Toast.makeText(this, "Checked = "+chkDiv.getText().toString(), Toast.LENGTH_SHORT).show();
                    lst.add(chkDiv.getText().toString());
                    Collections.sort(lst);
                    Log.i("My Division Checkbox",lst+"");
                }
                else
                {
                    lst.remove(chkDiv.getText());
                    Collections.sort(lst);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
