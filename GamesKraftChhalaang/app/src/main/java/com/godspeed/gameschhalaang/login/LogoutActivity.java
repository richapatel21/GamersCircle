package com.godspeed.gameschhalaang.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.godspeed.gameskraftchhalaang.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogoutActivity extends AppCompatActivity {

    private String TAG = LogoutActivity.class.getSimpleName();
    private TextView showLoggedInUserEMail;
    private Button logoutButton;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        showLoggedInUserEMail = findViewById(R.id.show_loggedIn_user_email);
        logoutButton = findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String retrieveLoggedInUserEmail;
        retrieveLoggedInUserEmail = user.getEmail().toString();

//        if(user == null) {
            Log.i(TAG, "retrieveLoggedInUserEmail: "+retrieveLoggedInUserEmail);
            showLoggedInUserEMail.setText(retrieveLoggedInUserEmail);
//        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // sign out from loggedIn account
                mAuth.signOut();

                // again it should navigate the user to the login screen
                Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}