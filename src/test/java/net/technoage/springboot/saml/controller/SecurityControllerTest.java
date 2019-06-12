package net.technoage.springboot.saml.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.technoage.springboot.saml.CommonTestSupport;
import net.technoage.springboot.saml.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.springframework.web.servlet.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class SecurityControllerTest extends CommonTestSupport {

    private static final Set<String> IDPS =
            Collections.unmodifiableSet(
            		new HashSet<>(Arrays.asList("idp1", "idp2", "idp3")));

    @InjectMocks
    SecurityController securityController;

    @Mock
    private MetadataManager metadata;

    @Mock
    private View mockView;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(securityController).setSingleView(mockView).build();
    }

    @Test
    @WithMockUser
    public void testUserSAMLDiscovery() throws Exception {
        mockMvc.perform(get("/saml/discovery"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/landing"));
    }

    @Test
    public void testAnonymousSAMLDiscovery() throws Exception {

        mockMvc.perform(get("/saml/discovery").session(mockAnonymousHttpSession()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("identityProvider", metadata.getDefaultIDP()))
                .andExpect(view().name("security/login"));
    }
    
    @Test
    public void testUserGetLogin() throws Exception 
    {
                mockMvc.perform(get("/security/login").session(mockAnonymousHttpSession()))
                        .andExpect(status().isOk())
                        .andExpect(model().attribute("identityProvider", metadata.getDefaultIDP()))
                        .andExpect(view().name("security/login"));
    }    

}
