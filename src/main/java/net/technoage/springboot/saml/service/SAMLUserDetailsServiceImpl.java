package net.technoage.springboot.saml.service;


import net.technoage.springboot.saml.domain.CurrentUser;
import net.technoage.springboot.saml.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {
	
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(SAMLUserDetailsServiceImpl.class);
        private final UserServiceInterface userService;
	
        public SAMLUserDetailsServiceImpl(UserServiceInterface userService)
        {
            this.userService = userService;
        }
        
        @Override
	public Object loadUserBySAML(SAMLCredential credential)
			throws UsernameNotFoundException {
		
		// The method is supposed to identify local account of user referenced by
		// data in the SAML assertion and return UserDetails object describing the user.
		
		String userEmail = credential.getNameID().getValue();
                LOGGER.debug("Authenticating user with mail = {}", userEmail);
                
                User user = this.userService.getUserByEmail(userEmail)
                        .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", userEmail))
                        );

                return new CurrentUser(user);
	}
	
}
