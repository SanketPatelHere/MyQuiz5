package com.example.myquiz2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MathsActivity extends AppCompatActivity {
    TextView tvQuestionNo, tvQuestion;
    EditText etanswer;
    Button btnsubmit;

    int countQuestion = 1;
    String checkboxes;
    String range;
    String totalQuestion;
    int totalQuestion1;
    String finalQuestionType = "null";
    int min, max;
    int no1, no2;
    int answer;
    static int myscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);

        try {

            checkboxes = getIntent().getExtras().getString("checkboxes");
            range = getIntent().getExtras().getString("range");
            totalQuestion = getIntent().getExtras().getString("totalQuestion");
            Toast.makeText(MathsActivity.this, "Maths : Checkboxes = " + checkboxes + "\nselect name = " + range + "\nTotal Questions = " + totalQuestion, Toast.LENGTH_SHORT).show();
            totalQuestion1 = Integer.parseInt(totalQuestion);
            Log.i("My totalQuestion1",totalQuestion1+"");

            tvQuestionNo = (TextView) findViewById(R.id.tvQuestionNo);
            tvQuestion = (TextView) findViewById(R.id.tvQuestion);
            etanswer = (EditText) findViewById(R.id.etanswer);
            btnsubmit = (Button) findViewById(R.id.btnsubmit);

            if(range.equalsIgnoreCase("0 - 10"))
            {   //Log.i("My","inside");
                min = 0;
                max = 10;
            }
            else if(range.equalsIgnoreCase("10 - 20"))
            {
                min = 10;
                max = 20;
            }
            else if(range.equalsIgnoreCase("20 - 30"))
            {
                min = 20;
                max = 30;
            }
            else if(range.equalsIgnoreCase("30 - 40"))
            {
                min = 30;
                max = 40;
            }
            else if(range.equalsIgnoreCase("40 - 50"))
            {
                min = 40;
                max = 50;
            }

            /////main
            updateQuestion();
            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MathsActivity.this, "countQuestion = "+countQuestion, Toast.LENGTH_SHORT).show();
                    ///////////////////////////////////////////////////////////////
                    ////////answer checking, score update///////////////
                    Log.i("My Answer = "," "+answer);
                    if(etanswer.getText().toString().equalsIgnoreCase(answer+""))
                    {
                        myscore++;
                        Log.i("My Score = "," "+myscore);
                        etanswer.setText("");
                        updateScore(myscore);
                        Toast.makeText(MathsActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        etanswer.setText("");
                        Toast.makeText(MathsActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    }

                    ///////////////////////////////////////////////////////////////
                    updateQuestion();

                }
            });
            /////////////////////////////////////////////
            ////save data of whole activity///////////
            savedInstanceState.putInt("totalQuestion1",totalQuestion1);
            /////////////////////////////////////////////
        }
        catch (Exception e)
        {
            Log.e("My Exception",e+"");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int totalQuestion1 = savedInstanceState.getInt("totalQuestion1");
        //Log.i("My onRestore"," totalQuestion1 = "+totalQuestion1);
    }

    public void updateQuestion()
    {
        if(countQuestion>totalQuestion1)  //1>=5  => 5>=5
        {
            Intent i = new Intent(MathsActivity.this, ResultActivity.class);
            if(myscore>totalQuestion1+1)
            {
                myscore=0;
            }
            i.putExtra("totalQuestions",String.valueOf(totalQuestion1));
            i.putExtra("result",String.valueOf(myscore));
            startActivity(i);
            countQuestion = 1;
        }
        else
        {
        tvQuestionNo.setText("Question " + countQuestion + " out of " + totalQuestion1);
        ///////////////////////////////////////////////////////////
        ////////random number///////////////////
        int num1 = new Random().nextInt((max-min)+1)+min; //(10-0+1)+0 = 11+0 = 11 = 0,10
        int num2 = new Random().nextInt((max-min)+1)+min; //(10-0+1)+0 = 11+0 = 11
            if(num1>num2)
            {
                no1 = num1;
                no2 = num2;

            }
            else if(num2>num1)
            {
                no1 = num2;
                no2 = num1;
            }
        Log.i("My Random"," Number1 = "+num1);
        Log.i("My Random"," Number2 = "+num2);
        //////////////////////////////////////////////////////////

        if(checkboxes.equalsIgnoreCase("[Addition]"))
        {
            tvQuestion.setText(no1+"+"+no2);
            answer = no1+no2;
        }
        else if(checkboxes.equalsIgnoreCase("[Substraction]"))
        {
            tvQuestion.setText(no1+"-"+no2);
            answer = no1-no2;
        }
        else if(checkboxes.equalsIgnoreCase("[Multiplication]"))
        {
            tvQuestion.setText(no1+"*"+no2);
            answer = no1*no2;
        }
        else if(checkboxes.equalsIgnoreCase("[Division]"))
        {
            tvQuestion.setText(no1+"/"+no2);
            answer = no1/no2;
        }
        else if(checkboxes.equalsIgnoreCase("[Addition, Division, Multiplication, Substraction]"))
        {
            String type = "+-*/";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(4);
            String s = "";
            for(int i=0;i<4;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"ADMS"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Addition, Division, Multiplication]"))
        {
            String type = "+*/";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(3);
            String s = "";
            for(int i=0;i<3;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"ADM"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Addition, Multiplication, Substraction]"))
        {
            String type = "+*-";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(3);
            String s = "";
            for(int i=0;i<3;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"AMS"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Addition, Division, Substraction]"))
        {
            String type = "+*-";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(3);
            String s = "";
            for(int i=0;i<3;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"ADS"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Division, Multiplication, Substraction]"))
        {
            String type = "-*/";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(3);
            String s = "";
            for(int i=0;i<3;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"DMS"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Division, Multiplication]"))
        {
            String type = "*/";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(3);
            String s = "";
            for(int i=0;i<3;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"DMS"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Multiplication, Substraction]"))
        {
            String type = "-*";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(2);
            String s = "";
            for(int i=0;i<2;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //answer = no1+Integer.parseInt(finalQuestionType)+no2;
            /*if(finalQuestionType.equalsIgnoreCase("+"))
            {
                answer = no1*no2;
            }
            else if(finalQuestionType.equalsIgnoreCase("-"))
            {
                answer = no1-no2;
            }
            else if(finalQuestionType.equalsIgnoreCase("*"))
            {
                answer = no1*no2;
            }
            else if(finalQuestionType.equalsIgnoreCase("/"))
            {
                answer = no1/no2;
            }*/

            //tvQuestion.setText("100 "+"MS"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Addition, Substraction]"))
        {
            String type = "+-";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(2);
            String s = "";
            for(int i=0;i<2;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"AS"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Division, Substraction]"))
        {
            String type = "-/";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(2);
            String s = "";
            for(int i=0;i<2;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"DS"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Addition, Division]"))
        {
            String type = "+/";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(2);
            String s = "";
            for(int i=0;i<2;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"AD"+" 200");
        }
        else if(checkboxes.equalsIgnoreCase("[Addition, Multiplication]"))
        {
            String type = "+*";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(2);
            String s = "";
            for(int i=0;i<2;i++)
            {
                s = sb.append(type.charAt(random.nextInt(type.length()))).toString();
            }
            finalQuestionType = s.charAt(0)+"";
            tvQuestion.setText(no1+finalQuestionType+no2);
            //tvQuestion.setText("100 "+"AM"+" 200");
        }

            if(finalQuestionType.equalsIgnoreCase("+"))
            {
                answer = no1+no2;
            }
            else if(finalQuestionType.equalsIgnoreCase("-"))
            {
                answer = no1-no2;
            }
            else if(finalQuestionType.equalsIgnoreCase("*"))
            {
                answer = no1*no2;
            }
            else if(finalQuestionType.equalsIgnoreCase("/"))
            {
                answer = no1/no2;
            }
            countQuestion++;


        }
    }
    public void updateScore(int myscore)
    {
        //score.setText("Score = "+myscore);
    }
}
