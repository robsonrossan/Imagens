package com.br.robson.core.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.br.robson.core.converter.ConverterObject;
import com.br.robson.core.model.Departament;
import com.br.robson.core.model.vo.DepartamentPeopleVO;
import com.br.robson.core.model.vo.DepartamentVO;
import com.br.robson.core.model.vo.PeopleVO;
import com.br.robson.core.repository.DepartamentRepository;


@Service
public class DepartamentService {
	
	@Autowired
	private DepartamentRepository repository;
	
	/**
	 * Consulta Por ID
	 * @param id
	 * @return
	 */
	public DepartamentVO findDepartamentById(Long id) {
		Departament entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não Existe Infomações para o ID"));
		return ConverterObject.parseObject(entity, DepartamentVO.class);
	}
	
	/**
	 * Retorna as consulta simples passando o nomeDepartamento como parametro
	 * @param nomeDepartamento
	 * @return
	 */
	public Map<String, Set<PeopleVO>> findPersonByName(String nomeDepartamento) {
		
		List<DepartamentPeopleVO> listaRetorno = new ArrayList<>();
		Integer idDepartamento;
		String nomDepartamento;
		String firstName;
		String lastName;
		String address;
		try {
			List<Object[]> listResult = repository.findPersonByDepartament(nomeDepartamento);
			
			if(listResult.size() > 0) {
				
				for(Object [] obj: listResult) {
					DepartamentPeopleVO departamentPeopleVO = new DepartamentPeopleVO();
					
					idDepartamento =  ((BigInteger) obj[0]).intValue();
					nomDepartamento =  (String) obj[1];
					firstName =  (String) obj[2];
					lastName =  (String) obj[3];
					address =  (String) obj[4];
					
					departamentPeopleVO.setIdDepartamento(idDepartamento);
					departamentPeopleVO.setNomeDepartamento(nomDepartamento);
					departamentPeopleVO.setFirstName(firstName);
					departamentPeopleVO.setLastName(lastName);
					departamentPeopleVO.setAddress(address);
					listaRetorno.add(departamentPeopleVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.montarListaRetorno(listaRetorno);
	}
	
	/**
	 * Retorna as consulta passando o idDapartamento como parametro
	 * @param idDapartamento
	 * @return
	 */
	public Map<String, Set<PeopleVO>> findPersonByIdDepartament(String idDapartamento) {
		
		List<DepartamentPeopleVO> listaRetorno = new ArrayList<>();
		Integer idDepartamento;
		String nomDepartamento;
		String firstName;
		String lastName;
		String address;
		try {
			List<Object[]> listResult = repository.findPersonByIdDepartament(idDapartamento);
			
			if(listResult.size() > 0) {
				
				for(Object [] obj: listResult) {
					DepartamentPeopleVO departamentPeopleVO = new DepartamentPeopleVO();
					
					idDepartamento =  ((BigInteger) obj[0]).intValue();
					nomDepartamento =  (String) obj[1];
					firstName =  (String) obj[2];
					lastName =  (String) obj[3];
					address =  (String) obj[4];
					
					departamentPeopleVO.setIdDepartamento(idDepartamento);
					departamentPeopleVO.setNomeDepartamento(nomDepartamento);
					departamentPeopleVO.setFirstName(firstName);
					departamentPeopleVO.setLastName(lastName);
					departamentPeopleVO.setAddress(address);
					listaRetorno.add(departamentPeopleVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.montarListaRetorno(listaRetorno);
	}

	
	public Map<String, Set<PeopleVO>> montarListaRetorno(List<DepartamentPeopleVO> listaRetornoDp){
		
		PeopleVO peopleVO = new PeopleVO();
		
		Map<String, Set<PeopleVO>> mapRetorno = new HashMap<String, Set<PeopleVO>>();
		Set<PeopleVO> listaTratada = new HashSet<PeopleVO>(); 
		
		try {
			for(DepartamentPeopleVO dp: listaRetornoDp) {
				
				peopleVO = new PeopleVO();
				peopleVO.setFirstName(dp.getFirstName());
				peopleVO.setLastName(dp.getLastName());
				peopleVO.setAddress(dp.getAddress());
				listaTratada.add(peopleVO);
				mapRetorno.put(dp.getNomeDepartamento(), listaTratada);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapRetorno;
	}

}
