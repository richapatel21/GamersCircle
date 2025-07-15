package com.godspeed.gameschhalaang.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.godspeed.gameschhalaang.dashboard.DashboardActivity;
import com.godspeed.gameschhalaang.game.activities.ChooseSymbolActivity;
import com.godspeed.gameskraftchhalaang.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView screenTitle;
    private ImageView orgLogo;
    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginButton;
    private Button linkRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        screenTitle = findViewById(R.id.screen_title);
        orgLogo = findViewById(R.id.org_logo);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.btn_login);
        linkRegister = findViewById(R.id.link_register);
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String retrieveEmail, retrievePassword;
                retrieveEmail = loginEmail.getText().toString();
                retrievePassword = loginPassword.getText().toString();

                if(retrieveEmail.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Email field is empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if(retrievePassword.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Password field is empty", Toast.LENGTH_LONG).show();
                    return;
                }

//              this below method will validate is user is registered or not
                mAuth.signInWithEmailAndPassword(retrieveEmail, retrievePassword)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(LoginActivity.this,
                                            "Login is successful", Toast.LENGTH_LONG).show();

                                    //after account is created it will navigate to logout page, it will show user's email

                                    //TODO add this to settings
                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                    startActivity(intent);

//                                    Intent intent = new Intent(LoginActivity.this, LogoutActivity.class);
//                                    startActivity(intent);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginActivity.this, "Authentication failed, User is not registered",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    // IMP: if user is loggedIn already then it won't open the LOGIN/ REG screens

//    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //TODO add this to settings
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
//            Intent intent = new Intent(LoginActivity.this, LogoutActivity.class);
//            startActivity(intent);
        }
    }
}