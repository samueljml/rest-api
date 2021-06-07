package br.com.zup.domain.model.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.zup.domain.model.Veiculo;
import br.com.zup.domain.model.feignmodels.Ano;
import br.com.zup.domain.model.feignmodels.Marca;
import br.com.zup.domain.model.feignmodels.ModeloAux;

@Service
@FeignClient(name = "FIPE-API", url="parallelum.com.br/fipe/api/v1", path = "/carros")
public interface FipeService {

	@GetMapping(value = "/marcas")
	ResponseEntity<List<Marca>> getVehiclesMarcas();
	
	@GetMapping(value = "/marcas/{cod_marca}/modelos")
	ResponseEntity<ModeloAux> getVehicleModelos(@PathVariable("cod_marca") String cod_marca);
	
	@GetMapping(value = "/marcas/{cod_marca}/modelos/{cod_modelo}/anos")
	ResponseEntity<List<Ano>> getVehicleAnos(@PathVariable("cod_marca") String cod_marca, @PathVariable("cod_modelo") String cod_modelo);
	
	@GetMapping(value = "/marcas/{cod_marca}/modelos/{cod_modelo}/anos/{cod_ano}")
	ResponseEntity<Veiculo> getVehicle(@PathVariable("cod_marca") String cod_marca, @PathVariable("cod_modelo") String cod_modelo, @PathVariable("cod_ano") String cod_ano);
}
