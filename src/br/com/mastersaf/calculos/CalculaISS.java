package br.com.mastersaf.calculos;

import br.com.mastersaf.modelo.NotaFiscal;

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