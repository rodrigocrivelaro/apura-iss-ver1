package br.com.rcrnota.leitor;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.rcrnota.calculos.CalculaISS;
import br.com.rcrnota.gerador.GeradorXml;
import br.com.rcrnota.modelo.Iss;
import br.com.rcrnota.modelo.NotaFiscal;

/**
 * Classe principal do programa respons�vel pela atribui��o das demais classes
 * 
 * @author Rodrigo Crivelro
 */
public class Controle {

	public static void main(String[] args) {
		
		List<NotaFiscal> notas = new LeitorXML().lerXML();
		
		double valorIssTotal = 0;
		String oldEmitente = " ";
		String oldDataEmissao = " ";
		
		try {
			String emitente = notas.get(0).getEmitente_cnpj();
			String dataEmissao = notas.get(0).getData_emissao();
						
			Iss iss = new Iss(emitente, dataEmissao, "", valorIssTotal);
			
			for(NotaFiscal notaFiscal : notas) {
	
				emitente = notaFiscal.getEmitente_cnpj();
				dataEmissao = notaFiscal.getData_emissao();
									
				if(iss.isMesmaData(dataEmissao) && iss.isMesmoEmitente(emitente)) {
					valorIssTotal = calcValorISSMes(valorIssTotal, notaFiscal);
				} else {
					oldEmitente = iss.getEmitente_cnpj();
					oldDataEmissao = iss.getDataEmissao();
					gerarXMLDoISS(valorIssTotal, oldEmitente, oldDataEmissao);
					valorIssTotal = 0;
					valorIssTotal = calcValorISSMes(valorIssTotal, notaFiscal);
					String dataRecolhimentoNova = converteDtEmissaoEmDtRecolhimento(dataEmissao);
					iss = new Iss(emitente, dataEmissao, dataRecolhimentoNova , valorIssTotal);
				}
			}
			
			gerarXMLDoISS(valorIssTotal, emitente, dataEmissao);
			
			String mensagem = "XML(�s) de ISS gerados com suscesso no diret�rio C:\\notasfiscais\\iss\\";
			JOptionPane.showMessageDialog(null, mensagem);
			
		} catch (IndexOutOfBoundsException iB) {
			System.out.println("O diret�rio c:\\notasfiscais\\ n�o cont�m nenhum xml de NF para integra��o!");
			String mensagem = "O diret�rio c:\\notasfiscais\\ n�o cont�m nenhum xml de NF para integra��o!\n Favor copiar para o diret�rio os arquivos xml das NF a serem integradas e rodar o programa novamente!";
			JOptionPane.showMessageDialog(null,mensagem);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Um erro ocorreu!");
		} 
			
	}

	public static void gerarXMLDoISS(double valorIssTotal, String emitente, String dataEmissao) {
		Iss iss;
		String dataRecolhimentoNova = converteDtEmissaoEmDtRecolhimento(dataEmissao);
		iss = new Iss(emitente, dataEmissao, dataRecolhimentoNova , valorIssTotal);
		GeradorXml geradorXml = new GeradorXml();
		geradorXml.gerarXML(iss);
	}

	public static double calcValorISSMes(double valorIssTotal, NotaFiscal notaFiscal) {
		double valorIss =  0;
		valorIss =  new CalculaISS().calcularISS(notaFiscal);
		valorIssTotal += valorIss;
		return valorIssTotal;
	}

	public static String converteDtEmissaoEmDtRecolhimento(String dataEmissao) {
		String ano = dataEmissao.substring(0, 4);
		int mesInt = Integer.parseInt(dataEmissao.substring(5, 7));
		int mesData = mesInt < 12 ? mesInt +1 : mesInt;
		String mes = mesData <= 9 ? "0"+Integer.toString(mesData): Integer.toString(mesData);
		return ano + "-" + mes + "-10";
	}

}