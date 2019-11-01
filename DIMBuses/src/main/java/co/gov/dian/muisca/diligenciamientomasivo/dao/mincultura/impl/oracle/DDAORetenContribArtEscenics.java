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
 * <p>Descripcion: Objeto de acceso a datos para RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAORetenContribArtEscenics extends DDAO implements IDDAORetenContribArtEscenics {
	/** colección de objetos DRetenContribArtEscenicsTO */
	private Collection<DRetenContribArtEscenicsTO> objetosRetenContribArtEscenics;
	/** Objeto de transporte de RetenContribArtEscenics */
	private DRetenContribArtEscenicsTO toRetenContribArtEscenics;
	/** Llave primaria de RetenContribArtEscenics */
	private DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics;
	/** Atributos de RetenContribArtEscenics */
	private DRetenContribArtEscenicsAttTO attRetenContribArtEscenics;
	/** Colección de objetos de transporte de RetenContribArtEscenics */
	protected List<DDetRetContrArtEscenicsTO> lstDetRetContrArtEscenics;	

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkRetenContribArtEscenics Llave primaria de RetenContribArtEscenics
	 */
	public void inicializarConsultarPorPK(DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
	}

	/**
	 * Inicializa la creaciónn de RetenContribArtEscenics.
	 * @param toRetenContribArtEscenics Objeto de Transporte de RetenContribArtEscenics
	 */
	public void inicializarCrear(DRetenContribArtEscenicsTO toRetenContribArtEscenics) {
		setTipoOperacion(CREAR);
		this.toRetenContribArtEscenics = toRetenContribArtEscenics;
		if (toRetenContribArtEscenics != null) {
			pkRetenContribArtEscenics = this.toRetenContribArtEscenics.getPK();
			attRetenContribArtEscenics = this.toRetenContribArtEscenics.getAtt();
		}
	}

	/**
	 * Inicializa la actualización de RetenContribArtEscenics.
	 * @param toRetenContribArtEscenics Objeto de Transporte de RetenContribArtEscenics
	 */
	public void inicializarActualizar(DRetenContribArtEscenicsTO toRetenContribArtEscenics) {
		setTipoOperacion(ACTUALIZAR);
		this.toRetenContribArtEscenics = toRetenContribArtEscenics;
		if (toRetenContribArtEscenics != null) {
			pkRetenContribArtEscenics = this.toRetenContribArtEscenics.getPK();
			attRetenContribArtEscenics = this.toRetenContribArtEscenics.getAtt();
		}
	}

	/**
	 * Inicializa la eliminación de RetenContribArtEscenics.
	 * @param pkRetenContribArtEscenics Llave primaria de RetenContribArtEscenics
	 */
	public void inicializarEliminar(DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics) {
		setTipoOperacion(ELIMINAR);
		this.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
	}

	/**
	 * Inicializa la consulta genérica de RetenContribArtEscenics.
	 * @param attRetenContribArtEscenics Atributos de RetenContribArtEscenics
	 */
	public void inicializarConsultaGenerica(DRetenContribArtEscenicsTO toRetenContribArtEscenics) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toRetenContribArtEscenics = toRetenContribArtEscenics;
		if (toRetenContribArtEscenics != null) {
			pkRetenContribArtEscenics = this.toRetenContribArtEscenics.getPK();
			attRetenContribArtEscenics = this.toRetenContribArtEscenics.getAtt();
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
				sql.append("insert into DIL_RETEN_CONTRIB_ART_ESCENICS")
					.append(" (IDE_DOCUMENTO, NUM_REPETICION, IDE_FORMATO, NUM_VERSION_FORMATO, IDE_DOCUMENTO_CARGA, NUM_REPETICION_CARGA, IDE_FORMATO_CARGA, NUM_VERSION_FORMATO_CARGA, IDE_DOCUMENTO_DECLARACION, NUM_REPETICION_DECLARACION, IDE_FORMATO_DECLARACION, NUM_VERSION_FORMATO_DECLA, IDE_PERSONA_RUT_AGENTE, COD_ESTADO, NUM_ANIO, NUM_PERIODO, VAL_SUBT_BOLS_PREC_IG_SUP_3UVT, VAL_SUBT_DER_ASIST_IG_SUP_3UVT, VAL_SUBT_DEVS_RETENS_EVEN_ANT, NUM_TIPO_DECLARACION, NUM_FORMULARIO_ANTERIOR, FEC_TRANSACCION, IDE_USUARIO_CAMBIO, FEC_CAMBIO)")
					.append(" VALUES (:IDE_DOCUMENTO, :NUM_REPETICION, :IDE_FORMATO, :NUM_VERSION_FORMATO, :IDE_DOCUMENTO_CARGA, :NUM_REPETICION_CARGA, :IDE_FORMATO_CARGA, :NUM_VERSION_FORMATO_CARGA, :IDE_DOCUMENTO_DECLARACION, :NUM_REPETICION_DECLARACION, :IDE_FORMATO_DECLARACION, :NUM_VERSION_FORMATO_DECLA, :IDE_PERSONA_RUT_AGENTE, :COD_ESTADO, :NUM_ANIO, :NUM_PERIODO, :VAL_SUBT_BOLS_PREC_IG_SUP_3UVT, :VAL_SUBT_DER_ASIST_IG_SUP_3UVT, :VAL_SUBT_DEVS_RETENS_EVEN_ANT, :NUM_TIPO_DECLARACION, :NUM_FORMULARIO_ANTERIOR, :FEC_TRANSACCION, :IDE_USUARIO_CAMBIO, :FEC_CAMBIO)");
				m.put("sentencia1", sql.toString());
				break;
			case ACTUALIZAR:
				sql.append("update DIL_RETEN_CONTRIB_ART_ESCENICS")
					.append(" set IDE_FORMATO=:IDE_FORMATO, NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO, IDE_DOCUMENTO_CARGA=:IDE_DOCUMENTO_CARGA, NUM_REPETICION_CARGA=:NUM_REPETICION_CARGA, IDE_FORMATO_CARGA=:IDE_FORMATO_CARGA, NUM_VERSION_FORMATO_CARGA=:NUM_VERSION_FORMATO_CARGA, IDE_DOCUMENTO_DECLARACION=:IDE_DOCUMENTO_DECLARACION, NUM_REPETICION_DECLARACION=:NUM_REPETICION_DECLARACION, IDE_FORMATO_DECLARACION=:IDE_FORMATO_DECLARACION, NUM_VERSION_FORMATO_DECLA=:NUM_VERSION_FORMATO_DECLA, IDE_PERSONA_RUT_AGENTE=:IDE_PERSONA_RUT_AGENTE, COD_ESTADO=:COD_ESTADO, NUM_ANIO=:NUM_ANIO, NUM_PERIODO=:NUM_PERIODO,")
					.append(" VAL_SUBT_BOLS_PREC_IG_SUP_3UVT=:VAL_SUBT_BOLS_PREC_IG_SUP_3UVT, VAL_SUBT_DER_ASIST_IG_SUP_3UVT=:VAL_SUBT_DER_ASIST_IG_SUP_3UVT, VAL_SUBT_DEVS_RETENS_EVEN_ANT=:VAL_SUBT_DEVS_RETENS_EVEN_ANT, NUM_TIPO_DECLARACION=:NUM_TIPO_DECLARACION, NUM_FORMULARIO_ANTERIOR=:NUM_FORMULARIO_ANTERIOR, FEC_TRANSACCION=:FEC_TRANSACCION, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO, FEC_CAMBIO=:FEC_CAMBIO")
					.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR:
				sql.append("delete from DIL_RETEN_CONTRIB_ART_ESCENICS")
					.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_PK:
				sql.append("select * from DIL_RETEN_CONTRIB_ART_ESCENICS")
					.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTA_GENERICA:
				sql.append("select * from DIL_RETEN_CONTRIB_ART_ESCENICS where ")
					.append(generarFiltroGenerico());
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

		if (pkRetenContribArtEscenics != null) {

			if (pkRetenContribArtEscenics.getIdeDocumento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO=:IDE_DOCUMENTO");
				append = true;

			}
			if (pkRetenContribArtEscenics.getNumRepeticion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION=:NUM_REPETICION");
				append = true;

			}
		}

		if (attRetenContribArtEscenics != null) {

			if (attRetenContribArtEscenics.getIdeFormato() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_FORMATO=:IDE_FORMATO");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumVersionFormato() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO");
				append = true;

			}
			if (attRetenContribArtEscenics.getIdeDocumentoCarga() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO_CARGA=:IDE_DOCUMENTO_CARGA");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumRepeticionCarga() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION_CARGA=:NUM_REPETICION_CARGA");
				append = true;

			}
			if (attRetenContribArtEscenics.getIdeFormatoCarga() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_FORMATO_CARGA=:IDE_FORMATO_CARGA");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumVersionFormatoCarga() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_VERSION_FORMATO_CARGA=:NUM_VERSION_FORMATO_CARGA");
				append = true;

			}
			if (attRetenContribArtEscenics.getIdeDocumentoDeclaracion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO_DECLARACION=:IDE_DOCUMENTO_DECLARACION");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumRepeticionDeclaracion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION_DECLARACION=:NUM_REPETICION_DECLARACION");
				append = true;

			}
			if (attRetenContribArtEscenics.getIdeFormatoDeclaracion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_FORMATO_DECLARACION=:IDE_FORMATO_DECLARACION");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumVersionFormatoDecla() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_VERSION_FORMATO_DECLA=:NUM_VERSION_FORMATO_DECLA");
				append = true;

			}			
			if (attRetenContribArtEscenics.getIdePersonaRutAgente() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_PERSONA_RUT_AGENTE=:IDE_PERSONA_RUT_AGENTE");
				append = true;

			}
			if (attRetenContribArtEscenics.getCodEstado() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("COD_ESTADO=:COD_ESTADO");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumAnio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_ANIO=:NUM_ANIO");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumPeriodo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_PERIODO=:NUM_PERIODO");
				append = true;

			}
			if (attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_SUBT_BOLS_PREC_IG_SUP_3UVT=:VAL_SUBT_BOLS_PREC_IG_SUP_3UVT");
				append = true;

			}
			if (attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_SUBT_DER_ASIST_IG_SUP_3UVT=:VAL_SUBT_DER_ASIST_IG_SUP_3UVT");
				append = true;

			}
			if (attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_SUBT_DEVS_RETENS_EVEN_ANT=:VAL_SUBT_DEVS_RETENS_EVEN_ANT");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumTipoDeclaracion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_TIPO_DECLARACION=:NUM_TIPO_DECLARACION");
				append = true;

			}
			if (attRetenContribArtEscenics.getNumFormularioAnterior() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_FORMULARIO_ANTERIOR=:NUM_FORMULARIO_ANTERIOR");
				append = true;

			}
			if (attRetenContribArtEscenics.getFecTransaccion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_TRANSACCION=:FEC_TRANSACCION");
				append = true;

			}
			if (attRetenContribArtEscenics.getIdeUsuarioCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
				append = true;

			}
			if (attRetenContribArtEscenics.getFecCambio() != null) {
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
		if (pkRetenContribArtEscenics != null) {
			if (pkRetenContribArtEscenics.getIdeDocumento() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO", pkRetenContribArtEscenics.getIdeDocumento());
			}
			if (pkRetenContribArtEscenics.getNumRepeticion() != null) {
				unaSentencia.setInt("NUM_REPETICION", pkRetenContribArtEscenics.getNumRepeticion());
			}
		}

		if (attRetenContribArtEscenics != null) {
			if (attRetenContribArtEscenics.getIdeFormato() != null) {
				unaSentencia.setInt("IDE_FORMATO", attRetenContribArtEscenics.getIdeFormato());
			}
			if (attRetenContribArtEscenics.getNumVersionFormato() != null) {
				unaSentencia.setShort("NUM_VERSION_FORMATO", attRetenContribArtEscenics.getNumVersionFormato());
			}
			if (attRetenContribArtEscenics.getIdeDocumentoCarga() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO_CARGA", attRetenContribArtEscenics.getIdeDocumentoCarga());
			}
			if (attRetenContribArtEscenics.getNumRepeticionCarga() != null) {
				unaSentencia.setInt("NUM_REPETICION_CARGA", attRetenContribArtEscenics.getNumRepeticionCarga());
			}
			if (attRetenContribArtEscenics.getIdeFormatoCarga() != null) {
				unaSentencia.setInt("IDE_FORMATO_CARGA", attRetenContribArtEscenics.getIdeFormatoCarga());
			}
			if (attRetenContribArtEscenics.getNumVersionFormatoCarga() != null) {
				unaSentencia.setShort("NUM_VERSION_FORMATO_CARGA", attRetenContribArtEscenics.getNumVersionFormatoCarga());
			}
			if (attRetenContribArtEscenics.getIdeDocumentoDeclaracion() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO_DECLARACION", attRetenContribArtEscenics.getIdeDocumentoDeclaracion());
			}
			if (attRetenContribArtEscenics.getNumRepeticionDeclaracion() != null) {
				unaSentencia.setInt("NUM_REPETICION_DECLARACION", attRetenContribArtEscenics.getNumRepeticionDeclaracion());
			}
			if (attRetenContribArtEscenics.getIdeFormatoDeclaracion() != null) {
				unaSentencia.setInt("IDE_FORMATO_DECLARACION", attRetenContribArtEscenics.getIdeFormatoDeclaracion());
			}
			if (attRetenContribArtEscenics.getNumVersionFormatoDecla() != null) {
				unaSentencia.setShort("NUM_VERSION_FORMATO_DECLA", attRetenContribArtEscenics.getNumVersionFormatoDecla());
			}			
			if (attRetenContribArtEscenics.getIdePersonaRutAgente() != null) {
				unaSentencia.setLong("IDE_PERSONA_RUT_AGENTE", attRetenContribArtEscenics.getIdePersonaRutAgente());
			}
			if (attRetenContribArtEscenics.getCodEstado() != null) {
				unaSentencia.setInt("COD_ESTADO", attRetenContribArtEscenics.getCodEstado());
			}
			if (attRetenContribArtEscenics.getNumAnio() != null) {
				unaSentencia.setShort("NUM_ANIO", attRetenContribArtEscenics.getNumAnio());
			}
			if (attRetenContribArtEscenics.getNumPeriodo() != null) {
				unaSentencia.setShort("NUM_PERIODO", attRetenContribArtEscenics.getNumPeriodo());
			}
			if (attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt() != null) {
				unaSentencia.setBigDecimal("VAL_SUBT_BOLS_PREC_IG_SUP_3UVT", attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt());
			}
			if (attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt() != null) {
				unaSentencia.setBigDecimal("VAL_SUBT_DER_ASIST_IG_SUP_3UVT", attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt());
			}
			if (attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt() != null) {
				unaSentencia.setBigDecimal("VAL_SUBT_DEVS_RETENS_EVEN_ANT", attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt());
			}
			if (attRetenContribArtEscenics.getNumTipoDeclaracion() != null) {
				unaSentencia.setInt("NUM_TIPO_DECLARACION", attRetenContribArtEscenics.getNumTipoDeclaracion());
			}
			if (attRetenContribArtEscenics.getNumFormularioAnterior() != null) {
				unaSentencia.setLong("NUM_FORMULARIO_ANTERIOR", attRetenContribArtEscenics.getNumFormularioAnterior());
			}
			if (attRetenContribArtEscenics.getFecTransaccion() != null) {
				unaSentencia.setLong("FEC_TRANSACCION", attRetenContribArtEscenics.getFecTransaccion());
			}
			if (attRetenContribArtEscenics.getIdeUsuarioCambio() != null) {
				unaSentencia.setLong("IDE_USUARIO_CAMBIO", attRetenContribArtEscenics.getIdeUsuarioCambio());
			}
			if (attRetenContribArtEscenics.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attRetenContribArtEscenics.getFecCambio());
			}
		}
	}

	/**
	 * Guarda los datos de RetenContribArtEscenics.
	 * @return @return Un int con el número de registros guardados
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
	 * Elimina registros de RetenContribArtEscenics.
	 * @return Un int con el número de registros eliminados
	 * @throws SQLException Si ocurre un error de base de datos al eliminar
	 */
	public int eliminar() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		return sentencia.getRegistrosAfectados();
	}

	/**
	 * Consulta los datos de RetenContribArtEscenics.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	public IDDataSet consultar() throws SQLException {
		switch (getTipoOperacion()) {
			case CONSULTAR_POR_PK:
				return consultarPorPK();
			case CONSULTA_GENERICA:
				return consultaGenerica();
		}
		return null;
	}

	/**
	 * Crea un registro de RetenContribArtEscenics.
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
	 * Actualiza los datos de RetenContribArtEscenics.
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
	 * Actualiza los datos de RetenContribArtEscenics.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarRetenContribArtEscenics(resultado);
		return resultado;
	}

	/**
	 * Consulta genérica de los datos de RetenContribArtEscenics.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosRetenContribArtEscenics(resultado);
		return resultado;
	}

	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO", pkRetenContribArtEscenics.getIdeDocumento());
		unaSentencia.setInt("NUM_REPETICION", pkRetenContribArtEscenics.getNumRepeticion());
	}

	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setInt("IDE_FORMATO", attRetenContribArtEscenics.getIdeFormato());
		unaSentencia.setShort("NUM_VERSION_FORMATO", attRetenContribArtEscenics.getNumVersionFormato());
		unaSentencia.setLong("IDE_DOCUMENTO_CARGA", attRetenContribArtEscenics.getIdeDocumentoCarga());
		unaSentencia.setInt("NUM_REPETICION_CARGA", attRetenContribArtEscenics.getNumRepeticionCarga());
		unaSentencia.setInt("IDE_FORMATO_CARGA", attRetenContribArtEscenics.getIdeFormatoCarga());
		unaSentencia.setShort("NUM_VERSION_FORMATO_CARGA", attRetenContribArtEscenics.getNumVersionFormatoCarga());
		unaSentencia.setLong("IDE_DOCUMENTO_DECLARACION", attRetenContribArtEscenics.getIdeDocumentoDeclaracion());
		unaSentencia.setInt("NUM_REPETICION_DECLARACION", attRetenContribArtEscenics.getNumRepeticionDeclaracion());
		unaSentencia.setInt("IDE_FORMATO_DECLARACION", attRetenContribArtEscenics.getIdeFormatoDeclaracion());
		unaSentencia.setShort("NUM_VERSION_FORMATO_DECLA", attRetenContribArtEscenics.getNumVersionFormatoDecla());		
		unaSentencia.setLong("IDE_PERSONA_RUT_AGENTE", attRetenContribArtEscenics.getIdePersonaRutAgente());
		unaSentencia.setInt("COD_ESTADO", attRetenContribArtEscenics.getCodEstado());
		unaSentencia.setShort("NUM_ANIO", attRetenContribArtEscenics.getNumAnio());
		unaSentencia.setShort("NUM_PERIODO", attRetenContribArtEscenics.getNumPeriodo());
		unaSentencia.setBigDecimal("VAL_SUBT_BOLS_PREC_IG_SUP_3UVT", attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt());
		unaSentencia.setBigDecimal("VAL_SUBT_DER_ASIST_IG_SUP_3UVT", attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt());
		unaSentencia.setBigDecimal("VAL_SUBT_DEVS_RETENS_EVEN_ANT", attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt());
		unaSentencia.setInt("NUM_TIPO_DECLARACION", attRetenContribArtEscenics.getNumTipoDeclaracion());
		unaSentencia.setLong("NUM_FORMULARIO_ANTERIOR", attRetenContribArtEscenics.getNumFormularioAnterior());
		unaSentencia.setLong("FEC_TRANSACCION", attRetenContribArtEscenics.getFecTransaccion());
		unaSentencia.setLong("IDE_USUARIO_CAMBIO", attRetenContribArtEscenics.getIdeUsuarioCambio());
		unaSentencia.setTimestamp("FEC_CAMBIO", attRetenContribArtEscenics.getFecCambio());
	}

	/**
	 * Construye un objeto RetenContribArtEscenics con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarRetenContribArtEscenics(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toRetenContribArtEscenics = completarRetenContribArtEscenics(resultado);
	}

	/**
	 * Construye objetos RetenContribArtEscenics con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosRetenContribArtEscenics(IDDataSet resultado) throws SQLException {
		objetosRetenContribArtEscenics = new ArrayList<DRetenContribArtEscenicsTO>();
		toRetenContribArtEscenics = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DRetenContribArtEscenicsTO objeto = completarRetenContribArtEscenics(resultado);
			objetosRetenContribArtEscenics.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto RetenContribArtEscenics con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un RetenContribArtEscenics
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DRetenContribArtEscenicsTO completarRetenContribArtEscenics(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DRetenContribArtEscenicsPKTO pk = new DRetenContribArtEscenicsPKTO();
		//java.lang.Long
		pk.setIdeDocumento(resultado.getLongWrapper("IDE_DOCUMENTO"));
		//java.lang.Integer
		pk.setNumRepeticion(resultado.getIntWrapper("NUM_REPETICION"));

		// Conformar el objeto Att
		DRetenContribArtEscenicsAttTO att = new DRetenContribArtEscenicsAttTO();
		//java.lang.Integer
		att.setIdeFormato(resultado.getIntWrapper("IDE_FORMATO"));
		//java.lang.Byte
		att.setNumVersionFormato(resultado.getByteWrapper("NUM_VERSION_FORMATO"));
		//java.lang.Long
		att.setIdeDocumentoCarga(resultado.getLongWrapper("IDE_DOCUMENTO_CARGA"));
		//java.lang.Integer
		att.setNumRepeticionCarga(resultado.getIntWrapper("NUM_REPETICION_CARGA"));
		//java.lang.Integer
		att.setIdeFormatoCarga(resultado.getIntWrapper("IDE_FORMATO_CARGA"));
		//java.lang.Byte
		att.setNumVersionFormatoCarga(resultado.getByteWrapper("NUM_VERSION_FORMATO_CARGA"));
		//java.lang.Long
		att.setIdeDocumentoDeclaracion(resultado.getLongWrapper("IDE_DOCUMENTO_DECLARACION"));
		//java.lang.Integer
		att.setNumRepeticionDeclaracion(resultado.getIntWrapper("NUM_REPETICION_DECLARACION"));
		//java.lang.Integer
		att.setIdeFormatoDeclaracion(resultado.getIntWrapper("IDE_FORMATO_DECLARACION"));
		//java.lang.Byte
		att.setNumVersionFormatoDecla(resultado.getByteWrapper("NUM_VERSION_FORMATO_DECLARACION"));		
		//java.lang.Long
		att.setIdePersonaRutAgente(resultado.getLongWrapper("IDE_PERSONA_RUT_AGENTE"));
		//java.lang.Integer
		if (resultado.getValorPorNombre("COD_ESTADO") != null) {
			att.setCodEstado(resultado.getIntWrapper("COD_ESTADO"));
		}
		//java.lang.Short
		att.setNumAnio(resultado.getShortWrapper("NUM_ANIO"));
		//java.lang.Byte
		if (resultado.getValorPorNombre("NUM_PERIODO") != null) {
			att.setNumPeriodo(resultado.getByteWrapper("NUM_PERIODO"));
		}
		//java.math.BigDecimal
		att.setValSubtBolsPrecIgSup3uvt(resultado.getBigDecimal("VAL_SUBT_BOLS_PREC_IG_SUP_3UVT"));
		//java.math.BigDecimal
		att.setValSubtDerAsistIgSup3uvt(resultado.getBigDecimal("VAL_SUBT_DER_ASIST_IG_SUP_3UVT"));
		//java.math.BigDecimal
		att.setValSubtDevsRetensEvenAnt(resultado.getBigDecimal("VAL_SUBT_DEVS_RETENS_EVEN_ANT"));
		//java.lang.Integer
		if (resultado.getValorPorNombre("NUM_TIPO_DECLARACION") != null) {
			att.setNumTipoDeclaracion(resultado.getIntWrapper("NUM_TIPO_DECLARACION"));
		}
		//java.lang.Long
		if (resultado.getValorPorNombre("NUM_FORMULARIO_ANTERIOR") != null) {
			att.setNumFormularioAnterior(resultado.getLongWrapper("NUM_FORMULARIO_ANTERIOR"));
		}
		//java.lang.Long
		if (resultado.getValorPorNombre("IDE_USUARIO_CAMBIO") != null) {
			att.setIdeUsuarioCambio(resultado.getLongWrapper("IDE_USUARIO_CAMBIO"));
		}
		att.setFecTransaccion(resultado.getLongWrapper("FEC_TRANSACCION"));
		//java.sql.Timestamp
		att.setFecCambio((Timestamp)resultado.getValorPorNombre("FEC_CAMBIO"));

		// Conformar el TO
		DRetenContribArtEscenicsTO to = new DRetenContribArtEscenicsTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}

	/**
	 * Devuelve el objeto RetenContribArtEscenics que se haya consultado.
	 * @return Un objeto DRetenContribArtEscenicsTO
	 */
	public DRetenContribArtEscenicsTO getRetenContribArtEscenics() {
		return toRetenContribArtEscenics;
	}

	/**
	 * Devuelve la colección de objetos RetenContribArtEscenics que se hayan consultado.
	 * @return Un Collection con objetos DRetenContribArtEscenicsTO
	 */
	public Collection<DRetenContribArtEscenicsTO> getColeccionRetenContribArtEscenics() {
		return objetosRetenContribArtEscenics;
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
			case ACTUALIZAR: operacion = "la actualización"; break;
			case ELIMINAR: operacion = "la eliminación"; break;
			case CONSULTAR_POR_PK: operacion = "la consulta"; break;
			case CONSULTA_GENERICA: operacion = "la consulta"; break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics",toRetenContribArtEscenics);
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics",pkRetenContribArtEscenics);
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics",attRetenContribArtEscenics);

			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getIdeDocumento()",pkRetenContribArtEscenics.getIdeDocumento());
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getNumRepeticion()",pkRetenContribArtEscenics.getNumRepeticion());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeFormato()",attRetenContribArtEscenics.getIdeFormato());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumVersionFormato()",attRetenContribArtEscenics.getNumVersionFormato());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeDocumentoCarga()",attRetenContribArtEscenics.getIdeDocumentoCarga());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumRepeticionCarga()",attRetenContribArtEscenics.getNumRepeticionCarga());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeFormatoCarga()",attRetenContribArtEscenics.getIdeFormatoCarga());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumVersionFormato()",attRetenContribArtEscenics.getNumVersionFormato());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeDocumentoDeclaracion()",attRetenContribArtEscenics.getIdeDocumentoDeclaracion());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumRepeticionDeclaracion()",attRetenContribArtEscenics.getNumRepeticionDeclaracion());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeFormatoDeclaracion()",attRetenContribArtEscenics.getIdeFormatoDeclaracion());			
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumVersionFormatoDecla()",attRetenContribArtEscenics.getNumVersionFormatoDecla());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdePersonaRutAgente()",attRetenContribArtEscenics.getIdePersonaRutAgente());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumAnio()",attRetenContribArtEscenics.getNumAnio());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt()",attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt()",attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt()",attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getFecTransaccion()",attRetenContribArtEscenics.getFecTransaccion());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics",pkRetenContribArtEscenics);
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getIdeDocumento()",pkRetenContribArtEscenics.getIdeDocumento());
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getNumRepeticion()",pkRetenContribArtEscenics.getNumRepeticion());
		}

		if (tipoOperacion == CONSULTA_GENERICA) {
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics",toRetenContribArtEscenics);
		}


		validarParametros(operacion,parametros);
		return true;
	}
}