package com.randomm.domain;

public class UserAccount {
    String username;
    String surname;

    public UserAccount(String username, String surname) {
        this.username = username;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;

        UserAccount that = (UserAccount) o;

        if (!getUsername().equals(that.getUsername())) return false;
        return getSurname().equals(that.getSurname());
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getSurname().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
