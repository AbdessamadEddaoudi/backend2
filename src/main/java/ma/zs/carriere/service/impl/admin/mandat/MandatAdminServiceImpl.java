package ma.zs.carriere.service.impl.admin.mandat;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.mandat.Mandat;
import ma.zs.carriere.dao.criteria.core.mandat.MandatCriteria;
import ma.zs.carriere.dao.facade.core.mandat.MandatDao;
import ma.zs.carriere.dao.specification.core.mandat.MandatSpecification;
import ma.zs.carriere.service.facade.admin.mandat.MandatAdminService;
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

import ma.zs.carriere.service.facade.admin.mandat.ResponsabiliteAdminService ;
import ma.zs.carriere.service.facade.admin.commun.EmployeAdminService ;

@Service
public class MandatAdminServiceImpl implements MandatAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Mandat update(Mandat t) {
        Mandat loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Mandat.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Mandat findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Mandat findOrSave(Mandat t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Mandat result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Mandat> importData(List<Mandat> items) {
        List<Mandat> list = new ArrayList<>();
        for (Mandat t : items) {
            Mandat founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Mandat> findAll() {
        return dao.findAll();
    }

    public List<Mandat> findByCriteria(MandatCriteria criteria) {
        List<Mandat> content = null;
        if (criteria != null) {
            MandatSpecification mySpecification = constructSpecification(criteria);
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


    private MandatSpecification constructSpecification(MandatCriteria criteria) {
        MandatSpecification mySpecification =  (MandatSpecification) RefelexivityUtil.constructObjectUsingOneParam(MandatSpecification.class, criteria);
        return mySpecification;
    }

    public List<Mandat> findPaginatedByCriteria(MandatCriteria criteria, int page, int pageSize, String order, String sortField) {
        MandatSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MandatCriteria criteria) {
        MandatSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Mandat> findByEmployeId(Long id){
        return dao.findByEmployeId(id);
    }
    public int deleteByEmployeId(Long id){
        return dao.deleteByEmployeId(id);
    }
    public long countByEmployeId(Long id){
        return dao.countByEmployeId(id);
    }
    public List<Mandat> findByResponsabiliteId(Long id){
        return dao.findByResponsabiliteId(id);
    }
    public int deleteByResponsabiliteId(Long id){
        return dao.deleteByResponsabiliteId(id);
    }
    public long countByResponsabiliteRef(String ref){
        return dao.countByResponsabiliteRef(ref);
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
    public int delete(Mandat t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Mandat> delete(List<Mandat> list) {
		List<Mandat> result = new ArrayList();
        if (list != null) {
            for (Mandat t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Mandat create(Mandat t) {
        Mandat loaded = findByReferenceEntity(t);
        Mandat saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Mandat> create(List<Mandat> ts) {
        List<Mandat> result = new ArrayList<>();
        if (ts != null) {
            for (Mandat t : ts) {
				Mandat created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Mandat findWithAssociatedLists(Long id){
        Mandat result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Mandat> update(List<Mandat> ts, boolean createIfNotExist) {
        List<Mandat> result = new ArrayList<>();
        if (ts != null) {
            for (Mandat t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Mandat loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Mandat findByReferenceEntity(Mandat t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Mandat t){
        if( t != null) {
            t.setEmploye(employeService.findOrSave(t.getEmploye()));
            t.setResponsabilite(responsabiliteService.findOrSave(t.getResponsabilite()));
        }
    }



    public List<Mandat> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Mandat>> getToBeSavedAndToBeDeleted(List<Mandat> oldList, List<Mandat> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Mandat> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ResponsabiliteAdminService responsabiliteService ;
    @Autowired
    private EmployeAdminService employeService ;

    private @Autowired MandatDao dao;


}
