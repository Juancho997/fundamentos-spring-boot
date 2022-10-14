package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // @Transactional permite, en caso de error, hacer rollback de todas las transacciones registradas previamente en la BD
    // 1º registro OK, 2º registro OK, 3º registro ERROR > se vuelve a cero y ningún registro es persistido
    @Transactional
    public void saveTransactional(List<User> users){
       users.stream()
               .peek(user -> LOG.info("Inserted User > " + user))
//               .forEach(user -> userRepository.save(user));
               .forEach(userRepository::save); //por referencia
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
