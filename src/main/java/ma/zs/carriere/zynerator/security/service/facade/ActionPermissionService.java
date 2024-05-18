package ma.zs.carriere.zynerator.security.service.facade;

import ma.zs.carriere.zynerator.security.bean.ActionPermission;
import ma.zs.carriere.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zs.carriere.zynerator.service.IService;
import java.util.List;


public interface ActionPermissionService extends  IService<ActionPermission,ActionPermissionCriteria>  {

    List<ActionPermission> findAllOptimized();

}
