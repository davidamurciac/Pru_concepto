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
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearSolicitudDocConciliaPago extends DComandoServicioNegocio {
  /** Objeto de transporte de SolicitudDocConciliaPago */
  protected DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago;
  /** Llave primaria de SolicitudDocConciliaPago */
  protected DSolicitudDocConciliaPagoPKTO pkSolicitudDocConciliaPago;
  /** Atributos de SolicitudDocConciliaPago */
  protected DSolicitudDocConciliaPagoAttTO attSolicitudDocConciliaPago;

  /**
   * Inicializa la creaci� de SolicitudDocConciliaPago.
   * @param toSolicitudDocConciliaPago Objeto de Transporte de SolicitudDocConciliaPago
   */
  public void inicializar(DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago) {
    this.toSolicitudDocConciliaPago = toSolicitudDocConciliaPago;
    if (toSolicitudDocConciliaPago != null) {
      pkSolicitudDocConciliaPago = this.toSolicitudDocConciliaPago.getPK();
      attSolicitudDocConciliaPago = this.toSolicitudDocConciliaPago.getAtt();
    }
  }


  /**
   * Ejecuta el comando de servicio.
   * @throws DExcepcion Si ocurre alg�n error al realizar la
   * la creaci�n de SolicitudDocConciliaPago
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvCrearSolicitudDocConciliaPago();
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
    return "Permite crear un objeto SolicitudDocConciliaPago";
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�ndel comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
   */
  public boolean validar() throws DValidarExcepcion {
   		Map parametros=new HashMap();
	    parametros.put(this.getClass().getName()+":validar:toSolicitudDocConciliaPago",toSolicitudDocConciliaPago);
        parametros.put(this.getClass().getName()+":validar:attSolicitudDocConciliaPago",attSolicitudDocConciliaPago);    
    	parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago",pkSolicitudDocConciliaPago);
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeSolicitud()",pkSolicitudDocConciliaPago.getIdeSolicitud());
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeDocumentoConcilia()",pkSolicitudDocConciliaPago.getIdeDocumentoConcilia());
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeDeclaracion()",pkSolicitudDocConciliaPago.getIdeDeclaracion());
		parametros.put(this.getClass().getName()+":validar:attSolicitudDocConciliaPago.getCodEstadoProceso()",attSolicitudDocConciliaPago.getCodEstadoProceso());
		validarParametros("Crear",parametros);
    	return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvCrearSolicitudDocConciliaPago) {
      DCmdSrvCrearSolicitudDocConciliaPago copia = (DCmdSrvCrearSolicitudDocConciliaPago) comando;
      copia.toSolicitudDocConciliaPago = toSolicitudDocConciliaPago;
      copia.pkSolicitudDocConciliaPago = pkSolicitudDocConciliaPago;
      copia.attSolicitudDocConciliaPago = attSolicitudDocConciliaPago;
    }
  }
}