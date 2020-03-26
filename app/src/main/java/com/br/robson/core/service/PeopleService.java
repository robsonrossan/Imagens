package com.br.robson.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.robson.core.model.People;
import com.br.robson.core.repository.PeopleRepository;

@Service
public class PeopleService {

	@Autowired
	PeopleRepository repository;

	public List<People> findPersonByName(String firstName) {
		List<People> listPeole = repository.findPersonByName(firstName);
		return listPeole;
	}

}
