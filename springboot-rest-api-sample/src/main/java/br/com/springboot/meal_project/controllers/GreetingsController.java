package br.com.springboot.meal_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.meal_project.model.Meals;
import br.com.springboot.meal_project.repository.MealRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired // Injeção de dependências
	private MealRepository mealRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/alo/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {

		Meals meal = new Meals();
		meal.setName(name);

		mealRepository.save(meal);

		return "Hello " + name;
	}

	@GetMapping(value = "listatodos")
	@ResponseBody
	public ResponseEntity<List<Meals>> listaMeal() {

		List<Meals> meals = mealRepository.findAll();

		return new ResponseEntity<List<Meals>>(meals, HttpStatus.OK);
	}

	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Meals> salvar(@RequestBody Meals meals) {
		Meals meal = mealRepository.save(meals);
		return new ResponseEntity<Meals>(meal, HttpStatus.CREATED);

	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Meals meals) {

		if (meals.getId() == null) {
			return new ResponseEntity<String>("ID não foi encontrado para atualizar!", HttpStatus.OK);
		}

		Meals meal = mealRepository.saveAndFlush(meals);
		return new ResponseEntity<Meals>(meal, HttpStatus.OK);

	}

	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long id) {
		mealRepository.deleteById(id);
		return new ResponseEntity<String>("Meal deletado com sucesso!", HttpStatus.OK);

	}

	@GetMapping(value = "buscarmealid")
	@ResponseBody
	public ResponseEntity<Meals> buscarmealid(@RequestParam(name = "id") Long id) {
		Meals meals = mealRepository.findById(id).get();
		return new ResponseEntity<Meals>(meals, HttpStatus.OK);

	}

	@GetMapping(value = "buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Meals>> buscarPorNome(@RequestParam(name = "name") String name) {
		List<Meals> meals = mealRepository.buscarPorNome(name.trim().toUpperCase());
		return new ResponseEntity<List<Meals>>(meals, HttpStatus.OK);

	}

}
