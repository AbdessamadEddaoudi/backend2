package ma.zs.carriere.service.facade.employe.demande;

import java.util.List;
import ma.zs.carriere.bean.core.demande.TemplateDocument;
import ma.zs.carriere.dao.criteria.core.demande.TemplateDocumentCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TemplateDocumentEmployeService {



    List<TemplateDocument> findByTypeDocumentId(Long id);
    int deleteByTypeDocumentId(Long id);
    long countByTypeDocumentId(Long id);




	TemplateDocument create(TemplateDocument t);

    TemplateDocument update(TemplateDocument t);

    List<TemplateDocument> update(List<TemplateDocument> ts,boolean createIfNotExist);

    TemplateDocument findById(Long id);

    TemplateDocument findOrSave(TemplateDocument t);

    TemplateDocument findByReferenceEntity(TemplateDocument t);

    TemplateDocument findWithAssociatedLists(Long id);

    List<TemplateDocument> findAllOptimized();

    List<TemplateDocument> findAll();

    List<TemplateDocument> findByCriteria(TemplateDocumentCriteria criteria);

    List<TemplateDocument> findPaginatedByCriteria(TemplateDocumentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TemplateDocumentCriteria criteria);

    List<TemplateDocument> delete(List<TemplateDocument> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TemplateDocument>> getToBeSavedAndToBeDeleted(List<TemplateDocument> oldList, List<TemplateDocument> newList);

    List<TemplateDocument> importData(List<TemplateDocument> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TemplateDocument> importExcel(MultipartFile file);

}
