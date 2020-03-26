package com.br.robson.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.robson.core.model.vo.DepartamentPeopleVO;
import com.br.robson.core.model.vo.DepartamentVO;
import com.br.robson.core.model.vo.PeopleVO;
import com.br.robson.core.service.DepartamentService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/coreJasmin/departament")
@Log4j2
public class DepartamentController {
	
	@Autowired
	private DepartamentService service;
	
	List<DepartamentPeopleVO> listaRetorno = new ArrayList<>();
	
	
	/**
	 * Consulta Por ID
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public DepartamentVO findDepartamentById(@PathVariable("id") Long id){
		DepartamentVO departamentVO = service.findDepartamentById(id);
		return departamentVO;
		
	}

	/**
	 * Consulta Por nome do Departamento. VALOR PASSADO NO BODY
	 * @param departamentPeopleVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/findByDepartament", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<Map<String, Set<PeopleVO>>> findByDepartament(@RequestBody DepartamentPeopleVO departamentPeopleVO) throws Exception{
		
		Map<String, Set<PeopleVO>> mapaRetorno = service.findPersonByName(departamentPeopleVO.getNomeDepartamento());
		log.debug("Lista de Retorno: " + mapaRetorno);
		
		return ResponseEntity.ok(mapaRetorno);
	}
	
	/**
	 * Consulta Por nome do Departamento. VALOR PASSADO NO BODY
	 * @param departamentPeopleVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/findPersonByIdDepartament", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<Map<String, Set<PeopleVO>>> findPersonByIdDepartament() throws Exception{
		String idDepartamento = null;
		Map<String, Set<PeopleVO>> mapaRetorno = service.findPersonByIdDepartament(idDepartamento);
		log.debug("Lista de Retorno: " + mapaRetorno);
		
		return ResponseEntity.ok(mapaRetorno);
	}
}
