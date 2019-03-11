package br.com.sensedia.cadastro.service;

import br.com.sensedia.StepsAuthenticator.model.AuthenticationKey;
import br.com.sensedia.StepsAuthenticator.model.SecretInfo;
import br.com.sensedia.StepsAuthenticator.service.AuthenticationKeyService;
import br.com.sensedia.cadastro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    AuthenticationKeyService authenticationKeyService
            = new AuthenticationKeyService();

    public User authenticate(String username, String authenticationKey)
            throws InvalidKeyException{

        User user = userRepository.findByUsername(username);
        boolean isValidAuthenticationKey;

        if (user == null) {
            //TODO create login does not exist exception
            return null;
        }

        isValidAuthenticationKey = validateAuthenticationKey(user, authenticationKey);

        if (!isValidAuthenticationKey) {
            //TODO create invalid key exception
            return null;
        }

        return user;

    }

    public AuthenticationKey generateAuthenticationKey(User user) throws InvalidKeyException {

        SecretInfo secretInfo = SecretInfo.build(user.getSecret());
        AuthenticationKey authenticationKey
                = authenticationKeyService.generateAuthenticationKey(secretInfo);

        return authenticationKey;

    }

    private boolean validateAuthenticationKey(User user, String providedAuthenticationKey)
            throws InvalidKeyException {

        SecretInfo secretInfo = SecretInfo.build(user.getSecret());
        AuthenticationKey authenticationKey = AuthenticationKey.build(providedAuthenticationKey);
        boolean isValidAuthenticationKey =
                authenticationKeyService
                        .validateAuthenticationKey(secretInfo, authenticationKey);

        return isValidAuthenticationKey;

    }

}
