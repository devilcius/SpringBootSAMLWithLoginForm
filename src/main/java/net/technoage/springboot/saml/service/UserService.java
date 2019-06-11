
package net.technoage.springboot.saml.service;

import java.util.Optional;
import net.technoage.springboot.saml.entity.User;
import net.technoage.springboot.saml.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos Peña
 */
@Service
public class UserService implements UserServiceInterface
{

    private final UserRepository userRepository;    
    
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }    
    
    @Override
    public Optional<User> getUserById(long id)
    {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email)
    {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username)
    {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public Iterable<User> getAll()
    {
        return userRepository.findAll();
    }
    
}
