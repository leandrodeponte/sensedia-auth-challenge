package br.com.sensedia.cadastro.service;

import br.com.sensedia.StepsAuthenticator.model.SecretInfo;
import br.com.sensedia.StepsAuthenticator.service.SecretCodeService;
import br.com.sensedia.cadastro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findById(Long id){
        return  userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username){
        return  userRepository.findByUsername(username);
    }

    public User create(User user){

        SecretCodeService secretCodeService = new SecretCodeService();
        SecretInfo secretInfo = secretCodeService.generateSecret();

        user.setSecret(secretInfo.getSecret());

        userRepository.save(user);

        return user;

    }

}
