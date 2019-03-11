package br.com.sensedia.StepsAuthenticator.service;

import br.com.sensedia.StepsAuthenticator.model.AuthenticationKey;
import br.com.sensedia.StepsAuthenticator.model.SecretInfo;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidKeyException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AuthenticationKeyServiceTest {

    private AuthenticationKeyService authenticationKeyService = new AuthenticationKeyService();
    private SecretCodeService secretCodeService = new SecretCodeService();

    private AuthenticationKey authenticationKey;
    private SecretInfo secretInfo;


    @Before
    public  void startup(){
        secretInfo = SecretInfo.build("RCDZ36FCF7U6NX5J");
        secretInfo.setData("salves");
        try {
            authenticationKey = authenticationKeyService.generateAuthenticationKey(secretInfo);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generateAuthenticationKey_OK(){

        assertNotNull(authenticationKey);

    }

    @Test
    public void validateAuthenticationKey_OK() throws InvalidKeyException, InterruptedException {

        Thread.sleep(5000);

        boolean isUserKeyValid = authenticationKeyService
                .validateAuthenticationKey(secretInfo
                , authenticationKey);

        assertTrue(isUserKeyValid);
    }
}
