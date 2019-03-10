package com.revature.services;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Editor;
import com.revature.repos.EditorRepo;

@Service
public class EditorServiceImpl implements EditorService {

	@Autowired
	private EditorRepo eRepo;

	@Override
	public List<Editor> findAll() {
		return eRepo.findAll();
	}

	@Override
	@Transactional
	public Editor findById(int id) {
		return eRepo.getOne(id);
	}

	@Override
	public Editor findByEmail(String email) {
		return eRepo.findByEmail(email);
	}

	@Override
	public List<Editor> findBySurveyId(int id) {
		return eRepo.findBySurveyId(id);
	}

	@Override
	public Editor createEditor(Editor editor) {
		return eRepo.save(editor);
	}

	@Override
	@Transactional
	public Editor updateEditor(Editor editor) {
		Editor updatedEditor= new Editor();
		updatedEditor=findById(editor.getId());
		updatedEditor.setEmail(editor.getEmail());
		updatedEditor.setSurveyId(editor.getSurveyId());
		return eRepo.save(updatedEditor);
	}

	@Override
	public void deleteEditor(int id) {
		eRepo.deleteById(id);
	}

}
