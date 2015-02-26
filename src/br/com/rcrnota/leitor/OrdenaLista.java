package br.com.rcrnota.leitor;

import java.util.Comparator;

import br.com.rcrnota.modelo.NotaFiscal;

/**
 * Classe respons�vel por ordenar a lista de notas fiscais criada pela classe LeitorXML
 * 
 * @author Rodrigo Crivelro
 */
public class OrdenaLista implements Comparator<NotaFiscal> {

	@Override
	public int compare(NotaFiscal nota1, NotaFiscal nota2) {
	    int compara1 = nota1.getEmitente_cnpj().compareTo(nota2.getEmitente_cnpj());  
	    return compara1 == 0 ? nota1.getData_emissao().substring(1, 7).compareTo(nota2.getData_emissao().substring(1, 7)) : compara1; 
	}
	
}
