package com.br.robson.core.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "departamento")
@EqualsAndHashCode(of = "id")
public class Departament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_departamento")
	private Long id;
	
	@Column(name = "nome_departamento")
	private String nomeDepartamento;
	
	@Column(name = "data_criacao_dp")
	private Date dataCriacaoDp;
	
	@Column(name = "enable")
	private Integer enable;
		
   @OneToMany( targetEntity=People.class )
   private List<People> listPeople;
 
	 
}
