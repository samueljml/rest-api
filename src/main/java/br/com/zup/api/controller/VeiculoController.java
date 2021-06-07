package br.com.zup.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.api.model.VeiculoInput;
import br.com.zup.domain.model.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoService service;
	
	@PostMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.CREATED)
	public VeiculoInput criar (@Valid @RequestBody VeiculoInput veiculo, @PathVariable Long usuarioId) {
		
		return service.salvar(veiculo, usuarioId);
	}
}