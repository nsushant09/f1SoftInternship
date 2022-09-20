package com.neupanesushant.bankingdashboardclone.user_data.domain;

public class UserDataUc {

    private final UserRepository userRepository;

    public UserDataUc(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUser() {
        return userRepository.getUser();
    }

    public void insertUser(UserModel model) {
        userRepository.insertUser(model);
    }

    public int updateUser(UserModel model) {
        return userRepository.updateUser(model);
    }

}