package ma.zs.carriere.service.impl.admin.demande;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.demande.EtatDemandeDocument;
import ma.zs.carriere.dao.criteria.core.demande.EtatDemandeDocumentCriteria;
import ma.zs.carriere.dao.facade.core.demande.EtatDemandeDocumentDao;
import ma.zs.carriere.dao.specification.core.demande.EtatDemandeDocumentSpecification;
import ma.zs.carriere.service.facade.admin.demande.EtatDemandeDocumentAdminService;
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


import java.util.List;
@Service
public class EtatDemandeDocumentAdminServiceImpl implements EtatDemandeDocumentAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatDemandeDocument update(EtatDemandeDocument t) {
        EtatDemandeDocument loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EtatDemandeDocument.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EtatDemandeDocument findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EtatDemandeDocument findOrSave(EtatDemandeDocument t) {
        if (t != null) {
            EtatDemandeDocument result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EtatDemandeDocument> importData(List<EtatDemandeDocument> items) {
        List<EtatDemandeDocument> list = new ArrayList<>();
        for (EtatDemandeDocument t : items) {
            EtatDemandeDocument founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EtatDemandeDocument> findAll() {
        return dao.findAll();
    }

    public List<EtatDemandeDocument> findByCriteria(EtatDemandeDocumentCriteria criteria) {
        List<EtatDemandeDocument> content = null;
        if (criteria != null) {
            EtatDemandeDocumentSpecification mySpecification = constructSpecification(criteria);
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


    private EtatDemandeDocumentSpecification constructSpecification(EtatDemandeDocumentCriteria criteria) {
        EtatDemandeDocumentSpecification mySpecification =  (EtatDemandeDocumentSpecification) RefelexivityUtil.constructObjectUsingOneParam(EtatDemandeDocumentSpecification.class, criteria);
        return mySpecification;
    }

    public List<EtatDemandeDocument> findPaginatedByCriteria(EtatDemandeDocumentCriteria criteria, int page, int pageSize, String order, String sortField) {
        EtatDemandeDocumentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EtatDemandeDocumentCriteria criteria) {
        EtatDemandeDocumentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public int delete(EtatDemandeDocument t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatDemandeDocument> delete(List<EtatDemandeDocument> list) {
		List<EtatDemandeDocument> result = new ArrayList();
        if (list != null) {
            for (EtatDemandeDocument t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatDemandeDocument create(EtatDemandeDocument t) {
        EtatDemandeDocument loaded = findByReferenceEntity(t);
        EtatDemandeDocument saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatDemandeDocument> create(List<EtatDemandeDocument> ts) {
        List<EtatDemandeDocument> result = new ArrayList<>();
        if (ts != null) {
            for (EtatDemandeDocument t : ts) {
				EtatDemandeDocument created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EtatDemandeDocument findWithAssociatedLists(Long id){
        EtatDemandeDocument result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatDemandeDocument> update(List<EtatDemandeDocument> ts, boolean createIfNotExist) {
        List<EtatDemandeDocument> result = new ArrayList<>();
        if (ts != null) {
            for (EtatDemandeDocument t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EtatDemandeDocument loadedItem = dao.findById(t.getId()).orElse(null);
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





    public EtatDemandeDocument findByReferenceEntity(EtatDemandeDocument t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<EtatDemandeDocument> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<EtatDemandeDocument>> getToBeSavedAndToBeDeleted(List<EtatDemandeDocument> oldList, List<EtatDemandeDocument> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EtatDemandeDocument> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired EtatDemandeDocumentDao dao;


}
