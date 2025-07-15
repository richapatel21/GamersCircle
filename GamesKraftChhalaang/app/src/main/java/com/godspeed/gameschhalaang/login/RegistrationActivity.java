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

import com.godspeed.gameskraftchhalaang.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private TextView screenTitle;
    private ImageView orgLogo;
    private EditText regEmail;
    private EditText regPassword;
    private Button regButton;
    private Button linkLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        screenTitle = findViewById(R.id.screen_title);
        orgLogo = findViewById(R.id.org_logo);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        regButton = findViewById(R.id.btn_reg);
        linkLogin = findViewById(R.id.link_login);
        mAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String retrieveEmail, retrievePassword;
                retrieveEmail = regEmail.getText().toString();
                retrievePassword = regPassword.getText().toString();

                if(retrieveEmail.isEmpty()){
                    Toast.makeText(RegistrationActivity.this,"Email field is empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if(retrievePassword.isEmpty()){
                    Toast.makeText(RegistrationActivity.this,"Password field is empty", Toast.LENGTH_LONG).show();
                    return;
                }

//                below code will be responsible for sending register user data to the server
                mAuth.createUserWithEmailAndPassword(retrieveEmail, retrievePassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistrationActivity.this,
                                            "User account is created successfully", Toast.LENGTH_LONG).show();

                                    //after account is created it will navigate to logout page, it will show user's email
                                    Intent intent = new Intent(RegistrationActivity.this, LogoutActivity.class);
                                    startActivity(intent);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegistrationActivity.this, "User Account creation failed, password should be 6 characters",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    // IMP: if user is loggedIn already then it won't open the LOGIN/ REG screens
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(RegistrationActivity.this, LogoutActivity.class);
            startActivity(intent);
        }
    }

}