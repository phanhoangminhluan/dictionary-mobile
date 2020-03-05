package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.dictionary.fragment.FavoriteFragment;
import com.example.dictionary.fragment.FlashcardFragment;
import com.example.dictionary.fragment.HomeFragment;
import com.example.dictionary.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;


public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private int fragmentSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,
                        new HomeFragment()).commit();
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment  = null;
            if(menuItem.getItemId() == fragmentSelected) {
                return true;
            }
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    fragmentSelected =R.id.nav_home;
                    break;
                case R.id.nav_flashcard:
                    selectedFragment = new FlashcardFragment();
                    fragmentSelected =R.id.nav_flashcard;
                    break;
                case R.id.nav_favo:
                    selectedFragment = new FavoriteFragment();
                    fragmentSelected = R.id.nav_favo;
                    break;
                case R.id.nav_setting:
                    selectedFragment = new SettingFragment();

                    break;

                default: return true;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,
                            selectedFragment).commit();
            return true;
        }
    };


}
//                .setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_right) làm hiệu ứng
