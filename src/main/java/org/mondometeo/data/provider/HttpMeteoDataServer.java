/*  
    Copyright 2012  Alessandro Staniscia ( alessandro@staniscia.net )

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License, version 2, as
    published by the Free Software Foundation.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.mondometeo.data.provider;

import javax.xml.ws.Endpoint;

import net.staniscia.odynodatabus.DataBusService;

import org.mondometeo.common.MeteoFrame;
import org.mondometeo.data.provider.soap.MeteoDataProvider;
import org.mondometeo.data.provider.soap.WSMeteoDataProvider;

public class HttpMeteoDataServer {

	private DataBusService dataBaseService;
	private MeteoDataProvider webController;
	private Endpoint endpoint;
	private String hostname;
	private String port;

	public HttpMeteoDataServer(String host, String port) {
		super();
		this.hostname=host;
		this.port=port;
		this.dataBaseService = null;
		webController = new MeteoDataProvider();
		endpoint = Endpoint.create(new WSMeteoDataProvider(webController));
	}

	public void setDataBusService(DataBusService dataBaseService) {
		this.dataBaseService = dataBaseService;
		webController.setDataService(dataBaseService.getDataPublisher(MeteoFrame.class));
	}
	
	public void unsetDataBusService(DataBusService dataBaseService){
		this.dataBaseService=null;
		webController.setDataService(null);
	}

	public void doStart() {
		String wsAddress = "http://"+this.hostname+":"+this.port+"/MMDP";
		endpoint.publish(wsAddress);
	}

	/**
	 * Do stop.
	 */
	public void doStop() {
		endpoint.stop();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		HttpMeteoDataServer m = new HttpMeteoDataServer("localhost","23979");
		m.doStart();
	}

}
