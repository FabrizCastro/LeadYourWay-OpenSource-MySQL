package com.lyw.LeadYourWay.service;

import com.lyw.LeadYourWay.model.User;

public interface UserService {
    public abstract User createUser(User user);
    public abstract User getUserById(Long user_id);
    public abstract User updateUser(User user);
    public abstract void deleteUser(Long user_id);
}
