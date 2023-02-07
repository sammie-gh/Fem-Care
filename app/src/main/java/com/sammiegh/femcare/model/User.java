package com.sammiegh.femcare.model;

public class User {
    private String answer;
    private int avatar;
    private String email;
    private int id;
    private String password;
    private String question;
    private int status;
    private int theme;
    private int uid;
    private String username;

    public User() {
    }

    public User(int id2, int uid2, int status2, String username2, String password2, String email2, String question2, String answer2, int theme2, int avatar2) {
        this.id = id2;
        this.uid = uid2;
        this.status = status2;
        this.username = username2;
        this.password = password2;
        this.email = email2;
        this.question = question2;
        this.answer = answer2;
        this.theme = theme2;
        this.avatar = avatar2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid2) {
        this.uid = uid2;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status2) {
        this.status = status2;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username2) {
        this.username = username2;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password2) {
        this.password = password2;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email2) {
        this.email = email2;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question2) {
        this.question = question2;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer2) {
        this.answer = answer2;
    }

    public int getTheme() {
        return 3;
    }

    public void setTheme(int theme2) {
        this.theme = theme2;
    }

    public int getAvatar() {
        return this.avatar;
    }

    public void setAvatar(int avatar2) {
        this.avatar = avatar2;
    }
}
