package com.neupanesushant.bankingdashboardclone;

import android.os.Bundle;
import android.service.autofill.UserData;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.neupanesushant.bankingdashboardclone.user_data.data.DataBaseUserRepoImpl;
import com.neupanesushant.bankingdashboardclone.user_data.data.UserRepoImpl;
import com.neupanesushant.bankingdashboardclone.user_data.domain.UserDataUc;
import com.neupanesushant.bankingdashboardclone.user_data.vm.UserDataViewModel;

public class UserDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserData();
    }

    private void getUserData() {
        //UserRepoImpl userRepo = new UserRepoImpl();
        DataBaseUserRepoImpl userRepo = new DataBaseUserRepoImpl();

        UserDataUc userDataUc = new UserDataUc(userRepo);
        UserDataViewModel viewModel = new UserDataViewModel(userDataUc);

        viewModel.getUser();
    }
}
