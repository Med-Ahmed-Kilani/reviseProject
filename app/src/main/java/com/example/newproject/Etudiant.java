package com.example.newproject;

public class Etudiant {
    private int id;
    private String prenom;
    private String nom;
    private String login;
    private String password;

    public Etudiant(){}

    public Etudiant(String prenom, String nom, String login, String password) {
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
