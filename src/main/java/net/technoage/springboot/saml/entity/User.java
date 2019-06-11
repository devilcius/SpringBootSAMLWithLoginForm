package net.technoage.springboot.saml.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import net.technoage.springboot.saml.domain.Role;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name = "app_user")
public class User implements java.io.Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;    

    @Column(length = 128, name = "name")
    private String name;    

    @Column(length = 45, unique = true, name = "username")
    private String username;
    
    @Column(length = 128, name = "lastname")
    private String lastname;
    
    @Column(length = 128, unique = true, name = "email")
    private String email;
    
    @Column(length = 256, name = "password")
    @JsonIgnore
    private String password;
    
    @Column(length = 128, name = "salt")
    @JsonIgnore
    private String salt;
    
    @Column(name = "enabled")
    private Boolean enabled;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "app_role")
    private Role role;

    public Long getId()
    {
        return id;
    }

    protected void setId(final Long id)
    {
        this.id = id;
    }    
    
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }   
    
    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return this.salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public Boolean getEnabled()
    {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }


    /**
     * @return the last name
     */
    public String getLastname()
    {
        return lastname;
    }

    /**
     * @param lastname the last name to set
     */
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
}
