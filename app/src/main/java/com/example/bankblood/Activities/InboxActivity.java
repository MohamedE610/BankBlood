package com.example.bankblood.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bankblood.Fragments.ReceivedMessagesFragment;
import com.example.bankblood.Fragments.SentMessagesFragment;
import com.example.bankblood.R;

public class InboxActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_inbox);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addOnTabSelectedListener(this);
        addReceivedMsgFragment();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position=tab.getPosition();
        switch (position){
            case 0:
                addReceivedMsgFragment();
                break;
            case 1:
                addSentMsgFragment();
                break;
        }
    }

    private void addSentMsgFragment() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        SentMessagesFragment sentMessagesFragment=new SentMessagesFragment();
        fragmentManager.beginTransaction().replace(R.id.container,sentMessagesFragment).commit();
    }


    private void addReceivedMsgFragment() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        ReceivedMessagesFragment receivedMessagesFragment=new ReceivedMessagesFragment();
        fragmentManager.beginTransaction().replace(R.id.container,receivedMessagesFragment).commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
