/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.pagocaja;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.pagocaja.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.pagocaja.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acci�n utilizado para consultar un objeto SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsSolicitudDocConciliaPago extends DComandoAccion {
  /** Objeto de transporte de SolicitudDocConciliaPago */
  protected DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago;
  /** Llave primaria de SolicitudDocConciliaPago */
  protected DSolicitudDocConciliaPagoPKTO pkSolicitudDocConciliaPago;
  /** Atributos de SolicitudDocConciliaPago */
  protected DSolicitudDocConciliaPagoAttTO attSolicitudDocConciliaPago;

  /**
   * Inicializa la consulta por llave primaria.
   * @param pkSolicitudDocConciliaPago Llave primaria de SolicitudDocConciliaPago
   */
  public void inicializar(DSolicitudDocConciliaPagoPKTO pkSolicitudDocConciliaPago) {
    isOk = false;
    toSolicitudDocConciliaPago = null;
    attSolicitudDocConciliaPago = null;
    this.pkSolicitudDocConciliaPago = pkSolicitudDocConciliaPago;
  }

  /**
   * Devuelve el objeto SolicitudDocConciliaPago que se haya consultado.
   * @return Un objeto DSolicitudDocConciliaPagoTO
   */
  public DSolicitudDocConciliaPagoTO getSolicitudDocConciliaPago() {
    return toSolicitudDocConciliaPago;
  }

  /**
   * Ejecuta el comando de acci�n.
   */
  protected void ejecutarComando() {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdAccConsSolicitudDocConciliaPago();
  }

  /**
   * Indica si el comando es auditable.
   * @return true si el comando es auditable; false de lo contrario
   */
  public boolean isAuditable() {
    return true;
  }

  /**
   * Obtiene la descripci�n del comando.
   * @return Un String con la descripci� del comando
   */
  public String getDescripcion() {
    return "Permite consultar un objeto SolicitudDocConciliaPago";
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�n del comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
   */
  public boolean validar() throws DValidarExcepcion {
  	Map parametros=new HashMap();
    parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago",pkSolicitudDocConciliaPago);
	parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeSolicitud()",pkSolicitudDocConciliaPago.getIdeSolicitud());
	parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeDocumentoConcilia()",pkSolicitudDocConciliaPago.getIdeDocumentoConcilia());
	parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeDeclaracion()",pkSolicitudDocConciliaPago.getIdeDeclaracion());
	validarParametros("Consulta",parametros);
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccConsSolicitudDocConciliaPago) {
      DCmdAccConsSolicitudDocConciliaPago copia = (DCmdAccConsSolicitudDocConciliaPago) comando;
      copia.toSolicitudDocConciliaPago = toSolicitudDocConciliaPago;
      copia.pkSolicitudDocConciliaPago = pkSolicitudDocConciliaPago;
      copia.attSolicitudDocConciliaPago = attSolicitudDocConciliaPago;
    }
  }
}