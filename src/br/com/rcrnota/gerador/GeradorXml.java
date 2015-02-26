package br.com.rcrnota.gerador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.rcrnota.modelo.Iss;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeradorXml {

	public void gerarXML(Iss iss, int cont) {

		XStream xStream = new XStream(new DomDriver());
		xStream.alias("ISS", Iss.class);
		xStream.aliasField("emitente_cnpj", Iss.class, "emitente_cnpj");
		xStream.aliasField("dataRecolhimento", Iss.class, "dataRecolhimento");
		xStream.aliasField("valorIss", Iss.class, "valorIss");

		/** Criando o arquivo em disco **/
		String dir = "C:\\notasfiscais\\iss\\";
		File diretorio = new File(dir);
		
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		} 
		
		File arquivo = new File("C:\\notasfiscais\\iss\\" + "xmlISS_" + iss.getEmitente_cnpj() + "_" + iss.getDataEmissao() + " " + cont + ".xml");
		FileOutputStream gravar;
		
		try {
			gravar = new FileOutputStream(arquivo);
			gravar.write(xStream.toXML(iss).getBytes());
			gravar.close();

			System.out.println("XML " + cont + " Gerado");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

}
