package com.example.topdeckv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.topdeckv3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new CreateFragment());

        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
//                case R.id.home:
//                    replaceFragment(new HomeFragment());
//                    break;
                case R.id.create:
                    replaceFragment(new CreateFragment());
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_frame, fragment)
                .commit();
    }
}