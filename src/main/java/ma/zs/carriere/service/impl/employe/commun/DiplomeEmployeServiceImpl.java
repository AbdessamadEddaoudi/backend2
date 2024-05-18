package ma.zs.carriere.service.impl.employe.commun;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.commun.Diplome;
import ma.zs.carriere.dao.criteria.core.commun.DiplomeCriteria;
import ma.zs.carriere.dao.facade.core.commun.DiplomeDao;
import ma.zs.carriere.dao.specification.core.commun.DiplomeSpecification;
import ma.zs.carriere.service.facade.employe.commun.DiplomeEmployeService;
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

import ma.zs.carriere.service.facade.employe.avancement.EchelonEmployeService ;
import ma.zs.carriere.bean.core.avancement.Echelon ;

import java.util.List;
@Service
public class DiplomeEmployeServiceImpl implements DiplomeEmployeService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Diplome update(Diplome t) {
        Diplome loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Diplome.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Diplome findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Diplome findOrSave(Diplome t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Diplome result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Diplome> importData(List<Diplome> items) {
        List<Diplome> list = new ArrayList<>();
        for (Diplome t : items) {
            Diplome founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Diplome> findAll() {
        return dao.findAll();
    }

    public List<Diplome> findByCriteria(DiplomeCriteria criteria) {
        List<Diplome> content = null;
        if (criteria != null) {
            DiplomeSpecification mySpecification = constructSpecification(criteria);
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


    private DiplomeSpecification constructSpecification(DiplomeCriteria criteria) {
        DiplomeSpecification mySpecification =  (DiplomeSpecification) RefelexivityUtil.constructObjectUsingOneParam(DiplomeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Diplome> findPaginatedByCriteria(DiplomeCriteria criteria, int page, int pageSize, String order, String sortField) {
        DiplomeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DiplomeCriteria criteria) {
        DiplomeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Diplome> findByEchelonId(Long id){
        return dao.findByEchelonId(id);
    }
    public int deleteByEchelonId(Long id){
        return dao.deleteByEchelonId(id);
    }
    public long countByEchelonId(Long id){
        return dao.countByEchelonId(id);
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
    public int delete(Diplome t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Diplome> delete(List<Diplome> list) {
		List<Diplome> result = new ArrayList();
        if (list != null) {
            for (Diplome t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Diplome create(Diplome t) {
        Diplome loaded = findByReferenceEntity(t);
        Diplome saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Diplome> create(List<Diplome> ts) {
        List<Diplome> result = new ArrayList<>();
        if (ts != null) {
            for (Diplome t : ts) {
				Diplome created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Diplome findWithAssociatedLists(Long id){
        Diplome result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Diplome> update(List<Diplome> ts, boolean createIfNotExist) {
        List<Diplome> result = new ArrayList<>();
        if (ts != null) {
            for (Diplome t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Diplome loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Diplome findByReferenceEntity(Diplome t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Diplome t){
        if( t != null) {
            t.setEchelon(echelonService.findOrSave(t.getEchelon()));
        }
    }



    public List<Diplome> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Diplome>> getToBeSavedAndToBeDeleted(List<Diplome> oldList, List<Diplome> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Diplome> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EchelonEmployeService echelonService ;

    private @Autowired DiplomeDao dao;


}
