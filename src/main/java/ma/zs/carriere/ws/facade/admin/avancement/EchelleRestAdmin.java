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

import ma.zs.carriere.bean.core.avancement.Echelle;
import ma.zs.carriere.dao.criteria.core.avancement.EchelleCriteria;
import ma.zs.carriere.service.facade.admin.avancement.EchelleAdminService;
import ma.zs.carriere.ws.converter.avancement.EchelleConverter;
import ma.zs.carriere.ws.dto.avancement.EchelleDto;
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
@RequestMapping("/api/admin/echelle/")
public class EchelleRestAdmin {




    @Operation(summary = "Finds a list of all echelles")
    @GetMapping("")
    public ResponseEntity<List<EchelleDto>> findAll() throws Exception {
        ResponseEntity<List<EchelleDto>> res = null;
        List<Echelle> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EchelleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all echelles")
    @GetMapping("optimized")
    public ResponseEntity<List<EchelleDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EchelleDto>> res = null;
        List<Echelle> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EchelleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a echelle by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EchelleDto> findById(@PathVariable Long id) {
        Echelle t = service.findById(id);
        if (t != null) {
            EchelleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a echelle by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EchelleDto> findByLibelle(@PathVariable String libelle) {
	    Echelle t = service.findByReferenceEntity(new Echelle(libelle));
        if (t != null) {
            EchelleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  echelle")
    @PostMapping("")
    public ResponseEntity<EchelleDto> save(@RequestBody EchelleDto dto) throws Exception {
        if(dto!=null){
            Echelle myT = converter.toItem(dto);
            Echelle t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EchelleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  echelle")
    @PutMapping("")
    public ResponseEntity<EchelleDto> update(@RequestBody EchelleDto dto) throws Exception {
        ResponseEntity<EchelleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Echelle t = service.findById(dto.getId());
            converter.copy(dto,t);
            Echelle updated = service.update(t);
            EchelleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of echelle")
    @PostMapping("multiple")
    public ResponseEntity<List<EchelleDto>> delete(@RequestBody List<EchelleDto> dtos) throws Exception {
        ResponseEntity<List<EchelleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Echelle> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified echelle")
    @DeleteMapping("")
    public ResponseEntity<EchelleDto> delete(@RequestBody EchelleDto dto) throws Exception {
		ResponseEntity<EchelleDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Echelle t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified echelle")
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
    @Operation(summary = "Delete multiple echelles by ids")
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



    @Operation(summary = "Finds a echelle and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EchelleDto> findWithAssociatedLists(@PathVariable Long id) {
        Echelle loaded =  service.findWithAssociatedLists(id);
        EchelleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds echelles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EchelleDto>> findByCriteria(@RequestBody EchelleCriteria criteria) throws Exception {
        ResponseEntity<List<EchelleDto>> res = null;
        List<Echelle> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EchelleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated echelles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EchelleCriteria criteria) throws Exception {
        List<Echelle> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EchelleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets echelle data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EchelleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EchelleDto> findDtos(List<Echelle> list){
        List<EchelleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EchelleDto> getDtoResponseEntity(EchelleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EchelleAdminService service;
    @Autowired private EchelleConverter converter;





}
