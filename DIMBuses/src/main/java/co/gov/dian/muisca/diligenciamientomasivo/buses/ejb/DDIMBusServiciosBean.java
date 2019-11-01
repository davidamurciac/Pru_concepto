package co.gov.dian.muisca.diligenciamientomasivo.buses.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.IDServicioDocumento;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.buses.DBusServiciosBaseSubsistema;
import co.gov.dian.muisca.entradasalida.servicios.DComandoServicioControl;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoEjecutorSrvSub;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.buses.DContexto;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoEjecutorServicios;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;


public class DDIMBusServiciosBean extends DBusServiciosBaseSubsistema
    implements SessionBean  {

    SessionContext sessionContext;

    public void ejbCreate() throws CreateException {
    }
    public void ejbRemove() {
    }
    public void ejbActivate() {
    }
    public void ejbPassivate() {
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     *
     * @param comando IDComando
     * @return IDComando
     */
    public IDComando ejecutarComando(IDComando comando) {

        if (comando instanceof IDServicioDocumento &&
            // El Decorador sólo se debe aplicar al Servicio de Transporte.
            comando.getClass().getSuperclass().getName().equals(
                DComandoServicioInterno.class.getName())) {

            // Prepara la implementación del Servicio decorado.
            IDComando comandoImpl = instanciarComando(comando.getNombre(),
                                                      TIPO_COMANDO_IMPL);
            comando.asignar(comandoImpl);
            comandoImpl.setInfoPaginacion(comando.getInfoPaginacion());
            comandoImpl.getContexto().setContextoSeguridad(
                    comando.getContexto().getContextoSeguridad());
            comandoImpl.setEjecutor(new DComandoEjecutorSrvSub());
            ((DContexto) comandoImpl.getContexto()).setIdeTransaccion(comando.
                    getContexto().getIdeTransaccion());
            ((DContexto) comandoImpl.getContexto()).setComandoTransaccional(
                    comando.getContexto().isComandoTransaccional());

            // Decora el Servicio que implementó la interfaz.
            comando = new DComandoServicioControl((IDServicioDocumento) comando, comandoImpl);
        }

        return super.ejecutarComando(comando);
    }
}
