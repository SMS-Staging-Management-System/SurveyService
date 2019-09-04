package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cognito.annotations.CognitoAuth;
import com.revature.cognito.constants.CognitoRoles;
import com.revature.models.Survey;
import com.revature.services.SurveyService;

@RestController
@RequestMapping("surveys")
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	// All Surveys and Templates
	@GetMapping("")
	public List<Survey> findAll() {
		return surveyService.findAllOrderByDateCreatedDesc();
	}
	
	@GetMapping("template/{isTemplate}")
	public Page<Survey> findByTemplate(@PathVariable String isTemplate, @RequestParam int page) {
		return surveyService.findByTemplateOrderByDateCreatedDesc(isTemplate, page);
	}

	@GetMapping("/{id}")
	public Survey findById(@PathVariable int id) {
		return surveyService.findById(id);
	}

//	@GetMapping("/title/{title}")
//	public List<Survey> findByTitle(@PathVariable String title) {
//		return surveyService.findByTitle(title);
//	}

	@GetMapping("/title/{title}")
	public List<Survey> findByTitleContainingIgnoreCase(@PathVariable String title) {
		return surveyService.findByTitleContainingIgnoreCase(title);
	}

	@GetMapping("/description/{description}")
	public List<Survey> findByDescriptionContainingIgnoreCase(@PathVariable String description) {
		return surveyService.findByDescriptionContainingIgnoreCase(description);
	}

	@CognitoAuth(roles= {CognitoRoles.STAGING_MANAGER, CognitoRoles.TRAINER})
	@PostMapping
	public Survey save(@Valid @RequestBody Survey s) {
		return surveyService.save(s);
	}
}