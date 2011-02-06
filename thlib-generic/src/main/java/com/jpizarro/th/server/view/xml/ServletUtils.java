/*
 * Android Runner is a multiplayer GPS game fully written by Xurxo Mendez Perez
 * 
 * Copyright (C) 2009 Xurxo Mendez Perez
 *   
 * This file is part of Android Runner.
 * 
 * Android Runner is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Android Runner is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Android Runner.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpizarro.th.server.view.xml;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.Response;
import org.jdom.Element;

import com.jpizarro.th.server.view.xml.response.ServerResponse;

/**
 * 
 * @author "Xurxo Mendez Perez"
 * 
 */
public class ServletUtils {

	private ServletUtils() {
		// Empty on purpose
	}

	public final static void writeServiceResponse(Element dataElement,
			HttpServletResponse response) throws IOException {

		writeServiceResponse(new ServerResponse(dataElement), response);

	}

	public final static void writeServiceResponse(Element dataElement,
			Response response) throws IOException {

		writeServiceResponse(new ServerResponse(dataElement), response);

	}

	private final static void writeServiceResponse(
			ServerResponse serverResponse, Response response)
			throws IOException {

		OutputStream out = response.getOutputStream();

		response.setContentType("text/xml; charset=UTF-8");
		ServerResponseXMLConversor.toXML(serverResponse, out, true);
		out.close();

	}

	private final static void writeServiceResponse(
			ServerResponse serverResponse, HttpServletResponse response)
			throws IOException {

		OutputStream out = response.getOutputStream();

		response.setContentType("text/xml; charset=UTF-8");
		ServerResponseXMLConversor.toXML(serverResponse, out, true);
		out.close();

	}
	public final static void writeServiceResponse(Element dataElement,
			OutputStream out) {
		ServerResponseXMLConversor.toXML(new ServerResponse(dataElement), out, true);
				
	}

}
