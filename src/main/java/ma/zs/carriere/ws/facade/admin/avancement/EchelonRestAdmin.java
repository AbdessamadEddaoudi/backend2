package  ma.zs.carriere.ws.facade.admin.avancement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.carriere.bean.core.avancement.Echelon;
import ma.zs.carriere.dao.criteria.core.avancement.EchelonCriteria;
import ma.zs.carriere.service.facade.admin.avancement.EchelonAdminService;
import ma.zs.carriere.ws.converter.avancement.EchelonConverter;
import ma.zs.carriere.ws.dto.avancement.EchelonDto;
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
@RequestMapping("/api/admin/echelon/")
public class EchelonRestAdmin {




    @Operation(summary = "Finds a list of all echelons")
    @GetMapping("")
    public ResponseEntity<List<EchelonDto>> findAll() throws Exception {
        ResponseEntity<List<EchelonDto>> res = null;
        List<Echelon> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EchelonDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all echelons")
    @GetMapping("optimized")
    public ResponseEntity<List<EchelonDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EchelonDto>> res = null;
        List<Echelon> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EchelonDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a echelon by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EchelonDto> findById(@PathVariable Long id) {
        Echelon t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EchelonDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a echelon by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EchelonDto> findByLibelle(@PathVariable String libelle) {
	    Echelon t = service.findByReferenceEntity(new Echelon(libelle));
        if (t != null) {
            converter.init(true);
            EchelonDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  echelon")
    @PostMapping("")
    public ResponseEntity<EchelonDto> save(@RequestBody EchelonDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Echelon myT = converter.toItem(dto);
            Echelon t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EchelonDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  echelon")
    @PutMapping("")
    public ResponseEntity<EchelonDto> update(@RequestBody EchelonDto dto) throws Exception {
        ResponseEntity<EchelonDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Echelon t = service.findById(dto.getId());
            converter.copy(dto,t);
            Echelon updated = service.update(t);
            EchelonDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of echelon")
    @PostMapping("multiple")
    public ResponseEntity<List<EchelonDto>> delete(@RequestBody List<EchelonDto> dtos) throws Exception {
        ResponseEntity<List<EchelonDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Echelon> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified echelon")
    @DeleteMapping("")
    public ResponseEntity<EchelonDto> delete(@RequestBody EchelonDto dto) throws Exception {
		ResponseEntity<EchelonDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Echelon t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified echelon")
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
    @Operation(summary = "Delete multiple echelons by ids")
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



    @Operation(summary = "Finds a echelon and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EchelonDto> findWithAssociatedLists(@PathVariable Long id) {
        Echelon loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EchelonDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds echelons by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EchelonDto>> findByCriteria(@RequestBody EchelonCriteria criteria) throws Exception {
        ResponseEntity<List<EchelonDto>> res = null;
        List<Echelon> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EchelonDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated echelons by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EchelonCriteria criteria) throws Exception {
        List<Echelon> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EchelonDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets echelon data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EchelonCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EchelonDto> findDtos(List<Echelon> list){
        converter.initObject(true);
        List<EchelonDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EchelonDto> getDtoResponseEntity(EchelonDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EchelonAdminService service;
    @Autowired private EchelonConverter converter;





}
