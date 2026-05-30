package com.cefet.subip_mg_backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CrudControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void deveExecutarCrudBasicoDosControllers() throws Exception {
		mockMvc.perform(get("/pessoas"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(3));

		mockMvc.perform(get("/acervo"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(4))
				.andExpect(jsonPath("$[0].titulo").value("Dom Casmurro"))
				.andExpect(jsonPath("$[0].tombo").value("TOMBO-0001"))
				.andExpect(jsonPath("$[0].bibliotecaNome").value("Biblioteca Publica Estadual de Minas Gerais"));

		mockMvc.perform(get("/acervo").param("titulo", "dom"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));

		mockMvc.perform(get("/acervo").param("bibliotecaId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));

		mockMvc.perform(get("/acervo").param("situacao", "DISPONIVEL"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));

		mockMvc.perform(get("/livros").param("titulo", "dom"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].titulo").value("Dom Casmurro"));

		mockMvc.perform(get("/livros").param("isbn", "9788535910663"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].isbn").value("9788535910663"));

		mockMvc.perform(get("/livros/1/exemplares"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));

		String pessoaJson = """
				{
					"nome": "Diego Costa",
					"cpf": "44444444444",
					"email": "diego.costa@email.com"
				}
				""";

		String pessoaResponse = mockMvc.perform(post("/pessoas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pessoaJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Long pessoaId = getId(pessoaResponse);

		String pessoaAlteradaJson = """
				{
					"nome": "Diego Costa Silva",
					"cpf": "44444444444",
					"email": "diego.silva@email.com"
				}
				""";

		mockMvc.perform(put("/pessoas/{id}", pessoaId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(pessoaAlteradaJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("Diego Costa Silva"));

		mockMvc.perform(delete("/pessoas/{id}", pessoaId))
				.andExpect(status().isNoContent());

		String livroJson = """
				{
					"titulo": "Vidas Secas",
					"isbn": "9788535926138"
				}
				""";

		String livroResponse = mockMvc.perform(post("/livros")
				.contentType(MediaType.APPLICATION_JSON)
				.content(livroJson))
				.andExpect(status().isCreated())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Long livroId = getId(livroResponse);

		mockMvc.perform(get("/livros/{id}", livroId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.titulo").value("Vidas Secas"));

		mockMvc.perform(delete("/livros/{id}", livroId))
				.andExpect(status().isNoContent());

		String bibliotecaJson = """
				{
					"nome": "Biblioteca Teste"
				}
				""";

		String bibliotecaResponse = mockMvc.perform(post("/bibliotecas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(bibliotecaJson))
				.andExpect(status().isCreated())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Long bibliotecaId = getId(bibliotecaResponse);

		mockMvc.perform(put("/bibliotecas/{id}", bibliotecaId)
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"nome": "Biblioteca Teste Atualizada"
						}
						"""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("Biblioteca Teste Atualizada"));

		mockMvc.perform(delete("/bibliotecas/{id}", bibliotecaId))
				.andExpect(status().isNoContent());

		String exemplarJson = """
				{
					"tombo": "TOMBO-9999",
					"situacao": "DISPONIVEL",
					"livroId": 1,
					"bibliotecaId": 1
				}
				""";

		String exemplarResponse = mockMvc.perform(post("/exemplares")
				.contentType(MediaType.APPLICATION_JSON)
				.content(exemplarJson))
				.andExpect(status().isCreated())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Long exemplarId = getId(exemplarResponse);

		mockMvc.perform(put("/exemplares/{id}", exemplarId)
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"tombo": "TOMBO-9999",
							"situacao": "INDISPONIVEL",
							"livroId": 1,
							"bibliotecaId": 1
						}
						"""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.situacao").value("INDISPONIVEL"));

		mockMvc.perform(delete("/exemplares/{id}", exemplarId))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/emprestimos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(0));

		String emprestimoJson = """
				{
					"dataRetirada": "2026-05-28",
					"dataDevolucaoPrevista": "2026-06-04",
					"exemplarId": 1,
					"pessoaId": 1
				}
				""";

		String emprestimoResponse = mockMvc.perform(post("/emprestimos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(emprestimoJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.situacao").value("EM_ANDAMENTO"))
				.andExpect(jsonPath("$.exemplarId").value(1))
				.andExpect(jsonPath("$.pessoaId").value(1))
				.andReturn()
				.getResponse()
				.getContentAsString();

		Long emprestimoId = getId(emprestimoResponse);

		mockMvc.perform(get("/pessoas/1/emprestimos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].id").value(emprestimoId));

		mockMvc.perform(get("/emprestimos/{id}", emprestimoId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.dataRetirada").value("2026-05-28"))
				.andExpect(jsonPath("$.dataDevolucaoPrevista").value("2026-06-04"));

		mockMvc.perform(get("/exemplares/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.situacao").value("EMPRESTADO"));

		mockMvc.perform(post("/emprestimos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(emprestimoJson))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Esse exemplar nao esta disponivel no momento."));

		String renovacaoJson = """
				{
					"dataDevolucaoPrevista": "2026-06-11"
				}
				""";

		mockMvc.perform(put("/emprestimos/{id}/renovacao", emprestimoId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(renovacaoJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.situacao").value("RENOVADO"))
				.andExpect(jsonPath("$.dataDevolucaoPrevista").value("2026-06-11"))
				.andExpect(jsonPath("$.dataDevolucao").doesNotExist())
				.andExpect(jsonPath("$.diasAtraso").value(0));

		String devolucaoJson = """
				{
					"dataDevolucao": "2026-06-13"
				}
				""";

		mockMvc.perform(put("/emprestimos/{id}/devolucao", emprestimoId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(devolucaoJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.situacao").value("DEVOLVIDO"))
				.andExpect(jsonPath("$.dataDevolucao").value("2026-06-13"))
				.andExpect(jsonPath("$.diasAtraso").value(2));

		mockMvc.perform(get("/exemplares/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.situacao").value("DISPONIVEL"));

		mockMvc.perform(put("/emprestimos/{id}/renovacao", emprestimoId)
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"dataDevolucaoPrevista": "2026-06-20"
						}
						"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Somente emprestimos abertos podem ser renovados."));

		mockMvc.perform(delete("/pessoas/1"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message")
						.value("Nao e possivel excluir uma pessoa que possui emprestimos cadastrados."));

		mockMvc.perform(delete("/exemplares/1"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message")
						.value("Nao e possivel excluir um exemplar que possui emprestimos cadastrados."));
	}

	private Long getId(String json) throws Exception {
		JsonNode node = objectMapper.readTree(json);
		return node.get("id").asLong();
	}
}
