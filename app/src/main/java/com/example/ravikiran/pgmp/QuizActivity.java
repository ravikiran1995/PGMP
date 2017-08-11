package com.example.ravikiran.pgmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.*;
import com.firebase.client.DataSnapshot;
import com.firebase.client.ValueEventListener;
public class QuizActivity extends AppCompatActivity {
private TextView mQuetsion,mRationale;
    private Button mchoice1,mchoice2,mchoice3,mchoice4,mquit;
private int mQuestionNumber=0;
    private String mAnswer;

    private Firebase mQuestionRef,mChoice1Ref,mChoice2Ref,mChoice3Ref,mChoice4Ref,mAnswerRef,mRationaleRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuetsion = (TextView) findViewById(R.id.question);

        mchoice1= (Button) findViewById(R.id.choice1);
        mchoice2= (Button) findViewById(R.id.choice2);
        mchoice3= (Button) findViewById(R.id.choice3);
        mchoice4= (Button) findViewById(R.id.choice4);

        updateQuestion();


        mchoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoice1.getText().equals(mAnswer)){
                    updateQuestion();
                }else
                {
                    updateQuestion();
                }
            }
        });
        /*button 2*/

        mchoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoice2.getText().equals(mAnswer)){
                    updateQuestion();
                }else
                {
                    updateQuestion();
                }
            }
        });
        /*button3*/

        mchoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoice3.getText().equals(mAnswer)){
                    updateQuestion();
                }else
                {
                    updateQuestion();
                }
            }
        });
/*button 4*/

        mchoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoice4.getText().equals(mAnswer)){
                    updateQuestion();
                }else
                {
                    updateQuestion();
                }
            }
        });

    }
    public void updateQuestion()
    {
mQuestionRef=new Firebase("https://pgmp-80545.firebaseio.com/"+mQuestionNumber+"/Question");

        mQuestionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            String question=dataSnapshot.getValue(String.class);
                mQuetsion.setText(question);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        mChoice1Ref=new Firebase("https://pgmp-80545.firebaseio.com/"+mQuestionNumber+"/choice1");

        mChoice1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice=dataSnapshot.getValue(String.class);
                mchoice1.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mChoice2Ref=new Firebase("https://pgmp-80545.firebaseio.com/"+mQuestionNumber+"/choice2");

        mChoice2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice=dataSnapshot.getValue(String.class);
                mchoice2.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mChoice3Ref=new Firebase("https://pgmp-80545.firebaseio.com/"+mQuestionNumber+"/choice3");

        mChoice3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice=dataSnapshot.getValue(String.class);
                mchoice3.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mChoice4Ref=new Firebase("https://pgmp-80545.firebaseio.com/"+mQuestionNumber+"/choice4");

        mChoice4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice=dataSnapshot.getValue(String.class);
                mchoice4.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



mAnswerRef=new Firebase("https://pgmp-80545.firebaseio.com/"+mQuestionNumber+"/Answer");
        mAnswerRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mAnswer=dataSnapshot.getValue(String.class);

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });

        mRationaleRef=new Firebase("https://pgmp-80545.firebaseio.com/"+mQuestionNumber+"/Rationale");
        mRationaleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Rationale=dataSnapshot.getValue(String.class);
                mRationale.setText(Rationale);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mQuestionNumber++;
    }

}
