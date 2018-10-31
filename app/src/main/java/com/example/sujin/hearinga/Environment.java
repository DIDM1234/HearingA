package com.example.sujin.hearinga;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class Environment extends AppCompatActivity implements MyListener {


    private CircleImageView mCircleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCircleImageView = (CircleImageView)findViewById(R.id.add);
        mCircleImageView.setOnClickListener(circleViewOnClick);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new AvatarListViewFragment();
            ;
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }


    }


    View.OnClickListener circleViewOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            openHearingTestActivity();
        }
    };

    private void openHearingTestActivity()
    {
        Intent myIntent = new Intent(this,HearingTest.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void listener(int id,String name) {
        openHomeActivity(id,name);
    }
    private void openHomeActivity(int id,String name)
    {
        Intent myIntent = new Intent(this,Home.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.putExtra("id", id);
        myIntent.putExtra("name", name);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
