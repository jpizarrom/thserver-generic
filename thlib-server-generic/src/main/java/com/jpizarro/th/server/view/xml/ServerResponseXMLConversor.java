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

import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.jpizarro.th.server.view.xml.response.ServerResponse;

/**
 * 
 * @author "Xurxo Mendez Perez"
 *
 */

public class ServerResponseXMLConversor {
       
    private ServerResponseXMLConversor() {}
    
    public final static void toXML(ServerResponse response, OutputStream out,
            boolean prettyFormat) {
            
        try {
        	
            Document document = new Document(response.getDataElements());
            XMLOutputter outputter;
            
            if (prettyFormat) {
                outputter = new XMLOutputter(Format.getPrettyFormat());                
            } else {
                outputter = new XMLOutputter();
            }
            outputter.output(document, out);
        
        } catch (Exception e) {
            System.out.println("Error serializing instance of " +
                    ServerResponse.class.toString());
        }
        
    }
}
