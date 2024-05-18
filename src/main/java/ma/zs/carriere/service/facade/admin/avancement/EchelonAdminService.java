package ma.zs.carriere.service.facade.admin.avancement;

import java.util.List;
import ma.zs.carriere.bean.core.avancement.Echelon;
import ma.zs.carriere.dao.criteria.core.avancement.EchelonCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EchelonAdminService {



    List<Echelon> findByEchelleId(Long id);
    int deleteByEchelleId(Long id);
    long countByEchelleRef(String ref);




	Echelon create(Echelon t);

    Echelon update(Echelon t);

    List<Echelon> update(List<Echelon> ts,boolean createIfNotExist);

    Echelon findById(Long id);

    Echelon findOrSave(Echelon t);

    Echelon findByReferenceEntity(Echelon t);

    Echelon findWithAssociatedLists(Long id);

    List<Echelon> findAllOptimized();

    List<Echelon> findAll();

    List<Echelon> findByCriteria(EchelonCriteria criteria);

    List<Echelon> findPaginatedByCriteria(EchelonCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EchelonCriteria criteria);

    List<Echelon> delete(List<Echelon> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Echelon>> getToBeSavedAndToBeDeleted(List<Echelon> oldList, List<Echelon> newList);

    List<Echelon> importData(List<Echelon> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Echelon> importExcel(MultipartFile file);

}
