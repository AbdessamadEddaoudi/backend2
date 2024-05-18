package ma.zs.carriere.service.facade.employe.avancement;

import java.util.List;
import ma.zs.carriere.bean.core.avancement.Echelle;
import ma.zs.carriere.dao.criteria.core.avancement.EchelleCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EchelleEmployeService {







	Echelle create(Echelle t);

    Echelle update(Echelle t);

    List<Echelle> update(List<Echelle> ts,boolean createIfNotExist);

    Echelle findById(Long id);

    Echelle findOrSave(Echelle t);

    Echelle findByReferenceEntity(Echelle t);

    Echelle findWithAssociatedLists(Long id);

    List<Echelle> findAllOptimized();

    List<Echelle> findAll();

    List<Echelle> findByCriteria(EchelleCriteria criteria);

    List<Echelle> findPaginatedByCriteria(EchelleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EchelleCriteria criteria);

    List<Echelle> delete(List<Echelle> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Echelle>> getToBeSavedAndToBeDeleted(List<Echelle> oldList, List<Echelle> newList);

    List<Echelle> importData(List<Echelle> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Echelle> importExcel(MultipartFile file);

}
