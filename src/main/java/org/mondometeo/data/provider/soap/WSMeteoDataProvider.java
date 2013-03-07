package org.mondometeo.data.provider.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import org.mondometeo.data.provider.soap.exception.DataProviderException;
import org.mondometeo.data.provider.soap.exception.WrongUrlException;

@WebService(name = "MeteoDataProvider",serviceName="MeteoDataProviderService",portName="MeteoDataProviderPort")
@SOAPBinding(style=Style.RPC)
public class WSMeteoDataProvider {
	
	private MeteoDataProvider controller;
	

	public WSMeteoDataProvider(MeteoDataProvider controller) {
		super();
		this.controller = controller;
	}


	/**
	 * Notify meteo frame.
	 *
	 * @param north the north
	 * @param south the south
	 * @param east the east
	 * @param west the west
	 * @param timeOfFrame the time of frame
	 * @param nrows the nrows
	 * @param ncols the ncols
	 * @param cloudContentUrl the cloud content url
	 * @param lstContentUrl the lst content url
	 * @param rainContentUrl the rain content url
	 * @param tempContentUrl the temp content url
	 * @param descriptions the descriptions
	 * @throws WrongUrlException the wrong url exception
	 * @throws DataProviderException the data provider exception
	 */
	@WebMethod(operationName = "notifyFrame")
	public boolean notifyMeteoFrame(
			@WebParam(name = "north") float north,
			@WebParam(name = "south") float south,
			@WebParam(name = "east") float east,
			@WebParam(name = "west") float west,
			@WebParam(name = "timeOfFrame") long timeOfFrame,
			@WebParam(name = "nrows") int nrows,
			@WebParam(name = "ncols") int ncols,
			@WebParam(name = "cloudContentUrl") String cloudContentUrl,
			@WebParam(name = "lstContentUrl") String lstContentUrl,
			@WebParam(name = "rainContentUrl") String rainContentUrl,
			@WebParam(name = "tempContentUrl") String tempContentUrl,
			@WebParam(name = "descriptions") String descriptions)
			throws WrongUrlException, DataProviderException{
		return controller.notifyMeteoFrame(north, south, east, west, timeOfFrame, nrows, ncols, cloudContentUrl, lstContentUrl, rainContentUrl, tempContentUrl, descriptions);
	}

}