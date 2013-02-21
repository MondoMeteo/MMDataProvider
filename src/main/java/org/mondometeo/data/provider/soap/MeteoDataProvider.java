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
package org.mondometeo.data.provider.soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.WebServiceProvider;

import net.staniscia.odynodatabus.Publisher;
import net.staniscia.odynodatabus.exceptions.PublishException;
import net.staniscia.odynodatabus.msg.SerializableMessage;

import org.mondometeo.common.MeteoFrame;
import org.mondometeo.common.MeteoFrameFactory;
import org.mondometeo.data.provider.soap.exception.DataProviderException;
import org.mondometeo.data.provider.soap.exception.WrongUrlException;

/**
 * The Interface MeteoDataProvider.
 * 
 */

public class MeteoDataProvider {

	private Publisher<MeteoFrame> env;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mondometeo.data.provider.soap.WSMeteoDataProvider#notifyMeteoFrame
	 * (float, float, float, float, long, int, int, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */

	public boolean notifyMeteoFrame(float north, float south, float east,
			float west, long timeOfFrame, int nrows, int ncols,
			String cloudContentUrl, String lstContentUrl,
			String rainContentUrl, String tempContentUrl, String descriptions)
			throws WrongUrlException, DataProviderException {

		// Converti
		BufferedReader cloudTypes;
		BufferedReader lstValues;
		BufferedReader tempValues;
		BufferedReader rainValues;
		try {
			cloudTypes = new BufferedReader(new InputStreamReader((new URL(
					cloudContentUrl)).openStream()));
		} catch (MalformedURLException e) {
			throw new WrongUrlException(e);
		} catch (IOException e) {
			throw new DataProviderException(cloudContentUrl + " is not readable", e);
		}

		try {
			lstValues = new BufferedReader(new InputStreamReader((new URL(
					lstContentUrl)).openStream()));
		} catch (MalformedURLException e) {
			throw new WrongUrlException(e);
		} catch (IOException e) {
			throw new DataProviderException(lstContentUrl + " is not readable", e);
		}
		
		try {
			rainValues = new BufferedReader(new InputStreamReader((new URL(
					rainContentUrl)).openStream()));
		} catch (MalformedURLException e) {
			throw new WrongUrlException(e);
		} catch (IOException e) {
			throw new DataProviderException(rainContentUrl + " is not readable", e);
		}
		
		try {

			tempValues = new BufferedReader(new InputStreamReader((new URL(
					tempContentUrl)).openStream()));

		} catch (MalformedURLException e) {
			throw new WrongUrlException(e);
		} catch (IOException e) {
			throw new DataProviderException(tempContentUrl + " is not readable", e);
		}

		// Crea il frame
		MeteoFrame objToSend;
		try {
			objToSend = MeteoFrameFactory.makeMeteoFrame(north, south, east,
					west, timeOfFrame, nrows, ncols, cloudTypes, lstValues,
					rainValues, tempValues);
		} catch (IOException e) {
			throw new DataProviderException("Error on making MeteoFrame", e);
		}

		if (env != null) {
			try {

				env.publish(new SerializableMessage<MeteoFrame>(objToSend));
			} catch (PublishException e) {
				throw new DataProviderException("Error on publish the data", e);
			}
		} else {
			throw new DataProviderException("NO Pubblisher ");
		}

		return true;

	}

	public void setDataService(Publisher<MeteoFrame> env) {
		this.env = env;
	}

}