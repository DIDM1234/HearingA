package com.example.sujin.hearinga;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SaveProfile extends AppCompatActivity implements MyListener {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentIconContainer);

        if (fragment == null) {
            fragment = new IconListViewFragment();
            ;
            fm.beginTransaction()
                    .add(R.id.fragmentIconContainer, fragment)
                    .commit();
        }

        mButton = (Button) findViewById(R.id.okbutton);
        mButton.setOnClickListener(mbuttonOnClick);
    }

    View.OnClickListener mbuttonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveProfile();
        }
    };

    private void saveProfile()
    {
        Account account = new Account(0,"ทดสอบ","office");
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.insertAccount(account);
        databaseAccess.close();
    }


    public void listener(int id,String name) {

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
