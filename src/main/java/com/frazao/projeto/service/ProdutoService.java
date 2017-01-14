package com.frazao.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frazao.projeto.model.Produto;
import com.frazao.projeto.repository.Produtos;

@Service
public class ProdutoService {

	@Autowired
	private Produtos produtos;

	public void salvar(Produto produto) {
		produtos.save(produto);
	}

}
