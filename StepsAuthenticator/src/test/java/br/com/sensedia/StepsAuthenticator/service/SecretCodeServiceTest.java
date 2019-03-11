package br.com.sensedia.StepsAuthenticator.service;

import br.com.sensedia.StepsAuthenticator.model.SecretInfo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SecretCodeServiceTest {


    private SecretCodeService secretCodeService = new SecretCodeService();

    private SecretInfo secretInfo;

    @Before
    public  void startup(){

    }

    @Test
    public void generateSecretController_OK(){

        secretInfo = secretCodeService.generateSecret();
        assertNotNull(secretInfo.getSecret());
    }
}
