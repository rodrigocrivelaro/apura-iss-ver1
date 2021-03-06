package br.com.rcrnota.calculos;

import br.com.rcrnota.modelo.NotaFiscal;

/**
 * Classe respons�vel pelo c�lculo do ISS de cada NF
 * 
 * @author Rodrigo Crivelaro
 */
public class CalculaISS {
		
	public double calcularISS(NotaFiscal nota) {
	
		if(nota.getCodigo_servico() == 2001) {
			return nota.getValor_total() * 0.03;
		} else if(nota.getCodigo_servico() == 2002) {
			return nota.getValor_total() * 0.04;
		} else {
			return nota.getValor_total() * 0.05;
		}
		
	}	
	
}