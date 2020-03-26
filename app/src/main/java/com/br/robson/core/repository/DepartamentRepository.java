package com.br.robson.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.robson.core.model.Departament;

@Repository
public interface DepartamentRepository extends JpaRepository<Departament, Long>{
	
@Query(value = "select d.id_departamento, " +  
			  "d.nome_departamento, " +
	          "p.first_name, " +
			  "p.last_name, " +
	          "p.address from departamento d " +
		"inner join person p on p.departamento_fk = d.id_departamento " +
	    "where d.nome_departamento = :nomeDepartamento ", nativeQuery = true)
	public List<Object[]> findPersonByDepartament(String nomeDepartamento);
	
	
	@Query(value = "select d.id_departamento, \r\n" + 
			"		d.nome_departamento,\r\n" + 
			"        p.first_name,\r\n" + 
			"		p.last_name,\r\n" + 
			"        p.address\r\n" + 
			" from db_estudo.person p\r\n" + 
			"	inner join db_estudo.departamento d on d.id_departamento = p.departamento_fk\r\n" + 
			"	where p.departamento_fk in (1,2)", nativeQuery = true)
	public List<Object[]> findPersonByIdDepartament(String idDapartamento);

}
