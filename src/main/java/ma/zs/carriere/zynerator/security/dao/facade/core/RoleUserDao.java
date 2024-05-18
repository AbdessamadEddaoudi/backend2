package ma.zs.carriere.zynerator.security.dao.facade.core;

import ma.zs.carriere.zynerator.repository.AbstractRepository;
import ma.zs.carriere.zynerator.security.bean.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleUserDao extends AbstractRepository<RoleUser,Long>  {

    List<RoleUser> findByRoleId(Long id);
    int deleteByRoleId(Long id);
    long countByRoleAuthority(String authority);
    List<RoleUser> findByUserId(Long id);
    int deleteByUserId(Long id);
    long countByUserEmail(String email);


}
