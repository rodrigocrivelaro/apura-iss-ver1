package br.com.mastersaf.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.mastersaf.modelo.NotaFiscal;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeitorXML {

	public List<NotaFiscal> lerXML() {
		
		XStream xStream = new XStream(new DomDriver());
		
		try{
			String dir = "C:\\notasfiscais\\"; 
			File diretorio = new File(dir); 
			
			//checa se o diretório existe
			if(!diretorio.exists()) {
				System.out.println("Diretório c:\\notasfiscais\\ não existe!");
			} else {
				//checa se existe algum arquivo no diretório
				if(diretorio.listFiles().length == 0) {
					System.out.println("O diretório c:\\notasfiscais\\ está vazio!");
				} else {	
					File listaArquivos[] = diretorio.listFiles();
										
					List<NotaFiscal> notas = new ArrayList<NotaFiscal>();
					
					for (File arquivo : listaArquivos) {
						String nomeArquivoXML = arquivo.getName();
						
						//checa se o arquivo é um XML
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
//					for (NotaFiscal notaFiscal : notas) {
//						System.out.println(notaFiscal);
//					}
					
					return notas;
				}
			}
			
		} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
		
		System.out.println("Não existem NF no diretório!");
		return null;
	}
}
