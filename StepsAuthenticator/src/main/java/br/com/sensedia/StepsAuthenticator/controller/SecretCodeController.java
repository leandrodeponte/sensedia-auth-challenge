package br.com.sensedia.StepsAuthenticator.controller;

import br.com.sensedia.StepsAuthenticator.core.TwoFactorAuthEngine;
import br.com.sensedia.StepsAuthenticator.model.SecretInfo;

public class SecretCodeController {

    TwoFactorAuthEngine twoFactorAuthEngine = new TwoFactorAuthEngine();

    public SecretInfo generateSecret( ){

        return SecretInfo.build(
                twoFactorAuthEngine.generateRandomSecretKey());
    }


}
