package ma.zs.carriere.service.impl.admin.avancement;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.avancement.Avancement;
import ma.zs.carriere.dao.criteria.core.avancement.AvancementCriteria;
import ma.zs.carriere.dao.facade.core.avancement.AvancementDao;
import ma.zs.carriere.dao.specification.core.avancement.AvancementSpecification;
import ma.zs.carriere.service.facade.admin.avancement.AvancementAdminService;
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

import ma.zs.carriere.service.facade.admin.avancement.EchelonAdminService ;
import ma.zs.carriere.bean.core.avancement.Echelon ;
import ma.zs.carriere.service.facade.admin.commun.EmployeAdminService ;
import ma.zs.carriere.bean.core.commun.Employe ;

import java.util.List;
@Service
public class AvancementAdminServiceImpl implements AvancementAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Avancement update(Avancement t) {
        Avancement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Avancement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Avancement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Avancement findOrSave(Avancement t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Avancement result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Avancement> importData(List<Avancement> items) {
        List<Avancement> list = new ArrayList<>();
        for (Avancement t : items) {
            Avancement founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Avancement> findAll() {
        return dao.findAll();
    }

    public List<Avancement> findByCriteria(AvancementCriteria criteria) {
        List<Avancement> content = null;
        if (criteria != null) {
            AvancementSpecification mySpecification = constructSpecification(criteria);
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


    private AvancementSpecification constructSpecification(AvancementCriteria criteria) {
        AvancementSpecification mySpecification =  (AvancementSpecification) RefelexivityUtil.constructObjectUsingOneParam(AvancementSpecification.class, criteria);
        return mySpecification;
    }

    public List<Avancement> findPaginatedByCriteria(AvancementCriteria criteria, int page, int pageSize, String order, String sortField) {
        AvancementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AvancementCriteria criteria) {
        AvancementSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Avancement> findByEmployeId(Long id){
        return dao.findByEmployeId(id);
    }
    public int deleteByEmployeId(Long id){
        return dao.deleteByEmployeId(id);
    }
    public long countByEmployeId(Long id){
        return dao.countByEmployeId(id);
    }
    public List<Avancement> findByEchelleAncienId(Long id){
        return dao.findByEchelleAncienId(id);
    }
    public int deleteByEchelleAncienId(Long id){
        return dao.deleteByEchelleAncienId(id);
    }
    public long countByEchelleAncienId(Long id){
        return dao.countByEchelleAncienId(id);
    }
    public List<Avancement> findByEchelleNouveauId(Long id){
        return dao.findByEchelleNouveauId(id);
    }
    public int deleteByEchelleNouveauId(Long id){
        return dao.deleteByEchelleNouveauId(id);
    }
    public long countByEchelleNouveauId(Long id){
        return dao.countByEchelleNouveauId(id);
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
    public int delete(Avancement t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Avancement> delete(List<Avancement> list) {
		List<Avancement> result = new ArrayList();
        if (list != null) {
            for (Avancement t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Avancement create(Avancement t) {
        Avancement loaded = findByReferenceEntity(t);
        Avancement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Avancement> create(List<Avancement> ts) {
        List<Avancement> result = new ArrayList<>();
        if (ts != null) {
            for (Avancement t : ts) {
				Avancement created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Avancement findWithAssociatedLists(Long id){
        Avancement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Avancement> update(List<Avancement> ts, boolean createIfNotExist) {
        List<Avancement> result = new ArrayList<>();
        if (ts != null) {
            for (Avancement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Avancement loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Avancement findByReferenceEntity(Avancement t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(Avancement t){
        if( t != null) {
            t.setEmploye(employeService.findOrSave(t.getEmploye()));
            t.setEchelleAncien(echelonService.findOrSave(t.getEchelleAncien()));
            t.setEchelleNouveau(echelonService.findOrSave(t.getEchelleNouveau()));
        }
    }



    public List<Avancement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Avancement>> getToBeSavedAndToBeDeleted(List<Avancement> oldList, List<Avancement> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Avancement> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EchelonAdminService echelonService ;
    @Autowired
    private EmployeAdminService employeService ;

    private @Autowired AvancementDao dao;


}
