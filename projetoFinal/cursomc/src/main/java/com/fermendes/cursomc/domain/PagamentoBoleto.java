package com.fermendes.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fermendes.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoBoleto extends Pagamento {
	
	private static final long serialVersionUID = 2468634642431006036L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoBoleto() {}

	public PagamentoBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}
