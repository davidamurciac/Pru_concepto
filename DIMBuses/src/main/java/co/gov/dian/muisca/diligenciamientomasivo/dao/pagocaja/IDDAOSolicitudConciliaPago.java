/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja;

import java.sql.*;
import java.util.*;

import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.pagocaja.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para SolicitudConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAOSolicitudConciliaPago extends IDDAO {
  static final int CONSULTAR_POR_PK = 0;
  static final int CREAR = 1;
  static final int ACTUALIZAR = 2;
  static final int ELIMINAR = 3;

  /**
   * Inicializa la consulta por llave primaria.
   * @param pkSolicitudConciliaPago Llave primaria de SolicitudConciliaPago
   */
  void inicializarConsultarPorPK(DSolicitudConciliaPagoPKTO pkSolicitudConciliaPago);

  /**
   * Inicializa la creaci�n de SolicitudConciliaPago.
   * @param toSolicitudConciliaPago Objeto de Transporte de SolicitudConciliaPago
   */
  void inicializarCrear(DSolicitudConciliaPagoTO toSolicitudConciliaPago);

  /**
   * Inicializa la actualizaci�n de SolicitudConciliaPago.
   * @param toSolicitudConciliaPago Objeto de Transporte de SolicitudConciliaPago
   */
  void inicializarActualizar(DSolicitudConciliaPagoTO toSolicitudConciliaPago);

  /**
   * Inicializa la eliminaci�n de SolicitudConciliaPago.
   * @param pkSolicitudConciliaPago Llave primaria de SolicitudConciliaPago
   */
  void inicializarEliminar(DSolicitudConciliaPagoPKTO pkSolicitudConciliaPago);

  /**
   * Devuelve el objeto SolicitudConciliaPago que se haya consultado.
   * @return Un objeto DSolicitudConciliaPagoTO
   */
  DSolicitudConciliaPagoTO getSolicitudConciliaPago();

  /**
   * Devuelve la colecci�n de objetos SolicitudConciliaPago que se hayan consultado.
   * @return Un Collection con objetos DSolicitudConciliaPagoTO
   */
  Collection getColeccionSolicitudConciliaPago();

}
