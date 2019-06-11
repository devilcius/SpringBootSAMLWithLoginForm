package net.technoage.springboot.saml.repository;

import java.util.Optional;
import net.technoage.springboot.saml.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Marcos Peña
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>
{
    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByUsername(String username);    
}
