package com.pwbe.tdsgrupo07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pwbe.tdsgrupo07.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	@Query("SELECT a FROM Aluno a WHERE a.nome LIKE %:nome%")
	Iterable<Aluno> findByNome(String nome);
}
