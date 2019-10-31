/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle;

import java.sql.*;
import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.dataset.*;
import co.gov.dian.muisca.arquitectura.mensajes.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAODeclaracionContribucionParafiscalPatrocinador extends DDAO implements IDDAODeclaracionContribucionParafiscalPatrocinador {
	/** colección de objetos DDeclaracionContribucionParafiscalPatrocinadorTO */
	private Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> objetosDeclaracionContribucionParafiscalPatrocinador;
	/** Objeto de transporte de DeclaracionContribucionParafiscalPatrocinador */
	private DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador;
	/** Llave primaria de DeclaracionContribucionParafiscalPatrocinador */
	private DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador;
	/** Atributos de DeclaracionContribucionParafiscalPatrocinador */
	private DDeclaracionContribucionParafiscalPatrocinadorAttTO attDeclaracionContribucionParafiscalPatrocinador;
	/** Llave primaria de DeclaracionContribucionParafiscal */
	private DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDeclaracionContribucionParafiscalPatrocinador Llave primaria de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializarConsultarPorPK(DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkDeclaracionContribucionParafiscalPatrocinador = pkDeclaracionContribucionParafiscalPatrocinador;
	}

	/**
	 * Inicializa la consulta por DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	public void inicializarConsultarPorDeclaracionContribucionParafiscal(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal) {
		setTipoOperacion(CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL);
		this.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
	}
	
	 /**
	 * Inicializa la eliminación por DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	public void inicializarEliminarPorDeclaracionContribucionParafiscal(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal) {
		setTipoOperacion(ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL);
		this.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
	}
	
	/**
	 * Inicializa la creaciónn de DeclaracionContribucionParafiscalPatrocinador.
	 * @param toDeclaracionContribucionParafiscalPatrocinador Objeto de Transporte de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializarCrear(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador) {
		setTipoOperacion(CREAR);
		this.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
		if (toDeclaracionContribucionParafiscalPatrocinador != null) {
			pkDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getPK();
			attDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getAtt();
		}
	}
	
	/**
	 * Inicializa la creación de DeclaracionContribucionParafiscalPatrocinador con colección.
	 * @param tosDeclaracionContribucionParafiscalPatrocinador Collection de Objetos de Transporte de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializarCrear(Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> tosDeclaracionContribucionParafiscalPatrocinador) {
		setTipoOperacion(CREAR_CON_COLECCION);
		this.objetosDeclaracionContribucionParafiscalPatrocinador = tosDeclaracionContribucionParafiscalPatrocinador;
	}
	/**
	 * Inicializa la actualización de DeclaracionContribucionParafiscalPatrocinador.
	 * @param toDeclaracionContribucionParafiscalPatrocinador Objeto de Transporte de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializarActualizar(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador) {
		setTipoOperacion(ACTUALIZAR);
		this.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
		if (toDeclaracionContribucionParafiscalPatrocinador != null) {
			pkDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getPK();
			attDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getAtt();
		}
	}

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscalPatrocinador.
	 * @param pkDeclaracionContribucionParafiscalPatrocinador Llave primaria de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializarEliminar(DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador) {
		setTipoOperacion(ELIMINAR);
		this.pkDeclaracionContribucionParafiscalPatrocinador = pkDeclaracionContribucionParafiscalPatrocinador;
	}

	/**
	 * Inicializa la consulta genérica de DeclaracionContribucionParafiscalPatrocinador.
	 * @param attDeclaracionContribucionParafiscalPatrocinador Atributos de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
		if (toDeclaracionContribucionParafiscalPatrocinador != null) {
			pkDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getPK();
			attDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getAtt();
		}
	}

	/**
	 * Devuelve las sentencias sql a ejecutar, dependiendo del tipo de operación a realizar.
	 * @return Un Map de Strings con la relación de sentencias sql
	 */
	public Map<String, String> getSentenciasSQL() {
		Map<String, String> m = new HashMap<String, String>();
		StringBuffer sql = new StringBuffer();
		switch (getTipoOperacion()) {
			case CREAR:
			case CREAR_CON_COLECCION:
				sql.append("insert into DIL_DECLA_CONTRIB_PATROCINA")
					.append(" (IDE_DOCUMENTO_CONT_PARAF, NUM_REPETICION_DOC_CONT_PARF, IDE_OCURRENCIA, IDE_ITEM, NUM_EVENTO, NUM_NIT, NUM_DV, VAL_RAZON_SOCIAL, NUM_BOLETAS_PATROCINIO, VAL_UBICACION_LOCALIDAD, VAL_INDIVIDUAL_BOLETA, IDE_USUARIO_CAMBIO, FEC_CAMBIO)")
					.append(" VALUES (:IDE_DOCUMENTO_CONT_PARAF, :NUM_REPETICION_DOC_CONT_PARF, :IDE_OCURRENCIA, :IDE_ITEM, :NUM_EVENTO, :NUM_NIT, :NUM_DV, :VAL_RAZON_SOCIAL, :NUM_BOLETAS_PATROCINIO, :VAL_UBICACION_LOCALIDAD, :VAL_INDIVIDUAL_BOLETA, :IDE_USUARIO_CAMBIO, :FEC_CAMBIO)");
				m.put("sentencia1", sql.toString());
				break;
			case ACTUALIZAR:
				sql.append("update DIL_DECLA_CONTRIB_PATROCINA")
					.append(" set NUM_EVENTO=:NUM_EVENTO, NUM_NIT=:NUM_NIT, NUM_DV=:NUM_DV, VAL_RAZON_SOCIAL=:VAL_RAZON_SOCIAL, NUM_BOLETAS_PATROCINIO=:NUM_BOLETAS_PATROCINIO, VAL_UBICACION_LOCALIDAD=:VAL_UBICACION_LOCALIDAD, VAL_INDIVIDUAL_BOLETA=:VAL_INDIVIDUAL_BOLETA, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO, FEC_CAMBIO=:FEC_CAMBIO")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF and IDE_OCURRENCIA=:IDE_OCURRENCIA and IDE_ITEM=:IDE_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR:
				sql.append("delete from DIL_DECLA_CONTRIB_PATROCINA")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF and IDE_OCURRENCIA=:IDE_OCURRENCIA and IDE_ITEM=:IDE_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL:
				sql.append("delete from DIL_DECLA_CONTRIB_PATROCINA")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF ");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_PK:
				sql.append("select * from DIL_DECLA_CONTRIB_PATROCINA")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF and IDE_OCURRENCIA=:IDE_OCURRENCIA and IDE_ITEM=:IDE_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTA_GENERICA:
				sql.append("select * from DIL_DECLA_CONTRIB_PATROCINA where ")
					.append(generarFiltroGenerico());
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL:
				sql.append("select * from DIL_DECLA_CONTRIB_PATROCINA")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF");
				m.put("sentencia1", sql.toString());
				break;
		}
		return m;
	}


	/**
	 * obtenerConsultaGenerica
	 *
	 * @return StringBuffer
	 */
	private String generarFiltroGenerico() {
		StringBuffer condiciones = new StringBuffer();
		String y = " AND ";
		boolean append = false;

		if (pkDeclaracionContribucionParafiscalPatrocinador != null) {

			if (pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF");
				append = true;

			}
			if (pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF");
				append = true;

			}
			if (pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_OCURRENCIA=:IDE_OCURRENCIA");
				append = true;

			}
			if (pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_ITEM=:IDE_ITEM");
				append = true;

			}
		}

		if (attDeclaracionContribucionParafiscalPatrocinador != null) {

			if (attDeclaracionContribucionParafiscalPatrocinador.getNumEvento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_EVENTO=:NUM_EVENTO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getNumNit() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_NIT=:NUM_NIT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getNumDv() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_DV=:NUM_DV");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getValRazonSocial() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_RAZON_SOCIAL=:VAL_RAZON_SOCIAL");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getNumBoletasPatrocinio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOLETAS_PATROCINIO=:NUM_BOLETAS_PATROCINIO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getValUbicacionLocalidad() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_UBICACION_LOCALIDAD=:VAL_UBICACION_LOCALIDAD");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getValIndividualBoleta() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_INDIVIDUAL_BOLETA=:VAL_INDIVIDUAL_BOLETA");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getIdeUsuarioCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getFecCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_CAMBIO=:FEC_CAMBIO");
				append = true;

			}
		}

		return condiciones.toString();
	}

	/**
	 * Asigna los valores no nulos de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresGenericos(DSentenciaSQL unaSentencia) throws SQLException {
		if (pkDeclaracionContribucionParafiscalPatrocinador != null) {
			if (pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf());
			}
			if (pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf() != null) {
				unaSentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf());
			}
			if (pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia() != null) {
				unaSentencia.setInt("IDE_OCURRENCIA", pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia());
			}
			if (pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem() != null) {
				unaSentencia.setInt("IDE_ITEM", pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem());
			}
		}

		if (attDeclaracionContribucionParafiscalPatrocinador != null) {
			if (attDeclaracionContribucionParafiscalPatrocinador.getNumEvento() != null) {
				unaSentencia.setString("NUM_EVENTO", attDeclaracionContribucionParafiscalPatrocinador.getNumEvento());
			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getNumNit() != null) {
				unaSentencia.setLong("NUM_NIT", attDeclaracionContribucionParafiscalPatrocinador.getNumNit());
			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getNumDv() != null) {
				unaSentencia.setInt("NUM_DV", attDeclaracionContribucionParafiscalPatrocinador.getNumDv());
			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getValRazonSocial() != null) {
				unaSentencia.setString("VAL_RAZON_SOCIAL", attDeclaracionContribucionParafiscalPatrocinador.getValRazonSocial());
			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getNumBoletasPatrocinio() != null) {
				unaSentencia.setInt("NUM_BOLETAS_PATROCINIO", attDeclaracionContribucionParafiscalPatrocinador.getNumBoletasPatrocinio());
			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getValUbicacionLocalidad() != null) {
				unaSentencia.setString("VAL_UBICACION_LOCALIDAD", attDeclaracionContribucionParafiscalPatrocinador.getValUbicacionLocalidad());
			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getValIndividualBoleta() != null) {
				unaSentencia.setLong("VAL_INDIVIDUAL_BOLETA", attDeclaracionContribucionParafiscalPatrocinador.getValIndividualBoleta());
			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getIdeUsuarioCambio() != null) {
				unaSentencia.setLong("IDE_USUARIO_CAMBIO", attDeclaracionContribucionParafiscalPatrocinador.getIdeUsuarioCambio());
			}
			if (attDeclaracionContribucionParafiscalPatrocinador.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attDeclaracionContribucionParafiscalPatrocinador.getFecCambio());
			}
		}
	}

	/**
	 * Guarda los datos de DeclaracionContribucionParafiscalPatrocinador.
	 * @return @return Un int con el número de registros guardados
	 * @throws SQLException Si ocurre un error de base de datos al guardar
	 */
	public int guardar() throws SQLException {
		switch (getTipoOperacion()) {
			case CREAR:
				return crear();
			case CREAR_CON_COLECCION:
		          return crearConColeccion();
			case ACTUALIZAR:
				return actualizar();
		}
		return -1;
	}

	/**
	 * Elimina registros de DeclaracionContribucionParafiscalPatrocinador.
	 * @return Un int con el número de registros eliminados
	 * @throws SQLException Si ocurre un error de base de datos al eliminar
	 */
	public int eliminar() throws SQLException {
		if(tipoOperacion == ELIMINAR) {
			DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
			asignarValoresPK(sentencia);
			sentencia.ejecutar();
			return sentencia.getRegistrosAfectados();
		} else if(tipoOperacion == ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL) {
			eliminarPorDeclaracionContribucionParafiscal();
		}
		return 1;
	}

	/**
	 * Consulta los datos de DeclaracionContribucionParafiscalPatrocinador.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	public IDDataSet consultar() throws SQLException {
		switch (getTipoOperacion()) {
			case CONSULTAR_POR_PK:
				return consultarPorPK();
			case CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL:
				return consultarPorDeclaracionContribucionParafiscal();
			case CONSULTA_GENERICA:
				return consultaGenerica();
		}
		return null;
	}

	/**
	 * Crea un registro de DeclaracionContribucionParafiscalPatrocinador.
	 * @return Un int con el número de registros creados
	 * @throws SQLException Si ocurre un error de base de datos al crear
	 */
	private int crear() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresObjeto(sentencia);
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		int resultado = sentencia.getRegistrosAfectados();
		if (resultado <= 0) {
			throw new SQLException("No se ha creado ningún registro");
		}
		if (resultado > 1) {
			throw new SQLException("Se intentó crear más de un registro");
		}
		return resultado;
	}

	/**
     * Crea los registros de DeclaracionContribucionParafiscalPatrocinador con colección.
     * @return Un int con el número de registros creados
     * @throws SQLException Si ocurre un error de base de datos al crear
     */
    private int crearConColeccion() throws SQLException {
        DSentenciaSQLBatch sentencia = getSentenciaSQLBatch("sentencia1");
        asignarValoresBatch(sentencia);
        sentencia.ejecutar();
        int resultado = sentencia.getRegistrosAfectados();
        return resultado;
    }
    
	/**
	 * Actualiza los datos de DeclaracionContribucionParafiscalPatrocinador.
	 * @return Un int con el número de registros actualizados
	 * @throws SQLException Si ocurre un error de base de datos al actualizar
	 */
	private int actualizar() throws SQLException {
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
	 * Actualiza los datos de DeclaracionContribucionParafiscalPatrocinador.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarDeclaracionContribucionParafiscalPatrocinador(resultado);
		return resultado;
	}
	
	private void eliminarPorDeclaracionContribucionParafiscal() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
		sentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		sentencia.ejecutar();		
	}
	
	/**
	 * Consulta genérica de los datos de DeclaracionContribucionParafiscalPatrocinador.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosDeclaracionContribucionParafiscalPatrocinador(resultado);
		return resultado;
	}

	/**
	 * Consulta por DeclaracionContribucionParafiscal.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorDeclaracionContribucionParafiscal() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
		sentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosDeclaracionContribucionParafiscalPatrocinador(resultado);
		return resultado;
	}

	/**
	   * Asigna todos los valores de una lista de DDocumentoSoporteTO.
	   * @param unaSentencia Sentencia para asignación
	   * @throws SQLException Si ocurre un error al asignar los valores
	   */
	  private void asignarValoresBatch(DSentenciaSQLBatch unaSentenciaBatch) throws SQLException {
	      for(DDeclaracionContribucionParafiscalPatrocinadorTO itemTO : objetosDeclaracionContribucionParafiscalPatrocinador){
	    	  	unaSentenciaBatch.setLong("IDE_DOCUMENTO_CONT_PARAF", itemTO.getPK().getIdeDocumentoContParaf());
	    	  	unaSentenciaBatch.setInt("NUM_REPETICION_DOC_CONT_PARF", itemTO.getPK().getNumRepeticionDocContParf());
	    	  	unaSentenciaBatch.setInt("IDE_OCURRENCIA", itemTO.getPK().getIdeOcurrencia());
	    	  	unaSentenciaBatch.setInt("IDE_ITEM", itemTO.getPK().getIdeItem());
	    	  	unaSentenciaBatch.setString("NUM_EVENTO", itemTO.getAtt().getNumEvento());
	    	  	unaSentenciaBatch.setLong("NUM_NIT", itemTO.getAtt().getNumNit());
	    	  	unaSentenciaBatch.setInt("NUM_DV", itemTO.getAtt().getNumDv());
	    	  	unaSentenciaBatch.setString("VAL_RAZON_SOCIAL", itemTO.getAtt().getValRazonSocial());
	    	  	unaSentenciaBatch.setInt("NUM_BOLETAS_PATROCINIO", itemTO.getAtt().getNumBoletasPatrocinio());
	    	  	unaSentenciaBatch.setString("VAL_UBICACION_LOCALIDAD", itemTO.getAtt().getValUbicacionLocalidad());
	    	  	unaSentenciaBatch.setLong("VAL_INDIVIDUAL_BOLETA", itemTO.getAtt().getValIndividualBoleta());
	    	  	unaSentenciaBatch.setLong("IDE_USUARIO_CAMBIO", itemTO.getAtt().getIdeUsuarioCambio());
	    	  	unaSentenciaBatch.setTimestamp("FEC_CAMBIO", itemTO.getAtt().getFecCambio());
		        unaSentenciaBatch.nuevoRegistro();
	      }
	  }
	
	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf());
		unaSentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf());
		unaSentencia.setInt("IDE_OCURRENCIA", pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia());
		unaSentencia.setInt("IDE_ITEM", pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem());
	}

	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setString("NUM_EVENTO", attDeclaracionContribucionParafiscalPatrocinador.getNumEvento());
		unaSentencia.setLong("NUM_NIT", attDeclaracionContribucionParafiscalPatrocinador.getNumNit());
		unaSentencia.setInt("NUM_DV", attDeclaracionContribucionParafiscalPatrocinador.getNumDv());
		unaSentencia.setString("VAL_RAZON_SOCIAL", attDeclaracionContribucionParafiscalPatrocinador.getValRazonSocial());
		unaSentencia.setInt("NUM_BOLETAS_PATROCINIO", attDeclaracionContribucionParafiscalPatrocinador.getNumBoletasPatrocinio());
		unaSentencia.setString("VAL_UBICACION_LOCALIDAD", attDeclaracionContribucionParafiscalPatrocinador.getValUbicacionLocalidad());
		unaSentencia.setLong("VAL_INDIVIDUAL_BOLETA", attDeclaracionContribucionParafiscalPatrocinador.getValIndividualBoleta());
		unaSentencia.setLong("IDE_USUARIO_CAMBIO", attDeclaracionContribucionParafiscalPatrocinador.getIdeUsuarioCambio());
		unaSentencia.setTimestamp("FEC_CAMBIO", attDeclaracionContribucionParafiscalPatrocinador.getFecCambio());
	}

	/**
	 * Construye un objeto DeclaracionContribucionParafiscalPatrocinador con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarDeclaracionContribucionParafiscalPatrocinador(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toDeclaracionContribucionParafiscalPatrocinador = completarDeclaracionContribucionParafiscalPatrocinador(resultado);
	}

	/**
	 * Construye objetos DeclaracionContribucionParafiscalPatrocinador con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosDeclaracionContribucionParafiscalPatrocinador(IDDataSet resultado) throws SQLException {
		objetosDeclaracionContribucionParafiscalPatrocinador = new ArrayList<DDeclaracionContribucionParafiscalPatrocinadorTO>();
		toDeclaracionContribucionParafiscalPatrocinador = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DDeclaracionContribucionParafiscalPatrocinadorTO objeto = completarDeclaracionContribucionParafiscalPatrocinador(resultado);
			objetosDeclaracionContribucionParafiscalPatrocinador.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto DeclaracionContribucionParafiscalPatrocinador con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un DeclaracionContribucionParafiscalPatrocinador
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DDeclaracionContribucionParafiscalPatrocinadorTO completarDeclaracionContribucionParafiscalPatrocinador(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DDeclaracionContribucionParafiscalPatrocinadorPKTO pk = new DDeclaracionContribucionParafiscalPatrocinadorPKTO();
		//java.lang.Long
		pk.setIdeDocumentoContParaf(resultado.getLongWrapper("IDE_DOCUMENTO_CONT_PARAF"));
		//java.lang.Integer
		pk.setNumRepeticionDocContParf(resultado.getIntWrapper("NUM_REPETICION_DOC_CONT_PARF"));
		//java.lang.Integer
		pk.setIdeOcurrencia(resultado.getIntWrapper("IDE_OCURRENCIA"));
		//java.lang.Integer
		pk.setIdeItem(resultado.getIntWrapper("IDE_ITEM"));

		// Conformar el objeto Att
		DDeclaracionContribucionParafiscalPatrocinadorAttTO att = new DDeclaracionContribucionParafiscalPatrocinadorAttTO();
		//java.lang.Long
		att.setNumEvento(resultado.getString("NUM_EVENTO"));
		//java.lang.Long
		att.setNumNit(resultado.getLongWrapper("NUM_NIT"));
		//java.lang.Boolean
		att.setNumDv(resultado.getIntWrapper("NUM_DV"));
		//java.lang.String
		att.setValRazonSocial(resultado.getString("VAL_RAZON_SOCIAL"));
		//java.lang.Integer
		att.setNumBoletasPatrocinio(resultado.getIntWrapper("NUM_BOLETAS_PATROCINIO"));
		//java.lang.String
		att.setValUbicacionLocalidad(resultado.getString("VAL_UBICACION_LOCALIDAD"));
		//java.lang.Long
		att.setValIndividualBoleta(resultado.getLongWrapper("VAL_INDIVIDUAL_BOLETA"));
		//java.lang.Long
		att.setIdeUsuarioCambio(resultado.getLongWrapper("IDE_USUARIO_CAMBIO"));
		//java.sql.Timestamp
		att.setFecCambio((Timestamp)resultado.getValorPorNombre("FEC_CAMBIO"));

		// Conformar el TO
		DDeclaracionContribucionParafiscalPatrocinadorTO to = new DDeclaracionContribucionParafiscalPatrocinadorTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}

	/**
	 * Devuelve el objeto DeclaracionContribucionParafiscalPatrocinador que se haya consultado.
	 * @return Un objeto DDeclaracionContribucionParafiscalPatrocinadorTO
	 */
	public DDeclaracionContribucionParafiscalPatrocinadorTO getDeclaracionContribucionParafiscalPatrocinador() {
		return toDeclaracionContribucionParafiscalPatrocinador;
	}

	/**
	 * Devuelve la colección de objetos DeclaracionContribucionParafiscalPatrocinador que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalPatrocinadorTO
	 */
	public Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> getColeccionDeclaracionContribucionParafiscalPatrocinador() {
		return objetosDeclaracionContribucionParafiscalPatrocinador;
	}

	/**
	 * Indica si el DAO es de ejecución libre.
	 * @return true si es de ejecución libre; false de lo contrario
	 */
	public boolean isEjecucionLibre() {
		return false;
	}

	/**
	 * Método para validar los parámetros inicializados, invocado por el administrador de persistencia.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 * @todo COMPLEMENTAR
	 */
	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
		String operacion = null;
		Map<String, Object> parametros=new HashMap<String, Object>();
		switch (getTipoOperacion()) {
			case CREAR: operacion = "la creación"; break;
			case CREAR_CON_COLECCION: operacion = "la creación"; break;
			case ACTUALIZAR: operacion = "la actualización"; break;
			case ELIMINAR: operacion = "la eliminación"; break;
			case ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL: operacion = "la eliminación"; break;
			case CONSULTAR_POR_PK: operacion = "la consulta"; break;
			case CONSULTA_GENERICA: operacion = "la consulta"; break;
			case CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL: operacion = "la consulta"; break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
			parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalPatrocinador",toDeclaracionContribucionParafiscalPatrocinador);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador",pkDeclaracionContribucionParafiscalPatrocinador);
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador",attDeclaracionContribucionParafiscalPatrocinador);

			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getNumEvento()",attDeclaracionContribucionParafiscalPatrocinador.getNumEvento());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getNumNit()",attDeclaracionContribucionParafiscalPatrocinador.getNumNit());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getNumDv()",attDeclaracionContribucionParafiscalPatrocinador.getNumDv());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getValRazonSocial()",attDeclaracionContribucionParafiscalPatrocinador.getValRazonSocial());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getNumBoletasPatrocinio()",attDeclaracionContribucionParafiscalPatrocinador.getNumBoletasPatrocinio());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getValIndividualBoleta()",attDeclaracionContribucionParafiscalPatrocinador.getValIndividualBoleta());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getIdeUsuarioCambio()",attDeclaracionContribucionParafiscalPatrocinador.getIdeUsuarioCambio());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getFecCambio()",attDeclaracionContribucionParafiscalPatrocinador.getFecCambio());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador",pkDeclaracionContribucionParafiscalPatrocinador);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem());
		}

		if (tipoOperacion == CONSULTA_GENERICA) {
			parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalPatrocinador",toDeclaracionContribucionParafiscalPatrocinador);
		}

		if (tipoOperacion == CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL || tipoOperacion == ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		}
		if(tipoOperacion == CREAR_CON_COLECCION){
	        parametros.put(this.getClass().getName()+":validar:objetosDeclaracionContribucionParafiscalPatrocinador",objetosDeclaracionContribucionParafiscalPatrocinador);

	    }

		validarParametros(operacion,parametros);
		return true;
	}
}
