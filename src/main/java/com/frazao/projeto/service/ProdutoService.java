package com.frazao.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.projeto.model.Categoria;
import com.frazao.projeto.model.Produto;
import com.frazao.projeto.repository.Categorias;
import com.frazao.projeto.repository.Produtos;

@Service
public class ProdutoService {

	@Autowired
	private Produtos produtos;

	@Autowired
	private Categorias categorias;

	public void salvar(Produto produto) {
		produtos.save(produto);
	}

	public void salvarCategoria(Categoria categoria) {
		categorias.save(categoria);
	}

	public void excluir(Long codigo) {
		produtos.delete(codigo);
	}

	public void excluirCategoria(Long codigo) {
		try {
			categorias.delete(codigo);
		} catch (Exception e) {
			throw new IllegalArgumentException("A categoria possui itens vinculados.");
		}

	}

}
