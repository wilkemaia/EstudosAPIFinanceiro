package com.financeiro.resource;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeiro.model.Categoria;
import com.financeiro.repository.CategoriaRepository;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	   @Autowired
	   private CategoriaRepository categoriaRepository;
	   
	   @Autowired
	   ApplicationEventPublisher publisher;
	   
	   @GetMapping
	   public List<Categoria>listar(){
		  return categoriaRepository.findAll(); 
	   }
	
	   
	   @PostMapping
	   public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria,HttpServletResponse response) {
		   
		  Categoria categoriaSalva = categoriaRepository.save(categoria);
		  /*
		  URI uri =ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				   .buildAndExpand(categoriaSalva.getCodigo()).toUri();
		   
		  response.setHeader("Location", uri.toASCIIString());
		  */
		  return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	   }
	   
	   @GetMapping("/{codigo}")
       public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		   
		   return this.categoriaRepository.findById(codigo)
			  .map(categoria -> ResponseEntity.ok(categoria))
		      .orElse(ResponseEntity.notFound().build());
		   
	   }
}
