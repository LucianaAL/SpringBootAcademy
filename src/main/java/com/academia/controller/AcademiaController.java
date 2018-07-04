package com.academia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.model.Academia;
import com.academia.model.Cliente;
import com.academia.model.FichaExercicio;
import com.academia.repository.AcademiaRepository;
import com.academia.repository.ClienteRepository;
import com.academia.repository.FichaExercicioRepository;

@Controller
public class AcademiaController {
	
	@Autowired
	private FichaExercicioRepository fer;
	
	@Autowired
	private AcademiaRepository ar;
	
	@Autowired
	private ClienteRepository cr;
	
	@RequestMapping(value="/cadastrarAcademia", method=RequestMethod.GET)
	public String form() {
		return "academia/formAcademia";
	}
	
	@RequestMapping(value="/cadastrarAcademia", method=RequestMethod.POST)
	public String form(Academia academia, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verivique os campos!");
			return "redirect:/cadastrarAcademia";
		}
		ar.save(academia);
		attributes.addFlashAttribute("mensagem", "Academia cadastrada com sucesso!");
		return "redirect:/cadastrarAcademia";
	}
	
	@RequestMapping("/academias")
	public ModelAndView listaAcademias() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Academia> academias = ar.findAll();
		mv.addObject("academias", academias);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView detalhesAcademia(@PathVariable("id") long id) {
		Academia academia = ar.findById(id);
		ModelAndView mv = new ModelAndView("academia/detalhesAcademia");
		mv.addObject("academia", academia);
		
		Iterable<Cliente> clientes = cr.findByAcademia(academia);
		mv.addObject("clientes", clientes);
		
		return mv;
		
	}
	
	@RequestMapping("/deletarAcademia")
	public String deletarAcademia(long id) {
		Academia academia = ar.findById(id);
		ar.delete(academia);
		return "redirect:/academias";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String detalhesAcademiaPost(@PathVariable("id") long id, @Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verivique os campos!");
			return "redirect:/{id}";
		}
		Academia academia = ar.findById(id);
		cliente.setAcademia(academia);
		cr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente adicionado com sucesso!");
		return "redirect:/{id}";
		
	}
	
	@RequestMapping("/deletarCliente")
	public String deletarCliente(long matricula) {
		Cliente cliente = cr.findByMatricula(matricula);
		cr.delete(cliente);
		Academia academia = cliente.getAcademia();
		long idLong = academia.getId();
		return "redirect:/" + idLong;
	}
	
}
