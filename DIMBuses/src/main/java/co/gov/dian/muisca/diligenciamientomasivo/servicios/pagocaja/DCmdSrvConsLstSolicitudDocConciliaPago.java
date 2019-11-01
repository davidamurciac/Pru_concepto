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
 * <p>Descripcion: Comando de servicio utilizado para consultar objetos SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsLstSolicitudDocConciliaPago extends DComandoServicioNegocio {


  /** Tipo de operaci� de consulta */
  protected int tipoOperacion = -1;
  /** Colecci� de objetos DSolicitudDocConciliaPagoTO */
  protected Collection objetosSolicitudDocConciliaPago;

  /**
   * Devuelve la colecci� de objetos SolicitudDocConciliaPago que se hayan consultado.
   * @return Un Collection con objetos DSolicitudDocConciliaPagoTO
   */
  public Collection getColeccionSolicitudDocConciliaPago() {
    return objetosSolicitudDocConciliaPago;
  }

  /**
   * Ejecuta el comando de servicio.
   * @throws DExcepcion Si ocurre alg�n error al realizar la
   * la consulta de SolicitudDocConciliaPago
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvConsLstSolicitudDocConciliaPago();
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
    return "Permite consultar objetos SolicitudDocConciliaPago";
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�n del comando.
   * @return true si los paretros son v�idos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�idos
   */
  public boolean validar() throws DValidarExcepcion {
  	  Map parametros=new HashMap();
      switch (tipoOperacion) {
      default:
         //throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos SolicitudDocConciliaPago"), getMensajeOperInvalida());
    }
    validarParametros("Listar",parametros);
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvConsLstSolicitudDocConciliaPago) {
      DCmdSrvConsLstSolicitudDocConciliaPago copia = (DCmdSrvConsLstSolicitudDocConciliaPago) comando;
      copia.tipoOperacion = tipoOperacion;
      copia.objetosSolicitudDocConciliaPago = objetosSolicitudDocConciliaPago;
    }
  }
}