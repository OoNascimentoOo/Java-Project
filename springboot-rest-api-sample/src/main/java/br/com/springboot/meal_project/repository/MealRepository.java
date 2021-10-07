package br.com.springboot.meal_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.meal_project.model.Meals;

@Repository
public interface MealRepository extends JpaRepository<Meals, Long> {

	@Query(value = "select m from Meals m where upper(trim(m.name)) like %?1%")
	List<Meals> buscarPorNome(String name);

}
