package com.claudemirojr.app.produtos.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claudemirojr.app.produtos.converter.DozerConverter;
import com.claudemirojr.app.produtos.dto.ProdutoDTO;
import com.claudemirojr.app.produtos.models.ParamsRequestModel;
import com.claudemirojr.app.produtos.models.entity.Produto;
import com.claudemirojr.app.produtos.models.service.IProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(tags = { "ProdutoEndpoint" })
@SwaggerDefinition(tags = { @Tag(name = "ProdutoEndpoint", description = "descrição do endpoint") })
@RestController
public class ProdutoController {

	@Autowired
	private IProdutoService _service;

	@Autowired
	private PagedResourcesAssembler<ProdutoDTO> _assembler;

	private ProdutoDTO conerterToProdutoDTO(Produto produto) {
		return DozerConverter.parseObject(produto, ProdutoDTO.class);
	}
	
	
	

	@ApiOperation(value = "Listar todos os produtos")
	@GetMapping("/listar")
	public ResponseEntity<?> findAll(@RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<Produto> produtosPage = _service.findAll(prm);

		Page<ProdutoDTO> produtoDTO = produtosPage.map(this::conerterToProdutoDTO);

		produtoDTO.stream()
				.forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).FindById(p.getKey())).withSelfRel()));

		PagedModel<?> resources = _assembler.toModel(produtoDTO);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "buscar produtos pelo nome")
	@GetMapping("/findByNome/{nome}")
	public ResponseEntity<?> findByNomeContaining(@PathVariable String nome, @RequestParam Map<String, String> params) {
		ParamsRequestModel prm = new ParamsRequestModel(params);

		Page<Produto> produtosPage = _service.findByNomeContaining(nome, prm);

		Page<ProdutoDTO> produtoDTO = produtosPage.map(this::conerterToProdutoDTO);

		produtoDTO.stream()
				.forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).FindById(p.getKey())).withSelfRel()));

		PagedModel<?> resources = _assembler.toModel(produtoDTO);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "Localizar um produto específico")
	@GetMapping("/{id}")
	public ProdutoDTO FindById(@PathVariable Long id) {
		Produto produto = _service.FindById(id);
		ProdutoDTO produtoDTO = this.conerterToProdutoDTO(produto);

		produtoDTO.add(linkTo(methodOn(ProdutoController.class).FindById(id)).withSelfRel());

		return produtoDTO;
	}

	@ApiOperation(value = "Salvar um produto no banco")
	@PostMapping
	public ProdutoDTO save(@RequestBody @Valid ProdutoDTO produtoDTO) {
		var entity = DozerConverter.parseObject(produtoDTO, Produto.class);

		Produto produto = _service.insert(entity);

		produtoDTO = this.conerterToProdutoDTO(produto);

		produtoDTO.add(linkTo(methodOn(ProdutoController.class).FindById(produto.getId())).withSelfRel());

		return produtoDTO;
	}

	@ApiOperation(value = "Atualizar um produto no banco")
	@PutMapping("/{id}")
	public ProdutoDTO update(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO) {
		Produto produtoExiste = _service.FindById(id);

		produtoExiste.Atualizar(produtoDTO.getNome(), produtoDTO.getPreco());

		Produto produto = _service.update(produtoExiste);

		produtoDTO = this.conerterToProdutoDTO(produto);

		produtoDTO.add(linkTo(methodOn(ProdutoController.class).FindById(produto.getId())).withSelfRel());

		return produtoDTO;
	}

	@ApiOperation(value = "Remover um produto no banco")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		_service.FindById(id);

		_service.deleteById(id);

		return ResponseEntity.ok().build();
	}

}
