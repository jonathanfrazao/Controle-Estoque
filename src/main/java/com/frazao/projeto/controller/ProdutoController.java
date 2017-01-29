package com.frazao.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frazao.projeto.model.Categoria;
import com.frazao.projeto.model.Produto;
import com.frazao.projeto.repository.Categorias;
import com.frazao.projeto.repository.Produtos;
import com.frazao.projeto.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private Categorias categorias;

	@Autowired
	private Produtos produtos;

	private static final String CADASTRO_PRODUTO_VIEW = "Conteudo/CadastroProduto";
	private static final String CATEGORIAS_VIEW = "Conteudo/Categorias";
	private static final String PRODUTOS_VIEW = "Conteudo/Produtos";

	// tela :: Home.
	@GetMapping("/novo")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView(CADASTRO_PRODUTO_VIEW);
		mv.addObject(new Produto());
		return mv;
	}

	// tela :: Categoria.
	@GetMapping("/categoria")
	public ModelAndView categoria() {
		ModelAndView mv = new ModelAndView(CATEGORIAS_VIEW);
		mv.addObject(new Categoria());
		return mv;
	}

	// função :: Cadastrar produtos.
	@PostMapping
	public String cadastrar(@Validated Produto produto, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_PRODUTO_VIEW;
		}

		if (produto.getCodigo() == null) {
			attributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso.");
		} else {
			attributes.addFlashAttribute("mensagem", "Produto editado com sucesso.");
		}
		produtoService.salvar(produto);
		return "redirect:/produtos/novo";
	}

	// função :: Pesquisar produtos.
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(PRODUTOS_VIEW);
		mv.addObject("produtos", produtos.findAll());
		return mv;
	}

	// função :: Excluir produtos.
	@DeleteMapping("{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		produtoService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "O produto foi excluido com sucesso.");
		return "redirect:/produtos";
	}

	// função :: Editar produtos.
	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Produto produto) {
		ModelAndView mv = new ModelAndView(CADASTRO_PRODUTO_VIEW);
		mv.addObject(produto);
		return mv;
	}

	// função :: Cadastrar categorias.
	@PostMapping("/novo/categoria")
	public String cadastrarCategoria(@Validated Categoria categoria, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CATEGORIAS_VIEW;
		}
		if (categoria.getCodigo() == null) {
			attributes.addFlashAttribute("mensagem", "Nova categoria adicionada.");
		} else {
			attributes.addFlashAttribute("mensagem", "A categoria foi editada com sucesso.");
		}
		produtoService.salvarCategoria(categoria);
		return "redirect:/produtos/categoria";
	}

	// função :: Excluir categoria
	@DeleteMapping("/categoria/excluir/{codigo}")
	public String excluirCategoria(@PathVariable Long codigo, RedirectAttributes attributes) {
		try {
			produtoService.excluirCategoria(codigo);
		} catch (IllegalArgumentException e) {
			attributes.addFlashAttribute("mensagemErroDireta", e.getMessage());
			return "redirect:/produtos/categoria";
		}
		attributes.addFlashAttribute("mensagem", "Categoria excluida com sucesso.");
		return "redirect:/produtos/categoria";
	}

	// função :: Editar categoria
	@GetMapping("/categoria/editar/{codigo}")
	public ModelAndView editarCategoria(@PathVariable("codigo") Categoria categoria) {
		ModelAndView mv = new ModelAndView(CATEGORIAS_VIEW);
		mv.addObject(categoria);
		return mv;
	}

	// função :: Entregar uma lista de categorias para a tela.
	@ModelAttribute("categorias")
	public List<Categoria> todasCategorias() {
		return categorias.findAll();
	}

}
