package com.example.ravikiran.pgmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    Fragment fragment=null;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }


        }
    };


        mDrawerLayout= (DrawerLayout) findViewById(R.id.navigation_layout);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//navigtaion item selection


navigationView= (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.nav_home:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_about_us:
                        Intent abtus = new Intent(MainActivity.this, AboutUs.class);
                        startActivity(abtus);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_articles:
                        Intent artact = new Intent(MainActivity.this, FreeArticles.class);
                        startActivity(artact);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_invite_friends:
                        mDrawerLayout.closeDrawers();
                        Intent inviteIntent=new Intent(Intent.ACTION_SEND);
                        inviteIntent.setType("text/plain");
                        String inviteBody="Invite body here";
                        String inviteSub="Invite subject here";
                        inviteIntent.putExtra(Intent.EXTRA_SUBJECT,inviteSub);
                        inviteIntent.putExtra(Intent.EXTRA_TEXT,inviteBody);
                        startActivity(Intent.createChooser(inviteIntent,"Invite via"));
                        break;
                    case R.id.nav_rate_us:
                        mDrawerLayout.closeDrawers();
                        Toast.makeText(getApplicationContext(), "rate us Later cont now", Toast.LENGTH_LONG).show();

//                        Intent rateApp=new Intent(Intent.ACTION_VIEW);
//                        rateApp.setData(Uri.parse("google account link here"));
//                        startActivity(rateApp);
                        break;
                    case R.id.nav_Share:
                        mDrawerLayout.closeDrawers();
                        Intent shareIntent=new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        String shareBody="share body here";
                        String shareSub="share subject here";
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                        shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        startActivity(Intent.createChooser(shareIntent,"Share the App using"));
                        break;
                    case R.id.logout:
                        auth.signOut();
                        break;
                    default:
                }
                return false;
            }
        });


    }
//toggle bar action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
if (mToggle.onOptionsItemSelected(item))
{
    return true;
}
return super.onOptionsItemSelected(item);
    }

    //text item selectio and on click procedure
    public void practise_test(View view)
    {
        Intent gotoQuiz = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(gotoQuiz);

        Toast.makeText(getApplicationContext(), "practise now master later", Toast.LENGTH_LONG).show();

    }
    public void quiz_test(View view)
    {
        Toast.makeText(getApplicationContext(), "Scale yourself  now", Toast.LENGTH_LONG).show();

    }
    public void free_test(View view)
    {
        Toast.makeText(getApplicationContext(), "practise made man perfect", Toast.LENGTH_LONG).show();

    }
    public void buy_test(View view)
    {
        Toast.makeText(getApplicationContext(), "Contact us | About Us ", Toast.LENGTH_LONG).show();

    }


}
