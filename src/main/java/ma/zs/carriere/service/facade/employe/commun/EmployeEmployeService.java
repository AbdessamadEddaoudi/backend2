package ma.zs.carriere.service.facade.employe.commun;

import java.util.List;
import ma.zs.carriere.bean.core.commun.Employe;
import ma.zs.carriere.dao.criteria.core.commun.EmployeCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EmployeEmployeService {


    Employe findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Employe> findByDiplomeId(Long id);
    int deleteByDiplomeId(Long id);
    long countByDiplomeRef(String ref);
    List<Employe> findByEntiteAdminId(Long id);
    int deleteByEntiteAdminId(Long id);
    long countByEntiteAdminRef(String ref);




	Employe create(Employe t);

    Employe update(Employe t);

    List<Employe> update(List<Employe> ts,boolean createIfNotExist);

    Employe findById(Long id);

    Employe findOrSave(Employe t);

    Employe findByReferenceEntity(Employe t);

    Employe findWithAssociatedLists(Long id);

    List<Employe> findAllOptimized();

    List<Employe> findAll();

    List<Employe> findByCriteria(EmployeCriteria criteria);

    List<Employe> findPaginatedByCriteria(EmployeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EmployeCriteria criteria);

    List<Employe> delete(List<Employe> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Employe>> getToBeSavedAndToBeDeleted(List<Employe> oldList, List<Employe> newList);

    List<Employe> importData(List<Employe> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Employe> importExcel(MultipartFile file);

}
