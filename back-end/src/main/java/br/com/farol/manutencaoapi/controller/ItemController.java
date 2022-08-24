package br.com.farol.manutencaoapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.farol.manutencaoapi.entity.Item;
import br.com.farol.manutencaoapi.service.ItemService;
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/itens")
public class ItemController {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MapConverter mapConverter;
	
	@Autowired
	private ItemService service;

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Map<String, Object> itemMap) {
		Item novoItem = mapper.convertValue(itemMap, Item.class);
		Item itemSalvo = service.inserir(novoItem);
		return ResponseEntity.created(URI.create("/itens/id/" + itemSalvo.getId())).build();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(mapConverter.toJsonMap(service.buscarPor(id)));
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Map<String, Object> itemMap) {
		Item novoItem = mapper.convertValue(itemMap, Item.class);
		Item itemAtualizado = service.alterar(novoItem);
		return ResponseEntity.ok(mapConverter.toJsonMap(itemAtualizado));
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<?> listarPor(@PathVariable(name = "descricao") String descricao) {
		List<Item> lista = null;
		lista = service.listarPor(descricao);
		return ResponseEntity.ok(mapConverter.toJsonList(lista));
	}
	
	@GetMapping("/descricao/")
	public ResponseEntity<?> listarTodos() {
		List<Item> lista = null;
		lista = service.listarTodos();
		return ResponseEntity.ok(mapConverter.toJsonList(lista));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> excluirPor(@PathVariable(name = "id") Integer id) {
		this.service.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
