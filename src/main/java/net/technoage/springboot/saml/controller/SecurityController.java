package net.technoage.springboot.saml.controller;

import java.security.Principal;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Marcos Peña
 */
@Controller
public class SecurityController
{

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SecurityController.class);

    @Autowired
    private MetadataManager metadata;

    @RequestMapping(value = "/security/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error, Principal principal, Model model) throws MetadataProviderException
    {
        if(principal != null && ((Authentication) principal).isAuthenticated()) {

            return "redirect:/landing/";
        }
        String identityProvider = metadata.getDefaultIDP();
        model.addAttribute("identityProvider", identityProvider);
        model.addAttribute("error", error);

        return "security/login";
    }

    @RequestMapping(value = "/saml/discovery", method = RequestMethod.GET)
    public String samlDiscovery(@RequestParam Optional<String> error, HttpServletRequest request, Model model) throws MetadataProviderException
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) {
            LOGGER.debug("Current authentication instance from security context is null");
        } else {
            LOGGER.debug("Current authentication instance from security context: "
                    + this.getClass().getSimpleName());
        }
        if(auth == null || (auth instanceof AnonymousAuthenticationToken)) {
            String identityProvider = metadata.getDefaultIDP();
            model.addAttribute("identityProvider", identityProvider);
            model.addAttribute("error", error);
            
            return "security/login";
        } else {
            LOGGER.warn("The current user is already logged.");
            return "redirect:/landing";
        }
    }
}
