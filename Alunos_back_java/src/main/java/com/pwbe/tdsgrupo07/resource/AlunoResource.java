package com.pwbe.tdsgrupo07.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pwbe.tdsgrupo07.entity.Aluno;
import com.pwbe.tdsgrupo07.repository.AlunoRepository;

@RestController
@RequestMapping("/senai/api")
public class AlunoResource {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public Iterable<Aluno> findAll(){
		return alunoRepository.findAll();
	}
	
	
	@PostMapping("/criar")
	public Aluno createAluno(@RequestBody Aluno aluno) {
		CepResource cepResource = new CepResource();
		aluno.setRua(cepResource.consultarLogradouro("logradouro", aluno.getCep()));
		aluno.setBairro(cepResource.consultarLogradouro("bairro", aluno.getCep()));
		aluno.setCidade(cepResource.consultarLogradouro("localidade", aluno.getCep()));
		aluno.setEstado(cepResource.consultarLogradouro("uf", aluno.getCep()));
		return alunoRepository.save(aluno);
	}
	
}
