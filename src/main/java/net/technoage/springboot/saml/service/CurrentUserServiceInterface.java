package net.technoage.springboot.saml.service;

import net.technoage.springboot.saml.domain.CurrentUser;

public interface CurrentUserServiceInterface {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
