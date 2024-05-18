package ma.zs.carriere.service.impl.employe.avancement;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.avancement.Echelon;
import ma.zs.carriere.dao.criteria.core.avancement.EchelonCriteria;
import ma.zs.carriere.dao.facade.core.avancement.EchelonDao;
import ma.zs.carriere.dao.specification.core.avancement.EchelonSpecification;
import ma.zs.carriere.service.facade.employe.avancement.EchelonEmployeService;
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

import ma.zs.carriere.service.facade.employe.avancement.EchelleEmployeService ;
import ma.zs.carriere.bean.core.avancement.Echelle ;

import java.util.List;
@Service
public class EchelonEmployeServiceImpl implements EchelonEmployeService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Echelon update(Echelon t) {
        Echelon loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Echelon.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Echelon findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Echelon findOrSave(Echelon t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Echelon result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Echelon> importData(List<Echelon> items) {
        List<Echelon> list = new ArrayList<>();
        for (Echelon t : items) {
            Echelon founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Echelon> findAll() {
        return dao.findAll();
    }

    public List<Echelon> findByCriteria(EchelonCriteria criteria) {
        List<Echelon> content = null;
        if (criteria != null) {
            EchelonSpecification mySpecification = constructSpecification(criteria);
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


    private EchelonSpecification constructSpecification(EchelonCriteria criteria) {
        EchelonSpecification mySpecification =  (EchelonSpecification) RefelexivityUtil.constructObjectUsingOneParam(EchelonSpecification.class, criteria);
        return mySpecification;
    }

    public List<Echelon> findPaginatedByCriteria(EchelonCriteria criteria, int page, int pageSize, String order, String sortField) {
        EchelonSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EchelonCriteria criteria) {
        EchelonSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Echelon> findByEchelleId(Long id){
        return dao.findByEchelleId(id);
    }
    public int deleteByEchelleId(Long id){
        return dao.deleteByEchelleId(id);
    }
    public long countByEchelleRef(String ref){
        return dao.countByEchelleRef(ref);
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
    public int delete(Echelon t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Echelon> delete(List<Echelon> list) {
		List<Echelon> result = new ArrayList();
        if (list != null) {
            for (Echelon t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Echelon create(Echelon t) {
        Echelon loaded = findByReferenceEntity(t);
        Echelon saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Echelon> create(List<Echelon> ts) {
        List<Echelon> result = new ArrayList<>();
        if (ts != null) {
            for (Echelon t : ts) {
				Echelon created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Echelon findWithAssociatedLists(Long id){
        Echelon result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Echelon> update(List<Echelon> ts, boolean createIfNotExist) {
        List<Echelon> result = new ArrayList<>();
        if (ts != null) {
            for (Echelon t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Echelon loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Echelon findByReferenceEntity(Echelon t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Echelon t){
        if( t != null) {
            t.setEchelle(echelleService.findOrSave(t.getEchelle()));
        }
    }



    public List<Echelon> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Echelon>> getToBeSavedAndToBeDeleted(List<Echelon> oldList, List<Echelon> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Echelon> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EchelleEmployeService echelleService ;

    private @Autowired EchelonDao dao;


}
