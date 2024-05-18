package ma.zs.carriere.zynerator.security.service.facade;

import ma.zs.carriere.zynerator.security.bean.Role;
import ma.zs.carriere.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.zs.carriere.zynerator.service.IService;



public interface RoleService extends  IService<Role,RoleCriteria>  {
    Role findByAuthority(String authority);
    int deleteByAuthority(String authority);


    



}
