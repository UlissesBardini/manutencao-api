package br.com.farol.manutencaoapi.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.farol.manutencaoapi.enums.Disponibilidade;
import br.com.farol.manutencaoapi.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Item")
@Table(name = "itens")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id")
	private Integer id;

	@NotEmpty(message = "O código do item é obrigatório")
	@NotBlank(message = "O código não deve conter espaços")
	@Pattern(regexp = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]+)$", message = "O código deve conter letras e números")
	@Size(max = 7, message = "O código não pode possuir mais de 7 caracteres")
	@Column(name = "codigo")
	private String codigo;

	@NotNull(message = "A disponibilidade é obrigatória")
	@Enumerated(EnumType.STRING)
	@Column(name = "disponibilidade")
	private Disponibilidade disponibilidade;

	@NotNull(message = "O Status é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@NotEmpty(message = "A descrição é obrigatória")
	@Size(max = 100, message = "A descrição não pode possuir mais de 100 caracteres")
	@Column(name = "descricao")
	private String descricao;

	@NotEmpty(message = "A localização é obrigatória")
	@Size(max = 250, min = 3, message = "A localização deve possuir entre 3 e 250 caracteres")
	@Column(name = "localizacao")
	private String localizacao;

	@Column(name = "dt_movimentacao")
	private LocalDateTime dataDeMovimentacao;

	@NotNull(message = "isDeletado é obrigatório")
	@Column(name = "deleted")
	@Max(value = 1, message = "O valor só pode ser 1 ou 0")
	@PositiveOrZero(message = "O valor só pode ser 1 ou 0")
	private Integer isDeletado = 0;

}
