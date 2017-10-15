package com.inducesmile.androidmultiquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.inducesmile.androidmultiquiz.adapters.QuizCategoryAdapter;
import com.inducesmile.androidmultiquiz.database.DatabaseQuery;
import com.inducesmile.androidmultiquiz.entities.CategoryObject;

import java.util.List;

import static com.inducesmile.androidmultiquiz.R.id.viewUsers;

public class QuizCategoryActivity extends AppCompatActivity {

    private static final String TAG = QuizCategoryActivity.class.getSimpleName();

    private RecyclerView quizRecyclerView;
    private Button quiz_instruction_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_category);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml("<font color='#e1c8d6'>Quiz Categories</font>"));
        //setTitle(getString(R.string.quiz_category));

        DatabaseQuery dbQuery = new DatabaseQuery(QuizCategoryActivity.this);
        List<CategoryObject> categoryData = dbQuery.getAllQuizCategory();

        quizRecyclerView = (RecyclerView)findViewById(R.id.quiz_category);
        GridLayoutManager mGrid = new GridLayoutManager(this, 2);
        quizRecyclerView.setLayoutManager(mGrid);
        quizRecyclerView.setHasFixedSize(true);

        QuizCategoryAdapter mAdapter = new QuizCategoryAdapter(QuizCategoryActivity.this, categoryData);
        quizRecyclerView.setAdapter(mAdapter);


    }
}
