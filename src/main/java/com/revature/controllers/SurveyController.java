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
    
    @GetMapping("/template/{isTemplate}")
    public Page<Survey> findByTemplateIsTrueOrderByDateCreatedDesc(@PathVariable boolean isTemplate, @RequestParam int page) {
        if(isTemplate) {
        	return surveyService.findByTemplateIsTrue(page);
        } else {
        	return surveyService.findByTemplateIsFalse(page);
        }
    }
	    
    @GetMapping("template/{isTemplate}/title/{title}")
    public Page<Survey> findByTitleAndTemplate(@PathVariable boolean isTemplate, @PathVariable String title,@RequestParam int page) {
    	if(isTemplate) {
            return surveyService.findByTitleContainingIgnoreCaseAndTemplateIsTrue(title, page);
    	} else {    		
    		return surveyService.findByTitleContainingIgnoreCaseAndTemplateIsFalse(title, page);
    	}
    }
    
    @GetMapping("template/{isTemplate}/{email}/creator")
    public Page<Survey> findByCreatorIgnoreCase(@PathVariable boolean isTemplate, @PathVariable String email,@RequestParam int page) {
    	if(isTemplate) {    		
    		return surveyService.findByCreatorIgnoreCaseAndTemplateIsTrue(email, page);
    	} else {
    		return surveyService.findByCreatorIgnoreCaseAndTemplateIsFalse(email, page);
    	}
    }

	@GetMapping("/{id}")
	public Survey findById(@PathVariable int id) {
		return surveyService.findById(id);
	}

	@GetMapping("/description/{description}")
	public List<Survey> findByDescriptionContainingIgnoreCase(@PathVariable String description) {
		return surveyService.findByDescriptionContainingIgnoreCase(description);
	}

	@PostMapping
	public Survey save(@Valid @RequestBody Survey s) {
		return surveyService.save(s);
	}
}