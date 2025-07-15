package com.godspeed.gameschhalaang.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.godspeed.gameschhalaang.community.CommunityActivity;
import com.godspeed.gameschhalaang.game.activities.ChooseSymbolActivity;
import com.godspeed.gameschhalaang.game.activities.SettingsActivity;
import com.godspeed.gameskraftchhalaang.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class DashboardActivity extends AppCompatActivity {

    private CardView gamesSection, communitySection;
    private CircularImageView settingsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        gamesSection = findViewById(R.id.games_section);
        communitySection = findViewById(R.id.community_section);
        settingsButton = findViewById(R.id.settings_btn);
        gamesSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ChooseSymbolActivity.class);
                startActivity(intent);
            }
        });

        communitySection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
