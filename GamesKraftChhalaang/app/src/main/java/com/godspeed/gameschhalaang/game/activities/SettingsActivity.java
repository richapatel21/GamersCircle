package com.godspeed.gameschhalaang.game.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.godspeed.gameschhalaang.login.LoginActivity;
import com.godspeed.gameschhalaang.login.LogoutActivity;
import com.godspeed.gameskraftchhalaang.R;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SettingsActivity extends AppCompatActivity {

    Switch vibrationSwitch;
    Switch soundSwitch;

    private LinearLayout rateUs,feedback ;

    private ImageView backBtn;

    private Button logoutButton;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_settings);
        logoutButton = findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String retrieveLoggedInUserEmail;
        retrieveLoggedInUserEmail = user.getEmail().toString();

         vibrationSwitch = (Switch)  findViewById(R.id.vibration_switch);
         soundSwitch = (Switch)  findViewById(R.id.sound_switch);

         backBtn = (ImageView) findViewById(R.id.settings_back_btn);
         rateUs = (LinearLayout) findViewById(R.id.rate_us_layout);
         feedback = (LinearLayout) findViewById(R.id.feedback_layout);

         if(MyServices.VIBRATION_CHECK)
         {
             vibrationSwitch.setChecked(true);
         }
         else if(!MyServices.VIBRATION_CHECK)
         {
             vibrationSwitch.setChecked(false);
         }



         vibrationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked)
               {
                   MyServices.VIBRATION_CHECK =true;
               }
               else {
                   MyServices.VIBRATION_CHECK= false;
               }
            }

        });



        if(MyServices.SOUND_CHECK)
        {
            soundSwitch.setChecked(true);
        }
        else if(!MyServices.SOUND_CHECK)
        {
            soundSwitch.setChecked(false);
        }


       soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    MyServices.SOUND_CHECK =true;
                }
                else {
                    MyServices.SOUND_CHECK= false;
                }
            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // sign out from loggedIn account
                mAuth.signOut();

                // again it should navigate the user to the login screen
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askRatings();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                composeEmail("Tic Tac Toe Feedback");

               /*
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "abdullahsh@hotmail.com"));

                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    //TODO smth
                }

                */
            }
        });

    }

    void askRatings() {
        ReviewManager manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(this, reviewInfo);
                flow.addOnCompleteListener(task2 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            } else  {

                //Toast.makeText(getBaseContext(), "App does'nt uploaded on Play Store", Toast.LENGTH_SHORT).show();
                // There was some problem, continue regardless of the result.
            }
        });
    }

    public void composeEmail( String subject) {
        try {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:abdullahsh123@hotmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Send feedback"));
        }
        } catch (ActivityNotFoundException e) {
            //TODO smth
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}