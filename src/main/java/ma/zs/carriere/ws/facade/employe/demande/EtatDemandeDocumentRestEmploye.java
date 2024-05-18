package  ma.zs.carriere.ws.facade.employe.demande;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.carriere.bean.core.demande.EtatDemandeDocument;
import ma.zs.carriere.dao.criteria.core.demande.EtatDemandeDocumentCriteria;
import ma.zs.carriere.service.facade.employe.demande.EtatDemandeDocumentEmployeService;
import ma.zs.carriere.ws.converter.demande.EtatDemandeDocumentConverter;
import ma.zs.carriere.ws.dto.demande.EtatDemandeDocumentDto;
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
@RequestMapping("/api/employe/etatDemandeDocument/")
public class EtatDemandeDocumentRestEmploye {




    @Operation(summary = "Finds a list of all etatDemandeDocuments")
    @GetMapping("")
    public ResponseEntity<List<EtatDemandeDocumentDto>> findAll() throws Exception {
        ResponseEntity<List<EtatDemandeDocumentDto>> res = null;
        List<EtatDemandeDocument> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatDemandeDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a etatDemandeDocument by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtatDemandeDocumentDto> findById(@PathVariable Long id) {
        EtatDemandeDocument t = service.findById(id);
        if (t != null) {
            EtatDemandeDocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  etatDemandeDocument")
    @PostMapping("")
    public ResponseEntity<EtatDemandeDocumentDto> save(@RequestBody EtatDemandeDocumentDto dto) throws Exception {
        if(dto!=null){
            EtatDemandeDocument myT = converter.toItem(dto);
            EtatDemandeDocument t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EtatDemandeDocumentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  etatDemandeDocument")
    @PutMapping("")
    public ResponseEntity<EtatDemandeDocumentDto> update(@RequestBody EtatDemandeDocumentDto dto) throws Exception {
        ResponseEntity<EtatDemandeDocumentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EtatDemandeDocument t = service.findById(dto.getId());
            converter.copy(dto,t);
            EtatDemandeDocument updated = service.update(t);
            EtatDemandeDocumentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etatDemandeDocument")
    @PostMapping("multiple")
    public ResponseEntity<List<EtatDemandeDocumentDto>> delete(@RequestBody List<EtatDemandeDocumentDto> dtos) throws Exception {
        ResponseEntity<List<EtatDemandeDocumentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EtatDemandeDocument> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified etatDemandeDocument")
    @DeleteMapping("")
    public ResponseEntity<EtatDemandeDocumentDto> delete(@RequestBody EtatDemandeDocumentDto dto) throws Exception {
		ResponseEntity<EtatDemandeDocumentDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            EtatDemandeDocument t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified etatDemandeDocument")
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
    @Operation(summary = "Delete multiple etatDemandeDocuments by ids")
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



    @Operation(summary = "Finds a etatDemandeDocument and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtatDemandeDocumentDto> findWithAssociatedLists(@PathVariable Long id) {
        EtatDemandeDocument loaded =  service.findWithAssociatedLists(id);
        EtatDemandeDocumentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds etatDemandeDocuments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EtatDemandeDocumentDto>> findByCriteria(@RequestBody EtatDemandeDocumentCriteria criteria) throws Exception {
        ResponseEntity<List<EtatDemandeDocumentDto>> res = null;
        List<EtatDemandeDocument> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatDemandeDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated etatDemandeDocuments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EtatDemandeDocumentCriteria criteria) throws Exception {
        List<EtatDemandeDocument> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EtatDemandeDocumentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets etatDemandeDocument data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EtatDemandeDocumentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EtatDemandeDocumentDto> findDtos(List<EtatDemandeDocument> list){
        List<EtatDemandeDocumentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtatDemandeDocumentDto> getDtoResponseEntity(EtatDemandeDocumentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EtatDemandeDocumentEmployeService service;
    @Autowired private EtatDemandeDocumentConverter converter;





}
