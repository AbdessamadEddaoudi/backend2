package ma.zs.carriere.service.impl.admin.mandat;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.mandat.ResponsabiliteDetail;
import ma.zs.carriere.dao.criteria.core.mandat.ResponsabiliteDetailCriteria;
import ma.zs.carriere.dao.facade.core.mandat.ResponsabiliteDetailDao;
import ma.zs.carriere.dao.specification.core.mandat.ResponsabiliteDetailSpecification;
import ma.zs.carriere.service.facade.admin.mandat.ResponsabiliteDetailAdminService;
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

@Service
public class ResponsabiliteDetailAdminServiceImpl implements ResponsabiliteDetailAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ResponsabiliteDetail update(ResponsabiliteDetail t) {
        ResponsabiliteDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ResponsabiliteDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ResponsabiliteDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ResponsabiliteDetail findOrSave(ResponsabiliteDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ResponsabiliteDetail result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<ResponsabiliteDetail> importData(List<ResponsabiliteDetail> items) {
        List<ResponsabiliteDetail> list = new ArrayList<>();
        for (ResponsabiliteDetail t : items) {
            ResponsabiliteDetail founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<ResponsabiliteDetail> findAll() {
        return dao.findAll();
    }

    public List<ResponsabiliteDetail> findByCriteria(ResponsabiliteDetailCriteria criteria) {
        List<ResponsabiliteDetail> content = null;
        if (criteria != null) {
            ResponsabiliteDetailSpecification mySpecification = constructSpecification(criteria);
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


    private ResponsabiliteDetailSpecification constructSpecification(ResponsabiliteDetailCriteria criteria) {
        ResponsabiliteDetailSpecification mySpecification =  (ResponsabiliteDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(ResponsabiliteDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<ResponsabiliteDetail> findPaginatedByCriteria(ResponsabiliteDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        ResponsabiliteDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ResponsabiliteDetailCriteria criteria) {
        ResponsabiliteDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ResponsabiliteDetail> findByResponsabiliteId(Long id){
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
    public int delete(ResponsabiliteDetail t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ResponsabiliteDetail> delete(List<ResponsabiliteDetail> list) {
		List<ResponsabiliteDetail> result = new ArrayList();
        if (list != null) {
            for (ResponsabiliteDetail t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ResponsabiliteDetail create(ResponsabiliteDetail t) {
        ResponsabiliteDetail loaded = findByReferenceEntity(t);
        ResponsabiliteDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ResponsabiliteDetail> create(List<ResponsabiliteDetail> ts) {
        List<ResponsabiliteDetail> result = new ArrayList<>();
        if (ts != null) {
            for (ResponsabiliteDetail t : ts) {
				ResponsabiliteDetail created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public ResponsabiliteDetail findWithAssociatedLists(Long id){
        ResponsabiliteDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ResponsabiliteDetail> update(List<ResponsabiliteDetail> ts, boolean createIfNotExist) {
        List<ResponsabiliteDetail> result = new ArrayList<>();
        if (ts != null) {
            for (ResponsabiliteDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ResponsabiliteDetail loadedItem = dao.findById(t.getId()).orElse(null);
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





    public ResponsabiliteDetail findByReferenceEntity(ResponsabiliteDetail t){
        return t==null? null : dao.findByRef(t.getRef());
    }
    public void findOrSaveAssociatedObject(ResponsabiliteDetail t){
        if( t != null) {
            t.setResponsabilite(responsabiliteService.findOrSave(t.getResponsabilite()));
        }
    }



    public List<ResponsabiliteDetail> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ResponsabiliteDetail>> getToBeSavedAndToBeDeleted(List<ResponsabiliteDetail> oldList, List<ResponsabiliteDetail> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<ResponsabiliteDetail> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ResponsabiliteAdminService responsabiliteService ;

    private @Autowired ResponsabiliteDetailDao dao;


}
