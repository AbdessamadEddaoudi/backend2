package ma.zs.carriere.service.facade.employe.mandat;

import java.util.List;
import ma.zs.carriere.bean.core.mandat.Mandat;
import ma.zs.carriere.dao.criteria.core.mandat.MandatCriteria;


import org.springframework.web.multipart.MultipartFile;

public interface MandatEmployeService {



    List<Mandat> findByEmployeId(Long id);
    int deleteByEmployeId(Long id);
    long countByEmployeId(Long id);
    List<Mandat> findByResponsabiliteId(Long id);
    int deleteByResponsabiliteId(Long id);
    long countByResponsabiliteRef(String ref);




	Mandat create(Mandat t);

    Mandat update(Mandat t);

    List<Mandat> update(List<Mandat> ts,boolean createIfNotExist);

    Mandat findById(Long id);

    Mandat findOrSave(Mandat t);

    Mandat findByReferenceEntity(Mandat t);

    Mandat findWithAssociatedLists(Long id);

    List<Mandat> findAllOptimized();

    List<Mandat> findAll();

    List<Mandat> findByCriteria(MandatCriteria criteria);

    List<Mandat> findPaginatedByCriteria(MandatCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MandatCriteria criteria);

    List<Mandat> delete(List<Mandat> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Mandat>> getToBeSavedAndToBeDeleted(List<Mandat> oldList, List<Mandat> newList);

    List<Mandat> importData(List<Mandat> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Mandat> importExcel(MultipartFile file);

}
