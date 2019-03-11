package br.com.sensedia.StepsAuthenticator.service;

import br.com.sensedia.StepsAuthenticator.controller.AuthenticationKeyController;
import br.com.sensedia.StepsAuthenticator.model.AuthenticationKey;
import br.com.sensedia.StepsAuthenticator.model.SecretInfo;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class AuthenticationKeyService {

    private AuthenticationKeyController authenticationKeyController
            = new AuthenticationKeyController();

    public AuthenticationKey generateAuthenticationKey(SecretInfo secretInfo)
            throws InvalidKeyException {

        AuthenticationKey authenticationKey = null;

        try {
            authenticationKey = authenticationKeyController.generateAuthenticationKey(secretInfo);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return authenticationKey;
    }

    public boolean validateAuthenticationKey(SecretInfo secretInfo,
                                             AuthenticationKey authenticationKey)
            throws InvalidKeyException{

        try {
            return authenticationKeyController.validateAuthenticationKey(
                    secretInfo,
                    authenticationKey.getProvidedAuthenticationKey()
            );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return false;

    }
}
