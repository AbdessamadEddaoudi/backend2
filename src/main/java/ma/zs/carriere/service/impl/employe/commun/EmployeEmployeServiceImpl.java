package ma.zs.carriere.service.impl.employe.commun;


import ma.zs.carriere.zynerator.exception.EntityNotFoundException;
import ma.zs.carriere.bean.core.commun.Employe;
import ma.zs.carriere.dao.criteria.core.commun.EmployeCriteria;
import ma.zs.carriere.dao.facade.core.commun.EmployeDao;
import ma.zs.carriere.dao.specification.core.commun.EmployeSpecification;
import ma.zs.carriere.service.facade.employe.commun.EmployeEmployeService;
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

import ma.zs.carriere.service.facade.employe.commun.EntiteAdminEmployeService ;
import ma.zs.carriere.bean.core.commun.EntiteAdmin ;
import ma.zs.carriere.service.facade.employe.commun.DiplomeEmployeService ;
import ma.zs.carriere.bean.core.commun.Diplome ;

import java.time.LocalDateTime;
import ma.zs.carriere.zynerator.security.service.facade.UserService;
import ma.zs.carriere.zynerator.security.service.facade.RoleService;
import ma.zs.carriere.zynerator.security.service.facade.RoleUserService;
import ma.zs.carriere.zynerator.security.bean.Role;
import ma.zs.carriere.zynerator.security.bean.RoleUser;
import ma.zs.carriere.zynerator.security.common.AuthoritiesConstants;
import ma.zs.carriere.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class EmployeEmployeServiceImpl implements EmployeEmployeService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Employe update(Employe t) {
        Employe loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Employe.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Employe findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Employe findOrSave(Employe t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Employe result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Employe> importData(List<Employe> items) {
        List<Employe> list = new ArrayList<>();
        for (Employe t : items) {
            Employe founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Employe> findAll() {
        return dao.findAll();
    }

    public List<Employe> findByCriteria(EmployeCriteria criteria) {
        List<Employe> content = null;
        if (criteria != null) {
            EmployeSpecification mySpecification = constructSpecification(criteria);
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


    private EmployeSpecification constructSpecification(EmployeCriteria criteria) {
        EmployeSpecification mySpecification =  (EmployeSpecification) RefelexivityUtil.constructObjectUsingOneParam(EmployeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Employe> findPaginatedByCriteria(EmployeCriteria criteria, int page, int pageSize, String order, String sortField) {
        EmployeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EmployeCriteria criteria) {
        EmployeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Employe> findByDiplomeId(Long id){
        return dao.findByDiplomeId(id);
    }
    public int deleteByDiplomeId(Long id){
        return dao.deleteByDiplomeId(id);
    }
    public long countByDiplomeRef(String ref){
        return dao.countByDiplomeRef(ref);
    }
    public List<Employe> findByEntiteAdminId(Long id){
        return dao.findByEntiteAdminId(id);
    }
    public int deleteByEntiteAdminId(Long id){
        return dao.deleteByEntiteAdminId(id);
    }
    public long countByEntiteAdminRef(String ref){
        return dao.countByEntiteAdminRef(ref);
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
    public int delete(Employe t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Employe> delete(List<Employe> list) {
		List<Employe> result = new ArrayList();
        if (list != null) {
            for (Employe t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Employe> create(List<Employe> ts) {
        List<Employe> result = new ArrayList<>();
        if (ts != null) {
            for (Employe t : ts) {
				Employe created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Employe findWithAssociatedLists(Long id){
        Employe result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Employe> update(List<Employe> ts, boolean createIfNotExist) {
        List<Employe> result = new ArrayList<>();
        if (ts != null) {
            for (Employe t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Employe loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Employe findByReferenceEntity(Employe t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Employe t){
        if( t != null) {
            t.setDiplome(diplomeService.findOrSave(t.getDiplome()));
            t.setEntiteAdmin(entiteAdminService.findOrSave(t.getEntiteAdmin()));
        }
    }



    public List<Employe> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Employe>> getToBeSavedAndToBeDeleted(List<Employe> oldList, List<Employe> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Employe> importExcel(MultipartFile file) {
        return null;
    }


    @Override
    public Employe create(Employe t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.EMPLOYE);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
            t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
            t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Employe mySaved = dao.save(t);

        if (t.getModelPermissionUsers() != null) {
            t.getModelPermissionUsers().forEach(e -> {
                e.setUser(mySaved);
                modelPermissionUserService.create(e);
            });
        }
        if (t.getRoleUsers() != null) {
            t.getRoleUsers().forEach(element-> {
                element.setUser(mySaved);
                roleUserService.create(element);
            });
        }

        return mySaved;
     }

    public Employe findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }





    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;

    @Autowired
    private EntiteAdminEmployeService entiteAdminService ;
    @Autowired
    private DiplomeEmployeService diplomeService ;

    private @Autowired EmployeDao dao;


}
