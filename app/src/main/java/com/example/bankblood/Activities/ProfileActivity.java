package com.example.bankblood.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bankblood.Fragments.EditProfileFragment;
import com.example.bankblood.Fragments.ProfileFragment;
import com.example.bankblood.R;

public class ProfileActivity extends AppCompatActivity {

    String action;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("الصفحة الشخصية");

        Intent intent=getIntent();
        bundle=intent.getExtras();
        action=intent.getAction();

        addProfileFragment(bundle);

    }

    private void addProfileFragment(Bundle bundle) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        ProfileFragment profileFragment=new ProfileFragment();
        profileFragment.setArguments(bundle);

        Fragment oldFragment=fragmentManager.findFragmentByTag("EditProfileFragment");
        if(oldFragment!=null)
            fragmentManager.beginTransaction().remove(oldFragment);

        fragmentManager.beginTransaction().replace(R.id.container,profileFragment,"ProfileFragment").commit();
    }

    private void addEditProfileFragment(Bundle bundle) {
       FragmentManager fragmentManager=getSupportFragmentManager();
        EditProfileFragment  editProfileFragment=new EditProfileFragment();
        editProfileFragment.setArguments(bundle);

        Fragment oldFragment=fragmentManager.findFragmentByTag("ProfileFragment");
        if(oldFragment!=null)
            fragmentManager.beginTransaction().remove(oldFragment);

        fragmentManager.beginTransaction().replace(R.id.container,editProfileFragment,"EditProfileFragment").commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(action!=null&&action.equals("me"))
            getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        switch (id){
            case R.id.action_settings:
                addEditProfileFragment(bundle);
            break;
        }

        return super.onOptionsItemSelected(item);
    }
}
