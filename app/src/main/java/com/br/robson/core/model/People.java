package com.br.robson.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class People {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "enabled")
	private Integer enabled;
	
	/*
	 * @Column(name = "departamento_fk") private Long departamentoFk;
	 */
	
	@ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
	@JoinColumn(name="id_departamento")
	private Departament departament;
	
}
