package com.inducesmile.androidmultiquiz;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.pavlospt.CircleView;
import com.inducesmile.androidmultiquiz.database.DBHandler;
import com.inducesmile.androidmultiquiz.entities.Client;

import org.w3c.dom.Text;

import static com.inducesmile.androidmultiquiz.R.string.register;

public class QuizResultActivity extends AppCompatActivity {

    private DBHandler dbh;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        dbh = new DBHandler(QuizResultActivity.this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#e1c8d6'>Quiz Results</font>"));
        //setTitle(getString(R.string.quiz_result));

        final String resultString = getIntent().getExtras().getString("RESULT_OBJECT");
        String userScore = getIntent().getExtras().getString("TOTAL_SCORE");

        double passResult = Double.parseDouble(userScore);
        int userPassMark = new Double(passResult).intValue();

        //CircleView userPassScore = (CircleView)findViewById(R.id.pass);

        //userPassScore.setTitleText(String.valueOf(userPassMark) + "%");

        if(passResult < 25 && passResult >= 0) {
            TextView result = (TextView) findViewById(R.id.resultText);
            result.setText(getString(R.string.less_than_25));
        } else if (passResult >= 25 && passResult <= 49){
            TextView result = (TextView) findViewById(R.id.resultText);
            result.setText(getString(R.string.less_than_50));
        } else if (passResult >= 50 && passResult <= 74) {
            TextView result = (TextView) findViewById(R.id.resultText);
            result.setText(getString(R.string.less_than_75));
        }  else if (passResult >= 75 && passResult <= 100) {
            TextView result = (TextView) findViewById(R.id.resultText);
            result.setText(getString(R.string.less_than_100));
        } else {TextView result = (TextView) findViewById(R.id.resultText);
            result.setText("There has been a mistake");
        }
        String StringScore = Double.toString(passResult);
       // client.setScore(StringScore);
        client = new Client("name", "email", StringScore);
        dbh.addClient(client);

        Button quiz_home = (Button)findViewById(R.id.go_home);
        assert quiz_home != null;
        quiz_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent instructionIntent = new Intent(QuizResultActivity.this, QuizCategoryActivity.class);
                startActivity(instructionIntent);
            }
        });

        Button viewQuizResultButton = (Button)findViewById(R.id.view_result);
        assert viewQuizResultButton != null;
        viewQuizResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(QuizResultActivity.this, ResultAnalysisActivity.class);
                resultIntent.putExtra("RESULT", resultString);
                startActivity(resultIntent);
            }
        });




    };


}