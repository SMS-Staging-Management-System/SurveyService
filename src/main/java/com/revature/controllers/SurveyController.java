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
	
    @GetMapping("/template/{page}")
    public Page<Survey> findByTemplateIsTrueOrderByDateCreatedDesc(@PathVariable int page) {
        System.out.println(page);
        return surveyService.findByTemplateIsTrueOrderByDateCreatedDesc(page);
    }
    
    @GetMapping("/survey/{page}")
    public Page<Survey> findByTemplateIsFalseOrderByDateCreatedDesc(@PathVariable int page) {
        System.out.println(page);
        return surveyService.findByTemplateIsFalseOrderByDateCreatedDesc(page);
    }
	    
    @GetMapping("template/{title}/{page}")
    public Page<Survey> findByTitleAndTemplate(@PathVariable String title,@PathVariable int page) {
        System.out.println(page);
        return surveyService.findByTitleContainingIgnoreCaseAndTemplateIsTrue(title, page);
    }
    
//	@GetMapping("published")
//	public List<Survey> findAllPublished() {
//		return surveyService.findAllPublishedOrderByDateCreatedDesc();
//	}

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

//	@CognitoAuth(roles= {CognitoRoles.STAGING_MANAGER, CognitoRoles.TRAINER})
	@PostMapping
	public Survey save(@Valid @RequestBody Survey s) {
		return surveyService.save(s);
	}
	
//	@GetMapping("template/creator/{creator}/")
//	public Page<Survey> findByCreatorIgnoreCase(@PathVariable String creator){
//		return surveyService.findByCreatorIgnoreCase(creator, 0);
//	}
    @GetMapping("template/creator/{title}/{page}")
    public Page<Survey> findByCreatorIgnoreCase(@PathVariable String title,@PathVariable int page) {
        System.out.println(page);
        return surveyService.findByCreatorIgnoreCaseAndTemplateIsTrue(title, page);
    }
}