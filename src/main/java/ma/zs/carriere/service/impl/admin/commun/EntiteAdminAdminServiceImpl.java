package ma.zs.carriere.service.impl.admin.commun;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.commun.EntiteAdmin;
import ma.zs.carriere.dao.criteria.core.commun.EntiteAdminCriteria;
import ma.zs.carriere.dao.facade.core.commun.EntiteAdminDao;
import ma.zs.carriere.dao.specification.core.commun.EntiteAdminSpecification;
import ma.zs.carriere.service.facade.admin.commun.EntiteAdminAdminService;
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

import ma.zs.carriere.service.facade.admin.commun.EmployeAdminService ;
import ma.zs.carriere.bean.core.commun.Employe ;

import java.util.List;
@Service
public class EntiteAdminAdminServiceImpl implements EntiteAdminAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EntiteAdmin update(EntiteAdmin t) {
        EntiteAdmin loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EntiteAdmin.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public EntiteAdmin findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EntiteAdmin findOrSave(EntiteAdmin t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            EntiteAdmin result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EntiteAdmin> importData(List<EntiteAdmin> items) {
        List<EntiteAdmin> list = new ArrayList<>();
        for (EntiteAdmin t : items) {
            EntiteAdmin founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EntiteAdmin> findAll() {
        return dao.findAll();
    }

    public List<EntiteAdmin> findByCriteria(EntiteAdminCriteria criteria) {
        List<EntiteAdmin> content = null;
        if (criteria != null) {
            EntiteAdminSpecification mySpecification = constructSpecification(criteria);
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


    private EntiteAdminSpecification constructSpecification(EntiteAdminCriteria criteria) {
        EntiteAdminSpecification mySpecification =  (EntiteAdminSpecification) RefelexivityUtil.constructObjectUsingOneParam(EntiteAdminSpecification.class, criteria);
        return mySpecification;
    }

    public List<EntiteAdmin> findPaginatedByCriteria(EntiteAdminCriteria criteria, int page, int pageSize, String order, String sortField) {
        EntiteAdminSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EntiteAdminCriteria criteria) {
        EntiteAdminSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<EntiteAdmin> findByChefDepartId(Long id){
        return dao.findByChefDepartId(id);
    }
    public int deleteByChefDepartId(Long id){
        return dao.deleteByChefDepartId(id);
    }
    public long countByChefDepartId(Long id){
        return dao.countByChefDepartId(id);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
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
    public int delete(EntiteAdmin t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        employeService.deleteByEntiteAdminId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteAdmin> delete(List<EntiteAdmin> list) {
		List<EntiteAdmin> result = new ArrayList();
        if (list != null) {
            for (EntiteAdmin t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EntiteAdmin create(EntiteAdmin t) {
        EntiteAdmin loaded = findByReferenceEntity(t);
        EntiteAdmin saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getEmployes() != null) {
                t.getEmployes().forEach(element-> {
                    element.setEntiteAdmin(saved);
                    employeService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteAdmin> create(List<EntiteAdmin> ts) {
        List<EntiteAdmin> result = new ArrayList<>();
        if (ts != null) {
            for (EntiteAdmin t : ts) {
				EntiteAdmin created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EntiteAdmin findWithAssociatedLists(Long id){
        EntiteAdmin result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setEmployes(employeService.findByEntiteAdminId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteAdmin> update(List<EntiteAdmin> ts, boolean createIfNotExist) {
        List<EntiteAdmin> result = new ArrayList<>();
        if (ts != null) {
            for (EntiteAdmin t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EntiteAdmin loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(EntiteAdmin entiteAdmin){
    if(entiteAdmin !=null && entiteAdmin.getId() != null){
        List<List<Employe>> resultEmployes= employeService.getToBeSavedAndToBeDeleted(employeService.findByEntiteAdminId(entiteAdmin.getId()),entiteAdmin.getEmployes());
            employeService.delete(resultEmployes.get(1));
        ListUtil.emptyIfNull(resultEmployes.get(0)).forEach(e -> e.setEntiteAdmin(entiteAdmin));
        employeService.update(resultEmployes.get(0),true);
        }
    }




    public EntiteAdmin findByReferenceEntity(EntiteAdmin t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(EntiteAdmin t){
        if( t != null) {
            t.setChefDepart(employeService.findOrSave(t.getChefDepart()));
        }
    }



    public List<EntiteAdmin> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EntiteAdmin>> getToBeSavedAndToBeDeleted(List<EntiteAdmin> oldList, List<EntiteAdmin> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EntiteAdmin> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EmployeAdminService employeService ;

    private @Autowired EntiteAdminDao dao;


}
