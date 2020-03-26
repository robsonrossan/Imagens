package com.br.robson.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.br.robson.core.model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long>{

	@Query("SELECT p FROM People p WHERE p.firstName LIKE LOWER(CONCAT ('%', :firstName, '%'))")
	List<People> findPersonByName(@Param("firstName") String firstName);
}
