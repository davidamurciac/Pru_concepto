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
 * <p>Descripcion: Comando de acci�n utilizado para consultar objetos SolicitudConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstSolicitudConciliaPago extends DComandoAccion {


  /** Tipo de operaci�n de consulta */
  protected int tipoOperacion = -1;
  /** Colecci�n de objetos DSolicitudConciliaPagoTO */
  protected Collection objetosSolicitudConciliaPago;

  /**
   * Devuelve la colecci�n de objetos SolicitudConciliaPago que se hayan consultado.
   * @return Un Collection con objetos DSolicitudConciliaPagoTO
   */
  public Collection getColeccionSolicitudConciliaPago() {
    return objetosSolicitudConciliaPago;
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
    return new DCmdAccConsLstSolicitudConciliaPago();
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
    return "Permite consultar objetos SolicitudConciliaPago";
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�n del comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
   */
  public boolean validar() throws DValidarExcepcion {
   Map parametros=new HashMap();
    switch (tipoOperacion) {
      default:
        // throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos SolicitudConciliaPago"), getMensajeOperInvalida());
    }
    validarParametros("Listar",parametros);
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccConsLstSolicitudConciliaPago) {
      DCmdAccConsLstSolicitudConciliaPago copia = (DCmdAccConsLstSolicitudConciliaPago) comando;
      copia.tipoOperacion = tipoOperacion;
      copia.objetosSolicitudConciliaPago = objetosSolicitudConciliaPago;
    }
  }
}