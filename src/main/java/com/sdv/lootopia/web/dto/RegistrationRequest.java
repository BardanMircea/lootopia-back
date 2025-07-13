package com.sdv.lootopia.web.dto;

public class RegistrationRequest {
    private String email;
    private String pseudo;
    private String motDePasse;
    private Boolean rgpdConsent;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public Boolean getRgpdConsent() {
        return rgpdConsent;
    }
    public void setRgpdConsent(Boolean rgpdConsent) {
        this.rgpdConsent = rgpdConsent;
    }
}
