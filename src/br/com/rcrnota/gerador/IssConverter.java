package br.com.rcrnota.gerador;

import br.com.rcrnota.modelo.Iss;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Classe respons�vel for filtrar os campos da Classe ISS que ser�o utilizados na gera��o do xls
 * 
 * @author Rodrigo Crivelaro
 */
public class IssConverter implements Converter {

	@Override
	public boolean canConvert(Class clazz) {
		return clazz.equals(Iss.class);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		Iss iss = (Iss)value; 
		
		writer.startNode("emitente_cnpj"); 
		context.convertAnother(iss.getEmitente_cnpj()); 
		writer.endNode(); 

		writer.startNode("dataRecolhimento"); 
		context.convertAnother(iss.getDataRecolhimento()); 
		writer.endNode(); 
		
		writer.startNode("valorIss"); 
		context.convertAnother(iss.getValorIss()); 
		writer.endNode(); 
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1) {
		return null;
	}

}
