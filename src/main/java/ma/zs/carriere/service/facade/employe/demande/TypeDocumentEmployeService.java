package ma.zs.carriere.service.facade.employe.demande;

import java.util.List;
import ma.zs.carriere.bean.core.demande.TypeDocument;
import ma.zs.carriere.dao.criteria.core.demande.TypeDocumentCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeDocumentEmployeService {







	TypeDocument create(TypeDocument t);

    TypeDocument update(TypeDocument t);

    List<TypeDocument> update(List<TypeDocument> ts,boolean createIfNotExist);

    TypeDocument findById(Long id);

    TypeDocument findOrSave(TypeDocument t);

    TypeDocument findByReferenceEntity(TypeDocument t);

    TypeDocument findWithAssociatedLists(Long id);

    List<TypeDocument> findAllOptimized();

    List<TypeDocument> findAll();

    List<TypeDocument> findByCriteria(TypeDocumentCriteria criteria);

    List<TypeDocument> findPaginatedByCriteria(TypeDocumentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeDocumentCriteria criteria);

    List<TypeDocument> delete(List<TypeDocument> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeDocument>> getToBeSavedAndToBeDeleted(List<TypeDocument> oldList, List<TypeDocument> newList);

    List<TypeDocument> importData(List<TypeDocument> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeDocument> importExcel(MultipartFile file);

}
