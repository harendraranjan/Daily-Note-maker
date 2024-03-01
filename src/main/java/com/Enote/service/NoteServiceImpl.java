package com.Enote.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Enote.entity.Notes;
import com.Enote.entity.User;
import com.Enote.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	private NoteRepository noteRepository;

	@Override
	public Notes saveNotes(Notes notes) {
		return noteRepository.save(notes);
	}

	@Override
	public Notes getNotesById(int id) {
		return noteRepository.findById(id).get() ;
	}

	@Override
	public Page<Notes> getNotesByUser(User user,int pageNo) {
		Pageable pageable= PageRequest.of(pageNo, 5);
		return noteRepository.findByUser(user,pageable);
	}

	@Override
	public Notes updateNotes(Notes notes) {
		return noteRepository.save(notes);
	}

	@Override
	public boolean deleteNotes(int id) {
		Notes notes=noteRepository.findById(id).get();
		if(notes!=null) {
			noteRepository.delete(notes);
			return true;
		}
		return false;
	}

}
