package ma.zs.carriere.service.facade.employe.commun;

import java.util.List;
import ma.zs.carriere.bean.core.commun.Diplome;
import ma.zs.carriere.dao.criteria.core.commun.DiplomeCriteria;
import ma.zs.carriere.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DiplomeEmployeService {



    List<Diplome> findByEchelonId(Long id);
    int deleteByEchelonId(Long id);
    long countByEchelonId(Long id);




	Diplome create(Diplome t);

    Diplome update(Diplome t);

    List<Diplome> update(List<Diplome> ts,boolean createIfNotExist);

    Diplome findById(Long id);

    Diplome findOrSave(Diplome t);

    Diplome findByReferenceEntity(Diplome t);

    Diplome findWithAssociatedLists(Long id);

    List<Diplome> findAllOptimized();

    List<Diplome> findAll();

    List<Diplome> findByCriteria(DiplomeCriteria criteria);

    List<Diplome> findPaginatedByCriteria(DiplomeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DiplomeCriteria criteria);

    List<Diplome> delete(List<Diplome> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Diplome>> getToBeSavedAndToBeDeleted(List<Diplome> oldList, List<Diplome> newList);

    List<Diplome> importData(List<Diplome> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Diplome> importExcel(MultipartFile file);

}
