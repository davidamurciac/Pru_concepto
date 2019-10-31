/**
 * 
 */
package co.gov.dian.muisca.diligenciamientomasivo.buses;

import co.gov.dian.muisca.arquitectura.web.buses.DBusServiciosDelegate;

/**
 * Clase que representa el delegado para los servicios que necesitan una transacción nueva.
 *
 * @author nfontechar
 */
public class DBusServiciosDelegateDIMBTxNueva extends DBusServiciosDelegate {
	
	
	
	
    public DBusServiciosDelegateDIMBTxNueva() {}

    /**
     *
     * @return String
     * @todo Implement this co.gov.dian.muisca.arquitectura.web.buses.DBusServiciosDelegateBase
     *   method
     */
    public String getNombreBus() {
    	return "DDIMBusServiciosTxNueva";
    }	

}/*fin de class*/
