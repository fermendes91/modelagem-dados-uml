package com.fermendes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fermendes.cursomc.domain.Pedido;
import com.fermendes.cursomc.repositories.PedidoRepository;
import com.fermendes.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository clienteRepository;
	
	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> pedido = clienteRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + " Tipo: " + Pedido.class.getName()));
	}
}
