package ma.zs.carriere.service.facade.admin.avancement;

import java.util.List;
import ma.zs.carriere.bean.core.avancement.Avancement;
import ma.zs.carriere.dao.criteria.core.avancement.AvancementCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AvancementAdminService {



    List<Avancement> findByEmployeId(Long id);
    int deleteByEmployeId(Long id);
    long countByEmployeId(Long id);
    List<Avancement> findByEchelleAncienId(Long id);
    int deleteByEchelleAncienId(Long id);
    long countByEchelleAncienId(Long id);
    List<Avancement> findByEchelleNouveauId(Long id);
    int deleteByEchelleNouveauId(Long id);
    long countByEchelleNouveauId(Long id);




	Avancement create(Avancement t);

    Avancement update(Avancement t);

    List<Avancement> update(List<Avancement> ts,boolean createIfNotExist);

    Avancement findById(Long id);

    Avancement findOrSave(Avancement t);

    Avancement findByReferenceEntity(Avancement t);

    Avancement findWithAssociatedLists(Long id);

    List<Avancement> findAllOptimized();

    List<Avancement> findAll();

    List<Avancement> findByCriteria(AvancementCriteria criteria);

    List<Avancement> findPaginatedByCriteria(AvancementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AvancementCriteria criteria);

    List<Avancement> delete(List<Avancement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Avancement>> getToBeSavedAndToBeDeleted(List<Avancement> oldList, List<Avancement> newList);

    List<Avancement> importData(List<Avancement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Avancement> importExcel(MultipartFile file);

}
