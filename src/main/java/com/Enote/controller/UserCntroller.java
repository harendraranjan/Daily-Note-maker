package com.Enote.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Enote.entity.Notes;
import com.Enote.entity.User;
import com.Enote.repository.UserRepository;
import com.Enote.service.NoteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserCntroller {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private NoteService noteService;

	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}

	@RequestMapping("/addNote")
	public String addNote() {
		return "addNote";
	}

	@GetMapping("/editNote/{id}")
	public String editNote( @PathVariable int id,Model m) {
		Notes notes=noteService.getNotesById(id);
		m.addAttribute("n",notes);
		return "editNote";
	}

	@RequestMapping("/viewNote")
	public String viewNote(Model m,Principal p,@RequestParam(defaultValue = "0") Integer pageNo) {
		User user=getUser(p, m);
		Page<Notes> notes = noteService.getNotesByUser(user,pageNo);
		
		m.addAttribute("currentPage",pageNo);
		m.addAttribute("totalElement",notes.getTotalElements());
		m.addAttribute("totalpage",notes.getTotalPages());
		m.addAttribute("notesList",notes.getContent());
		return "viewNote";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));

		Notes saveNotes = noteService.saveNotes(notes);

		if (saveNotes != null) {
			session.setAttribute("msg", " Registration successfully compleated");
		} else {
			session.setAttribute("msg", "Registration not done successfully");
		}

		return "redirect:/user/addNote";

	}
	
	@PostMapping("/updateNote")
	public String updateNote(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p, m));

		Notes saveNotes = noteService.saveNotes(notes);

		if (saveNotes != null) {
			session.setAttribute("msg", "update successfully compleated");
		} else {
			session.setAttribute("msg", "update not done successfully");
		}

		return "redirect:/user/viewNote";

	}
	
	@GetMapping("/deletNote/{id}")
	public String deletNote( @PathVariable int id,HttpSession session) {
		boolean f = noteService.deleteNotes(id);
		if (f) {
			session.setAttribute("msg", " Delete successfully compleated");
		} else {
			session.setAttribute("msg", "Registration not done successfully");
		}
		
		return "redirect:/user/viewNote";
	}

	
}
