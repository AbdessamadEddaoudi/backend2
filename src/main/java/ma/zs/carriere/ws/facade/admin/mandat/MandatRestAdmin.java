package  ma.zs.carriere.ws.facade.admin.mandat;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.carriere.bean.core.commun.Employe;
import ma.zs.carriere.ws.dto.mandat.MandatDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;

import ma.zs.carriere.bean.core.mandat.Mandat;
import ma.zs.carriere.dao.criteria.core.mandat.MandatCriteria;
import ma.zs.carriere.service.facade.admin.mandat.MandatAdminService;
import ma.zs.carriere.ws.converter.mandat.MandatConverter;
import ma.zs.carriere.zynerator.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin/mandat/")
public class MandatRestAdmin {




    @Operation(summary = "Finds a list of all mandats")
    @GetMapping("")
    public ResponseEntity<List<MandatDto>> findAll() throws Exception {
        ResponseEntity<List<MandatDto>> res = null;
        List<Mandat> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<MandatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all mandats")
    @GetMapping("optimized")
    public ResponseEntity<List<MandatDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MandatDto>> res = null;
        List<Mandat> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MandatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a mandat by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MandatDto> findById(@PathVariable Long id) {
        Mandat t = service.findById(id);
        if (t != null) {
            converter.init(true);
            MandatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a mandat by employe")
    @GetMapping("employe/{employe}")
    public ResponseEntity<MandatDto> findByEmploye(@PathVariable Employe employe) {
	    Mandat t = service.findByReferenceEntity(new Mandat(employe));
        if (t != null) {
            converter.init(true);
            MandatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  mandat")
    @PostMapping("")
    public ResponseEntity<MandatDto> save(@RequestBody MandatDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Mandat myT = converter.toItem(dto);
            Mandat t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MandatDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  mandat")
    @PutMapping("")
    public ResponseEntity<MandatDto> update(@RequestBody MandatDto dto) throws Exception {
        ResponseEntity<MandatDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Mandat t = service.findById(dto.getId());
            converter.copy(dto,t);
            Mandat updated = service.update(t);
            MandatDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of mandat")
    @PostMapping("multiple")
    public ResponseEntity<List<MandatDto>> delete(@RequestBody List<MandatDto> dtos) throws Exception {
        ResponseEntity<List<MandatDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Mandat> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified mandat")
    @DeleteMapping("")
    public ResponseEntity<MandatDto> delete(@RequestBody MandatDto dto) throws Exception {
		ResponseEntity<MandatDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Mandat t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified mandat")
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
    @Operation(summary = "Delete multiple mandats by ids")
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



    @Operation(summary = "Finds a mandat and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MandatDto> findWithAssociatedLists(@PathVariable Long id) {
        Mandat loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        MandatDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds mandats by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MandatDto>> findByCriteria(@RequestBody MandatCriteria criteria) throws Exception {
        ResponseEntity<List<MandatDto>> res = null;
        List<Mandat> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MandatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated mandats by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MandatCriteria criteria) throws Exception {
        List<Mandat> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<MandatDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets mandat data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MandatCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MandatDto> findDtos(List<Mandat> list){
        converter.initObject(true);
        List<MandatDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MandatDto> getDtoResponseEntity(MandatDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private MandatAdminService service;
    @Autowired private MandatConverter converter;





}
