package  ma.zs.carriere.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ma.zs.carriere.bean.core.commun.EntiteAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.carriere.bean.core.commun.Employe;
import ma.zs.carriere.dao.criteria.core.commun.EmployeCriteria;
import ma.zs.carriere.service.facade.admin.commun.EmployeAdminService;
import ma.zs.carriere.ws.converter.commun.EmployeConverter;
import ma.zs.carriere.ws.dto.commun.EmployeDto;
import ma.zs.carriere.zynerator.controller.AbstractController;
import ma.zs.carriere.zynerator.dto.AuditEntityDto;
import ma.zs.carriere.zynerator.util.PaginatedList;


import ma.zs.carriere.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.carriere.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.carriere.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/employe/")
public class EmployeRestAdmin {

    @GetMapping("/by-entite-admin")
    public Map<EntiteAdmin, List<Employe>> getEmployesByEntiteAdmin() {
        return service.getEmployesByEntiteAdmin();
    }

    @GetMapping("/total")
    public long getTotalEmployes() {
        return service.getTotalEmployes();
    }


    @Operation(summary = "Finds a list of all employes")
    @GetMapping("")
    public ResponseEntity<List<EmployeDto>> findAll() throws Exception {
        ResponseEntity<List<EmployeDto>> res = null;
        List<Employe> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EmployeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a employe by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EmployeDto> findById(@PathVariable Long id) {
        Employe t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EmployeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  employe")
    @PostMapping("")
    public ResponseEntity<EmployeDto> save(@RequestBody EmployeDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Employe myT = converter.toItem(dto);
            Employe t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EmployeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  employe")
    @PutMapping("")
    public ResponseEntity<EmployeDto> update(@RequestBody EmployeDto dto) throws Exception {
        ResponseEntity<EmployeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Employe t = service.findById(dto.getId());
            converter.copy(dto,t);
            Employe updated = service.update(t);
            EmployeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of employe")
    @PostMapping("multiple")
    public ResponseEntity<List<EmployeDto>> delete(@RequestBody List<EmployeDto> dtos) throws Exception {
        ResponseEntity<List<EmployeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Employe> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified employe")
    @DeleteMapping("")
    public ResponseEntity<EmployeDto> delete(@RequestBody EmployeDto dto) throws Exception {
		ResponseEntity<EmployeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Employe t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified employe")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple employes by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }


    @Operation(summary = "find by diplome id")
    @GetMapping("diplome/id/{id}")
    public List<EmployeDto> findByDiplomeId(@PathVariable Long id){
        return findDtos(service.findByDiplomeId(id));
    }
    @Operation(summary = "delete by diplome id")
    @DeleteMapping("diplome/id/{id}")
    public int deleteByDiplomeId(@PathVariable Long id){
        return service.deleteByDiplomeId(id);
    }

    @Operation(summary = "Finds a employe and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EmployeDto> findWithAssociatedLists(@PathVariable Long id) {
        Employe loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EmployeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds employes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EmployeDto>> findByCriteria(@RequestBody EmployeCriteria criteria) throws Exception {
        ResponseEntity<List<EmployeDto>> res = null;
        List<Employe> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EmployeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated employes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EmployeCriteria criteria) throws Exception {
        List<Employe> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EmployeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets employe data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EmployeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EmployeDto> findDtos(List<Employe> list){
        converter.initObject(true);
        List<EmployeDto> dtos = converter.toDto(list);
        return dtos;
    }

    @Operation(summary = "Finds a employe and associated list by id")
    @GetMapping("entiteAdmin/ref/{ref}")
    public long countByEntiteAdminRef(String ref) {
        return service.countByEntiteAdminRef(ref);
    }

    private ResponseEntity<EmployeDto> getDtoResponseEntity(EmployeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private EmployeAdminService service;
    @Autowired private EmployeConverter converter;





}
