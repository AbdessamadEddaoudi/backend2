package ma.zs.carriere.service.facade.employe.commun;

import java.util.List;
import ma.zs.carriere.bean.core.commun.EntiteAdmin;
import ma.zs.carriere.dao.criteria.core.commun.EntiteAdminCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EntiteAdminEmployeService {



    List<EntiteAdmin> findByChefDepartId(Long id);
    int deleteByChefDepartId(Long id);
    long countByChefDepartId(Long id);




	EntiteAdmin create(EntiteAdmin t);

    EntiteAdmin update(EntiteAdmin t);

    List<EntiteAdmin> update(List<EntiteAdmin> ts,boolean createIfNotExist);

    EntiteAdmin findById(Long id);

    EntiteAdmin findOrSave(EntiteAdmin t);

    EntiteAdmin findByReferenceEntity(EntiteAdmin t);

    EntiteAdmin findWithAssociatedLists(Long id);

    List<EntiteAdmin> findAllOptimized();

    List<EntiteAdmin> findAll();

    List<EntiteAdmin> findByCriteria(EntiteAdminCriteria criteria);

    List<EntiteAdmin> findPaginatedByCriteria(EntiteAdminCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EntiteAdminCriteria criteria);

    List<EntiteAdmin> delete(List<EntiteAdmin> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EntiteAdmin>> getToBeSavedAndToBeDeleted(List<EntiteAdmin> oldList, List<EntiteAdmin> newList);

    List<EntiteAdmin> importData(List<EntiteAdmin> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EntiteAdmin> importExcel(MultipartFile file);

}
