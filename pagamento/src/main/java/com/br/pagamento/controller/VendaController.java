package com.br.pagamento.controller;

import com.br.pagamento.data.vo.VendaVO;
import com.br.pagamento.services.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/venda")
@RequiredArgsConstructor
public class VendaController {
    private final VendaService service;
    private final PagedResourcesAssembler<VendaVO> assembler;

    @GetMapping(value = "{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public VendaVO findById(@PathVariable Long id){
        VendaVO vo = service.findById(id);
        vo.add(linkById(id));
        return service.findById(id);
    }


    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@PageableDefault(page = 0, size = 10, sort = "data", direction = Sort.Direction.ASC) Pageable pageable){
        Page<VendaVO> vo = service.findAll(pageable);

        vo.stream().forEach(v -> v.add(linkById(v.getId())));

        PagedModel<EntityModel<VendaVO>> pagedModel = assembler.toModel(vo);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public VendaVO create(@RequestBody VendaVO vo) {
        VendaVO proVo = service.create(vo);
        proVo.add(linkTo(methodOn(VendaController.class).findById(proVo.getId())).withSelfRel());
        return proVo;
    }


    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public VendaVO update(@RequestBody VendaVO vo){
        VendaVO newVo = service.update(vo);
        newVo.add(linkById(newVo.getId()));
        return newVo;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    private Link linkById(Long id){
        return linkTo(methodOn(VendaController.class).findById(id)).withSelfRel();
    }

}
