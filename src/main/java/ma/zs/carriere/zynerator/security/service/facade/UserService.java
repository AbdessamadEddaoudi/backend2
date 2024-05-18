package ma.zs.carriere.zynerator.security.service.facade;

import ma.zs.carriere.zynerator.security.dao.criteria.core.UserCriteria;
import ma.zs.carriere.zynerator.security.bean.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ma.zs.carriere.zynerator.service.IService;

public interface UserService extends IService<User, UserCriteria>, UserDetailsService {

    User findByUsername(String username);

    User findByUsernameWithRoles(String username);

    String cryptPassword(String value);

    boolean changePassword(String username, String newPassword);

    int deleteByUsername(String username);

    UserDetails loadUserByUsername(String username);


    User findByEmail(String email);
}
