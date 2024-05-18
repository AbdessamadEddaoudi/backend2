package  ma.zs.carriere.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.carriere.bean.core.commun.EntiteAdmin;
import ma.zs.carriere.dao.criteria.core.commun.EntiteAdminCriteria;
import ma.zs.carriere.service.facade.admin.commun.EntiteAdminAdminService;
import ma.zs.carriere.ws.converter.commun.EntiteAdminConverter;
import ma.zs.carriere.ws.dto.commun.EntiteAdminDto;
import ma.zs.carriere.zynerator.controller.AbstractController;
import ma.zs.carriere.zynerator.dto.AuditEntityDto;
import ma.zs.carriere.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.carriere.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.carriere.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/entiteAdmin/")
public class EntiteAdminRestAdmin {




    @Operation(summary = "Finds a list of all entiteAdmins")
    @GetMapping("")
    public ResponseEntity<List<EntiteAdminDto>> findAll() throws Exception {
        ResponseEntity<List<EntiteAdminDto>> res = null;
        List<EntiteAdmin> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<EntiteAdminDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all entiteAdmins")
    @GetMapping("optimized")
    public ResponseEntity<List<EntiteAdminDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EntiteAdminDto>> res = null;
        List<EntiteAdmin> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<EntiteAdminDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a entiteAdmin by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EntiteAdminDto> findById(@PathVariable Long id) {
        EntiteAdmin t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EntiteAdminDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a entiteAdmin by departement")
    @GetMapping("departement/{departement}")
    public ResponseEntity<EntiteAdminDto> findByDepartement(@PathVariable String departement) {
	    EntiteAdmin t = service.findByReferenceEntity(new EntiteAdmin(departement));
        if (t != null) {
            converter.init(true);
            EntiteAdminDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  entiteAdmin")
    @PostMapping("")
    public ResponseEntity<EntiteAdminDto> save(@RequestBody EntiteAdminDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            EntiteAdmin myT = converter.toItem(dto);
            EntiteAdmin t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EntiteAdminDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  entiteAdmin")
    @PutMapping("")
    public ResponseEntity<EntiteAdminDto> update(@RequestBody EntiteAdminDto dto) throws Exception {
        ResponseEntity<EntiteAdminDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EntiteAdmin t = service.findById(dto.getId());
            converter.copy(dto,t);
            EntiteAdmin updated = service.update(t);
            EntiteAdminDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of entiteAdmin")
    @PostMapping("multiple")
    public ResponseEntity<List<EntiteAdminDto>> delete(@RequestBody List<EntiteAdminDto> dtos) throws Exception {
        ResponseEntity<List<EntiteAdminDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<EntiteAdmin> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified entiteAdmin")
    @DeleteMapping("")
    public ResponseEntity<EntiteAdminDto> delete(@RequestBody EntiteAdminDto dto) throws Exception {
		ResponseEntity<EntiteAdminDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            EntiteAdmin t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified entiteAdmin")
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
    @Operation(summary = "Delete multiple entiteAdmins by ids")
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


    @Operation(summary = "find by chefDepart id")
    @GetMapping("chefDepart/id/{id}")
    public List<EntiteAdminDto> findByChefDepartId(@PathVariable Long id){
        return findDtos(service.findByChefDepartId(id));
    }
    @Operation(summary = "delete by chefDepart id")
    @DeleteMapping("chefDepart/id/{id}")
    public int deleteByChefDepartId(@PathVariable Long id){
        return service.deleteByChefDepartId(id);
    }

    @Operation(summary = "Finds a entiteAdmin and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EntiteAdminDto> findWithAssociatedLists(@PathVariable Long id) {
        EntiteAdmin loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EntiteAdminDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds entiteAdmins by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EntiteAdminDto>> findByCriteria(@RequestBody EntiteAdminCriteria criteria) throws Exception {
        ResponseEntity<List<EntiteAdminDto>> res = null;
        List<EntiteAdmin> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<EntiteAdminDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated entiteAdmins by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EntiteAdminCriteria criteria) throws Exception {
        List<EntiteAdmin> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<EntiteAdminDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets entiteAdmin data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EntiteAdminCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EntiteAdminDto> findDtos(List<EntiteAdmin> list){
        converter.initList(false);
        converter.initObject(true);
        List<EntiteAdminDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EntiteAdminDto> getDtoResponseEntity(EntiteAdminDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EntiteAdminAdminService service;
    @Autowired private EntiteAdminConverter converter;





}
