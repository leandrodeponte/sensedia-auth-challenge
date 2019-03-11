package br.com.sensedia.cadastro.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String secret;

}
