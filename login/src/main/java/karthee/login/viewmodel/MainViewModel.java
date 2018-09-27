package karthee.login.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import karthee.login.db.UserRepository;
import karthee.login.model.LoginResponse;

public class MainViewModel extends ViewModel {

    private UserRepository userRepository;

    public MainViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<LoginResponse.User> getUser() {
        return userRepository.getUser();
    }


    public void clearUserData() {
        userRepository.clearUserCached();
    }
}
