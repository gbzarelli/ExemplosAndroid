package com.example.exlistviewfilter;

public class ListViewObjetos {
	
	private int id;
	private String descricao;
	private String valor;

	public ListViewObjetos(int id,String descricao, String valor) {
		this.id=id;
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ListViewObjetos [id=" + id + ", descricao=" + descricao
				+ ", valor=" + valor + "]";
	}

	

}
