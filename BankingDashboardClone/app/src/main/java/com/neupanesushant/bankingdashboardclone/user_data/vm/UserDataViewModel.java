package com.neupanesushant.bankingdashboardclone.user_data.vm;

import androidx.lifecycle.ViewModel;

import com.neupanesushant.bankingdashboardclone.user_data.domain.UserDataUc;
import com.neupanesushant.bankingdashboardclone.user_data.domain.UserModel;

public class UserDataViewModel extends ViewModel {

    private final UserDataUc userDataUc;

    public UserDataViewModel(UserDataUc userDataUc) {
        this.userDataUc = userDataUc;
    }

    public void getUser() {
        UserModel userModel = userDataUc.getUser();
        System.out.println("User Name " + userModel.getName());
    }

    void insertUser(UserModel userModel) {
        userDataUc.insertUser(userModel);
    }

    void updateUser(UserModel userModel) {
        int update = userDataUc.updateUser(userModel);
    }
}
