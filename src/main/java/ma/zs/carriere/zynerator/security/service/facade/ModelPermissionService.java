package ma.zs.carriere.zynerator.security.service.facade;

import ma.zs.carriere.zynerator.security.bean.ModelPermission;
import ma.zs.carriere.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.zs.carriere.zynerator.service.IService;
import java.util.List;



public interface ModelPermissionService extends  IService<ModelPermission,ModelPermissionCriteria>  {
    List<ModelPermission> findAllOptimized();

}
