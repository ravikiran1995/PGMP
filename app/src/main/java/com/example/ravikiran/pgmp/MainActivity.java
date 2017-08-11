package com.example.ravikiran.pgmp;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.navigation_layout);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void click(View v) {
        Intent intent = new Intent();
        switch(v.getId()) {
            case R.id.txt_btn_mock_tests: // R.id.textView1
                intent = new Intent(this, QuizActivity.class);
                break;
            case R.id.txt_btn_quiz: // R.id.textView2
                Toast.makeText(MainActivity.this, "Comming Soon",
                        Toast.LENGTH_LONG).show();
                break;

        }
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
if (mToggle.onOptionsItemSelected(item))
{
    return true;
}
return super.onOptionsItemSelected(item);
    }
}
