package ma.zs.carriere.service.impl.employe.avancement;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.avancement.Echelle;
import ma.zs.carriere.dao.criteria.core.avancement.EchelleCriteria;
import ma.zs.carriere.dao.facade.core.avancement.EchelleDao;
import ma.zs.carriere.dao.specification.core.avancement.EchelleSpecification;
import ma.zs.carriere.service.facade.employe.avancement.EchelleEmployeService;
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
public class EchelleEmployeServiceImpl implements EchelleEmployeService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Echelle update(Echelle t) {
        Echelle loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Echelle.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Echelle findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Echelle findOrSave(Echelle t) {
        if (t != null) {
            Echelle result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Echelle> importData(List<Echelle> items) {
        List<Echelle> list = new ArrayList<>();
        for (Echelle t : items) {
            Echelle founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Echelle> findAll() {
        return dao.findAll();
    }

    public List<Echelle> findByCriteria(EchelleCriteria criteria) {
        List<Echelle> content = null;
        if (criteria != null) {
            EchelleSpecification mySpecification = constructSpecification(criteria);
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


    private EchelleSpecification constructSpecification(EchelleCriteria criteria) {
        EchelleSpecification mySpecification =  (EchelleSpecification) RefelexivityUtil.constructObjectUsingOneParam(EchelleSpecification.class, criteria);
        return mySpecification;
    }

    public List<Echelle> findPaginatedByCriteria(EchelleCriteria criteria, int page, int pageSize, String order, String sortField) {
        EchelleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EchelleCriteria criteria) {
        EchelleSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Echelle t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Echelle> delete(List<Echelle> list) {
		List<Echelle> result = new ArrayList();
        if (list != null) {
            for (Echelle t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Echelle create(Echelle t) {
        Echelle loaded = findByReferenceEntity(t);
        Echelle saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Echelle> create(List<Echelle> ts) {
        List<Echelle> result = new ArrayList<>();
        if (ts != null) {
            for (Echelle t : ts) {
				Echelle created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Echelle findWithAssociatedLists(Long id){
        Echelle result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Echelle> update(List<Echelle> ts, boolean createIfNotExist) {
        List<Echelle> result = new ArrayList<>();
        if (ts != null) {
            for (Echelle t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Echelle loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Echelle findByReferenceEntity(Echelle t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Echelle> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Echelle>> getToBeSavedAndToBeDeleted(List<Echelle> oldList, List<Echelle> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Echelle> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired EchelleDao dao;


}
