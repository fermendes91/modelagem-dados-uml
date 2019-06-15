package com.fermendes.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fermendes.cursomc.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) // Definindo a estrategia de como as subclasses serao geradas na base de dados, nesta estrategia será gerada uma tabela para cada subclasse
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // Nesta estrategia sera gerada uma tabela unica contemplando os atributos das subclasses
public abstract class Pagamento implements Serializable{
	
	private static final long serialVersionUID = 199884056271428723L;

	@Id
	private Integer id;
	
	private Integer estadoPagamento;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId // estas anotations farão com que o Pagamento tenha o mesmo ID do Pedido.
	private Pedido pedido;
	
	public Pagamento() {}

	public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
		this.id = id;
		this.estadoPagamento = estadoPagamento.getCodigo();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento.getCodigo();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
