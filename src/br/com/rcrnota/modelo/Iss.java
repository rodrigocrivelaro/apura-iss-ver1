package br.com.rcrnota.modelo;

/**
 * Classe responsavel pela modelagem do ISS
 * 
 * @author Rodrigo Crivelaro
 *
 */
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
		return dataRecolhimento;
	}

	public double getValorIss() {
		return valorIss;
	}

	/**
	 * M�todo que verifica se a data passada pelo parametro � a mesma da armazenada no atributo da classe
	 * @param outraData
	 * @return True or False
	 */
	public boolean isMesmaData(String outraData) {
		return this.dataEmissao.substring(1, 7).equals(outraData.substring(1, 7)); 
	}
	
	/**
	 * M�todo que verifica se o emitente passado pelo parametro � o mesmo do armazenado no atributo da classe
	 * @param outroEmitente
	 * @return True or False
	 */
	public boolean isMesmoEmitente(String outroEmitente) {
		return this.emitente_cnpj.equals(outroEmitente);
	}
	
}
