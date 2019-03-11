package br.com.sensedia.StepsAuthenticator.model;

import java.time.LocalDateTime;

public class AuthenticationKey {

    private String providedAuthenticationKey;
    private LocalDateTime timeToExpire;

    public String getProvidedAuthenticationKey() {
        return providedAuthenticationKey;
    }

    public void setProvidedAuthenticationKey(String providedAuthenticationKey) {
        this.providedAuthenticationKey = providedAuthenticationKey;
    }

    public LocalDateTime getTimeToExpire() {
        return timeToExpire;
    }

    public void setTimeToExpire(LocalDateTime timeToExpire) {
        this.timeToExpire = timeToExpire;
    }

    public static AuthenticationKey build(String providedAuthenticationKey, LocalDateTime timeToExpire){
        AuthenticationKey authKey = new AuthenticationKey();
        authKey.setProvidedAuthenticationKey(providedAuthenticationKey);
        authKey.setTimeToExpire(timeToExpire);
        return authKey;
    }

    public static AuthenticationKey build(String providedAuthenticationKey){
        AuthenticationKey authKey = new AuthenticationKey();
        authKey.setProvidedAuthenticationKey(providedAuthenticationKey);
        return authKey;
    }

}
