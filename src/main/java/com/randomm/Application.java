package com.randomm;

import com.randomm.dao.UserAccountDAO;
import com.randomm.dao.UserAccountDAOImpl;
import com.randomm.db.PostgreSqlDB;
import com.randomm.domain.UserAccount;

public class Application {
    public static void main(String[] args){
        UserAccountDAO  userAccountDAO = new UserAccountDAOImpl(new PostgreSqlDB());
        userAccountDAO.addUserAccount(new UserAccount("test","test"));
        userAccountDAO.updateSurname("test","test");
        System.out.println(userAccountDAO.findUserAccountByUsername("test"));
        userAccountDAO.updateSurname("test","newtest");
        System.out.println(userAccountDAO.findUserAccountByUsername("test"));
    }
}
