package com.Enote.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.Enote.entity.Notes;
import com.Enote.entity.User;

public interface NoteService {
	public Notes saveNotes(Notes notes);
	
	public Notes getNotesById(int id);
	
	//public List <Notes> getNotesByUser(User user,int pageNo);
	
	public Page<Notes> getNotesByUser(User user,int pageNo);
	
	public Notes updateNotes(Notes notes);
	public boolean deleteNotes(int id);
}
