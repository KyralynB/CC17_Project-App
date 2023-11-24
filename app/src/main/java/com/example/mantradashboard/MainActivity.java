package com.example.mantradashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.RouteListingPreference;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mantradashboard.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new FragmentHome());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.menu_home) {
                replaceFragment(new FragmentHome());
            } else if (item.getItemId() == R.id.menu_items) {
                replaceFragment(new FragmentItemInventory());
            } else if (item.getItemId() == R.id.menu_requests) {
                replaceFragment(new FragmentInventoryRequest());
            } else if (item.getItemId() == R.id.menu_users) {
                replaceFragment(new FragmentUserManagement());
            }
            return true;
        });
    }

    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}