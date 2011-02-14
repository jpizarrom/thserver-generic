package com.jpizarro.th.server.generic.view.web.ws.util;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.apache.wicket.PageParameters;
import org.apache.wicket.Response;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.target.basic.StringRequestTarget;
import org.jdom.Element;

import com.jpizarro.th.lib.generic.util.exception.ExceptionCodes;
import com.jpizarro.th.lib.generic.util.exception.THException;
import com.jpizarro.th.server.view.xml.ServletUtils;
import com.thoughtworks.xstream.XStream;
import com.jpizarro.th.lib.generic.util.xml.xstream.XStreamFactory;

public abstract class GenericWS extends WebPage{
	protected Element element = null;
	protected String selement = null;
	protected XStreamFactory xStreamFactory = null;
	
	public GenericWS(PageParameters parameters, XStreamFactory xStreamFactory) {
		super(parameters);
		this.xStreamFactory = xStreamFactory;
		try {
			doExecute(parameters);	
		} catch (Exception e) {
			e.printStackTrace();
//			this.element = TOToXMLConversor.toXML(e, ExceptionCodes.GENERAL_CODE);
			XStream xf = xStreamFactory.createXStream();
			this.selement = xf.toXML(new THException(ExceptionCodes.GENERAL_CODE, e.getLocalizedMessage()));
		}
		
		doShow();
	}

	protected abstract void doExecute(PageParameters parameters);
	
	protected void doShow(){
		if (this.element != null)
			showXML(this.element, this.getResponse());
		else
			showXMLString(this.selement, this.getResponse());
	}


	protected void showXML(Element dataElement,
			Response response) {
		StringRequestTarget r = null;
		StringWriter w = new StringWriter();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		try {
				 ServletUtils.writeServiceResponse(dataElement, out);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				System.out.println("IOIO : " + e.getMessage());
//			}
		byte[] charData = out.toByteArray();
		try {
			r = new StringRequestTarget("text/xml","UTF-8",new String(charData, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getRequestCycle().setRequestTarget(r);
	}
	protected void showXMLString(String dataElement,
			Response response){
	StringRequestTarget r = null;
	r = new StringRequestTarget("text/xml","UTF-8",dataElement);
	this.getRequestCycle().setRequestTarget(r);
}
}
