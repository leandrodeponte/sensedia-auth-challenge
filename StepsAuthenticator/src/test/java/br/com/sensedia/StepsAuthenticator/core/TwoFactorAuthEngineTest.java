package br.com.sensedia.StepsAuthenticator.core;

import br.com.sensedia.StepsAuthenticator.model.AuthenticationKey;
import br.com.sensedia.StepsAuthenticator.model.SecretInfo;
import br.com.sensedia.StepsAuthenticator.service.AuthenticationKeyService;
import br.com.sensedia.StepsAuthenticator.service.SecretCodeService;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TwoFactorAuthEngineTest {

    private TwoFactorAuthEngine twoFactorAuthEngine;
    private AuthenticationKeyService authenticationKeyService = new AuthenticationKeyService();
    private SecretCodeService secretCodeService = new SecretCodeService();

    private AuthenticationKey authenticationKey;
    private SecretInfo secretInfo;

    @Before
    public  void startup(){
        twoFactorAuthEngine = new TwoFactorAuthEngine();
        secretInfo = secretCodeService.generateSecret();
        secretInfo.setData("ralves@sensedia.com.br");

        try {
            authenticationKey = authenticationKeyService.generateAuthenticationKey(secretInfo);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateAuthenticationKey_OK() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        boolean isValid = twoFactorAuthEngine
                .validateAuthenticationKey(secretInfo.getData()
                        ,secretInfo.getSecret()
                        ,authenticationKey.getProvidedAuthenticationKey());

        assertTrue(isValid);
    }

    @Test
    public void validateAuthenticationKey_OK_3SecondsSleep()
            throws NoSuchAlgorithmException, SignatureException,
            InvalidKeyException, InterruptedException {

        Thread.sleep(3000);

        boolean isValid = twoFactorAuthEngine
                .validateAuthenticationKey(secretInfo.getData()
                        ,secretInfo.getSecret()
                        ,authenticationKey.getProvidedAuthenticationKey());

        assertTrue(isValid);
    }

    @Test
    public void validateAuthenticationKey_NOK_15SecondsSleep()
            throws NoSuchAlgorithmException, SignatureException,
            InvalidKeyException, InterruptedException {

        Thread.sleep(1000);

        boolean isValid = twoFactorAuthEngine
                .validateAuthenticationKey(secretInfo.getData()
                        ,secretInfo.getSecret()
                        ,authenticationKey.getProvidedAuthenticationKey());

        assertFalse(isValid);
    }
}
