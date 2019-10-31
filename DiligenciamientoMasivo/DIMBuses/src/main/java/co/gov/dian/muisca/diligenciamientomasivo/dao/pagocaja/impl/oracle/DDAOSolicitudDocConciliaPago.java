/**
 * Republica de Colombia
 * Copyright (c) 2004 DirecciÃ³n de Impuestos y Aduanas Nacionales.
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
public class DDAOSolicitudDocConciliaPago extends DDAO implements IDDAOSolicitudDocConciliaPago {
  /** Colecciï¿½n de objetos DSolicitudDocConciliaPagoTO */
  private Collection objetosSolicitudDocConciliaPago;
  /** Objeto de transporte de SolicitudDocConciliaPago */
  private DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago;
  /** Llave primaria de SolicitudDocConciliaPago */
  private DSolicitudDocConciliaPagoPKTO pkSolicitudDocConciliaPago;
  /** Atributos de SolicitudDocConciliaPago */
  private DSolicitudDocConciliaPagoAttTO attSolicitudDocConciliaPago;

  /**
   * Inicializa la consulta por llave primaria.
   * @param pkSolicitudDocConciliaPago Llave primaria de SolicitudDocConciliaPago
   */
  public void inicializarConsultarPorPK(DSolicitudDocConciliaPagoPKTO pkSolicitudDocConciliaPago) {
    setTipoOperacion(CONSULTAR_POR_PK);
    this.pkSolicitudDocConciliaPago = pkSolicitudDocConciliaPago;
  }

  /**
   * Inicializa la creaciï¿½n de SolicitudDocConciliaPago.
   * @param toSolicitudDocConciliaPago Objeto de Transporte de SolicitudDocConciliaPago
   */
  public void inicializarCrear(DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago) {
    setTipoOperacion(CREAR);
    this.toSolicitudDocConciliaPago = toSolicitudDocConciliaPago;
    if (toSolicitudDocConciliaPago != null) {
      pkSolicitudDocConciliaPago = this.toSolicitudDocConciliaPago.getPK();
      attSolicitudDocConciliaPago = this.toSolicitudDocConciliaPago.getAtt();
    }
  }

  /**
   * Inicializa la actualizaciï¿½n de SolicitudDocConciliaPago.
   * @param toSolicitudDocConciliaPago Objeto de Transporte de SolicitudDocConciliaPago
   */
  public void inicializarActualizar(DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago) {
    setTipoOperacion(ACTUALIZAR);
    this.toSolicitudDocConciliaPago = toSolicitudDocConciliaPago;
    if (toSolicitudDocConciliaPago != null) {
      pkSolicitudDocConciliaPago = this.toSolicitudDocConciliaPago.getPK();
      attSolicitudDocConciliaPago = this.toSolicitudDocConciliaPago.getAtt();
    }
  }
  
  /**
   * Inicializa el merge(creación/actualización) de SolicitudDocConciliaPago.
   * @param toSolicitudDocConciliaPago Objeto de Transporte de SolicitudDocConciliaPago
   */
  public void inicializarMerge(DSolicitudDocConciliaPagoTO toSolicitudDocConciliaPago){
	  setTipoOperacion(MERGE);
	    this.toSolicitudDocConciliaPago = toSolicitudDocConciliaPago;
	    if (toSolicitudDocConciliaPago != null) {
	      pkSolicitudDocConciliaPago = this.toSolicitudDocConciliaPago.getPK();
	      attSolicitudDocConciliaPago = this.toSolicitudDocConciliaPago.getAtt();
	    }  
  }

  /**
   * Inicializa la eliminaciï¿½n de SolicitudDocConciliaPago.
   * @param pkSolicitudDocConciliaPago Llave primaria de SolicitudDocConciliaPago
   */
  public void inicializarEliminar(DSolicitudDocConciliaPagoPKTO pkSolicitudDocConciliaPago) {
    setTipoOperacion(ELIMINAR);
    this.pkSolicitudDocConciliaPago = pkSolicitudDocConciliaPago;
  }

  /**
   * Devuelve las sentencias sql a ejecutar, dependiendo del tipo de operaciï¿½n a realizar.
   * @return Un Map de Strings con la relaciï¿½n de sentencias sql
   */
  public Map getSentenciasSQL() {
    Map m = new HashMap();
    StringBuffer sql = new StringBuffer();
    switch (getTipoOperacion()) {
      case CREAR:
        sql.append("insert into DIL_DOCUMENTOS_CONCILIA_PAGO")
            .append(" (IDE_SOLICITUD, IDE_DOCUMENTO_CONCILIA, IDE_DECLARACION, NIT_DECLARACION, CAD_HAS, VAL_FORMA_PAGO, VAL_CONCEPTO, VALOR_RECAUDADO, FEC_LIMITE_PAGO, FEC_TRANSACCION, COD_ENT_RECAUDADORA, COD_SUCURSAL, COD_CAJERO, NUM_TRANSACCION, COD_HORARIO, FEC_PROCESO, COD_ESTADO_PROCESO)")
            .append(" VALUES (:IDE_SOLICITUD, :IDE_DOCUMENTO_CONCILIA, :IDE_DECLARACION, :NIT_DECLARACION, :CAD_HAS, :VAL_FORMA_PAGO, :VAL_CONCEPTO, :VALOR_RECAUDADO, :FEC_LIMITE_PAGO, :FEC_TRANSACCION, :COD_ENT_RECAUDADORA, :COD_SUCURSAL, :COD_CAJERO, :NUM_TRANSACCION, :COD_HORARIO, :FEC_PROCESO, :COD_ESTADO_PROCESO)");
        m.put("sentencia1", sql.toString());
        break;
      case ACTUALIZAR:
        sql.append("update DIL_DOCUMENTOS_CONCILIA_PAGO")
            .append(" set NIT_DECLARACION=:NIT_DECLARACION, CAD_HAS=:CAD_HAS, VAL_FORMA_PAGO=:VAL_FORMA_PAGO, VAL_CONCEPTO=:VAL_CONCEPTO, VALOR_RECAUDADO=:VALOR_RECAUDADO, FEC_LIMITE_PAGO=:FEC_LIMITE_PAGO, FEC_TRANSACCION=:FEC_TRANSACCION, COD_ENT_RECAUDADORA=:COD_ENT_RECAUDADORA, COD_SUCURSAL=:COD_SUCURSAL, COD_CAJERO=:COD_CAJERO, NUM_TRANSACCION=:NUM_TRANSACCION, COD_HORARIO=:COD_HORARIO, FEC_PROCESO=:FEC_PROCESO, COD_ESTADO_PROCESO=:COD_ESTADO_PROCESO")
            .append(" where IDE_SOLICITUD=:IDE_SOLICITUD and IDE_DOCUMENTO_CONCILIA=:IDE_DOCUMENTO_CONCILIA and IDE_DECLARACION=:IDE_DECLARACION");
        m.put("sentencia1", sql.toString());
        break;
      case ELIMINAR:
        sql.append("delete from DIL_DOCUMENTOS_CONCILIA_PAGO")
            .append(" where IDE_SOLICITUD=:IDE_SOLICITUD and IDE_DOCUMENTO_CONCILIA=:IDE_DOCUMENTO_CONCILIA and IDE_DECLARACION=:IDE_DECLARACION");
        m.put("sentencia1", sql.toString());
        break;
      case CONSULTAR_POR_PK:
        sql.append("select * from DIL_DOCUMENTOS_CONCILIA_PAGO")
            .append(" where IDE_SOLICITUD=:IDE_SOLICITUD and IDE_DOCUMENTO_CONCILIA=:IDE_DOCUMENTO_CONCILIA and IDE_DECLARACION=:IDE_DECLARACION");
        m.put("sentencia1", sql.toString());
        break;
      case MERGE:
          sql.append("MERGE INTO DIL_DOCUMENTOS_CONCILIA_PAGO DC ")
          .append(" USING dual d ")
          .append(" ON (DC.IDE_SOLICITUD = :IDE_SOLICITUD AND DC.IDE_DECLARACION = :IDE_DECLARACION) ")
          .append(" WHEN MATCHED THEN UPDATE SET ")
          .append(" IDE_DOCUMENTO_CONCILIA = :IDE_DOCUMENTO_CONCILIA,NIT_DECLARACION=:NIT_DECLARACION, CAD_HAS=:CAD_HAS, VAL_FORMA_PAGO=:VAL_FORMA_PAGO, VAL_CONCEPTO=:VAL_CONCEPTO, VALOR_RECAUDADO=:VALOR_RECAUDADO, FEC_LIMITE_PAGO=:FEC_LIMITE_PAGO, FEC_TRANSACCION=:FEC_TRANSACCION, COD_ENT_RECAUDADORA=:COD_ENT_RECAUDADORA, COD_SUCURSAL=:COD_SUCURSAL, COD_CAJERO=:COD_CAJERO, NUM_TRANSACCION=:NUM_TRANSACCION, COD_HORARIO=:COD_HORARIO, FEC_PROCESO=:FEC_PROCESO, COD_ESTADO_PROCESO=:COD_ESTADO_PROCESO")
          .append(" WHEN NOT MATCHED THEN INSERT ")
          .append("  ")
          .append(" (IDE_SOLICITUD, IDE_DOCUMENTO_CONCILIA, IDE_DECLARACION, NIT_DECLARACION, CAD_HAS, VAL_FORMA_PAGO, VAL_CONCEPTO, VALOR_RECAUDADO, FEC_LIMITE_PAGO, FEC_TRANSACCION, COD_ENT_RECAUDADORA, COD_SUCURSAL, COD_CAJERO, NUM_TRANSACCION, COD_HORARIO, FEC_PROCESO, COD_ESTADO_PROCESO)")
          .append(" VALUES (:IDE_SOLICITUD, :IDE_DOCUMENTO_CONCILIA, :IDE_DECLARACION, :NIT_DECLARACION, :CAD_HAS, :VAL_FORMA_PAGO, :VAL_CONCEPTO, :VALOR_RECAUDADO, :FEC_LIMITE_PAGO, :FEC_TRANSACCION, :COD_ENT_RECAUDADORA, :COD_SUCURSAL, :COD_CAJERO, :NUM_TRANSACCION, :COD_HORARIO, :FEC_PROCESO, :COD_ESTADO_PROCESO)");
          m.put("sentencia1", sql.toString());
          break;  
    }
    return m;
  }

  /**
   * Guarda los datos de SolicitudDocConciliaPago.
   * @return @return Un int con el nï¿½mero de registros guardados
   * @throws SQLException Si ocurre un error de base de datos al guardar
   */
  public int guardar() throws SQLException {
    switch (getTipoOperacion()) {
      case CREAR:
        return crear();
      case ACTUALIZAR:
        return actualizar();
      case MERGE:
          return merge();
    }
    return -1;
  }

  /**
   * Elimina registros de SolicitudDocConciliaPago.
   * @return Un int con el nï¿½mero de registros eliminados
   * @throws SQLException Si ocurre un error de base de datos al eliminar
   */
   public int eliminar() throws SQLException {
     DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
     asignarValoresPK(sentencia);
     sentencia.ejecutar();
     return sentencia.getRegistrosAfectados();
   }

  /**
   * Consulta los datos de SolicitudDocConciliaPago.
   * @return Un IDDataSet con la colecciï¿½n de registros encontrados
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
   * Crea un registro de SolicitudDocConciliaPago.
   * @return Un int con el nï¿½mero de registros creados
   * @throws SQLException Si ocurre un error de base de datos al crear
   */
  private int crear() throws SQLException {
    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
    asignarValoresObjeto(sentencia);
    asignarValoresPK(sentencia);
    sentencia.ejecutar();
    int resultado = sentencia.getRegistrosAfectados();
    if (resultado <= 0) {
      throw new SQLException("No se ha creado ningï¿½n registro");
    }
    if (resultado > 1) {
      throw new SQLException("Se intentï¿½ crear mï¿½s de un registro");
    }
    return resultado;
  }

  /**
   * Actualiza los datos de SolicitudDocConciliaPago.
   * @return Un int con el nï¿½mero de registros actualizados
   * @throws SQLException Si ocurre un error de base de datos al actualizar
   */
  private int actualizar() throws SQLException {
    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
    asignarValoresObjeto(sentencia);
    asignarValoresPK(sentencia);
    sentencia.ejecutar();
    int resultado = sentencia.getRegistrosAfectados();
    if (resultado <= 0) {
      throw new SQLException("No se ha actualizado ningï¿½n registro");
    }
    if (resultado > 1) {
      throw new SQLException("Se intentï¿½ actualizar mï¿½s de un registro");
    }
    return resultado;
  }
  
  /**
   * Actualiza los datos de SolicitudDocConciliaPago.
   * @return Un int con el nï¿½mero de registros actualizados
   * @throws SQLException Si ocurre un error de base de datos al actualizar
   */
  private int merge() throws SQLException {
    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
    asignarValoresObjeto(sentencia);
    asignarValoresPK(sentencia);
    sentencia.ejecutar();
    int resultado = sentencia.getRegistrosAfectados();
    if (resultado <= 0) {
      throw new SQLException("No se ha actualizado ningún registro");
    }
    if (resultado > 1) {
      throw new SQLException("Se intentó actualizar más de un registro");
    }
    return resultado;
  }

  /**
   * Actualiza los datos de SolicitudDocConciliaPago.
   * @return Un IDDataSet con la colecciï¿½n de registros encontrados
   * @throws SQLException Si ocurre un error de base de datos al consultar
   */
  private IDDataSet consultarPorPK() throws SQLException {
    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
    asignarValoresPK(sentencia);
    sentencia.ejecutar();
    DDataSet resultado = sentencia.getDataSet();
    cargarSolicitudDocConciliaPago(resultado);
    return resultado;
  }

  /**
   * Asigna los valores para pk en una sentencia SQL.
   * @param unaSentencia Sentencia para asignaciï¿½n
   * @throws SQLException Si ocurre un error al asignar los valores
   */
  private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
    unaSentencia.setLong("IDE_SOLICITUD", pkSolicitudDocConciliaPago.getIdeSolicitud());
    unaSentencia.setLong("IDE_DOCUMENTO_CONCILIA", pkSolicitudDocConciliaPago.getIdeDocumentoConcilia());
    unaSentencia.setLong("IDE_DECLARACION", pkSolicitudDocConciliaPago.getIdeDeclaracion());
  }

  /**
   * Asigna todos los valores de un objeto.
   * @param unaSentencia Sentencia para asignaciï¿½n
   * @throws SQLException Si ocurre un error al asignar los valores
   */
  private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
    unaSentencia.setLong("NIT_DECLARACION", attSolicitudDocConciliaPago.getNitDeclaracion());


    unaSentencia.setString("CAD_HAS", attSolicitudDocConciliaPago.getCadHas());


    unaSentencia.setInt("VAL_FORMA_PAGO", attSolicitudDocConciliaPago.getValFormaPago());


    unaSentencia.setInt("VAL_CONCEPTO", attSolicitudDocConciliaPago.getValConcepto());


    unaSentencia.setDouble("VALOR_RECAUDADO", attSolicitudDocConciliaPago.getValorRecaudado());


    unaSentencia.setInt("FEC_LIMITE_PAGO", attSolicitudDocConciliaPago.getFecLimitePago());


    unaSentencia.setTimestamp("FEC_TRANSACCION", attSolicitudDocConciliaPago.getFecTransaccion());


    unaSentencia.setInt("COD_ENT_RECAUDADORA", attSolicitudDocConciliaPago.getCodEntRecaudadora());


    unaSentencia.setInt("COD_SUCURSAL", attSolicitudDocConciliaPago.getCodSucursal());


    unaSentencia.setInt("COD_CAJERO", attSolicitudDocConciliaPago.getCodCajero());


    unaSentencia.setString("NUM_TRANSACCION", attSolicitudDocConciliaPago.getNumTransaccion());


    unaSentencia.setInt("COD_HORARIO", attSolicitudDocConciliaPago.getCodHorario());


    unaSentencia.setTimestamp("FEC_PROCESO", attSolicitudDocConciliaPago.getFecProceso());


    unaSentencia.setInt("COD_ESTADO_PROCESO", attSolicitudDocConciliaPago.getCodEstadoProceso());


  }

  /**
   * Construye un objeto SolicitudDocConciliaPago con base en el data set.
   * @param resultado Contenedor de los datos
   * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
   */
  private void cargarSolicitudDocConciliaPago(IDDataSet resultado) throws SQLException {
    if (resultado.getNumeroFilas() == 0) {
      return;
    }
    resultado.primero();
    toSolicitudDocConciliaPago = completarSolicitudDocConciliaPago(resultado);
  }

  /**
   * Construye objetos SolicitudDocConciliaPago con base en el data set.
   * @param resultado Contenedor de los datos
   * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
   */
    private void cargarObjetosSolicitudDocConciliaPago(IDDataSet resultado) throws SQLException {
      objetosSolicitudDocConciliaPago = new ArrayList();
      toSolicitudDocConciliaPago = null;
      if (resultado.getNumeroFilas() == 0) {
        return;
      }
      resultado.primero();
      do {
        DSolicitudDocConciliaPagoTO objeto = completarSolicitudDocConciliaPago(resultado);
        objetosSolicitudDocConciliaPago.add(objeto);
      } while (resultado.siguiente());
      resultado.primero();
    }

  /**
   * Construye un objeto SolicitudDocConciliaPago con base en el data set.
   * El data set debe contener datos en la posiciï¿½n actual.
   * @param resultado Contenedor de los datos
   * @return Un SolicitudDocConciliaPago
   * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
   */
  private DSolicitudDocConciliaPagoTO completarSolicitudDocConciliaPago(IDDataSet resultado) throws SQLException {
    // Conformar el objeto PK
    DSolicitudDocConciliaPagoPKTO pk = new DSolicitudDocConciliaPagoPKTO();
      //java.lang.Long
pk.setIdeSolicitud(resultado.getLongWrapper("IDE_SOLICITUD"));
      //java.lang.Long
pk.setIdeDocumentoConcilia(resultado.getLongWrapper("IDE_DOCUMENTO_CONCILIA"));
      //java.lang.Long
pk.setIdeDeclaracion(resultado.getLongWrapper("IDE_DECLARACION"));

    // Conformar el objeto Att
    DSolicitudDocConciliaPagoAttTO att = new DSolicitudDocConciliaPagoAttTO();
      //java.lang.Long
if (resultado.getValorPorNombre("NIT_DECLARACION") != null) {
      att.setNitDeclaracion(resultado.getLongWrapper("NIT_DECLARACION"));
    }
      //java.lang.String
att.setCadHas(resultado.getString("CAD_HAS"));
      //java.lang.Integer
if (resultado.getValorPorNombre("VAL_FORMA_PAGO") != null) {
      att.setValFormaPago(resultado.getIntWrapper("VAL_FORMA_PAGO"));
    }
      //java.lang.Integer
if (resultado.getValorPorNombre("VAL_CONCEPTO") != null) {
      att.setValConcepto(resultado.getIntWrapper("VAL_CONCEPTO"));
    }
      //java.lang.Double
if (resultado.getValorPorNombre("VALOR_RECAUDADO") != null) {
      att.setValorRecaudado( new Double(((Number)resultado.getValorPorNombre("VALOR_RECAUDADO")).doubleValue()));
    }
      //java.lang.Integer
if (resultado.getValorPorNombre("FEC_LIMITE_PAGO") != null) {
      att.setFecLimitePago(resultado.getIntWrapper("FEC_LIMITE_PAGO"));
    }
      //java.sql.Timestamp
att.setFecTransaccion((Timestamp)resultado.getValorPorNombre("FEC_TRANSACCION"));
      //java.lang.Integer
if (resultado.getValorPorNombre("COD_ENT_RECAUDADORA") != null) {
      att.setCodEntRecaudadora(resultado.getIntWrapper("COD_ENT_RECAUDADORA"));
    }
      //java.lang.Integer
if (resultado.getValorPorNombre("COD_SUCURSAL") != null) {
      att.setCodSucursal(resultado.getIntWrapper("COD_SUCURSAL"));
    }
      //java.lang.Integer
if (resultado.getValorPorNombre("COD_CAJERO") != null) {
      att.setCodCajero(resultado.getIntWrapper("COD_CAJERO"));
    }
      //java.lang.String
att.setNumTransaccion(resultado.getString("NUM_TRANSACCION"));
      //java.lang.Integer
if (resultado.getValorPorNombre("COD_HORARIO") != null) {
      att.setCodHorario(resultado.getIntWrapper("COD_HORARIO"));
    }
      //java.sql.Timestamp
att.setFecProceso((Timestamp)resultado.getValorPorNombre("FEC_PROCESO"));
      //java.lang.Integer
att.setCodEstadoProceso(resultado.getIntWrapper("COD_ESTADO_PROCESO"));
    // Conformar el TO
    DSolicitudDocConciliaPagoTO to = new DSolicitudDocConciliaPagoTO();
    to.setPK(pk);
    to.setAtt(att);
    return to;
  }

  /**
   * Devuelve el objeto SolicitudDocConciliaPago que se haya consultado.
   * @return Un objeto DSolicitudDocConciliaPagoTO
   */
  public DSolicitudDocConciliaPagoTO getSolicitudDocConciliaPago() {
    return toSolicitudDocConciliaPago;
  }

  /**
   * Devuelve la colecciï¿½n de objetos SolicitudDocConciliaPago que se hayan consultado.
   * @return Un Collection con objetos DSolicitudDocConciliaPagoTO
   */
  public Collection getColeccionSolicitudDocConciliaPago() {
    return objetosSolicitudDocConciliaPago;
  }

  /**
   * Indica si el DAO es de ejecuciï¿½n libre.
   * @return true si es de ejecuciï¿½n libre; false de lo contrario
   */
  public boolean isEjecucionLibre() {
    return true;
  }

  /**
   * Mï¿½todo para validar los parï¿½metros inicializados, invocado por el administrador de persistencia.
   * @return true si los parï¿½metros son vï¿½lidos; false de lo contrario
   * @throws DValidarExcepcion Si los parï¿½metros no son vï¿½lidos
   * @todo COMPLEMENTAR
   */
  public boolean validar() throws DValidarExcepcion {
    DManipuladorMensaje manipulador;
    String operacion = null;
    Map parametros=new HashMap();
    switch (getTipoOperacion()) {
      case CREAR: operacion = "la creaciï¿½n"; break;
      case ACTUALIZAR: operacion = "la actualizaciï¿½n"; break;
      case MERGE: operacion = "la actualizaciï¿½n"; break;
      case ELIMINAR: operacion = "la eliminaciï¿½n"; break;
      case CONSULTAR_POR_PK: operacion = "la consulta"; break;
    }

    if (operacion == null) {
      manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
      String mensaje = manipulador.getMensaje();
      throw new DValidarExcepcion(mensaje, mensaje);
    }

    if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR || tipoOperacion == MERGE) {
     	parametros.put(this.getClass().getName()+":validar:toSolicitudDocConciliaPago",toSolicitudDocConciliaPago);
     	parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago",pkSolicitudDocConciliaPago);
     	parametros.put(this.getClass().getName()+":validar:attSolicitudDocConciliaPago",attSolicitudDocConciliaPago);
	
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeSolicitud()",pkSolicitudDocConciliaPago.getIdeSolicitud());
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeDocumentoConcilia()",pkSolicitudDocConciliaPago.getIdeDocumentoConcilia());
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeDeclaracion()",pkSolicitudDocConciliaPago.getIdeDeclaracion());
		parametros.put(this.getClass().getName()+":validar:attSolicitudDocConciliaPago.getCodEstadoProceso()",attSolicitudDocConciliaPago.getCodEstadoProceso());
    }

    if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
    	parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago",pkSolicitudDocConciliaPago);
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeSolicitud()",pkSolicitudDocConciliaPago.getIdeSolicitud());
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeDocumentoConcilia()",pkSolicitudDocConciliaPago.getIdeDocumentoConcilia());
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDocConciliaPago.getIdeDeclaracion()",pkSolicitudDocConciliaPago.getIdeDeclaracion());
    }

    
	validarParametros(operacion,parametros);
    return true;
  }
}