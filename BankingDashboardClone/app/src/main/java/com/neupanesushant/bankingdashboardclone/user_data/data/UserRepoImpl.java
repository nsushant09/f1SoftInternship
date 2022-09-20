package com.neupanesushant.bankingdashboardclone.user_data.data;

import androidx.dynamicanimation.animation.SpringAnimation;

import com.neupanesushant.bankingdashboardclone.user_data.domain.UserModel;
import com.neupanesushant.bankingdashboardclone.user_data.domain.UserRepository;

public class UserRepoImpl implements UserRepository {

    @Override
    public UserModel getUser() {
        return new UserModel("From Network", "1");
    }

    @Override
    public void insertUser(UserModel model) {

    }

    @Override
    public int updateUser(UserModel model) {
        return 0;
    }
}
