package br.com.sensedia.StepsAuthenticator.service;

import br.com.sensedia.StepsAuthenticator.controller.SecretCodeController;
import br.com.sensedia.StepsAuthenticator.model.SecretInfo;

public class SecretCodeService {

    private SecretCodeController secretCodeController
            = new SecretCodeController();


    public SecretInfo generateSecret(){

        return secretCodeController.generateSecret();

    }
}
