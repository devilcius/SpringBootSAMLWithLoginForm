package net.technoage.springboot.saml.service;

import java.util.Optional;
import net.technoage.springboot.saml.entity.User;

/**
 *
 * @author Marcos Peña
 */
public interface UserServiceInterface
{
    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    public Iterable<User> getAll();
}
