package com.sdv.lootopia.infrastructure.security;

import com.sdv.lootopia.domain.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private final Utilisateur utilisateur;

    public UserPrincipal(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Pas de rôles pour l'instant
    }

    @Override
    public String getPassword() {
        return utilisateur.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return utilisateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Peut être personnalisé plus tard
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Idem
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Idem
    }

    @Override
    public boolean isEnabled() {
        return true; // Peut dépendre de la validation email
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}
