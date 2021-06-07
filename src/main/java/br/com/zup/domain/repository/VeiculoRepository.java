package br.com.zup.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.api.model.VeiculoInput;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoInput, Long> {
	
	List<VeiculoInput> findAllByUsuarioId(Long id_usuario);
}