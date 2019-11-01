package co.gov.dian.muisca.diligenciamientomasivo.dao.etesa.impl.oracle;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DDAO;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DSentenciaSQL;
import co.gov.dian.muisca.arquitectura.interfaces.IDDataSet;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.dataset.DDataSet;
import co.gov.dian.muisca.arquitectura.mensajes.DManipuladorMensaje;
import co.gov.dian.muisca.arquitectura.mensajes.IDArqMensajes;
import co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.etesa.IDConstantesPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.dao.etesa.IDDAOPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSTO;



/**
 * Implementación de la lógica de las operaciones sobre la tabla EYS_DOCUMENTOS_BAN_SALIDA, para prevalidadores iniciales ETESA
 * 
 * @author nfontechar
 *
 */
public class DDAOPrevsABS extends DDAO implements IDDAOPrevsABS {
	
	
	/*
	 * ATRIBUTOS
	 */
	
	private boolean ejecucionLibre=false;
	private DPrevalidadorABSTO elTO=null;
	private DPrevalidadorABSPKTO laPK=null;
	private DPrevalidadorABSAttTO elAtt=null;
	private List<DPrevalidadorABSTO> losTOs=null;
	
	
	
	
	
	/*
	 * MÉTODOS PRIVADOS
	 */
	
	private void asignarValoresALaPK(DSentenciaSQL sentencia) throws SQLException{
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CONSULTAR_POR_PK:
		   case IDConstantesPrevsABS.CREAR:
		   case IDConstantesPrevsABS.ELIMINAR:
		   case IDConstantesPrevsABS.ACTUALIZAR:
			   sentencia.setLong("IDE_SOLICITUD", laPK.getIdeSolicitud());
			   sentencia.setLong("IDE_EXPEDIENTE", laPK.getIdeExpediente());
			   sentencia.setLong("NUM_NIT", laPK.getNumNIT());
			   break;
		   case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
			   sentencia.setLong("NUM_NIT", laPK.getNumNIT());
			   sentencia.setLong("TIPO_ARCHIVO", IDConstantesPrevsABS.TIPO_ARCHIVO);
			   break;
		   case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
			   sentencia.setLong("IDE_SOLICITUD", laPK.getIdeSolicitud());
			   sentencia.setLong("IDE_EXPEDIENTE", laPK.getIdeExpediente());
			   break;
		}/*fin de switch*/
	}/*fin de asignarValoresALaPK*/
	
	private void asignarValoresAlAtt(DSentenciaSQL sentencia) throws SQLException{
		sentencia.setLong("TIPO_ARCHIVO",elAtt.getTipoArchivo());
		sentencia.setLong("IDE_DOCUMENTO",elAtt.getIdeDocumento());
		sentencia.setInt("NUM_REPETICION",elAtt.getNumRepeticion());
		sentencia.setDate("FEC_GENERACION",elAtt.getFecGeneracion());
		sentencia.setLong("IDE_USUARIO_CAMBIO",elAtt.getIdeUsuarioCambio());
		sentencia.setInt("IDE_RECURSO",elAtt.getIdeRecurso());
	}/*fin de asignarValoresALAtt*/
	
	private IDDataSet consultarRegistros()throws SQLException{
		String clave=null;
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CONSULTAR_POR_PK:
			   clave="consPorPK";
			   break;
		   case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
			   clave="consPorNIT";
			   break;
		   case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
			   clave="consPorSolExp";
			   break;
		}/*fin de switch*/
		DSentenciaSQL sentencia = getSentenciaSQLPreparada(clave);
		asignarValoresALaPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado=sentencia.getDataSet();
		armarLosTOs(resultado);
		return resultado;
	}/*fin de consultarRegistros*/
	
	private int crearActualizarEliminarRegistros()throws SQLException{
		String clave=null;
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CREAR:
			   clave="crear";
			   break;
		   case IDConstantesPrevsABS.ACTUALIZAR:
			   clave="actualizar";
			   break;
		   case IDConstantesPrevsABS.ELIMINAR:
			   clave="eliminar";
			   break;
		}/*fin de switch*/
		DSentenciaSQL sentencia = getSentenciaSQLPreparada(clave);
		asignarValoresALaPK(sentencia);
		asignarValoresAlAtt(sentencia);
		sentencia.ejecutar();
		int resultado=sentencia.getRegistrosAfectados();
		return resultado;
	}/*fin de crearActualizarEliminarRegistros*/
	
	private DPrevalidadorABSAttTO armarElAtt(IDDataSet resultado) throws SQLException{
		DPrevalidadorABSAttTO att=new DPrevalidadorABSAttTO();
		BigDecimal bdTipoArchivo=(BigDecimal)resultado.getValorPorNombre("TIPO_ARCHIVO");
		BigDecimal bdIdeDocumento=(BigDecimal)resultado.getValorPorNombre("IDE_DOCUMENTO");
		BigDecimal bdNumRepeticion=(BigDecimal)resultado.getValorPorNombre("NUM_REPETICION");
		BigDecimal bdIdeUsuarioCambio=(BigDecimal)resultado.getValorPorNombre("IDE_USUARIO_CAMBIO");
		BigDecimal bdIdeRecurso=(BigDecimal)resultado.getValorPorNombre("IDE_RECURSO");
		Timestamp objFecGeneracion=(Timestamp) resultado.getValorPorNombre("FEC_GENERACION");
		att.setFecGeneracion(new Date(objFecGeneracion.getTime()));
		att.setIdeDocumento(bdIdeDocumento!=null ? bdIdeDocumento.longValue() : null);
		att.setIdeRecurso(bdIdeRecurso.intValue());
		att.setIdeUsuarioCambio(bdIdeUsuarioCambio.longValue());
		att.setNumRepeticion(bdNumRepeticion!=null ? bdNumRepeticion.intValue() : null);
		att.setTipoArchivo(bdTipoArchivo.longValue());
		return att;
	}/*fin de armarElAtt*/ 
	
	private DPrevalidadorABSPKTO armarLaPK(IDDataSet resultado) throws SQLException{
		DPrevalidadorABSPKTO pk=new DPrevalidadorABSPKTO();
		BigDecimal bdIdeSolicitud=(BigDecimal)resultado.getValorPorNombre("IDE_SOLICITUD");
		BigDecimal bdIdeExpediente=(BigDecimal)resultado.getValorPorNombre("IDE_EXPEDIENTE");
		BigDecimal bdNumNit=(BigDecimal)resultado.getValorPorNombre("NUM_NIT");
		pk.setIdeExpediente(bdIdeExpediente.longValue());
		pk.setIdeSolicitud(bdIdeSolicitud.longValue());
		pk.setNumNIT(bdNumNit.longValue());
		return pk;
	}/*fin de armarLaPK*/
	
	private void armarElTO(IDDataSet resultado) throws SQLException{
		elTO=new DPrevalidadorABSTO(armarLaPK(resultado),armarElAtt(resultado));
	}/*fin de armarElTO*/
	
	private void armarLosTOs(IDDataSet resultado) throws SQLException{
		losTOs = new ArrayList<DPrevalidadorABSTO>();
        if (resultado.getNumeroFilas() == 0) {
            return;
        }
        resultado.primero();
        do {
            armarElTO(resultado);
            losTOs.add(elTO);
        } while (resultado.siguiente());
        resultado.primero();
	}/*fin de armarLosTOs*/
	
	private void inicializarDAO(DPrevalidadorABSTO to){
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CREAR:
		   case IDConstantesPrevsABS.ACTUALIZAR:
			   this.elTO=to;
			   if(elTO!=null){
				   this.laPK=elTO.getPk();
				   this.elAtt=elTO.getAtt();
			   }/*fin de if*/
			   break;
		   case IDConstantesPrevsABS.ELIMINAR:
		   case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
		   case IDConstantesPrevsABS.CONSULTAR_POR_PK:
		   case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
			   this.elTO=to;
			   if(elTO!=null){
				   this.laPK=elTO.getPk();
			   }/*fin de if*/
			   break;
		}/*fin de switch*/
	}/*fin de inicializarDAO*/
	
	
	
	
	
	
	/*
	 * MÉTODOS INICIALIZAR*
	 */
	 
	 public void inicializarCrear(DPrevalidadorABSTO to){
		 setTipoOperacion(IDConstantesPrevsABS.CREAR);
		 inicializarDAO(to);
	 }/*fin de inicializarCrear*/
	 
	 public void inicializarActualizar(DPrevalidadorABSTO to){
		 setTipoOperacion(IDConstantesPrevsABS.ACTUALIZAR);
		 inicializarDAO(to);
	 }/*fin de inicializarActualizar*/
	 
	 public void inicializarEliminar(DPrevalidadorABSPKTO pk){
		 setTipoOperacion(IDConstantesPrevsABS.ELIMINAR);
		 inicializarDAO(new DPrevalidadorABSTO(pk, null));
	 }/*fin de inicializarEliminar*/
	 
	 public void inicializarConsultarPorPK(DPrevalidadorABSPKTO pk){
		 setTipoOperacion(IDConstantesPrevsABS.CONSULTAR_POR_PK);
		 inicializarDAO(new DPrevalidadorABSTO(pk, null));
	 }/*fin de inicializarConsultarPorPK*/
	 
	 public void inicializarConsultarPorNIT(DPrevalidadorABSPKTO pk){
		 setTipoOperacion(IDConstantesPrevsABS.CONSULTAR_POR_NIT);
		 inicializarDAO(new DPrevalidadorABSTO(pk, null));
	 }/*fin de inicializarConsultarPorNIT*/
	 
	 public void inicializarConsultarPorSolicitudYExpediente(DPrevalidadorABSPKTO pk){
		 setTipoOperacion(IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP);
		 inicializarDAO(new DPrevalidadorABSTO(pk, null));
	 }/*fin de inicializarConsultarPorSolicitudYExpediente*/
	
	
	 
	 
	 
	 
	 /*
	  * GETTERS y SETTERS
	  */
	 
	 public List<DPrevalidadorABSTO> getLosTOs() {
		return losTOs;
	 } 
	 
	 
	 
	 
	 
	 

	/*
	 * MÉTODOS HEREDADOS
	 */	
	 
	public IDDataSet consultar() throws SQLException {
		return consultarRegistros();
	}
	
	public int guardar() throws SQLException {
		return crearActualizarEliminarRegistros();
	}

	public int eliminar() throws SQLException {
		return crearActualizarEliminarRegistros();
	}

	public Map getSentenciasSQL() {
		Map m = new HashMap();
        StringBuffer sql = new StringBuffer();
        switch(getTipoOperacion()){
           case IDConstantesPrevsABS.CREAR:
        	   sql.append("INSERT INTO ")
        	      .append(IDConstantesPrevsABS.NOM_TABLA)
        	      .append(IDConstantesPrevsABS.NOM_CAMPOS_TABLA)
        	      .append(" VALUES")
        	      .append(IDConstantesPrevsABS.VAL_CAMPOS_TABLA);
        	   m.put("crear",sql.toString());
        	   break;
           case IDConstantesPrevsABS.ACTUALIZAR:
        	   sql.append("UPDATE ")
        	      .append(IDConstantesPrevsABS.NOM_TABLA)
        	      .append(" SET ")
        	      .append(IDConstantesPrevsABS.ACT_CAMPOS_TABLA)
        	      .append(" WHERE ")
        	      .append(IDConstantesPrevsABS.NOM_CAMPO_EXP+"="+IDConstantesPrevsABS.VAL_CAMPO_EXP)
        	      .append(" AND "+IDConstantesPrevsABS.NOM_CAMPO_SOL+"="+IDConstantesPrevsABS.VAL_CAMPO_SOL)
        	      .append(" AND "+IDConstantesPrevsABS.NOM_CAMPO_NIT+"="+IDConstantesPrevsABS.VAL_CAMPO_NIT);
        	   m.put("actualizar",sql.toString());
        	   break;
           case IDConstantesPrevsABS.ELIMINAR:
        	   sql.append("DELETE FROM ")
        	      .append(IDConstantesPrevsABS.NOM_TABLA)
        	      .append(" WHERE ")
        	      .append(IDConstantesPrevsABS.NOM_CAMPO_EXP+"="+IDConstantesPrevsABS.VAL_CAMPO_EXP)
        	      .append(" AND "+IDConstantesPrevsABS.NOM_CAMPO_SOL+"="+IDConstantesPrevsABS.VAL_CAMPO_SOL)
        	      .append(" AND "+IDConstantesPrevsABS.NOM_CAMPO_NIT+"="+IDConstantesPrevsABS.VAL_CAMPO_NIT);
        	   m.put("eliminar",sql.toString());
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_PK:
        	   sql.append("SELECT * FROM ")
        	      .append(IDConstantesPrevsABS.NOM_TABLA)
        	      .append(" WHERE ")
        	      .append(IDConstantesPrevsABS.NOM_CAMPO_EXP+"="+IDConstantesPrevsABS.VAL_CAMPO_EXP)
        	      .append(" AND "+IDConstantesPrevsABS.NOM_CAMPO_SOL+"="+IDConstantesPrevsABS.VAL_CAMPO_SOL)
        	      .append(" AND "+IDConstantesPrevsABS.NOM_CAMPO_NIT+"="+IDConstantesPrevsABS.VAL_CAMPO_NIT);
        	   m.put("consPorPK", sql.toString());
        	   ejecucionLibre=true;
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
        	   sql.append("SELECT * FROM ")
     	          .append(IDConstantesPrevsABS.NOM_TABLA)
     	          .append(" WHERE ")
     	          .append(IDConstantesPrevsABS.NOM_CAMPO_NIT+"="+IDConstantesPrevsABS.VAL_CAMPO_NIT)
     	          .append(" AND "+IDConstantesPrevsABS.NOM_CAMPO_TIPO+"="+IDConstantesPrevsABS.VAL_CAMPO_TIPO);
     	       m.put("consPorNIT", sql.toString());
     	       ejecucionLibre=true;
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
        	   sql.append("SELECT * FROM ")
     	          .append(IDConstantesPrevsABS.NOM_TABLA)
     	          .append(" WHERE ")
     	          .append(IDConstantesPrevsABS.NOM_CAMPO_EXP+"="+IDConstantesPrevsABS.VAL_CAMPO_EXP)
     	          .append(" AND "+IDConstantesPrevsABS.NOM_CAMPO_SOL+"="+IDConstantesPrevsABS.VAL_CAMPO_SOL);
     	       m.put("consPorSolExp", sql.toString());
     	       ejecucionLibre=true;
        	   break;
        }/*fin de switch*/
		return m;
	}

	public boolean isEjecucionLibre() {
		return ejecucionLibre;
	}

	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
        String operacion = null;
        Map pars=null;
        switch(getTipoOperacion()){
           case IDConstantesPrevsABS.CREAR:
        	   operacion="la creación";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:elTO", elTO);
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:elAtt", elAtt);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getFecGeneracion()", elAtt.getFecGeneracion());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeRecurso()", elAtt.getIdeRecurso());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeUsuarioCambio()", elAtt.getIdeUsuarioCambio());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getTipoArchivo()", elAtt.getTipoArchivo());        	   
        	   break;
           case IDConstantesPrevsABS.ACTUALIZAR:
        	   operacion="la actualización";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:elTO", elTO);
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:elAtt", elAtt);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getFecGeneracion()", elAtt.getFecGeneracion());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeRecurso()", elAtt.getIdeRecurso());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeUsuarioCambio()", elAtt.getIdeUsuarioCambio());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getTipoArchivo()", elAtt.getTipoArchivo());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeDocumento()", elAtt.getIdeDocumento());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getNumRepeticion()", elAtt.getNumRepeticion());
        	   break;
           case IDConstantesPrevsABS.ELIMINAR:
        	   operacion="la eliminación";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_PK:
        	   operacion="la consulta por llave primaria";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
        	   operacion="la consulta por NIT";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
        	   operacion="la consulta por solicitud y expediente";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   break;
        }/*fin de switch*/
        if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}/*fin de if*/
        validarParametros(operacion, pars);
		return true;
	}

}/*fin de class*/
