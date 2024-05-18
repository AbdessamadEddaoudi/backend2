package ma.zs.carriere.service.impl.employe.demande;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.demande.TypeDocument;
import ma.zs.carriere.dao.criteria.core.demande.TypeDocumentCriteria;
import ma.zs.carriere.dao.facade.core.demande.TypeDocumentDao;
import ma.zs.carriere.dao.specification.core.demande.TypeDocumentSpecification;
import ma.zs.carriere.service.facade.employe.demande.TypeDocumentEmployeService;
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
public class TypeDocumentEmployeServiceImpl implements TypeDocumentEmployeService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeDocument update(TypeDocument t) {
        TypeDocument loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeDocument.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeDocument findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeDocument findOrSave(TypeDocument t) {
        if (t != null) {
            TypeDocument result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeDocument> importData(List<TypeDocument> items) {
        List<TypeDocument> list = new ArrayList<>();
        for (TypeDocument t : items) {
            TypeDocument founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeDocument> findAll() {
        return dao.findAll();
    }

    public List<TypeDocument> findByCriteria(TypeDocumentCriteria criteria) {
        List<TypeDocument> content = null;
        if (criteria != null) {
            TypeDocumentSpecification mySpecification = constructSpecification(criteria);
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


    private TypeDocumentSpecification constructSpecification(TypeDocumentCriteria criteria) {
        TypeDocumentSpecification mySpecification =  (TypeDocumentSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeDocumentSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeDocument> findPaginatedByCriteria(TypeDocumentCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeDocumentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeDocumentCriteria criteria) {
        TypeDocumentSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(TypeDocument t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeDocument> delete(List<TypeDocument> list) {
		List<TypeDocument> result = new ArrayList();
        if (list != null) {
            for (TypeDocument t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeDocument create(TypeDocument t) {
        TypeDocument loaded = findByReferenceEntity(t);
        TypeDocument saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeDocument> create(List<TypeDocument> ts) {
        List<TypeDocument> result = new ArrayList<>();
        if (ts != null) {
            for (TypeDocument t : ts) {
				TypeDocument created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeDocument findWithAssociatedLists(Long id){
        TypeDocument result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeDocument> update(List<TypeDocument> ts, boolean createIfNotExist) {
        List<TypeDocument> result = new ArrayList<>();
        if (ts != null) {
            for (TypeDocument t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeDocument loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeDocument findByReferenceEntity(TypeDocument t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<TypeDocument> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<TypeDocument>> getToBeSavedAndToBeDeleted(List<TypeDocument> oldList, List<TypeDocument> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeDocument> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeDocumentDao dao;


}
