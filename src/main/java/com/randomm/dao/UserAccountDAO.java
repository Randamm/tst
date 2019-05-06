package com.randomm.dao;

import com.randomm.domain.UserAccount;

public interface UserAccountDAO {
    void addUserAccount(UserAccount userAccount);
    UserAccount findUserAccountByUsername(String username);
    void updateSurname(String username, String newSurname);
}
