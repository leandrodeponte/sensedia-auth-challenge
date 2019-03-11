package br.com.sensedia.cadastro.service;

import br.com.sensedia.cadastro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    User findOneById(Long id);
    User findByUsername(String username);

}
