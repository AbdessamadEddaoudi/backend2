package ma.zs.carriere.service.impl.employe.demande;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.demande.DemandeDocument;
import ma.zs.carriere.dao.criteria.core.demande.DemandeDocumentCriteria;
import ma.zs.carriere.dao.facade.core.demande.DemandeDocumentDao;
import ma.zs.carriere.dao.specification.core.demande.DemandeDocumentSpecification;
import ma.zs.carriere.service.facade.employe.demande.DemandeDocumentEmployeService;
import ma.zs.carriere.zynerator.service.AbstractServiceImpl;
import ma.zs.carriere.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.carriere.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.carriere.service.facade.employe.commun.EmployeEmployeService ;
import ma.zs.carriere.bean.core.commun.Employe ;
import ma.zs.carriere.service.facade.employe.demande.TypeDocumentEmployeService ;
import ma.zs.carriere.bean.core.demande.TypeDocument ;

import java.util.List;
@Service
public class DemandeDocumentEmployeServiceImpl implements DemandeDocumentEmployeService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DemandeDocument update(DemandeDocument t) {
        DemandeDocument loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DemandeDocument.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DemandeDocument findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DemandeDocument findOrSave(DemandeDocument t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DemandeDocument result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DemandeDocument> importData(List<DemandeDocument> items) {
        List<DemandeDocument> list = new ArrayList<>();
        for (DemandeDocument t : items) {
            DemandeDocument founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DemandeDocument> findAll() {
        return dao.findAll();
    }

    public List<DemandeDocument> findByCriteria(DemandeDocumentCriteria criteria) {
        List<DemandeDocument> content = null;
        if (criteria != null) {
            DemandeDocumentSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DemandeDocumentSpecification constructSpecification(DemandeDocumentCriteria criteria) {
        DemandeDocumentSpecification mySpecification =  (DemandeDocumentSpecification) RefelexivityUtil.constructObjectUsingOneParam(DemandeDocumentSpecification.class, criteria);
        return mySpecification;
    }

    public List<DemandeDocument> findPaginatedByCriteria(DemandeDocumentCriteria criteria, int page, int pageSize, String order, String sortField) {
        DemandeDocumentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DemandeDocumentCriteria criteria) {
        DemandeDocumentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DemandeDocument> findByEmployeId(Long id){
        return dao.findByEmployeId(id);
    }
    public int deleteByEmployeId(Long id){
        return dao.deleteByEmployeId(id);
    }
    public long countByEmployeId(Long id){
        return dao.countByEmployeId(id);
    }
    public List<DemandeDocument> findByTypeDocumentId(Long id){
        return dao.findByTypeDocumentId(id);
    }
    public int deleteByTypeDocumentId(Long id){
        return dao.deleteByTypeDocumentId(id);
    }
    public long countByTypeDocumentId(Long id){
        return dao.countByTypeDocumentId(id);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(DemandeDocument t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeDocument> delete(List<DemandeDocument> list) {
		List<DemandeDocument> result = new ArrayList();
        if (list != null) {
            for (DemandeDocument t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DemandeDocument create(DemandeDocument t) {
        DemandeDocument loaded = findByReferenceEntity(t);
        DemandeDocument saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeDocument> create(List<DemandeDocument> ts) {
        List<DemandeDocument> result = new ArrayList<>();
        if (ts != null) {
            for (DemandeDocument t : ts) {
				DemandeDocument created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DemandeDocument findWithAssociatedLists(Long id){
        DemandeDocument result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DemandeDocument> update(List<DemandeDocument> ts, boolean createIfNotExist) {
        List<DemandeDocument> result = new ArrayList<>();
        if (ts != null) {
            for (DemandeDocument t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DemandeDocument loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }





    public DemandeDocument findByReferenceEntity(DemandeDocument t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(DemandeDocument t){
        if( t != null) {
            t.setEmploye(employeService.findOrSave(t.getEmploye()));
            t.setTypeDocument(typeDocumentService.findOrSave(t.getTypeDocument()));
        }
    }



    public List<DemandeDocument> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DemandeDocument>> getToBeSavedAndToBeDeleted(List<DemandeDocument> oldList, List<DemandeDocument> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DemandeDocument> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EmployeEmployeService employeService ;
    @Autowired
    private TypeDocumentEmployeService typeDocumentService ;

    private @Autowired DemandeDocumentDao dao;


}
