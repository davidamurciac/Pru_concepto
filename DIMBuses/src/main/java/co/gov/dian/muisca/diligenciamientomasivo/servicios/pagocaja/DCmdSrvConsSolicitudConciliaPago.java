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
 * <p>Descripcion: Comando de servicio utilizado para consultar un objeto SolicitudConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsSolicitudConciliaPago extends DComandoServicioNegocio {
  /** Objeto de transporte de SolicitudConciliaPago */
  protected DSolicitudConciliaPagoTO toSolicitudConciliaPago;
  /** Llave primaria de SolicitudConciliaPago */
  protected DSolicitudConciliaPagoPKTO pkSolicitudConciliaPago;
  /** Atributos de SolicitudConciliaPago */
  protected DSolicitudConciliaPagoAttTO attSolicitudConciliaPago;

  /**
   * Inicializa la consulta por llave primaria.
   * @param pkSolicitudConciliaPago Llave primaria de SolicitudConciliaPago
   */
  public void inicializar(DSolicitudConciliaPagoPKTO pkSolicitudConciliaPago) {
    toSolicitudConciliaPago = null;
    attSolicitudConciliaPago = null;
    this.pkSolicitudConciliaPago = pkSolicitudConciliaPago;
  }

  /**
   * Devuelve el objeto SolicitudConciliaPago que se haya consultado.
   * @return Un objeto DSolicitudConciliaPagoTO
   */
  public DSolicitudConciliaPagoTO getSolicitudConciliaPago() {
    return toSolicitudConciliaPago;
  }

  /**
   * Ejecuta el comando de servicio.
   * @throws DExcepcion Si ocurre alg�n error al realizar la
   * la consulta de SolicitudConciliaPago
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvConsSolicitudConciliaPago();
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
    return "Permite consultar un objeto SolicitudConciliaPago";
  }

  /**
   * M�odo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�n del comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
   */
  public boolean validar() throws DValidarExcepcion {
    Map parametros=new HashMap();
    parametros.put(this.getClass().getName()+":validar:pkSolicitudConciliaPago",pkSolicitudConciliaPago);
	parametros.put(this.getClass().getName()+":validar:pkSolicitudConciliaPago.getIdeSolicitud()",pkSolicitudConciliaPago.getIdeSolicitud());
	validarParametros("Consulta",parametros);
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvConsSolicitudConciliaPago) {
      DCmdSrvConsSolicitudConciliaPago copia = (DCmdSrvConsSolicitudConciliaPago) comando;
      copia.toSolicitudConciliaPago = toSolicitudConciliaPago;
      copia.pkSolicitudConciliaPago = pkSolicitudConciliaPago;
      copia.attSolicitudConciliaPago = attSolicitudConciliaPago;
    }
  }
}