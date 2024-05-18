package  ma.zs.carriere.ws.facade.employe.avancement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ma.zs.carriere.bean.core.commun.Employe;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.carriere.bean.core.avancement.Avancement;
import ma.zs.carriere.dao.criteria.core.avancement.AvancementCriteria;
import ma.zs.carriere.service.facade.employe.avancement.AvancementEmployeService;
import ma.zs.carriere.ws.converter.avancement.AvancementConverter;
import ma.zs.carriere.ws.dto.avancement.AvancementDto;
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
@RequestMapping("/api/employe/avancement/")
public class AvancementRestEmploye {




    @Operation(summary = "Finds a list of all avancements")
    @GetMapping("")
    public ResponseEntity<List<AvancementDto>> findAll() throws Exception {
        ResponseEntity<List<AvancementDto>> res = null;
        List<Avancement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AvancementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all avancements")
    @GetMapping("optimized")
    public ResponseEntity<List<AvancementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AvancementDto>> res = null;
        List<Avancement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AvancementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a avancement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AvancementDto> findById(@PathVariable Long id) {
        Avancement t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AvancementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a avancement by employe")
    @GetMapping("employe/{employe}")
    public ResponseEntity<AvancementDto> findByEmploye(@PathVariable Employe employe) {
	    Avancement t = service.findByReferenceEntity(new Avancement(employe));
        if (t != null) {
            converter.init(true);
            AvancementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  avancement")
    @PostMapping("")
    public ResponseEntity<AvancementDto> save(@RequestBody AvancementDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Avancement myT = converter.toItem(dto);
            Avancement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AvancementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  avancement")
    @PutMapping("")
    public ResponseEntity<AvancementDto> update(@RequestBody AvancementDto dto) throws Exception {
        ResponseEntity<AvancementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Avancement t = service.findById(dto.getId());
            converter.copy(dto,t);
            Avancement updated = service.update(t);
            AvancementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of avancement")
    @PostMapping("multiple")
    public ResponseEntity<List<AvancementDto>> delete(@RequestBody List<AvancementDto> dtos) throws Exception {
        ResponseEntity<List<AvancementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Avancement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified avancement")
    @DeleteMapping("")
    public ResponseEntity<AvancementDto> delete(@RequestBody AvancementDto dto) throws Exception {
		ResponseEntity<AvancementDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Avancement t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified avancement")
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
    @Operation(summary = "Delete multiple avancements by ids")
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


    @Operation(summary = "find by employe id")
    @GetMapping("employe/id/{id}")
    public List<AvancementDto> findByEmployeId(@PathVariable Long id){
        return findDtos(service.findByEmployeId(id));
    }
    @Operation(summary = "delete by employe id")
    @DeleteMapping("employe/id/{id}")
    public int deleteByEmployeId(@PathVariable Long id){
        return service.deleteByEmployeId(id);
    }
    @Operation(summary = "find by echelleAncien id")
    @GetMapping("echelleAncien/id/{id}")
    public List<AvancementDto> findByEchelleAncienId(@PathVariable Long id){
        return findDtos(service.findByEchelleAncienId(id));
    }
    @Operation(summary = "delete by echelleAncien id")
    @DeleteMapping("echelleAncien/id/{id}")
    public int deleteByEchelleAncienId(@PathVariable Long id){
        return service.deleteByEchelleAncienId(id);
    }
    @Operation(summary = "find by echelleNouveau id")
    @GetMapping("echelleNouveau/id/{id}")
    public List<AvancementDto> findByEchelleNouveauId(@PathVariable Long id){
        return findDtos(service.findByEchelleNouveauId(id));
    }
    @Operation(summary = "delete by echelleNouveau id")
    @DeleteMapping("echelleNouveau/id/{id}")
    public int deleteByEchelleNouveauId(@PathVariable Long id){
        return service.deleteByEchelleNouveauId(id);
    }

    @Operation(summary = "Finds a avancement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AvancementDto> findWithAssociatedLists(@PathVariable Long id) {
        Avancement loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AvancementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds avancements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AvancementDto>> findByCriteria(@RequestBody AvancementCriteria criteria) throws Exception {
        ResponseEntity<List<AvancementDto>> res = null;
        List<Avancement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AvancementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated avancements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AvancementCriteria criteria) throws Exception {
        List<Avancement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AvancementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets avancement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AvancementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AvancementDto> findDtos(List<Avancement> list){
        converter.initObject(true);
        List<AvancementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AvancementDto> getDtoResponseEntity(AvancementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AvancementEmployeService service;
    @Autowired private AvancementConverter converter;





}
