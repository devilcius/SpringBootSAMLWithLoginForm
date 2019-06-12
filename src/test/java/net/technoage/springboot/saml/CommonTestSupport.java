package net.technoage.springboot.saml;

import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
    "technoage.saml.entity-id=net:technoage:sp",
})
public class CommonTestSupport {

    public static final String USER_NAME = "user";

    public static final String ADMIN_NAME = "admin";

    public static final String USER_PASSWORD = "123456";

    public static final String ADMIN_PASSWORD = "123456";

    public static final String USER_ROLE = "USER";

    public static final String ADMIN_ROLE = "ADMIN";

    public static final String ANONYMOUS_USER_KEY = "UserKey";

    public static final String ANONYMOUS_USER_PRINCIPAL = "UserPrincipal";

    public static final List<GrantedAuthority> USER_AUTHORITIES =
            Collections.singletonList(new SimpleGrantedAuthority(USER_ROLE));

    public static final List<GrantedAuthority> ADMIN_AUTHORITIES =
            Collections.singletonList(new SimpleGrantedAuthority(ADMIN_ROLE));

    public static final User USER_DETAILS = new User(USER_NAME, USER_PASSWORD, USER_AUTHORITIES);

    public static final User ADMIN_DETAILS = new User(ADMIN_NAME, ADMIN_PASSWORD, ADMIN_AUTHORITIES);

    public MockHttpSession mockUserHttpSession(boolean secured) {
        MockHttpSession mockSession = new MockHttpSession();

        SecurityContext mockSecurityContext = mock(SecurityContext.class);

        if (secured) {
            ExpiringUsernameAuthenticationToken principal =
                    new ExpiringUsernameAuthenticationToken(null, USER_DETAILS, USER_NAME, USER_AUTHORITIES);
            principal.setDetails(USER_DETAILS);
            when(mockSecurityContext.getAuthentication()).thenReturn(principal);
        }

        SecurityContextHolder.setContext(mockSecurityContext);
        mockSession.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                mockSecurityContext);

        return mockSession;
    }

    public MockHttpSession mockAdminHttpSession(boolean secured) {
        MockHttpSession mockSession = new MockHttpSession();

        SecurityContext mockSecurityContext = mock(SecurityContext.class);

        if (secured) {
            ExpiringUsernameAuthenticationToken principal =
                    new ExpiringUsernameAuthenticationToken(null, ADMIN_DETAILS, ADMIN_NAME, ADMIN_AUTHORITIES);
            principal.setDetails(ADMIN_DETAILS);
            when(mockSecurityContext.getAuthentication()).thenReturn(principal);
        }

        SecurityContextHolder.setContext(mockSecurityContext);
        mockSession.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                mockSecurityContext);

        return mockSession;
    }

    public MockHttpSession mockAnonymousHttpSession() {
        MockHttpSession mockSession = new MockHttpSession();

        SecurityContext mockSecurityContext = mock(SecurityContext.class);

        AnonymousAuthenticationToken principal =
                new AnonymousAuthenticationToken(
                        ANONYMOUS_USER_KEY,
                        ANONYMOUS_USER_PRINCIPAL,
                        USER_AUTHORITIES);

        when(mockSecurityContext.getAuthentication()).thenReturn(principal);
        
        SecurityContextHolder.setContext(mockSecurityContext);
        mockSession.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                mockSecurityContext);

        return mockSession;
    }
}
