package net.technoage.springboot.saml.controller;

import net.technoage.springboot.saml.CommonTestSupport;
import net.technoage.springboot.saml.TestConfig;
import net.technoage.springboot.saml.service.UserServiceInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class HomeControllerTest extends CommonTestSupport {

    

    @Mock
    UserServiceInterface userService;    
    
    @InjectMocks
    private HomeController homeController;

    @Mock
    private View mockView;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(homeController)
                .setCustomArgumentResolvers(new MockArgumentResolver())
                .setSingleView(mockView).build();
    }

    @Test
    public void testLandingPage() throws Exception {
        mockMvc.perform(get("/landing").session(mockUserHttpSession(true)))
                .andExpect(status().isOk())
                .andExpect(view().name("landing/index"));
    }

    @Test
    public void testAdminPage() throws Exception {
        MockHttpSession buh = mockAdminHttpSession(true);
        MockHttpServletRequestBuilder bih = get("/admin").session(buh);
        ResultActions bah = mockMvc.perform(bih);
                bah.andExpect(status().isOk())
                .andExpect(model().attribute("users", this.userService.getAll()))
                .andExpect(view().name("admin/index"));
    }

    private static class MockArgumentResolver implements HandlerMethodArgumentResolver
    {
        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            return methodParameter.getParameterType().equals(User.class);
        }

        @Override
        public Object resolveArgument(MethodParameter methodParameter,
                                      ModelAndViewContainer modelAndViewContainer,
                                      NativeWebRequest nativeWebRequest,
                                      WebDataBinderFactory webDataBinderFactory)
                                    		  throws Exception {
            return CommonTestSupport.ADMIN_DETAILS;
        }
    }

}
