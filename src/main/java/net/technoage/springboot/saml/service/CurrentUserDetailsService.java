package net.technoage.springboot.saml.service;

import net.technoage.springboot.saml.domain.CurrentUser;
import net.technoage.springboot.saml.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private final UserServiceInterface userService;

    @Autowired
    public CurrentUserDetailsService(UserServiceInterface userService)
    {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException
    {
        LOGGER.debug("Authenticating user with username = {}", username);
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username=%s was not found", username))
                );

        return new CurrentUser(user);
    }
}
