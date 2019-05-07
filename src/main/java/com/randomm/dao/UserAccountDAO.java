package com.randomm.dao;

import com.randomm.domain.UserAccount;

public interface UserAccountDAO {
    void addUserAccount(UserAccount userAccount);
    UserAccount findUserAccountByUsername(String username);
    boolean updateSurname(String username, String newSurname);
}
