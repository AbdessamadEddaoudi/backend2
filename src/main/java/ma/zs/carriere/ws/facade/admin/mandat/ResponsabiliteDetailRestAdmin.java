package  ma.zs.carriere.ws.facade.admin.mandat;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;

import ma.zs.carriere.bean.core.mandat.ResponsabiliteDetail;
import ma.zs.carriere.dao.criteria.core.mandat.ResponsabiliteDetailCriteria;
import ma.zs.carriere.service.facade.admin.mandat.ResponsabiliteDetailAdminService;
import ma.zs.carriere.ws.converter.mandat.ResponsabiliteDetailConverter;
import ma.zs.carriere.ws.dto.mandat.ResponsabiliteDetailDto;
import ma.zs.carriere.zynerator.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin/responsabiliteDetail/")
public class ResponsabiliteDetailRestAdmin {




    @Operation(summary = "Finds a list of all responsabiliteDetails")
    @GetMapping("")
    public ResponseEntity<List<ResponsabiliteDetailDto>> findAll() throws Exception {
        ResponseEntity<List<ResponsabiliteDetailDto>> res = null;
        List<ResponsabiliteDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ResponsabiliteDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all responsabiliteDetails")
    @GetMapping("optimized")
    public ResponseEntity<List<ResponsabiliteDetailDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ResponsabiliteDetailDto>> res = null;
        List<ResponsabiliteDetail> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ResponsabiliteDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a responsabiliteDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ResponsabiliteDetailDto> findById(@PathVariable Long id) {
        ResponsabiliteDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ResponsabiliteDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a responsabiliteDetail by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<ResponsabiliteDetailDto> findByRef(@PathVariable String ref) {
	    ResponsabiliteDetail t = service.findByReferenceEntity(new ResponsabiliteDetail(ref));
        if (t != null) {
            converter.init(true);
            ResponsabiliteDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  responsabiliteDetail")
    @PostMapping("")
    public ResponseEntity<ResponsabiliteDetailDto> save(@RequestBody ResponsabiliteDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ResponsabiliteDetail myT = converter.toItem(dto);
            ResponsabiliteDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ResponsabiliteDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  responsabiliteDetail")
    @PutMapping("")
    public ResponseEntity<ResponsabiliteDetailDto> update(@RequestBody ResponsabiliteDetailDto dto) throws Exception {
        ResponseEntity<ResponsabiliteDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ResponsabiliteDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            ResponsabiliteDetail updated = service.update(t);
            ResponsabiliteDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of responsabiliteDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<ResponsabiliteDetailDto>> delete(@RequestBody List<ResponsabiliteDetailDto> dtos) throws Exception {
        ResponseEntity<List<ResponsabiliteDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ResponsabiliteDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified responsabiliteDetail")
    @DeleteMapping("")
    public ResponseEntity<ResponsabiliteDetailDto> delete(@RequestBody ResponsabiliteDetailDto dto) throws Exception {
		ResponseEntity<ResponsabiliteDetailDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            ResponsabiliteDetail t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified responsabiliteDetail")
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
    @Operation(summary = "Delete multiple responsabiliteDetails by ids")
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



    @Operation(summary = "Finds a responsabiliteDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ResponsabiliteDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        ResponsabiliteDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ResponsabiliteDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds responsabiliteDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ResponsabiliteDetailDto>> findByCriteria(@RequestBody ResponsabiliteDetailCriteria criteria) throws Exception {
        ResponseEntity<List<ResponsabiliteDetailDto>> res = null;
        List<ResponsabiliteDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ResponsabiliteDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated responsabiliteDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ResponsabiliteDetailCriteria criteria) throws Exception {
        List<ResponsabiliteDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ResponsabiliteDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets responsabiliteDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ResponsabiliteDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ResponsabiliteDetailDto> findDtos(List<ResponsabiliteDetail> list){
        converter.initObject(true);
        List<ResponsabiliteDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ResponsabiliteDetailDto> getDtoResponseEntity(ResponsabiliteDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ResponsabiliteDetailAdminService service;
    @Autowired private ResponsabiliteDetailConverter converter;





}
