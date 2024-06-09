package ma.zs.carriere.service.facade.admin.mandat;

import java.util.List;
import ma.zs.carriere.bean.core.mandat.Responsabilite;
import ma.zs.carriere.dao.criteria.core.mandat.ResponsabiliteCriteria;


import org.springframework.web.multipart.MultipartFile;

public interface ResponsabiliteAdminService {


    long getTotalResponsabilites();


    List<Responsabilite> findByEchelonMinId(Long id);
    int deleteByEchelonMinId(Long id);
    long countByEchelonMinId(Long id);




	Responsabilite create(Responsabilite t);

    Responsabilite update(Responsabilite t);

    List<Responsabilite> update(List<Responsabilite> ts,boolean createIfNotExist);

    Responsabilite findById(Long id);

    Responsabilite findOrSave(Responsabilite t);

    Responsabilite findByReferenceEntity(Responsabilite t);

    Responsabilite findWithAssociatedLists(Long id);

    List<Responsabilite> findAllOptimized();

    List<Responsabilite> findAll();

    List<Responsabilite> findByCriteria(ResponsabiliteCriteria criteria);

    List<Responsabilite> findPaginatedByCriteria(ResponsabiliteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ResponsabiliteCriteria criteria);

    List<Responsabilite> delete(List<Responsabilite> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Responsabilite>> getToBeSavedAndToBeDeleted(List<Responsabilite> oldList, List<Responsabilite> newList);

    List<Responsabilite> importData(List<Responsabilite> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Responsabilite> importExcel(MultipartFile file);

}
