package br.com.zup.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.domain.exception.NegocioException;
import br.com.zup.domain.model.Usuario;
import br.com.zup.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	public Usuario salvar(Usuario usuario)  {
		verificarExistente(usuario, usuarioRepo.findByEmail(usuario.getEmail()), "email");
		verificarExistente(usuario, usuarioRepo.findByCpf(usuario.getCpf()), "cpf");
		
		return usuarioRepo.save(usuario);
	}
	
	public void verificarExistente (Usuario novoUsuario, Usuario usuarioExistente, String dado) {
		if(usuarioExistente != null && !usuarioExistente.equals(novoUsuario)) {
			throw new NegocioException("Já existe um usuario cadastrado com esse " + dado);
		}
	}
}
