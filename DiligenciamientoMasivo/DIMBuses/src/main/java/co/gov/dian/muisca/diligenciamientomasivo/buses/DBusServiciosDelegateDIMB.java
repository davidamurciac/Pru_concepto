package co.gov.dian.muisca.diligenciamientomasivo.buses;

import co.gov.dian.muisca.arquitectura.web.buses.DBusServiciosDelegate;

public class DBusServiciosDelegateDIMB extends DBusServiciosDelegate {

	private static final long serialVersionUID = -4259860577610500092L;

	public DBusServiciosDelegateDIMB() {
    }

    /**
     * @return String
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.web.buses.DBusServiciosDelegateBase
     *   method
     */
    public String getNombreBus() {
        if (isTransaccional()) {
            return "DDIMBusServiciosTx";
        }
        else {
            return "DDIMBusServicios";
        }
    }
}
