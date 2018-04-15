package com.example.bankblood.Activities;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.bankblood.Dialogs.DonateDialog;
import com.example.bankblood.Dialogs.MapSearchDialog;
import com.example.bankblood.Dialogs.SearchDialog;
import com.example.bankblood.Fragments.HomeFragment;
import com.example.bankblood.Models.Messages.Messages;
import com.example.bankblood.R;
import com.example.bankblood.Utils.Callbacks;
import com.example.bankblood.Utils.MySharedPreferences;
import com.example.bankblood.Utils.RestApiRequests.GetIncomingMessagesRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("بنك الدم");

        MySharedPreferences.setUpMySharedPreferences(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    HomeActivity.this.finish();
                }
            }
        };
        addHomeFragment();
        checkInboxUnreadMsg();
    }

    private void addHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.container, homeFragment).commit();
    }

    //sign out method
    public void signOut() {
        auth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            profileMethod();
        } else if (id == R.id.nav_inbox) {
            inboxMethod();
        } else if (id == R.id.nav_map) {
            mapMethod();
        } else if (id == R.id.nav_logout) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void mapMethod() {
        MapSearchDialog mapSearchDialog = new MapSearchDialog(this);
        mapSearchDialog.show();
    }

    private void inboxMethod() {
        Intent intent = new Intent(this, InboxActivity.class);
        startActivity(intent);
    }

    private void profileMethod() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.setAction("me");
        int id = Integer.valueOf(MySharedPreferences.getUserSetting("id"));
        intent.putExtra("person_id", id);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }

    private void checkInboxUnreadMsg() {
        int id = Integer.valueOf(MySharedPreferences.getUserSetting("id"));
        GetIncomingMessagesRequest getIncomingMessagesRequest = new GetIncomingMessagesRequest(id);
        getIncomingMessagesRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Messages messages = (Messages) obj;
                if (messages != null && messages.data != null && messages.data.size() > 0) {
                    if (!messages.data.get(0).read) {
                        handleDataMessage(messages.data.get(0).from.name, messages.data.get(0).title);
                    } else {

                    }
                }
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });
        getIncomingMessagesRequest.start();
    }

    private void handleDataMessage(String name, String msg) {
        try {
            Notification notification = createNotification(name, msg);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(485, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(this, InboxActivity.class);
        return PendingIntent.getActivity(this, 678, intent, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification createNotification(String sender, String msg) {

        PendingIntent pendingIntent = createPendingIntent();

        Notification notification = new Notification.Builder(this)
                .setContentTitle("بنك الدم")
                .setSubText(sender)
                .setContentText(msg)
                .setSmallIcon(R.drawable.circlar_logo)
                .setContentIntent(pendingIntent)
                .build();

        return notification;
    }

}
