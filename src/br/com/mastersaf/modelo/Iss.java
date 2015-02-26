package br.com.mastersaf.modelo;


public class Iss {

	private String emitente_cnpj;
	private String dataEmissao;
	private String dataRecolhimento;
	private double valorIss;

	public Iss(String emitente_cnpj, String dataEmissao, String dataRecolhimento, double valorIss) {
		this.emitente_cnpj = emitente_cnpj;
		this.dataEmissao = dataEmissao;
		this.dataRecolhimento = dataRecolhimento;
		this.valorIss = valorIss;		
	}

	public String getEmitente_cnpj() {
		return emitente_cnpj;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public String getDataRecolhimento() {
		return dataEmissao;
	}

	public double getValorIss() {
		return valorIss;
	}

	public boolean isMesmaData(String outraData) {
		return this.dataEmissao.substring(1, 7).equals(outraData.substring(1, 7)); 
	}
	
	public boolean isMesmoEmitente(String outroEmitente) {
		 return this.emitente_cnpj.equals(outroEmitente);
	}
	
}
