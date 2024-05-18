package ma.zs.carriere.service.facade.employe.demande;

import java.util.List;
import ma.zs.carriere.bean.core.demande.DemandeDocument;
import ma.zs.carriere.dao.criteria.core.demande.DemandeDocumentCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DemandeDocumentEmployeService {



    List<DemandeDocument> findByEmployeId(Long id);
    int deleteByEmployeId(Long id);
    long countByEmployeId(Long id);
    List<DemandeDocument> findByTypeDocumentId(Long id);
    int deleteByTypeDocumentId(Long id);
    long countByTypeDocumentId(Long id);




	DemandeDocument create(DemandeDocument t);

    DemandeDocument update(DemandeDocument t);

    List<DemandeDocument> update(List<DemandeDocument> ts,boolean createIfNotExist);

    DemandeDocument findById(Long id);

    DemandeDocument findOrSave(DemandeDocument t);

    DemandeDocument findByReferenceEntity(DemandeDocument t);

    DemandeDocument findWithAssociatedLists(Long id);

    List<DemandeDocument> findAllOptimized();

    List<DemandeDocument> findAll();

    List<DemandeDocument> findByCriteria(DemandeDocumentCriteria criteria);

    List<DemandeDocument> findPaginatedByCriteria(DemandeDocumentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DemandeDocumentCriteria criteria);

    List<DemandeDocument> delete(List<DemandeDocument> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DemandeDocument>> getToBeSavedAndToBeDeleted(List<DemandeDocument> oldList, List<DemandeDocument> newList);

    List<DemandeDocument> importData(List<DemandeDocument> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DemandeDocument> importExcel(MultipartFile file);

}
