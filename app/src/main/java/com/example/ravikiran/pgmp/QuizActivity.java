package com.example.ravikiran.pgmp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private TextView tvQues;
    private TextView clock;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button next;
    private Button quit;
    private Button rationale;
    public static boolean flag=false;
    FirebaseAuth firebaseAuth;
    private Button previous;
    public static int quesNo=1;
    private String choice;
    public String rationaletxt;
    public static int score;
    private String ans;
    public static int count[]=new int[16];
    int milSec=100;
    int sec=60;
    int min=19;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tvQues=(TextView)findViewById(R.id.ques);
        clock=(TextView)findViewById(R.id.clock);
        radioGroup=(RadioGroup)findViewById(R.id.rg) ;
        radioButton1=(RadioButton)findViewById(R.id.a);
        radioButton2=(RadioButton)findViewById(R.id.b);
        radioButton3=(RadioButton)findViewById(R.id.c);
        radioButton4=(RadioButton)findViewById(R.id.d);
        previous=(Button)findViewById(R.id.previous);
        next=(Button)findViewById(R.id.next);
        quit=(Button)findViewById(R.id.quit);
        rationale=(Button)findViewById(R.id.rationale);

        databaseReference= mFirebaseDatabase.getReference();
        clock.setText("20:00:00");
        showQues();

        new CountDownTimer(1200000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(milSec>0){
                    milSec--;
                }
                if(milSec==0){
                    milSec=100;
                    sec--;
                }
                if(sec==0){
                    min--;
                    sec=60;
                }
                clock.setText(min+":"+sec+":"+milSec);
            }

            @Override
            public void onFinish() {
                calculateScore();
                submitScore();
                showScore();
            }
        }.start();

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                if(quesNo>1) {
                    quesNo--;
                    showQues();
                }
                if(quesNo<50){
                    next.setText("Next");
                }

            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                score = 0;
                calculateScore();


                //submitScore();
                showScore();


            }
        });
        rationale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(QuizActivity.this);
                alertDialog.setTitle("Rationale");
                alertDialog.setMessage(" "+rationaletxt+" ");
                alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        // Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                if (quesNo < 50) {
                    quesNo++;
                    next.setText("Next");
                    showQues();
                }
                if (quesNo == 50) {
                    next.setText("submit");
                    quesNo++;
                    return;
                }
                if (quesNo > 50) {
                    next.setText("Submit");

                    score = 0;
                    calculateScore();


                    //submitScore();
                    showScore();
                }
            }
        });
    }

    public void showScore() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(QuizActivity.this);
        alertDialog.setMessage("Your Score is"+score+"").setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //       Toast.makeText(NewTest.this,"Thank You" ,Toast.LENGTH_SHORT).show();

//                        StudentPageActivity.subject="quant";
//                        StudentPageActivity.count=count;
//                        StudentPageActivity.flag=false;
//                        finishAfterTransition();
                        startActivity(new Intent(QuizActivity.this,MainActivity.class));
                    }
                });
        AlertDialog alert=alertDialog.create();
        alert.setTitle("Score");
        alert.show();
    }

    public void calculateScore() {

        for(int i=1;i<=10;i++){
            if(count[i]==1){
                score++;
            }
        }
    }

    public void submitScore() {
//
//        firebaseAuth= FirebaseAuth.getInstance();
//        DatabaseReference databaseReference1=databaseReference.child("student").child(firebaseAuth.getCurrentUser().getUid());
//        databaseReference1.child("quant").setValue(score);
    }

    public void showQues(){

        DatabaseReference databaseReference1=mFirebaseDatabase.getReference(quesNo+"");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rationaletxt=dataSnapshot.child("Rationale").getValue().toString();

                String ques=dataSnapshot.child("Question").getValue().toString();
                final String a=dataSnapshot.child("choice1").getValue().toString();
                final String b=dataSnapshot.child("choice2").getValue().toString();
                final String c=dataSnapshot.child("choice3").getValue().toString();
                final String d=dataSnapshot.child("choice4").getValue().toString();
                ans=dataSnapshot.child("Answer").getValue().toString();
                choice="";

                tvQues.setText(ques);
                radioButton1.setText("A:"+a);
                radioButton2.setText("B:"+b);
                radioButton3.setText("C:"+c);
                radioButton4.setText("D:"+d);


                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                        int rgid =group.getCheckedRadioButtonId();
                        switch (rgid){
                            case R.id.a:
                                choice="A";
                                break;
                            case R.id.b:
                                choice="B";
                                break;
                            case R.id.c:
                                choice="C";
                                break;
                            case R.id.d:
                                choice="D";
                                break;
                        }

                        checkAns();

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
        startActivity(new Intent(QuizActivity.this,MainActivity.class));
    }

    public void checkAns() {
        if(choice.equals(ans)){
            Toast.makeText(QuizActivity.this,"Ur answer is right"  +"  ",Toast.LENGTH_SHORT).show();

            count[quesNo]=10;
        }
        else{
            Toast.makeText(QuizActivity.this,"Ur answer is wrong" ,Toast.LENGTH_SHORT).show();

            count[quesNo]=0;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
