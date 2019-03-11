package br.com.sensedia.StepsAuthenticator.controller;

import br.com.sensedia.StepsAuthenticator.core.TwoFactorAuthEngine;
import br.com.sensedia.StepsAuthenticator.model.AuthenticationKey;
import br.com.sensedia.StepsAuthenticator.model.SecretInfo;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class AuthenticationKeyController {

    private TwoFactorAuthEngine twoFactorAuthEngine = new TwoFactorAuthEngine();

    public boolean validateAuthenticationKey(SecretInfo secretInfo
                                                       , String providedAuthenticationKey)
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        return twoFactorAuthEngine.validateAuthenticationKey(secretInfo.getData()
                ,secretInfo.getSecret()
                ,providedAuthenticationKey);

    }

    public AuthenticationKey generateAuthenticationKey(SecretInfo secretInfo)
            throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        AuthenticationKey generatedAuthenticationKey
                = twoFactorAuthEngine.generateAuthenticationKey(secretInfo.getData(),secretInfo.getSecret());


        return generatedAuthenticationKey;

    }

}
