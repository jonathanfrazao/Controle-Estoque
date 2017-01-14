package com.frazao.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frazao.projeto.model.Categoria;
import com.frazao.projeto.model.Produto;
import com.frazao.projeto.repository.Categorias;
import com.frazao.projeto.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private Categorias categorias;

	@GetMapping("/novo")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("CadastroProduto");
		mv.addObject(new Produto());
		return mv;
	}

	@PostMapping
	public String cadastrar(@Validated Produto produto, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "CadastroProduto";
		}
		produtoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso.");
		return "redirect:/produtos/novo";
	}

	@PostMapping("/novo/categoria")
	public String cadastrarCategoria(@Validated Categoria categoria, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "CadastroProduto";
		}
		categorias.save(categoria);
		attributes.addFlashAttribute("mensagem", "Nova categoria adicionada.");
		return "redirect:/produtos/novo";
	}

	@GetMapping
	public String pesquisar() {
		return "Produtos";
	}

	@ModelAttribute("categorias")
	public List<Categoria> todasCategorias() {
		return categorias.findAll();
	}
}
