package br.com.rcrnota.modelo;

/**
 * Classe responsavel pela modelagem da NF
 * 
 * @author Rodrigo Crivelaro
 */
public class NotaFiscal {

	private String emitente_cnpj;
	private int numero;
	private String data_emissao;
	private int codigo_servico;
	private double valor_total;

	public NotaFiscal(String emitente_cnpj, int numero, String data_emissao, int codigo_servico, double valor_total) {
		this.emitente_cnpj = emitente_cnpj;
		this.numero = numero;
		this.data_emissao = data_emissao;
		this.codigo_servico = codigo_servico;
		this.valor_total = valor_total;
	}

	public String getEmitente_cnpj() {
		return emitente_cnpj;
	}

	public int getNumero() {
		return numero;
	}

	public String getData_emissao() {
		return data_emissao;
	}

	public int getCodigo_servico() {
		return codigo_servico;
	}

	public double getValor_total() {
		return valor_total;
	}

	@Override
	public String toString() {
		return "Emitente: " + this.emitente_cnpj + " - Data Emiss�o: " + this.data_emissao + " - Numero da NF: " + this.numero;
	}
}
