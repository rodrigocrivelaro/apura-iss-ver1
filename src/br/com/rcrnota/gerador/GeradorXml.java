package br.com.rcrnota.gerador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

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
		
		// Cria��o da data para compor o nome do arquivo
		Calendar dt = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss_S");  
        String emissaoISS = s.format(dt.getTime()); 
		
        // N�mero aleat�rio para evitar repeti��o e sobreposi��o do nome do arquivo
        Random random = new Random();
        int aleatorio = random.nextInt(1001);
        
		File arquivo = new File(dir + "ISS_EMITENTE_" + iss.getEmitente_cnpj() + "_" + emissaoISS + "_" + aleatorio +".xml");
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
