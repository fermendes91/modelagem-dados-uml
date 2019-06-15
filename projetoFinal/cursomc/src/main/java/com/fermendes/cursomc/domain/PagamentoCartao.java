package com.fermendes.cursomc.domain;

import javax.persistence.Entity;

import com.fermendes.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento{
	
	private static final long serialVersionUID = -3370711891877313909L;
	
	private Integer numeroParcelas;
	
	public PagamentoCartao () {}

	public PagamentoCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, estadoPagamento, pedido);
		
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
	

}
