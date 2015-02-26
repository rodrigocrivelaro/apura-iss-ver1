package br.com.mastersaf.leitor;

import java.util.Comparator;

import br.com.mastersaf.modelo.NotaFiscal;

public class OrdenaLista implements Comparator<NotaFiscal> {

	@Override
	public int compare(NotaFiscal nota1, NotaFiscal nota2) {
	    int compara1 = nota1.getData_emissao().substring(1, 7).compareTo(nota2.getData_emissao().substring(1, 7));  
	    return compara1 == 0 ? nota1.getEmitente_cnpj().compareTo(nota2.getEmitente_cnpj()) : compara1; 
	}
	
}
