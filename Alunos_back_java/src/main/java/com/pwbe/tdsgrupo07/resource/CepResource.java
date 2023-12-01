package com.pwbe.tdsgrupo07.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CepResource {
	@GetMapping("/cep/{cep}")
	public String consultarCep(@PathVariable String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://viacep.com.br/ws/" + cep + "/json/";

		try {
			String resposta = restTemplate.getForObject(url, String.class);
			return resposta;
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao consultar o CEP: " + e.getMessage();
		}
	}

	@GetMapping("/cep/{campo}/{cep}")
	public String consultarLogradouro(@PathVariable String campo, @PathVariable String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://viacep.com.br/ws/" + cep + "/json/";

		try {
			String resposta = restTemplate.getForObject(url, String.class);
			return extrairLogradouro(resposta, campo);
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao consultar o logradouro do CEP: " + e.getMessage();
		}
	}

	private String extrairLogradouro(String resposta, String campo) {
		try {
			// Cria um objeto ObjectMapper para mapear o JSON
			ObjectMapper objectMapper = new ObjectMapper();

			// Lê o JSON e obtém a raiz do nó
			JsonNode rootNode = objectMapper.readTree(resposta);

			// Obtém o campo 'logradouro'
			String logradouro = rootNode.get(campo).asText();

			// Retorno
			return campo + ": " + logradouro;
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao o CEP: " + e.getMessage();
		}
	}
}
