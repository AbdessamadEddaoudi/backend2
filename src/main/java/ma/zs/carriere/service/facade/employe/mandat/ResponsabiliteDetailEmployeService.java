package ma.zs.carriere.service.facade.employe.mandat;

import java.util.List;
import ma.zs.carriere.bean.core.mandat.ResponsabiliteDetail;
import ma.zs.carriere.dao.criteria.core.mandat.ResponsabiliteDetailCriteria;


import org.springframework.web.multipart.MultipartFile;

public interface ResponsabiliteDetailEmployeService {



    List<ResponsabiliteDetail> findByResponsabiliteId(Long id);
    int deleteByResponsabiliteId(Long id);
    long countByResponsabiliteRef(String ref);




	ResponsabiliteDetail create(ResponsabiliteDetail t);

    ResponsabiliteDetail update(ResponsabiliteDetail t);

    List<ResponsabiliteDetail> update(List<ResponsabiliteDetail> ts,boolean createIfNotExist);

    ResponsabiliteDetail findById(Long id);

    ResponsabiliteDetail findOrSave(ResponsabiliteDetail t);

    ResponsabiliteDetail findByReferenceEntity(ResponsabiliteDetail t);

    ResponsabiliteDetail findWithAssociatedLists(Long id);

    List<ResponsabiliteDetail> findAllOptimized();

    List<ResponsabiliteDetail> findAll();

    List<ResponsabiliteDetail> findByCriteria(ResponsabiliteDetailCriteria criteria);

    List<ResponsabiliteDetail> findPaginatedByCriteria(ResponsabiliteDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ResponsabiliteDetailCriteria criteria);

    List<ResponsabiliteDetail> delete(List<ResponsabiliteDetail> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<ResponsabiliteDetail>> getToBeSavedAndToBeDeleted(List<ResponsabiliteDetail> oldList, List<ResponsabiliteDetail> newList);

    List<ResponsabiliteDetail> importData(List<ResponsabiliteDetail> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<ResponsabiliteDetail> importExcel(MultipartFile file);

}
