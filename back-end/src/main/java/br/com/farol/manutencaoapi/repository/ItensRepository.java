package br.com.farol.manutencaoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.farol.manutencaoapi.entity.Item;

@Repository
public interface ItensRepository extends JpaRepository<Item, Integer> {

	@Query("SELECT i FROM Item i WHERE i.id = :id AND isDeletado = 0")
	public Item buscarPor(@Param("id") Integer id);

	@Query("SELECT i FROM Item i WHERE i.codigo = :codigo AND isDeletado = 0")
	public Item buscarPor(@Param("codigo") String codigo);

	@Query("SELECT i FROM Item i WHERE isDeletado = 0")
	public List<Item> ListarTodos();

	@Query("SELECT i FROM Item i WHERE i.descricao LIKE :desc AND isDeletado = 0")
	public List<Item> listarPor(@Param("desc") String descricao);
	
}
