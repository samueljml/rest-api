package br.com.zup.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.api.model.UsuarioDTO;
import br.com.zup.domain.model.Usuario;
import br.com.zup.domain.model.service.UsuarioService;
import br.com.zup.domain.model.service.VeiculoService;
import br.com.zup.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired 
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private VeiculoService veiculoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cadastrarUsuario (@Valid @RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDTO> listarVeiculosDoUsuario (@PathVariable Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepo.findById(usuarioId);
		
		if(usuario.isPresent()) {
			UsuarioDTO usuarioModel = toModel(usuario.get());
			
			usuarioModel.setVeiculos(veiculoService.findAll(usuarioId));
			
			return ResponseEntity.ok(usuarioModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	private UsuarioDTO toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDTO.class);
	}
}
