package  ma.zs.carriere.ws.facade.employe.mandat;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;

import ma.zs.carriere.bean.core.mandat.Responsabilite;
import ma.zs.carriere.dao.criteria.core.mandat.ResponsabiliteCriteria;
import ma.zs.carriere.service.facade.employe.mandat.ResponsabiliteEmployeService;
import ma.zs.carriere.ws.converter.mandat.ResponsabiliteConverter;
import ma.zs.carriere.ws.dto.mandat.ResponsabiliteDto;
import ma.zs.carriere.zynerator.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/employe/responsabilite/")
public class ResponsabiliteRestEmploye {




    @Operation(summary = "Finds a list of all responsabilites")
    @GetMapping("")
    public ResponseEntity<List<ResponsabiliteDto>> findAll() throws Exception {
        ResponseEntity<List<ResponsabiliteDto>> res = null;
        List<Responsabilite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ResponsabiliteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all responsabilites")
    @GetMapping("optimized")
    public ResponseEntity<List<ResponsabiliteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ResponsabiliteDto>> res = null;
        List<Responsabilite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ResponsabiliteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a responsabilite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ResponsabiliteDto> findById(@PathVariable Long id) {
        Responsabilite t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ResponsabiliteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a responsabilite by nom")
    @GetMapping("nom/{nom}")
    public ResponseEntity<ResponsabiliteDto> findByNom(@PathVariable String nom) {
	    Responsabilite t = service.findByReferenceEntity(new Responsabilite(nom));
        if (t != null) {
            converter.init(true);
            ResponsabiliteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  responsabilite")
    @PostMapping("")
    public ResponseEntity<ResponsabiliteDto> save(@RequestBody ResponsabiliteDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Responsabilite myT = converter.toItem(dto);
            Responsabilite t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ResponsabiliteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  responsabilite")
    @PutMapping("")
    public ResponseEntity<ResponsabiliteDto> update(@RequestBody ResponsabiliteDto dto) throws Exception {
        ResponseEntity<ResponsabiliteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Responsabilite t = service.findById(dto.getId());
            converter.copy(dto,t);
            Responsabilite updated = service.update(t);
            ResponsabiliteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of responsabilite")
    @PostMapping("multiple")
    public ResponseEntity<List<ResponsabiliteDto>> delete(@RequestBody List<ResponsabiliteDto> dtos) throws Exception {
        ResponseEntity<List<ResponsabiliteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Responsabilite> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified responsabilite")
    @DeleteMapping("")
    public ResponseEntity<ResponsabiliteDto> delete(@RequestBody ResponsabiliteDto dto) throws Exception {
		ResponseEntity<ResponsabiliteDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Responsabilite t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified responsabilite")
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
    @Operation(summary = "Delete multiple responsabilites by ids")
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



    @Operation(summary = "Finds a responsabilite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ResponsabiliteDto> findWithAssociatedLists(@PathVariable Long id) {
        Responsabilite loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ResponsabiliteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds responsabilites by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ResponsabiliteDto>> findByCriteria(@RequestBody ResponsabiliteCriteria criteria) throws Exception {
        ResponseEntity<List<ResponsabiliteDto>> res = null;
        List<Responsabilite> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ResponsabiliteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated responsabilites by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ResponsabiliteCriteria criteria) throws Exception {
        List<Responsabilite> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ResponsabiliteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets responsabilite data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ResponsabiliteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ResponsabiliteDto> findDtos(List<Responsabilite> list){
        converter.initObject(true);
        List<ResponsabiliteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ResponsabiliteDto> getDtoResponseEntity(ResponsabiliteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ResponsabiliteEmployeService service;
    @Autowired private ResponsabiliteConverter converter;





}
