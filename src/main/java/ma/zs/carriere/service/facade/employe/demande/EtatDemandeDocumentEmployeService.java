package ma.zs.carriere.service.facade.employe.demande;

import java.util.List;
import ma.zs.carriere.bean.core.demande.EtatDemandeDocument;
import ma.zs.carriere.dao.criteria.core.demande.EtatDemandeDocumentCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EtatDemandeDocumentEmployeService {







	EtatDemandeDocument create(EtatDemandeDocument t);

    EtatDemandeDocument update(EtatDemandeDocument t);

    List<EtatDemandeDocument> update(List<EtatDemandeDocument> ts,boolean createIfNotExist);

    EtatDemandeDocument findById(Long id);

    EtatDemandeDocument findOrSave(EtatDemandeDocument t);

    EtatDemandeDocument findByReferenceEntity(EtatDemandeDocument t);

    EtatDemandeDocument findWithAssociatedLists(Long id);

    List<EtatDemandeDocument> findAllOptimized();

    List<EtatDemandeDocument> findAll();

    List<EtatDemandeDocument> findByCriteria(EtatDemandeDocumentCriteria criteria);

    List<EtatDemandeDocument> findPaginatedByCriteria(EtatDemandeDocumentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtatDemandeDocumentCriteria criteria);

    List<EtatDemandeDocument> delete(List<EtatDemandeDocument> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EtatDemandeDocument>> getToBeSavedAndToBeDeleted(List<EtatDemandeDocument> oldList, List<EtatDemandeDocument> newList);

    List<EtatDemandeDocument> importData(List<EtatDemandeDocument> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EtatDemandeDocument> importExcel(MultipartFile file);

}
