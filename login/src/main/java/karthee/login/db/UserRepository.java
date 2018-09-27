package karthee.login.db;

import android.arch.lifecycle.LiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import karthee.login.MApplication;
import karthee.login.db.local.AppDatabase;
import karthee.login.db.local.UserDao;
import karthee.login.model.LoginRequest;
import karthee.login.model.LoginResponse;
import karthee.login.utils.RxUtils;

import static karthee.login.utils.NetworkUtils.getAPIService;


public class UserRepository {
    UserDao userDao;
    Executor executor;

    public UserRepository() {
        this.userDao = AppDatabase.getAppDatabase(MApplication.context).userDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void clearUserCached() {
        executor.execute(() -> {
            userDao.deleteAll();
        });
    }

    public void loginUser(String email, String password) {

        getAPIService().login(new LoginRequest(email, password))
            .compose(RxUtils.applySchedulers())
            .subscribe(
                (LoginResponse response) -> {
                    executor.execute(() -> {
                        userDao.insert(response.getUser());
                    });
                },
                (Throwable e) -> {
                    e.printStackTrace();
                }
            );
    }

    public LiveData<LoginResponse.User> getUser() {
        return userDao.getUser();
    }
}

