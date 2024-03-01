package com.Enote.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Enote.entity.Notes;
import com.Enote.entity.User;

public interface NoteRepository extends JpaRepository<Notes, Integer>{
	public Page<Notes> findByUser(User user, Pageable pageable);
}
