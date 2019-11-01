/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.pagocaja;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.pagocaja.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.impl.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto SolicitudConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearSolicitudConciliaPago extends DComandoServicioNegocio {
  /** Objeto de transporte de SolicitudConciliaPago */
  protected DSolicitudConciliaPagoTO toSolicitudConciliaPago;
  /** Llave primaria de SolicitudConciliaPago */
  protected DSolicitudConciliaPagoPKTO pkSolicitudConciliaPago;
  /** Atributos de SolicitudConciliaPago */
  protected DSolicitudConciliaPagoAttTO attSolicitudConciliaPago;

  /**
   * Inicializa la creaci� de SolicitudConciliaPago.
   * @param toSolicitudConciliaPago Objeto de Transporte de SolicitudConciliaPago
   */
  public void inicializar(DSolicitudConciliaPagoTO toSolicitudConciliaPago) {
    this.toSolicitudConciliaPago = toSolicitudConciliaPago;
    if (toSolicitudConciliaPago != null) {
      pkSolicitudConciliaPago = this.toSolicitudConciliaPago.getPK();
      attSolicitudConciliaPago = this.toSolicitudConciliaPago.getAtt();
    }
  }


  /**
   * Ejecuta el comando de servicio.
   * @throws DExcepcion Si ocurre alg�n error al realizar la
   * la creaci�n de SolicitudConciliaPago
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvCrearSolicitudConciliaPago();
  }

  /**
   * Indica si el comando es auditable.
   * @return true si el comando es auditable; false de lo contrario
   */
  public boolean isAuditable() {
    return true;
  }

  /**
   * Obtiene la descripci� del comando.
   * @return Un String con la descripci� del comando
   */
  public String getDescripcion() {
    return "Permite crear un objeto SolicitudConciliaPago";
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�ndel comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
   */
  public boolean validar() throws DValidarExcepcion {
   		Map parametros=new HashMap();
	    parametros.put(this.getClass().getName()+":validar:toSolicitudConciliaPago",toSolicitudConciliaPago);
        parametros.put(this.getClass().getName()+":validar:attSolicitudConciliaPago",attSolicitudConciliaPago);    
    	parametros.put(this.getClass().getName()+":validar:pkSolicitudConciliaPago",pkSolicitudConciliaPago);
		parametros.put(this.getClass().getName()+":validar:pkSolicitudConciliaPago.getIdeSolicitud()",pkSolicitudConciliaPago.getIdeSolicitud());
		parametros.put(this.getClass().getName()+":validar:attSolicitudConciliaPago.getIdePersonaSolicitud()",attSolicitudConciliaPago.getIdePersonaSolicitud());
		parametros.put(this.getClass().getName()+":validar:attSolicitudConciliaPago.getCodEstadoProceso()",attSolicitudConciliaPago.getCodEstadoProceso());
		validarParametros("Crear",parametros);
    	return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvCrearSolicitudConciliaPago) {
      DCmdSrvCrearSolicitudConciliaPago copia = (DCmdSrvCrearSolicitudConciliaPago) comando;
      copia.toSolicitudConciliaPago = toSolicitudConciliaPago;
      copia.pkSolicitudConciliaPago = pkSolicitudConciliaPago;
      copia.attSolicitudConciliaPago = attSolicitudConciliaPago;
    }
  }
}