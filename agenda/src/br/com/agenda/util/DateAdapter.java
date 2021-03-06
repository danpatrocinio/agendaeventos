package br.com.agenda.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@Override
	public String marshal(Date value) throws Exception {
		return dateFormat.format(value);
	}

	@Override
	public Date unmarshal(String value) throws Exception {
		return dateFormat.parse(value);
	}

}
