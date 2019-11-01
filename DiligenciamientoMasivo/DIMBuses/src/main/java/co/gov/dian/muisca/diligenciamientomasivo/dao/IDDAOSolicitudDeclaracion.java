/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao;

import java.util.Collection;

import co.gov.dian.muisca.arquitectura.interfaces.IDDAO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAOSolicitudDeclaracion extends IDDAO {
  static final int CONSULTAR_POR_PK = 0;
  static final int CREAR = 1;
  static final int ACTUALIZAR = 2;
  static final int ELIMINAR = 3;
  static final int CONSULTA_GENERICA=4;
  static final int CONSULTA_GENERICA_ATT=5;
  static final int CONSULTA_FORMATO_FEC_CAMBIO=6;

  /**
   * Inicializa la consulta por llave primaria.
   * @param pkSolicitudDeclaracion Llave primaria de SolicitudDeclaracion
   */
  void inicializarConsultarPorPK(DSolicitudDeclaracionPKTO pkSolicitudDeclaracion);

  /**
   * Inicializa la creaci�n de SolicitudDeclaracion.
   * @param toSolicitudDeclaracion Objeto de Transporte de SolicitudDeclaracion
   */
  void inicializarCrear(DSolicitudDeclaracionTO toSolicitudDeclaracion);

  /**
   * Inicializa la actualizaci�n de SolicitudDeclaracion.
   * @param toSolicitudDeclaracion Objeto de Transporte de SolicitudDeclaracion
   */
  void inicializarActualizar(DSolicitudDeclaracionTO toSolicitudDeclaracion);

  /**
   * Inicializa la eliminaci�n de SolicitudDeclaracion.
   * @param pkSolicitudDeclaracion Llave primaria de SolicitudDeclaracion
   */
  void inicializarEliminar(DSolicitudDeclaracionPKTO pkSolicitudDeclaracion);
  
  /**
   * Inicializa el DAO para hacer consultas gen�ricas.
   * 
   * @param to Dato de tipo <code>DSolictudDeclaracionTO</code> con los par�metros de b�squeda.
   */
  void inicializarConsultarGenerica(DSolicitudDeclaracionTO to);
  
  /**
   * Inicializa el DAO para hacer consultas por ideFormato y FecCambio.
   * 
   * @param to Dato de tipo <code>DSolictudDeclaracionTO</code> con los par�metros de b�squeda.
   */
  void inicializarConsultarPorFormatoFecCambio(DSolicitudDeclaracionTO to);
  
  /**
   * Inicializa el DAO para hacer consultas gen�ricas.
   * 
   * @param to Dato de tipo <code>DSolictudDeclaracionTO</code> con los par�metros de b�squeda.
   */
  void inicializarConsultarGenericaAtt(DSolicitudDeclaracionTO to);

  /**
   * Devuelve el objeto SolicitudDeclaracion que se haya consultado.
   * @return Un objeto DSolicitudDeclaracionTO
   */
  DSolicitudDeclaracionTO getSolicitudDeclaracion();

  /**
   * Devuelve la colecci�n de objetos SolicitudDeclaracion que se hayan consultado.
   * @return Un Collection con objetos DSolicitudDeclaracionTO
   */
  Collection getColeccionSolicitudDeclaracion();

}
