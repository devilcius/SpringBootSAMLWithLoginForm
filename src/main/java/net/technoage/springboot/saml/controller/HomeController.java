package net.technoage.springboot.saml.controller;

import java.util.Collection;
import net.technoage.springboot.saml.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author Marcos Peña
 */
@RequestMapping("*")
@Controller
public class HomeController
{

    @Autowired
    UserServiceInterface userService;

    @RequestMapping(value = "", method = GET)
    public String index(Authentication auth)
    {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/admin";
        }

        return "redirect:/landing";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin", method = GET)
    public String admin(Model model)
    {
        model.addAttribute("users", this.userService.getAll());
        
        return "admin/index";
    }

    @RequestMapping(value = "/landing", method = GET)
    public String appHomePage()
    {
        return "landing/index";
    }

}
