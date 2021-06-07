package br.com.zup.domain.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.api.model.VeiculoInput;
import br.com.zup.domain.exception.NegocioException;
import br.com.zup.domain.model.Usuario;
import br.com.zup.domain.model.Veiculo;
import br.com.zup.domain.model.feignmodels.Ano;
import br.com.zup.domain.model.feignmodels.Marca;
import br.com.zup.domain.model.feignmodels.Modelo;
import br.com.zup.domain.repository.UsuarioRepository;
import br.com.zup.domain.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private FipeService fipeService;
	
	@Autowired
	private ModelMapper modelMapper;

	public VeiculoInput salvar(VeiculoInput veiculoInput, Long usuarioId) {
		Usuario usuario = usuarioRepo.findById(usuarioId)
				.orElseThrow(() -> new NegocioException("O usuario não existe!"));
		
		String valor = getValor(veiculoInput);
		
		veiculoInput.setUsuario(usuario);
		veiculoInput.setValor(valor);
		
		return repository.save(veiculoInput);
	}
	
	public List<Veiculo> findAll(Long usuarioId) {
		return toModel(repository.findAllByUsuarioId(usuarioId));
	}
	
	private Veiculo toModel(VeiculoInput veiculoInput) {
		Veiculo veiculo = modelMapper.map(veiculoInput, Veiculo.class);
		veiculo.updateRodizio();
		
		return veiculo;
	}
	
	private List<Veiculo> toModel(List<VeiculoInput> veiculosInput) {
		return veiculosInput.stream()
					.map(veiculo -> toModel(veiculo))
					.collect(Collectors.toList());
	}
	
	public String getValor(VeiculoInput veiculoInput) {
		String marca = setMarca(veiculoInput.getMarca());
		String modelo = setModelo(marca, veiculoInput.getModelo());
		String ano = setAno(marca, modelo, String.valueOf(veiculoInput.getAno()));
		
		Veiculo veiculo = fipeService.getVehicle(marca, modelo, ano).getBody();
		
		return veiculo.getValor();
	}
	
	private String setMarca(String marca) {
		List<Marca> marcas = fipeService.getVehiclesMarcas().getBody();
		for (String itemPesquisa : marca.split(" ")) {
			marcas = marcas.stream().filter((Marca) -> org.apache.commons.lang3.StringUtils.containsIgnoreCase(Marca.getNome(), itemPesquisa))
					.collect(Collectors.toList());
		}
		try {
			return marcas.get(0).getCodigo();
		} catch (IndexOutOfBoundsException e) {
			throw new NegocioException("Marca não encontrada: " + marca);
		}
	}

	private String setModelo(String cod_marca, String modelo) {
		List<Modelo> modelos = fipeService.getVehicleModelos(cod_marca).getBody().getModelos();
		for (String itemPesquisa : modelo.split(" ")) {
			if (modelos.size() > 1) {
				modelos = modelos.stream()
						.filter((Modelo) -> StringUtils.containsIgnoreCase(Modelo.getNome(), itemPesquisa))
						.collect(Collectors.toList());
			}
		}
		try {
			return modelos.get(0).getCodigo();
		}catch(IndexOutOfBoundsException e) {
			throw new NegocioException("Modelo não encontrado: " + modelos);
		}	
	}

	private String setAno(String cod_marca, String cod_modelo, String ano) {
		List<Ano> anos = fipeService.getVehicleAnos(cod_marca, cod_modelo).getBody();
		anos = anos.stream().filter((Ano) -> StringUtils.containsIgnoreCase(Ano.getNome(), ano))
				.collect(Collectors.toList());
		
		try {
			return anos.get(0).getCodigo();
		}
		catch(IndexOutOfBoundsException e) {
			throw new NegocioException("Ano do modelo não encontrado: " + anos);
		}	
	}
}
