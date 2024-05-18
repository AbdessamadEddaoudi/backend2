package ma.zs.carriere.service.impl.employe.demande;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.demande.TemplateDocument;
import ma.zs.carriere.dao.criteria.core.demande.TemplateDocumentCriteria;
import ma.zs.carriere.dao.facade.core.demande.TemplateDocumentDao;
import ma.zs.carriere.dao.specification.core.demande.TemplateDocumentSpecification;
import ma.zs.carriere.service.facade.employe.demande.TemplateDocumentEmployeService;
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

import ma.zs.carriere.service.facade.employe.demande.TypeDocumentEmployeService ;
import ma.zs.carriere.bean.core.demande.TypeDocument ;

import java.util.List;
@Service
public class TemplateDocumentEmployeServiceImpl implements TemplateDocumentEmployeService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TemplateDocument update(TemplateDocument t) {
        TemplateDocument loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TemplateDocument.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TemplateDocument findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TemplateDocument findOrSave(TemplateDocument t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            TemplateDocument result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TemplateDocument> importData(List<TemplateDocument> items) {
        List<TemplateDocument> list = new ArrayList<>();
        for (TemplateDocument t : items) {
            TemplateDocument founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TemplateDocument> findAll() {
        return dao.findAll();
    }

    public List<TemplateDocument> findByCriteria(TemplateDocumentCriteria criteria) {
        List<TemplateDocument> content = null;
        if (criteria != null) {
            TemplateDocumentSpecification mySpecification = constructSpecification(criteria);
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


    private TemplateDocumentSpecification constructSpecification(TemplateDocumentCriteria criteria) {
        TemplateDocumentSpecification mySpecification =  (TemplateDocumentSpecification) RefelexivityUtil.constructObjectUsingOneParam(TemplateDocumentSpecification.class, criteria);
        return mySpecification;
    }

    public List<TemplateDocument> findPaginatedByCriteria(TemplateDocumentCriteria criteria, int page, int pageSize, String order, String sortField) {
        TemplateDocumentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TemplateDocumentCriteria criteria) {
        TemplateDocumentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<TemplateDocument> findByTypeDocumentId(Long id){
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
    public int delete(TemplateDocument t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TemplateDocument> delete(List<TemplateDocument> list) {
		List<TemplateDocument> result = new ArrayList();
        if (list != null) {
            for (TemplateDocument t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TemplateDocument create(TemplateDocument t) {
        TemplateDocument loaded = findByReferenceEntity(t);
        TemplateDocument saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TemplateDocument> create(List<TemplateDocument> ts) {
        List<TemplateDocument> result = new ArrayList<>();
        if (ts != null) {
            for (TemplateDocument t : ts) {
				TemplateDocument created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TemplateDocument findWithAssociatedLists(Long id){
        TemplateDocument result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TemplateDocument> update(List<TemplateDocument> ts, boolean createIfNotExist) {
        List<TemplateDocument> result = new ArrayList<>();
        if (ts != null) {
            for (TemplateDocument t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TemplateDocument loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TemplateDocument findByReferenceEntity(TemplateDocument t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(TemplateDocument t){
        if( t != null) {
            t.setTypeDocument(typeDocumentService.findOrSave(t.getTypeDocument()));
        }
    }



    public List<TemplateDocument> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TemplateDocument>> getToBeSavedAndToBeDeleted(List<TemplateDocument> oldList, List<TemplateDocument> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TemplateDocument> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private TypeDocumentEmployeService typeDocumentService ;

    private @Autowired TemplateDocumentDao dao;


}
