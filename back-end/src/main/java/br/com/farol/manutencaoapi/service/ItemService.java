package br.com.farol.manutencaoapi.service;

import static com.google.common.base.Preconditions.checkArgument;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.farol.manutencaoapi.entity.Item;
import br.com.farol.manutencaoapi.exception.EntidadeDeletadaException;
import br.com.farol.manutencaoapi.exception.RegistroNaoEncontradoException;
import br.com.farol.manutencaoapi.repository.ItensRepository;

@Service
@Validated
public class ItemService {

	@Autowired
	private ItensRepository repository;

	public Item inserir(@NotNull @Valid Item item) {
		checkArgument(item.getId() == null, "O id deve ser nulo para inserção");
		this.isUnico(item);
		if (item.getIsDeletado() == 1) {
			throw new EntidadeDeletadaException();
		}
		item.setDataDeMovimentacao(LocalDateTime.now());
		return repository.save(item);
	}

	public Item buscarPor(@NotNull Integer id) {
		Item item = repository.buscarPor(id);
		if (item == null) {
			throw new RegistroNaoEncontradoException("Nenhum registro encontrado");
		}
		return item;
	}

	public List<Item> listarPor(@NotEmpty String descricao) {
		return repository.listarPor("%" + descricao + "%");
	}

	public List<Item> listarTodos() {
		return repository.ListarTodos();
	}

	public Item alterar(@NotNull @Valid Item item) {
		checkArgument(item.getId() != null, "O id não pode ser nulo para atualização");
		this.isUnico(item);
		if (item.getIsDeletado() == 1) {
			throw new EntidadeDeletadaException();
		}
		item.setDataDeMovimentacao(LocalDateTime.now());
		return repository.save(item);
	}

	public void excluir(@NotNull @Valid Integer id) {
		Item item = repository.buscarPor(id);
		if (item.getIsDeletado() == 1) {
			throw new EntidadeDeletadaException();
		}
		item.setIsDeletado(1);
		repository.save(item);
	}
	
	private void isUnico(Item item) {
		Item itemBuscado = repository.buscarPor(item.getCodigo());
		if (itemBuscado != null) {
			checkArgument(itemBuscado.equals(item), "O código digitado já existe");
		}
	}
}
