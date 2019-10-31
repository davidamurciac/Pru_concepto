/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao;

import java.sql.*;
import java.util.*;

import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public interface IDDAOJuegoSuerteAzar extends IDDAO {
  static final int CONSULTAR_POR_PK = 0;
  static final int CREAR = 1;
  static final int ACTUALIZAR = 2;
  static final int ELIMINAR = 3;
  static final int CONSULTA_GENERICA = 4;
  static final int ACTUALIZAR_ESTADO = 5;


  /**
   * Inicializa la consulta por llave primaria.
   * @param pkJuegoSuerteAzar Llave primaria de JuegoSuerteAzar
   */
  void inicializarConsultarPorPK(DJuegoSuerteAzarPKTO pkJuegoSuerteAzar);

  /**
   * Inicializa la creación de JuegoSuerteAzar.
   * @param toJuegoSuerteAzar Objeto de Transporte de JuegoSuerteAzar
   */
  void inicializarCrear(DJuegoSuerteAzarTO toJuegoSuerteAzar);

  /**
   * Inicializa la actualización de JuegoSuerteAzar.
   * @param toJuegoSuerteAzar Objeto de Transporte de JuegoSuerteAzar
   */
  void inicializarActualizar(DJuegoSuerteAzarTO toJuegoSuerteAzar);

  /**
   * Inicializa la eliminación de JuegoSuerteAzar.
   * @param pkJuegoSuerteAzar Llave primaria de JuegoSuerteAzar
   */
  void inicializarEliminar(DJuegoSuerteAzarPKTO pkJuegoSuerteAzar);

  /**
   * Inicializa la eliminación de JuegoSuerteAzar.
   * @param attJuegoSuerteAzar Atributos de JuegoSuerteAzar
   */
  void inicializarConsultaGenerica(DJuegoSuerteAzarTO toJuegoSuerteAzar);
  /**
   * Inicializa la actualización de JuegoSuerteAzar.
   * @param toJuegoSuerteAzar Objeto de Transporte de JuegoSuerteAzar
   */
  void inicializarActualizarEstado(DJuegoSuerteAzarPKTO pkJuegoSuerteAzar, Integer codEstado);
  /**
   * Devuelve el objeto JuegoSuerteAzar que se haya consultado.
   * @return Un objeto DJuegoSuerteAzarTO
   */
  DJuegoSuerteAzarTO getJuegoSuerteAzar();

  /**
   * Devuelve la colección de objetos JuegoSuerteAzar que se hayan consultado.
   * @return Un Collection con objetos DJuegoSuerteAzarTO
   */
  Collection getColeccionJuegoSuerteAzar();

}
