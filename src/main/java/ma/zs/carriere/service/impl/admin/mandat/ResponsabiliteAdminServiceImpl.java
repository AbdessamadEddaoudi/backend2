package ma.zs.carriere.service.impl.admin.mandat;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.mandat.Responsabilite;
import ma.zs.carriere.dao.criteria.core.mandat.ResponsabiliteCriteria;
import ma.zs.carriere.dao.facade.core.mandat.ResponsabiliteDao;
import ma.zs.carriere.dao.specification.core.mandat.ResponsabiliteSpecification;
import ma.zs.carriere.service.facade.admin.mandat.ResponsabiliteAdminService;
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

import ma.zs.carriere.service.facade.admin.avancement.EchelonAdminService ;

@Service
public class ResponsabiliteAdminServiceImpl implements ResponsabiliteAdminService {

    @Override
    public long getTotalResponsabilites() {
        return dao.count();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Responsabilite update(Responsabilite t) {
        Responsabilite loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Responsabilite.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Responsabilite findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Responsabilite findOrSave(Responsabilite t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Responsabilite result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Responsabilite> importData(List<Responsabilite> items) {
        List<Responsabilite> list = new ArrayList<>();
        for (Responsabilite t : items) {
            Responsabilite founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Responsabilite> findAll() {
        return dao.findAll();
    }

    public List<Responsabilite> findByCriteria(ResponsabiliteCriteria criteria) {
        List<Responsabilite> content = null;
        if (criteria != null) {
            ResponsabiliteSpecification mySpecification = constructSpecification(criteria);
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


    private ResponsabiliteSpecification constructSpecification(ResponsabiliteCriteria criteria) {
        ResponsabiliteSpecification mySpecification =  (ResponsabiliteSpecification) RefelexivityUtil.constructObjectUsingOneParam(ResponsabiliteSpecification.class, criteria);
        return mySpecification;
    }

    public List<Responsabilite> findPaginatedByCriteria(ResponsabiliteCriteria criteria, int page, int pageSize, String order, String sortField) {
        ResponsabiliteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ResponsabiliteCriteria criteria) {
        ResponsabiliteSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Responsabilite> findByEchelonMinId(Long id){
        return dao.findByEchelonMinId(id);
    }
    public int deleteByEchelonMinId(Long id){
        return dao.deleteByEchelonMinId(id);
    }
    public long countByEchelonMinId(Long id){
        return dao.countByEchelonMinId(id);
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
    public int delete(Responsabilite t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Responsabilite> delete(List<Responsabilite> list) {
		List<Responsabilite> result = new ArrayList();
        if (list != null) {
            for (Responsabilite t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Responsabilite create(Responsabilite t) {
        Responsabilite loaded = findByReferenceEntity(t);
        Responsabilite saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Responsabilite> create(List<Responsabilite> ts) {
        List<Responsabilite> result = new ArrayList<>();
        if (ts != null) {
            for (Responsabilite t : ts) {
				Responsabilite created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Responsabilite findWithAssociatedLists(Long id){
        Responsabilite result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Responsabilite> update(List<Responsabilite> ts, boolean createIfNotExist) {
        List<Responsabilite> result = new ArrayList<>();
        if (ts != null) {
            for (Responsabilite t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Responsabilite loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Responsabilite findByReferenceEntity(Responsabilite t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Responsabilite t){
        if( t != null) {
            t.setEchelonMin(echelonService.findOrSave(t.getEchelonMin()));
        }
    }



    public List<Responsabilite> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Responsabilite>> getToBeSavedAndToBeDeleted(List<Responsabilite> oldList, List<Responsabilite> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Responsabilite> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EchelonAdminService echelonService ;

    private @Autowired ResponsabiliteDao dao;


}
