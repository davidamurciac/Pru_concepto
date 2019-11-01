/**
 * Republica de Colombia
 * Copyright (c) 2004 DirecciÃ³n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Objeto de acceso a datos para SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAOSolicitudDocConciliaPago extends IDDAO {
  static final int CONSULTAR_POR_PK = 0;
  static final int CREAR = 1;
  static final int ACTUALIZAR = 2;
  static final int ELIMINAR = 3;
  static final int MERGE = 4;

  /**
   * Inicializa la consulta por llave primaria.
   * @param pkSolicitudDocConciliaPago Llave primaria de SolicitudDocConciliaPago
   */
  void inicializarConsultarPorPK(DSolicitudDocConciliaPagoPKTO pkSolicitudDocConciliaPago);

  /**
   * Inicializa la creaciï¿½n de SolicitudDocConciliaPago.
   * @param toSolicitudDocConciliaPago Objeto de Transporte de SolicitudDocConciliaPago
   */
  void inicializarCrear(DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago);

  /**
   * Inicializa la actualizaciï¿½n de SolicitudDocConciliaPago.
   * @param toSolicitudDocConciliaPago Objeto de Transporte de SolicitudDocConciliaPago
   */
  void inicializarActualizar(DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago);

  /**
   * Inicializa la eliminaciï¿½n de SolicitudDocConciliaPago.
   * @param pkSolicitudDocConciliaPago Llave primaria de SolicitudDocConciliaPago
   */
  void inicializarEliminar(DSolicitudDocConciliaPagoPKTO pkSolicitudDocConciliaPago);

  /**
   * Devuelve el objeto SolicitudDocConciliaPago que se haya consultado.
   * @return Un objeto DSolicitudDocConciliaPagoTO
   */
  DSolicitudDocConciliaPagoTO getSolicitudDocConciliaPago();

  /**
   * Devuelve la colecciï¿½n de objetos SolicitudDocConciliaPago que se hayan consultado.
   * @return Un Collection con objetos DSolicitudDocConciliaPagoTO
   */
  Collection getColeccionSolicitudDocConciliaPago();
  
  /**
   * Inicializa el merge(creación/actualización) de SolicitudDocConciliaPago.
   * @param toSolicitudDocConciliaPago Objeto de Transporte de SolicitudDocConciliaPago
   */
  void inicializarMerge(DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago);

}
