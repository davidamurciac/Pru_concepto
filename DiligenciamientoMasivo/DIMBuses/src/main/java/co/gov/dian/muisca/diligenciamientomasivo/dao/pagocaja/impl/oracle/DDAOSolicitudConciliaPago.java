/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.impl.oracle;

import java.sql.*;
import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.dataset.*;
import co.gov.dian.muisca.arquitectura.mensajes.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.pagocaja.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.*;

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
public class DDAOSolicitudConciliaPago extends DDAO implements IDDAOSolicitudConciliaPago {
  /** Colecci�n de objetos DSolicitudConciliaPagoTO */
  private Collection objetosSolicitudConciliaPago;
  /** Objeto de transporte de SolicitudConciliaPago */
  private DSolicitudConciliaPagoTO toSolicitudConciliaPago;
  /** Llave primaria de SolicitudConciliaPago */
  private DSolicitudConciliaPagoPKTO pkSolicitudConciliaPago;
  /** Atributos de SolicitudConciliaPago */
  private DSolicitudConciliaPagoAttTO attSolicitudConciliaPago;

  /**
   * Inicializa la consulta por llave primaria.
   * @param pkSolicitudConciliaPago Llave primaria de SolicitudConciliaPago
   */
  public void inicializarConsultarPorPK(DSolicitudConciliaPagoPKTO pkSolicitudConciliaPago) {
    setTipoOperacion(CONSULTAR_POR_PK);
    this.pkSolicitudConciliaPago = pkSolicitudConciliaPago;
  }

  /**
   * Inicializa la creaci�n de SolicitudConciliaPago.
   * @param toSolicitudConciliaPago Objeto de Transporte de SolicitudConciliaPago
   */
  public void inicializarCrear(DSolicitudConciliaPagoTO toSolicitudConciliaPago) {
    setTipoOperacion(CREAR);
    this.toSolicitudConciliaPago = toSolicitudConciliaPago;
    if (toSolicitudConciliaPago != null) {
      pkSolicitudConciliaPago = this.toSolicitudConciliaPago.getPK();
      attSolicitudConciliaPago = this.toSolicitudConciliaPago.getAtt();
    }
  }

  /**
   * Inicializa la actualizaci�n de SolicitudConciliaPago.
   * @param toSolicitudConciliaPago Objeto de Transporte de SolicitudConciliaPago
   */
  public void inicializarActualizar(DSolicitudConciliaPagoTO toSolicitudConciliaPago) {
    setTipoOperacion(ACTUALIZAR);
    this.toSolicitudConciliaPago = toSolicitudConciliaPago;
    if (toSolicitudConciliaPago != null) {
      pkSolicitudConciliaPago = this.toSolicitudConciliaPago.getPK();
      attSolicitudConciliaPago = this.toSolicitudConciliaPago.getAtt();
    }
  }

  /**
   * Inicializa la eliminaci�n de SolicitudConciliaPago.
   * @param pkSolicitudConciliaPago Llave primaria de SolicitudConciliaPago
   */
  public void inicializarEliminar(DSolicitudConciliaPagoPKTO pkSolicitudConciliaPago) {
    setTipoOperacion(ELIMINAR);
    this.pkSolicitudConciliaPago = pkSolicitudConciliaPago;
  }

  /**
   * Devuelve las sentencias sql a ejecutar, dependiendo del tipo de operaci�n a realizar.
   * @return Un Map de Strings con la relaci�n de sentencias sql
   */
  public Map getSentenciasSQL() {
    Map m = new HashMap();
    StringBuffer sql = new StringBuffer();
    switch (getTipoOperacion()) {
      case CREAR:
        sql.append("insert into DIL_SOLICITUDES_CONCILIA_PAGO")
            .append(" (IDE_SOLICITUD, NUM_NIT, IDE_PERSONA_SOLICITUD, FEC_SOLICITUD, NUM_TOTAL_REGISTROS, NUM_REGISTROS_PAGO, VAL_TOTAL, FEC_PROCESO, COD_ESTADO_PROCESO)")
            .append(" VALUES (:IDE_SOLICITUD, :NUM_NIT, :IDE_PERSONA_SOLICITUD, :FEC_SOLICITUD, :NUM_TOTAL_REGISTROS, :NUM_REGISTROS_PAGO, :VAL_TOTAL, :FEC_PROCESO, :COD_ESTADO_PROCESO)");
        m.put("sentencia1", sql.toString());
        break;
      case ACTUALIZAR:
        sql.append("update DIL_SOLICITUDES_CONCILIA_PAGO")
            .append(" set NUM_NIT=:NUM_NIT, IDE_PERSONA_SOLICITUD=:IDE_PERSONA_SOLICITUD, FEC_SOLICITUD=:FEC_SOLICITUD, NUM_TOTAL_REGISTROS=:NUM_TOTAL_REGISTROS, NUM_REGISTROS_PAGO=:NUM_REGISTROS_PAGO, VAL_TOTAL=:VAL_TOTAL, FEC_PROCESO=:FEC_PROCESO, COD_ESTADO_PROCESO=:COD_ESTADO_PROCESO")
            .append(" where IDE_SOLICITUD=:IDE_SOLICITUD");
        m.put("sentencia1", sql.toString());
        break;
      case ELIMINAR:
        sql.append("delete from DIL_SOLICITUDES_CONCILIA_PAGO")
            .append(" where IDE_SOLICITUD=:IDE_SOLICITUD");
        m.put("sentencia1", sql.toString());
        break;
      case CONSULTAR_POR_PK:
        sql.append("select * from DIL_SOLICITUDES_CONCILIA_PAGO")
            .append(" where IDE_SOLICITUD=:IDE_SOLICITUD");
        m.put("sentencia1", sql.toString());
        break;
    }
    return m;
  }

  /**
   * Guarda los datos de SolicitudConciliaPago.
   * @return @return Un int con el n�mero de registros guardados
   * @throws SQLException Si ocurre un error de base de datos al guardar
   */
  public int guardar() throws SQLException {
    switch (getTipoOperacion()) {
      case CREAR:
        return crear();
      case ACTUALIZAR:
        return actualizar();
    }
    return -1;
  }

  /**
   * Elimina registros de SolicitudConciliaPago.
   * @return Un int con el n�mero de registros eliminados
   * @throws SQLException Si ocurre un error de base de datos al eliminar
   */
   public int eliminar() throws SQLException {
     DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
     asignarValoresPK(sentencia);
     sentencia.ejecutar();
     return sentencia.getRegistrosAfectados();
   }

  /**
   * Consulta los datos de SolicitudConciliaPago.
   * @return Un IDDataSet con la colecci�n de registros encontrados
   * @throws SQLException Si ocurre un error de base de datos al consultar
   */
  public IDDataSet consultar() throws SQLException {
//    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
    switch (getTipoOperacion()) {
      case CONSULTAR_POR_PK:
        return consultarPorPK();
    }
    return null;
  }

  /**
   * Crea un registro de SolicitudConciliaPago.
   * @return Un int con el n�mero de registros creados
   * @throws SQLException Si ocurre un error de base de datos al crear
   */
  private int crear() throws SQLException {
    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
    asignarValoresObjeto(sentencia);
    asignarValoresPK(sentencia);
    sentencia.ejecutar();
    int resultado = sentencia.getRegistrosAfectados();
    if (resultado <= 0) {
      throw new SQLException("No se ha creado ning�n registro");
    }
    if (resultado > 1) {
      throw new SQLException("Se intent� crear m�s de un registro");
    }
    return resultado;
  }

  /**
   * Actualiza los datos de SolicitudConciliaPago.
   * @return Un int con el n�mero de registros actualizados
   * @throws SQLException Si ocurre un error de base de datos al actualizar
   */
  private int actualizar() throws SQLException {
    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
    asignarValoresObjeto(sentencia);
    asignarValoresPK(sentencia);
    sentencia.ejecutar();
    int resultado = sentencia.getRegistrosAfectados();
    if (resultado <= 0) {
      throw new SQLException("No se ha actualizado ning�n registro");
    }
    if (resultado > 1) {
      throw new SQLException("Se intent� actualizar m�s de un registro");
    }
    return resultado;
  }

  /**
   * Actualiza los datos de SolicitudConciliaPago.
   * @return Un IDDataSet con la colecci�n de registros encontrados
   * @throws SQLException Si ocurre un error de base de datos al consultar
   */
  private IDDataSet consultarPorPK() throws SQLException {
    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
    asignarValoresPK(sentencia);
    sentencia.ejecutar();
    DDataSet resultado = sentencia.getDataSet();
    cargarSolicitudConciliaPago(resultado);
    return resultado;
  }

  /**
   * Asigna los valores para pk en una sentencia SQL.
   * @param unaSentencia Sentencia para asignaci�n
   * @throws SQLException Si ocurre un error al asignar los valores
   */
  private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
    unaSentencia.setLong("IDE_SOLICITUD", pkSolicitudConciliaPago.getIdeSolicitud());
  }

  /**
   * Asigna todos los valores de un objeto.
   * @param unaSentencia Sentencia para asignaci�n
   * @throws SQLException Si ocurre un error al asignar los valores
   */
  private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
    unaSentencia.setLong("NUM_NIT", attSolicitudConciliaPago.getNumNit());


    unaSentencia.setLong("IDE_PERSONA_SOLICITUD", attSolicitudConciliaPago.getIdePersonaSolicitud());


    unaSentencia.setTimestamp("FEC_SOLICITUD", attSolicitudConciliaPago.getFecSolicitud());


    unaSentencia.setLong("NUM_TOTAL_REGISTROS", attSolicitudConciliaPago.getNumTotalRegistros());


    unaSentencia.setInt("NUM_REGISTROS_PAGO", attSolicitudConciliaPago.getNumRegistrosPago());


    unaSentencia.setDouble("VAL_TOTAL", attSolicitudConciliaPago.getValTotal());


    unaSentencia.setTimestamp("FEC_PROCESO", attSolicitudConciliaPago.getFecProceso());


    unaSentencia.setInt("COD_ESTADO_PROCESO", attSolicitudConciliaPago.getCodEstadoProceso());


  }

  /**
   * Construye un objeto SolicitudConciliaPago con base en el data set.
   * @param resultado Contenedor de los datos
   * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
   */
  private void cargarSolicitudConciliaPago(IDDataSet resultado) throws SQLException {
    if (resultado.getNumeroFilas() == 0) {
      return;
    }
    resultado.primero();
    toSolicitudConciliaPago = completarSolicitudConciliaPago(resultado);
  }

  /**
   * Construye objetos SolicitudConciliaPago con base en el data set.
   * @param resultado Contenedor de los datos
   * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
   */
    private void cargarObjetosSolicitudConciliaPago(IDDataSet resultado) throws SQLException {
      objetosSolicitudConciliaPago = new ArrayList();
      toSolicitudConciliaPago = null;
      if (resultado.getNumeroFilas() == 0) {
        return;
      }
      resultado.primero();
      do {
        DSolicitudConciliaPagoTO objeto = completarSolicitudConciliaPago(resultado);
        objetosSolicitudConciliaPago.add(objeto);
      } while (resultado.siguiente());
      resultado.primero();
    }

  /**
   * Construye un objeto SolicitudConciliaPago con base en el data set.
   * El data set debe contener datos en la posici�n actual.
   * @param resultado Contenedor de los datos
   * @return Un SolicitudConciliaPago
   * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
   */
  private DSolicitudConciliaPagoTO completarSolicitudConciliaPago(IDDataSet resultado) throws SQLException {
    // Conformar el objeto PK
    DSolicitudConciliaPagoPKTO pk = new DSolicitudConciliaPagoPKTO();
      //java.lang.Long
pk.setIdeSolicitud(resultado.getLongWrapper("IDE_SOLICITUD"));

    // Conformar el objeto Att
    DSolicitudConciliaPagoAttTO att = new DSolicitudConciliaPagoAttTO();
      //java.lang.Long
if (resultado.getValorPorNombre("NUM_NIT") != null) {
      att.setNumNit(resultado.getLongWrapper("NUM_NIT"));
    }
      //java.lang.Long
att.setIdePersonaSolicitud(resultado.getLongWrapper("IDE_PERSONA_SOLICITUD"));
      //java.sql.Timestamp
att.setFecSolicitud((Timestamp)resultado.getValorPorNombre("FEC_SOLICITUD"));
      //java.lang.Long
if (resultado.getValorPorNombre("NUM_TOTAL_REGISTROS") != null) {
      att.setNumTotalRegistros(resultado.getLongWrapper("NUM_TOTAL_REGISTROS"));
    }
      //java.lang.Integer
if (resultado.getValorPorNombre("NUM_REGISTROS_PAGO") != null) {
      att.setNumRegistrosPago(resultado.getIntWrapper("NUM_REGISTROS_PAGO"));
    }
      //java.lang.Double
if (resultado.getValorPorNombre("VAL_TOTAL") != null) {
      att.setValTotal( new Double(((Number)resultado.getValorPorNombre("VAL_TOTAL")).doubleValue()));
    }
      //java.sql.Timestamp
att.setFecProceso((Timestamp)resultado.getValorPorNombre("FEC_PROCESO"));
      //java.lang.Integer
att.setCodEstadoProceso(resultado.getIntWrapper("COD_ESTADO_PROCESO"));
    // Conformar el TO
    DSolicitudConciliaPagoTO to = new DSolicitudConciliaPagoTO();
    to.setPK(pk);
    to.setAtt(att);
    return to;
  }

  /**
   * Devuelve el objeto SolicitudConciliaPago que se haya consultado.
   * @return Un objeto DSolicitudConciliaPagoTO
   */
  public DSolicitudConciliaPagoTO getSolicitudConciliaPago() {
    return toSolicitudConciliaPago;
  }

  /**
   * Devuelve la colecci�n de objetos SolicitudConciliaPago que se hayan consultado.
   * @return Un Collection con objetos DSolicitudConciliaPagoTO
   */
  public Collection getColeccionSolicitudConciliaPago() {
    return objetosSolicitudConciliaPago;
  }

  /**
   * Indica si el DAO es de ejecuci�n libre.
   * @return true si es de ejecuci�n libre; false de lo contrario
   */
  public boolean isEjecucionLibre() {
    return true;
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado por el administrador de persistencia.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
   * @todo COMPLEMENTAR
   */
  public boolean validar() throws DValidarExcepcion {
    DManipuladorMensaje manipulador;
    String operacion = null;
    Map parametros=new HashMap();
    switch (getTipoOperacion()) {
      case CREAR: operacion = "la creaci�n"; break;
      case ACTUALIZAR: operacion = "la actualizaci�n"; break;
      case ELIMINAR: operacion = "la eliminaci�n"; break;
      case CONSULTAR_POR_PK: operacion = "la consulta"; break;
    }

    if (operacion == null) {
      manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
      String mensaje = manipulador.getMensaje();
      throw new DValidarExcepcion(mensaje, mensaje);
    }

    if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
     	parametros.put(this.getClass().getName()+":validar:toSolicitudConciliaPago",toSolicitudConciliaPago);
     	parametros.put(this.getClass().getName()+":validar:pkSolicitudConciliaPago",pkSolicitudConciliaPago);
     	parametros.put(this.getClass().getName()+":validar:attSolicitudConciliaPago",attSolicitudConciliaPago);
	
		parametros.put(this.getClass().getName()+":validar:pkSolicitudConciliaPago.getIdeSolicitud()",pkSolicitudConciliaPago.getIdeSolicitud());
		parametros.put(this.getClass().getName()+":validar:attSolicitudConciliaPago.getIdePersonaSolicitud()",attSolicitudConciliaPago.getIdePersonaSolicitud());
		parametros.put(this.getClass().getName()+":validar:attSolicitudConciliaPago.getCodEstadoProceso()",attSolicitudConciliaPago.getCodEstadoProceso());
    }

    if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
    	parametros.put(this.getClass().getName()+":validar:pkSolicitudConciliaPago",pkSolicitudConciliaPago);
		parametros.put(this.getClass().getName()+":validar:pkSolicitudConciliaPago.getIdeSolicitud()",pkSolicitudConciliaPago.getIdeSolicitud());
    }

    
	validarParametros(operacion,parametros);
    return true;
  }
}