package net.technoage.springboot.saml.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;

public interface SAMLUserDetailsServiceInterface
{

    public Object loadUserBySAML(SAMLCredential samlc) throws UsernameNotFoundException;
}

