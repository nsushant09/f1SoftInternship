package com.neupanesushant.bankingdashboardclone.user_data.domain;

public interface UserRepository {

    UserModel getUser();

    void insertUser(UserModel model);

    int updateUser(UserModel model);
}
