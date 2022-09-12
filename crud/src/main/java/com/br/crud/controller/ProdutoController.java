package com.br.crud.controller;

import com.br.crud.data.vo.ProdutoVO;
import com.br.crud.service.ProdutoService;
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
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;
    private final PagedResourcesAssembler<ProdutoVO> assembler;

    @GetMapping(value = "{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ProdutoVO findById(@PathVariable Long id){
        ProdutoVO vo = service.findById(id);
        vo.add(linkById(id));
        return service.findById(id);
    }


    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
        Page<ProdutoVO> vo = service.findAll(pageable);

        vo.stream().forEach(v -> v.add(linkById(v.getId())));

        PagedModel<EntityModel<ProdutoVO>> pagedModel = assembler.toModel(vo);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
                 consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ProdutoVO create(@RequestBody ProdutoVO vo){
        ProdutoVO newVo = service.create(vo);
        newVo.add(linkById(newVo.getId()));
        return newVo;
    }


    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ProdutoVO update(@RequestBody ProdutoVO vo){
        ProdutoVO newVo = service.update(vo);
        newVo.add(linkById(newVo.getId()));
        return newVo;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    private Link linkById(Long id){
        return linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel();
    }

}
