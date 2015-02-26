package br.com.rcrnota.leitor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.rcrnota.calculos.CalculaISS;
import br.com.rcrnota.gerador.GeradorXml;
import br.com.rcrnota.modelo.Iss;
import br.com.rcrnota.modelo.NotaFiscal;

public class Controle {

	public static void main(String[] args) {
		
		int i = 0;
		List<NotaFiscal> notas = new LeitorXML().lerXML();
		
		double valorIssTotal = 0;
		String outroEmitente = notas.get(0).getEmitente_cnpj();
		String oldEmitente = " ";
		String outraDataEmissao = notas.get(0).getData_emissao();
		String oldDataEmissao = " ";
		Iss iss = new Iss(outroEmitente, outraDataEmissao, "", valorIssTotal);
		double valorIss = 0;
		
		for(NotaFiscal notaFiscal : notas) {
			CalculaISS calculaISS = new CalculaISS();
			valorIss = calculaISS.calcularISS(notaFiscal);
			
			outroEmitente = notaFiscal.getEmitente_cnpj();
			outraDataEmissao = notaFiscal.getData_emissao();
								
			if(!(iss.isMesmaData(outraDataEmissao) && iss.isMesmoEmitente(outroEmitente))) {
				String dataRecolhimento = notaFiscal.getData_emissao().substring(1, 4) + "-" + notaFiscal.getData_emissao().substring(5, 6) + 1 + "-10";
				iss = new Iss(oldEmitente, oldDataEmissao, dataRecolhimento , valorIssTotal);
				GeradorXml geradorXml = new GeradorXml();
				geradorXml.gerarXML(iss, i++);
				valorIssTotal = 0;
			}
			
			valorIssTotal += valorIss;
			oldEmitente = outroEmitente;
			oldDataEmissao = outraDataEmissao;
			
		}
	
	}

}