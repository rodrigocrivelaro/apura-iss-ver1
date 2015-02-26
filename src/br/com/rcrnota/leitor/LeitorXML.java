package br.com.rcrnota.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.rcrnota.modelo.NotaFiscal;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Classe respons�vel por extrair os dados dos xml�s do diret�rio notasfiscais e gurad�-los em uma Lista de Notas Fiscais.
 * 
 * @author Rodrigo Crivelaro
 */
public class LeitorXML {

	/**
	 * M�todo respons�vel pelo funcionamento da classe
	 * L� os arquivos xml�s do diret�rio notasfiscais e armazena os dados em uma lista "notas"
	 * @return uma lista notas fiscais
	 */
	public List<NotaFiscal> lerXML() {
		
		XStream xStream = new XStream(new DomDriver());
		
		try{
			String dir = "C:\\notasfiscais\\"; 
			File diretorio = new File(dir); 
			
			// checa se o diret�rio existe
			if(!diretorio.exists()) {
				diretorio.mkdirs();
			}
				
			// checa se existe algum arquivo no diret�rio
			if (diretorio.listFiles().length == 0) {	
				System.out.println("O diret�rio c:\\notasfiscais\\ est� vazio!");
				String mensagem = "O diret�rio c:\\notasfiscais\\ est� vazio!\n Favor copiar para o diret�rio os arquivos xml das NF a serem integradas e confirmar!";
				JOptionPane.showMessageDialog(null,mensagem);
			}
			
			// lista os arquivos presentes no diret�rio notasfiscais
           	File listaArquivos[] = diretorio.listFiles();
								
			List<NotaFiscal> notas = new ArrayList<NotaFiscal>();
			
			for (File arquivo : listaArquivos) {
				String nomeArquivoXML = arquivo.getName();
				
				//checa se o arquivo � um XML
				if(nomeArquivoXML.contains(".xml")) {

					//carrega o arquivo XML para um objeto reader
					File arquivoXML = new File(dir + nomeArquivoXML);
					FileInputStream input = new FileInputStream(arquivoXML);
			        
					//popula o objeto nota
			        xStream.alias("nota", NotaFiscal.class);    
			        NotaFiscal nota = (NotaFiscal) xStream.fromXML(input);
			        notas.add(nota);
				} 
			}	
			
			//ordeno a lista pelo emitente
			OrdenaLista ordenaLista = new OrdenaLista();
			Collections.sort(notas, ordenaLista);
			
			return notas;
			
		} catch (FileNotFoundException e) {
			System.out.println("O diret�rio c:\\notasfiscais\\ n�o cont�m nenhum xml de NF para integra��o!");
    	}
		
		return null;
	}
}
