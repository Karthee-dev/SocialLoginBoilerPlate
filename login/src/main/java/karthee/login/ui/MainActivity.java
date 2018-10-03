package karthee.login.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import karthee.login.R;
import karthee.login.viewmodel.MainViewModel;
import karthee.login.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // The observer updates the UI to display prefetched user details
        mViewModel.getUser().observe(this, userResponse -> {

            if (userResponse != null) {
                binding.txtWelcome.setText("Welcome " + userResponse.getName() + " " + userResponse.getRole()
                        + "\n\n" + "You are more than a " + userResponse.getUserguid());

            } else {
                logoutUser();
            }
        });
    }


    public void btnLogout(View view) {
        logoutUser();
    }

    private void logoutUser() {
        mViewModel.clearUserData();
        finish();
    }
}
