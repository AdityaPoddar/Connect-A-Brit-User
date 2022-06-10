package com.example.connect_a_brituser.UI.Club;

public class ClubData {

        String email,gender;

        public ClubData() {
        }

    public ClubData(String email, String gender) {
        this.email = email;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
