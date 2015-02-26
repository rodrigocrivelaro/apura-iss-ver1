package br.com.rcrnota.gerador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.rcrnota.modelo.Iss;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Classe respons�vel pela gera��o do XML de ISS
 *  
 * @author Rodrigo Crivelaro
 */
public class GeradorXml {

	public void gerarXML(Iss iss) {

		/** Criando o arquivo em disco **/
		String dir = "C:\\notasfiscais\\iss\\";
		File diretorio = new File(dir);
		
		// Verifica se o diret�rio existe, se n�o cria ele
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		} 
		
		String dataRecolheISS = iss.getDataRecolhimento();
		File arquivo = new File(dir + "ISS_EMITENTE_" + iss.getEmitente_cnpj() + "_" + dataRecolheISS + ".xml");
		FileOutputStream gravar;
		
		XStream xStream = new XStream(new DomDriver());
		
		// Registrando conversor 
		xStream.registerConverter(new IssConverter()); 
		
		// Registrando alias
		xStream.alias("ISS", Iss.class);
		
		try {
			gravar = new FileOutputStream(arquivo);
			gravar.write(xStream.toXML(iss).getBytes());
			gravar.close();

			System.out.println("XML Gerado");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

}
