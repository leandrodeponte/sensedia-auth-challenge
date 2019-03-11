package br.com.sensedia.StepsAuthenticator.model;

public class SecretInfo {

    private String data;
    private String secret;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public static SecretInfo build(String secret){
        SecretInfo secretInfo = new SecretInfo();
        secretInfo.setSecret(secret);
        return secretInfo;
    }

}
