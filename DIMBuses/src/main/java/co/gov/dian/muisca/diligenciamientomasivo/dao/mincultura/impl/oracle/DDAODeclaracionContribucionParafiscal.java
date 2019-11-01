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
 * <p>Descripcion: Objeto de acceso a datos para DeclaracionContribucionParafiscal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAODeclaracionContribucionParafiscal extends DDAO implements IDDAODeclaracionContribucionParafiscal {
	/** colección de objetos DDeclaracionContribucionParafiscalTO */
	private Collection<DDeclaracionContribucionParafiscalTO> objetosDeclaracionContribucionParafiscal;
	/** Objeto de transporte de DeclaracionContribucionParafiscal */
	private DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal;
	/** Llave primaria de DeclaracionContribucionParafiscal */
	private DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal;
	/** Atributos de DeclaracionContribucionParafiscal */
	private DDeclaracionContribucionParafiscalAttTO attDeclaracionContribucionParafiscal;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	public void inicializarConsultarPorPK(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
	}

	/**
	 * Inicializa la creaciónn de DeclaracionContribucionParafiscal.
	 * @param toDeclaracionContribucionParafiscal Objeto de Transporte de DeclaracionContribucionParafiscal
	 */
	public void inicializarCrear(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal) {
		setTipoOperacion(CREAR);
		this.toDeclaracionContribucionParafiscal = toDeclaracionContribucionParafiscal;
		if (toDeclaracionContribucionParafiscal != null) {
			pkDeclaracionContribucionParafiscal = this.toDeclaracionContribucionParafiscal.getPK();
			attDeclaracionContribucionParafiscal = this.toDeclaracionContribucionParafiscal.getAtt();
		}
	}

	/**
	 * Inicializa la actualización de DeclaracionContribucionParafiscal.
	 * @param toDeclaracionContribucionParafiscal Objeto de Transporte de DeclaracionContribucionParafiscal
	 */
	public void inicializarActualizar(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal) {
		setTipoOperacion(ACTUALIZAR);
		this.toDeclaracionContribucionParafiscal = toDeclaracionContribucionParafiscal;
		if (toDeclaracionContribucionParafiscal != null) {
			pkDeclaracionContribucionParafiscal = this.toDeclaracionContribucionParafiscal.getPK();
			attDeclaracionContribucionParafiscal = this.toDeclaracionContribucionParafiscal.getAtt();
		}
	}

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscal.
	 * @param pkDeclaracionContribucionParafiscal Llave primaria de DeclaracionContribucionParafiscal
	 */
	public void inicializarEliminar(DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal) {
		setTipoOperacion(ELIMINAR);
		this.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
	}

	/**
	 * Inicializa la consulta genérica de DeclaracionContribucionParafiscal.
	 * @param attDeclaracionContribucionParafiscal Atributos de DeclaracionContribucionParafiscal
	 */
	public void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toDeclaracionContribucionParafiscal = toDeclaracionContribucionParafiscal;
		if (toDeclaracionContribucionParafiscal != null) {
			pkDeclaracionContribucionParafiscal = this.toDeclaracionContribucionParafiscal.getPK();
			attDeclaracionContribucionParafiscal = this.toDeclaracionContribucionParafiscal.getAtt();
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
				sql.append("insert into DIL_DECLA_CONTRIB_PARAF")
					.append(" (IDE_DOCUMENTO_CONT_PARAF, NUM_REPETICION_DOC_CONT_PARF, IDE_PERSONA_RUT, NUM_ANIO, NUM_PERIODO, IDE_SOLICITUD, IDE_TIPO_DOCUMENTO, NUM_IDENTIFICACION, VAL_PRIMER_APELLIDO, VAL_SEGUNDO_APELLIDO, VAL_PRIMER_NOMBRE, VAL_OTROS_NOMBRES, VAL_RAZON_SOCIAL, VAL_TOTAL_BOL_VENDIDA, VAL_TOTAL_DER_CORTESIA, VAL_TOTAL_DER_PATROCINIO, VAL_TOTAL_CRUCES, VAL_SUBTOTAL_RETENCIONES, VAL_SUBTOTAL_DEVOLUCIONES, VAL_TIPO_DECLARACION, NUM_FORMULARIO_ANTERIOR, VAL_CLASE_PRODUCTOR, VAL_NUM_REGISTRO_PRODUCTOR, VAL_FECHA_PRESENTACION, IDE_DOCUMENTO_480, NUM_REPETICION_DOC_480, IDE_USUARIO_CAMBIO, FEC_CAMBIO)")
					.append(" VALUES (:IDE_DOCUMENTO_CONT_PARAF, :NUM_REPETICION_DOC_CONT_PARF, :IDE_PERSONA_RUT, :NUM_ANIO, :NUM_PERIODO, :IDE_SOLICITUD, :IDE_TIPO_DOCUMENTO, :NUM_IDENTIFICACION, :VAL_PRIMER_APELLIDO, :VAL_SEGUNDO_APELLIDO, :VAL_PRIMER_NOMBRE, :VAL_OTROS_NOMBRES, :VAL_RAZON_SOCIAL, :VAL_TOTAL_BOL_VENDIDA, :VAL_TOTAL_DER_CORTESIA, :VAL_TOTAL_DER_PATROCINIO, :VAL_TOTAL_CRUCES, :VAL_SUBTOTAL_RETENCIONES, :VAL_SUBTOTAL_DEVOLUCIONES, :VAL_TIPO_DECLARACION, :NUM_FORMULARIO_ANTERIOR, :VAL_CLASE_PRODUCTOR, :VAL_NUM_REGISTRO_PRODUCTOR, :VAL_FECHA_PRESENTACION, :IDE_DOCUMENTO_480, :NUM_REPETICION_DOC_480, :IDE_USUARIO_CAMBIO, :FEC_CAMBIO)");
				m.put("sentencia1", sql.toString());
				break;
			case ACTUALIZAR:
				sql.append("update DIL_DECLA_CONTRIB_PARAF")
					.append(" set IDE_PERSONA_RUT=:IDE_PERSONA_RUT, NUM_ANIO=:NUM_ANIO, NUM_PERIODO=:NUM_PERIODO, IDE_SOLICITUD=:IDE_SOLICITUD, IDE_TIPO_DOCUMENTO=:IDE_TIPO_DOCUMENTO, NUM_IDENTIFICACION=:NUM_IDENTIFICACION, VAL_PRIMER_APELLIDO=:VAL_PRIMER_APELLIDO, VAL_SEGUNDO_APELLIDO=:VAL_SEGUNDO_APELLIDO, VAL_PRIMER_NOMBRE=:VAL_PRIMER_NOMBRE, VAL_OTROS_NOMBRES=:VAL_OTROS_NOMBRES, VAL_RAZON_SOCIAL=:VAL_RAZON_SOCIAL, VAL_TOTAL_BOL_VENDIDA=:VAL_TOTAL_BOL_VENDIDA, VAL_TOTAL_DER_CORTESIA=:VAL_TOTAL_DER_CORTESIA, VAL_TOTAL_DER_PATROCINIO=:VAL_TOTAL_DER_PATROCINIO, VAL_TOTAL_CRUCES=:VAL_TOTAL_CRUCES, VAL_SUBTOTAL_RETENCIONES=:VAL_SUBTOTAL_RETENCIONES, VAL_SUBTOTAL_DEVOLUCIONES=:VAL_SUBTOTAL_DEVOLUCIONES, VAL_TIPO_DECLARACION=:VAL_TIPO_DECLARACION, NUM_FORMULARIO_ANTERIOR=:NUM_FORMULARIO_ANTERIOR, VAL_CLASE_PRODUCTOR=:VAL_CLASE_PRODUCTOR, VAL_NUM_REGISTRO_PRODUCTOR=:VAL_NUM_REGISTRO_PRODUCTOR, VAL_FECHA_PRESENTACION=:VAL_FECHA_PRESENTACION, IDE_DOCUMENTO_480=:IDE_DOCUMENTO_480, NUM_REPETICION_DOC_480=:NUM_REPETICION_DOC_480, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO, FEC_CAMBIO=:FEC_CAMBIO")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR:
				sql.append("delete from DIL_DECLA_CONTRIB_PARAF")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_PK:
				sql.append("select * from DIL_DECLA_CONTRIB_PARAF")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTA_GENERICA:
				sql.append("select * from DIL_DECLA_CONTRIB_PARAF where ")
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

		if (pkDeclaracionContribucionParafiscal != null) {

			if (pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF");
				append = true;

			}
			if (pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF");
				append = true;

			}
		}

		if (attDeclaracionContribucionParafiscal != null) {

			if (attDeclaracionContribucionParafiscal.getIdePersonaRut() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_PERSONA_RUT=:IDE_PERSONA_RUT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getNumAnio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_ANIO=:NUM_ANIO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getNumPeriodo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_PERIODO=:NUM_PERIODO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getIdeSolicitud() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_SOLICITUD=:IDE_SOLICITUD");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getIdeTipoDocumento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_TIPO_DOCUMENTO=:IDE_TIPO_DOCUMENTO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getNumIdentificacion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_IDENTIFICACION=:NUM_IDENTIFICACION");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValPrimerApellido() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_PRIMER_APELLIDO=:VAL_PRIMER_APELLIDO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValSegundoApellido() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_SEGUNDO_APELLIDO=:VAL_SEGUNDO_APELLIDO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValPrimerNombre() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_PRIMER_NOMBRE=:VAL_PRIMER_NOMBRE");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValOtrosNombres() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_OTROS_NOMBRES=:VAL_OTROS_NOMBRES");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValRazonSocial() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_RAZON_SOCIAL=:VAL_RAZON_SOCIAL");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValTotalBolVendida() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_TOTAL_BOL_VENDIDA=:VAL_TOTAL_BOL_VENDIDA");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValTotalDerCortesia() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_TOTAL_DER_CORTESIA=:VAL_TOTAL_DER_CORTESIA");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValTotalDerPatrocinio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_TOTAL_DER_PATROCINIO=:VAL_TOTAL_DER_PATROCINIO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValTotalCruces() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_TOTAL_CRUCES=:VAL_TOTAL_CRUCES");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValSubtotalRetenciones() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_SUBTOTAL_RETENCIONES=:VAL_SUBTOTAL_RETENCIONES");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValSubtotalDevoluciones() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_SUBTOTAL_DEVOLUCIONES=:VAL_SUBTOTAL_DEVOLUCIONES");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValTipoDeclaracion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_TIPO_DECLARACION=:VAL_TIPO_DECLARACION");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getNumFormularioAnterior() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_FORMULARIO_ANTERIOR=:NUM_FORMULARIO_ANTERIOR");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValClaseProductor() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_CLASE_PRODUCTOR=:VAL_CLASE_PRODUCTOR");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValNumRegistroProductor() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_NUM_REGISTRO_PRODUCTOR=:VAL_NUM_REGISTRO_PRODUCTOR");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getValFechaPresentacion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_FECHA_PRESENTACION=:VAL_FECHA_PRESENTACION");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getIdeDocumento480() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO_480=:IDE_DOCUMENTO_480");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getNumRepeticionDoc480() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION_DOC_480=:NUM_REPETICION_DOC_480");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getIdeUsuarioCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscal.getFecCambio() != null) {
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
		if (pkDeclaracionContribucionParafiscal != null) {
			if (pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
			}
			if (pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf() != null) {
				unaSentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
			}
		}

		if (attDeclaracionContribucionParafiscal != null) {
			if (attDeclaracionContribucionParafiscal.getIdePersonaRut() != null) {
				unaSentencia.setLong("IDE_PERSONA_RUT", attDeclaracionContribucionParafiscal.getIdePersonaRut());
			}
			if (attDeclaracionContribucionParafiscal.getNumAnio() != null) {
				unaSentencia.setInt("NUM_ANIO", attDeclaracionContribucionParafiscal.getNumAnio());
			}
			if (attDeclaracionContribucionParafiscal.getNumPeriodo() != null) {
				unaSentencia.setInt("NUM_PERIODO", attDeclaracionContribucionParafiscal.getNumPeriodo());
			}
			if (attDeclaracionContribucionParafiscal.getIdeSolicitud() != null) {
				unaSentencia.setLong("IDE_SOLICITUD", attDeclaracionContribucionParafiscal.getIdeSolicitud());
			}
			if (attDeclaracionContribucionParafiscal.getIdeTipoDocumento() != null) {
				unaSentencia.setInt("IDE_TIPO_DOCUMENTO", attDeclaracionContribucionParafiscal.getIdeTipoDocumento());
			}
			if (attDeclaracionContribucionParafiscal.getNumIdentificacion() != null) {
				unaSentencia.setString("NUM_IDENTIFICACION", attDeclaracionContribucionParafiscal.getNumIdentificacion());
			}
			if (attDeclaracionContribucionParafiscal.getValPrimerApellido() != null) {
				unaSentencia.setString("VAL_PRIMER_APELLIDO", attDeclaracionContribucionParafiscal.getValPrimerApellido());
			}
			if (attDeclaracionContribucionParafiscal.getValSegundoApellido() != null) {
				unaSentencia.setString("VAL_SEGUNDO_APELLIDO", attDeclaracionContribucionParafiscal.getValSegundoApellido());
			}
			if (attDeclaracionContribucionParafiscal.getValPrimerNombre() != null) {
				unaSentencia.setString("VAL_PRIMER_NOMBRE", attDeclaracionContribucionParafiscal.getValPrimerNombre());
			}
			if (attDeclaracionContribucionParafiscal.getValOtrosNombres() != null) {
				unaSentencia.setString("VAL_OTROS_NOMBRES", attDeclaracionContribucionParafiscal.getValOtrosNombres());
			}
			if (attDeclaracionContribucionParafiscal.getValRazonSocial() != null) {
				unaSentencia.setString("VAL_RAZON_SOCIAL", attDeclaracionContribucionParafiscal.getValRazonSocial());
			}
			if (attDeclaracionContribucionParafiscal.getValTotalBolVendida() != null) {
				unaSentencia.setLong("VAL_TOTAL_BOL_VENDIDA", attDeclaracionContribucionParafiscal.getValTotalBolVendida());
			}
			if (attDeclaracionContribucionParafiscal.getValTotalDerCortesia() != null) {
				unaSentencia.setLong("VAL_TOTAL_DER_CORTESIA", attDeclaracionContribucionParafiscal.getValTotalDerCortesia());
			}
			if (attDeclaracionContribucionParafiscal.getValTotalDerPatrocinio() != null) {
				unaSentencia.setLong("VAL_TOTAL_DER_PATROCINIO", attDeclaracionContribucionParafiscal.getValTotalDerPatrocinio());
			}
			if (attDeclaracionContribucionParafiscal.getValTotalCruces() != null) {
				unaSentencia.setLong("VAL_TOTAL_CRUCES", attDeclaracionContribucionParafiscal.getValTotalCruces());
			}
			if (attDeclaracionContribucionParafiscal.getValSubtotalRetenciones() != null) {
				unaSentencia.setLong("VAL_SUBTOTAL_RETENCIONES", attDeclaracionContribucionParafiscal.getValSubtotalRetenciones());
			}
			if (attDeclaracionContribucionParafiscal.getValSubtotalDevoluciones() != null) {
				unaSentencia.setLong("VAL_SUBTOTAL_DEVOLUCIONES", attDeclaracionContribucionParafiscal.getValSubtotalDevoluciones());
			}
			if (attDeclaracionContribucionParafiscal.getValTipoDeclaracion() != null) {
				unaSentencia.setInt("VAL_TIPO_DECLARACION", attDeclaracionContribucionParafiscal.getValTipoDeclaracion());
			}
			if (attDeclaracionContribucionParafiscal.getNumFormularioAnterior() != null) {
				unaSentencia.setLong("NUM_FORMULARIO_ANTERIOR", attDeclaracionContribucionParafiscal.getNumFormularioAnterior());
			}
			if (attDeclaracionContribucionParafiscal.getValClaseProductor() != null) {
				unaSentencia.setString("VAL_CLASE_PRODUCTOR", attDeclaracionContribucionParafiscal.getValClaseProductor());
			}
			if (attDeclaracionContribucionParafiscal.getValNumRegistroProductor() != null) {
				unaSentencia.setString("VAL_NUM_REGISTRO_PRODUCTOR", attDeclaracionContribucionParafiscal.getValNumRegistroProductor());
			}
			if (attDeclaracionContribucionParafiscal.getValFechaPresentacion() != null) {
				unaSentencia.setLong("VAL_FECHA_PRESENTACION", attDeclaracionContribucionParafiscal.getValFechaPresentacion());
			}
			if (attDeclaracionContribucionParafiscal.getIdeDocumento480() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO_480", attDeclaracionContribucionParafiscal.getIdeDocumento480());
			}
			if (attDeclaracionContribucionParafiscal.getNumRepeticionDoc480() != null) {
				unaSentencia.setLong("NUM_REPETICION_DOC_480", attDeclaracionContribucionParafiscal.getNumRepeticionDoc480());
			}
			if (attDeclaracionContribucionParafiscal.getIdeUsuarioCambio() != null) {
				unaSentencia.setLong("IDE_USUARIO_CAMBIO", attDeclaracionContribucionParafiscal.getIdeUsuarioCambio());
			}
			if (attDeclaracionContribucionParafiscal.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attDeclaracionContribucionParafiscal.getFecCambio());
			}
		}
	}

	/**
	 * Guarda los datos de DeclaracionContribucionParafiscal.
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
	 * Elimina registros de DeclaracionContribucionParafiscal.
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
	 * Consulta los datos de DeclaracionContribucionParafiscal.
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
	 * Crea un registro de DeclaracionContribucionParafiscal.
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
	 * Actualiza los datos de DeclaracionContribucionParafiscal.
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
	 * Actualiza los datos de DeclaracionContribucionParafiscal.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarDeclaracionContribucionParafiscal(resultado);
		return resultado;
	}

	/**
	 * Consulta genérica de los datos de DeclaracionContribucionParafiscal.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosDeclaracionContribucionParafiscal(resultado);
		return resultado;
	}

	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
		unaSentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
	}

	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_PERSONA_RUT", attDeclaracionContribucionParafiscal.getIdePersonaRut());
		unaSentencia.setInt("NUM_ANIO", attDeclaracionContribucionParafiscal.getNumAnio());
		unaSentencia.setInt("NUM_PERIODO", attDeclaracionContribucionParafiscal.getNumPeriodo());
		unaSentencia.setLong("IDE_SOLICITUD", attDeclaracionContribucionParafiscal.getIdeSolicitud());
		unaSentencia.setInt("IDE_TIPO_DOCUMENTO", attDeclaracionContribucionParafiscal.getIdeTipoDocumento());
		unaSentencia.setString("NUM_IDENTIFICACION", attDeclaracionContribucionParafiscal.getNumIdentificacion());
		unaSentencia.setString("VAL_PRIMER_APELLIDO", attDeclaracionContribucionParafiscal.getValPrimerApellido());
		unaSentencia.setString("VAL_SEGUNDO_APELLIDO", attDeclaracionContribucionParafiscal.getValSegundoApellido());
		unaSentencia.setString("VAL_PRIMER_NOMBRE", attDeclaracionContribucionParafiscal.getValPrimerNombre());
		unaSentencia.setString("VAL_OTROS_NOMBRES", attDeclaracionContribucionParafiscal.getValOtrosNombres());
		unaSentencia.setString("VAL_RAZON_SOCIAL", attDeclaracionContribucionParafiscal.getValRazonSocial());
		unaSentencia.setLong("VAL_TOTAL_BOL_VENDIDA", attDeclaracionContribucionParafiscal.getValTotalBolVendida());
		unaSentencia.setLong("VAL_TOTAL_DER_CORTESIA", attDeclaracionContribucionParafiscal.getValTotalDerCortesia());
		unaSentencia.setLong("VAL_TOTAL_DER_PATROCINIO", attDeclaracionContribucionParafiscal.getValTotalDerPatrocinio());
		unaSentencia.setLong("VAL_TOTAL_CRUCES", attDeclaracionContribucionParafiscal.getValTotalCruces());
		unaSentencia.setLong("VAL_SUBTOTAL_RETENCIONES", attDeclaracionContribucionParafiscal.getValSubtotalRetenciones());
		unaSentencia.setLong("VAL_SUBTOTAL_DEVOLUCIONES", attDeclaracionContribucionParafiscal.getValSubtotalDevoluciones());
		unaSentencia.setInt("VAL_TIPO_DECLARACION", attDeclaracionContribucionParafiscal.getValTipoDeclaracion());
		unaSentencia.setLong("NUM_FORMULARIO_ANTERIOR", attDeclaracionContribucionParafiscal.getNumFormularioAnterior());
		unaSentencia.setString("VAL_CLASE_PRODUCTOR", attDeclaracionContribucionParafiscal.getValClaseProductor());
		unaSentencia.setString("VAL_NUM_REGISTRO_PRODUCTOR", attDeclaracionContribucionParafiscal.getValNumRegistroProductor());
		unaSentencia.setLong("VAL_FECHA_PRESENTACION", attDeclaracionContribucionParafiscal.getValFechaPresentacion());
		unaSentencia.setLong("IDE_DOCUMENTO_480", attDeclaracionContribucionParafiscal.getIdeDocumento480());
		unaSentencia.setLong("NUM_REPETICION_DOC_480", attDeclaracionContribucionParafiscal.getNumRepeticionDoc480());
		unaSentencia.setLong("IDE_USUARIO_CAMBIO", attDeclaracionContribucionParafiscal.getIdeUsuarioCambio());
		unaSentencia.setTimestamp("FEC_CAMBIO", attDeclaracionContribucionParafiscal.getFecCambio());
	}

	/**
	 * Construye un objeto DeclaracionContribucionParafiscal con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarDeclaracionContribucionParafiscal(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toDeclaracionContribucionParafiscal = completarDeclaracionContribucionParafiscal(resultado);
	}

	/**
	 * Construye objetos DeclaracionContribucionParafiscal con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosDeclaracionContribucionParafiscal(IDDataSet resultado) throws SQLException {
		objetosDeclaracionContribucionParafiscal = new ArrayList<DDeclaracionContribucionParafiscalTO>();
		toDeclaracionContribucionParafiscal = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DDeclaracionContribucionParafiscalTO objeto = completarDeclaracionContribucionParafiscal(resultado);
			objetosDeclaracionContribucionParafiscal.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto DeclaracionContribucionParafiscal con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un DeclaracionContribucionParafiscal
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DDeclaracionContribucionParafiscalTO completarDeclaracionContribucionParafiscal(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DDeclaracionContribucionParafiscalPKTO pk = new DDeclaracionContribucionParafiscalPKTO();
		//java.lang.Long
		pk.setIdeDocumentoContParaf(resultado.getLongWrapper("IDE_DOCUMENTO_CONT_PARAF"));
		//java.lang.Integer
		pk.setNumRepeticionDocContParf(resultado.getIntWrapper("NUM_REPETICION_DOC_CONT_PARF"));

		// Conformar el objeto Att
		DDeclaracionContribucionParafiscalAttTO att = new DDeclaracionContribucionParafiscalAttTO();
		//java.lang.Long
if (resultado.getValorPorNombre("IDE_PERSONA_RUT") != null) {
			att.setIdePersonaRut(resultado.getLongWrapper("IDE_PERSONA_RUT"));
		}
		//java.lang.Short
		att.setNumAnio(resultado.getIntWrapper("NUM_ANIO"));
		//java.lang.Byte
		att.setNumPeriodo(resultado.getIntWrapper("NUM_PERIODO"));
		//java.lang.Long
		att.setIdeSolicitud(resultado.getLongWrapper("IDE_SOLICITUD"));
		//java.lang.Integer
		att.setIdeTipoDocumento(resultado.getIntWrapper("IDE_TIPO_DOCUMENTO"));
		//java.lang.String
		att.setNumIdentificacion(resultado.getString("NUM_IDENTIFICACION"));
		//java.lang.String
		att.setValPrimerApellido(resultado.getString("VAL_PRIMER_APELLIDO"));
		//java.lang.String
		att.setValSegundoApellido(resultado.getString("VAL_SEGUNDO_APELLIDO"));
		//java.lang.String
		att.setValPrimerNombre(resultado.getString("VAL_PRIMER_NOMBRE"));
		//java.lang.String
		att.setValOtrosNombres(resultado.getString("VAL_OTROS_NOMBRES"));
		//java.lang.String
		att.setValRazonSocial(resultado.getString("VAL_RAZON_SOCIAL"));
		//java.lang.Long
		att.setValTotalBolVendida(resultado.getLongWrapper("VAL_TOTAL_BOL_VENDIDA"));
		//java.lang.Long
		att.setValTotalDerCortesia(resultado.getLongWrapper("VAL_TOTAL_DER_CORTESIA"));
		//java.lang.Long
		att.setValTotalDerPatrocinio(resultado.getLongWrapper("VAL_TOTAL_DER_PATROCINIO"));
		//java.lang.Long
		att.setValTotalCruces(resultado.getLongWrapper("VAL_TOTAL_CRUCES"));
		//java.lang.Long
		att.setValSubtotalRetenciones(resultado.getLongWrapper("VAL_SUBTOTAL_RETENCIONES"));
		//java.lang.Long
		att.setValSubtotalDevoluciones(resultado.getLongWrapper("VAL_SUBTOTAL_DEVOLUCIONES"));
		//java.lang.Integer
		att.setValTipoDeclaracion(resultado.getIntWrapper("VAL_TIPO_DECLARACION"));
		//java.lang.Long
		if (resultado.getValorPorNombre("NUM_FORMULARIO_ANTERIOR") != null) {
			att.setNumFormularioAnterior(resultado.getLongWrapper("NUM_FORMULARIO_ANTERIOR"));
		}
		//java.lang.String
		att.setValClaseProductor(resultado.getString("VAL_CLASE_PRODUCTOR"));
		//java.lang.String
		att.setValNumRegistroProductor(resultado.getString("VAL_NUM_REGISTRO_PRODUCTOR"));
		//java.lang.Long
		att.setValFechaPresentacion(resultado.getLongWrapper("VAL_FECHA_PRESENTACION"));
		//java.lang.Long
		if (resultado.getValorPorNombre("IDE_DOCUMENTO_480") != null) {
			att.setIdeDocumento480(resultado.getLongWrapper("IDE_DOCUMENTO_480"));
		}
		//java.lang.Long
		if (resultado.getValorPorNombre("NUM_REPETICION_DOC_480") != null) {
			att.setNumRepeticionDoc480(resultado.getIntWrapper("NUM_REPETICION_DOC_480"));
		}
		//java.lang.Long
		att.setIdeUsuarioCambio(resultado.getLongWrapper("IDE_USUARIO_CAMBIO"));
		//java.sql.Date
		att.setFecCambio((Timestamp)resultado.getValorPorNombre("FEC_CAMBIO"));

		// Conformar el TO
		DDeclaracionContribucionParafiscalTO to = new DDeclaracionContribucionParafiscalTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}

	/**
	 * Devuelve el objeto DeclaracionContribucionParafiscal que se haya consultado.
	 * @return Un objeto DDeclaracionContribucionParafiscalTO
	 */
	public DDeclaracionContribucionParafiscalTO getDeclaracionContribucionParafiscal() {
		return toDeclaracionContribucionParafiscal;
	}

	/**
	 * Devuelve la colección de objetos DeclaracionContribucionParafiscal que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalTO
	 */
	public Collection<DDeclaracionContribucionParafiscalTO> getColeccionDeclaracionContribucionParafiscal() {
		return objetosDeclaracionContribucionParafiscal;
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
			parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscal",toDeclaracionContribucionParafiscal);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal",pkDeclaracionContribucionParafiscal);
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal",attDeclaracionContribucionParafiscal);

			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getNumAnio()",attDeclaracionContribucionParafiscal.getNumAnio());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getNumPeriodo()",attDeclaracionContribucionParafiscal.getNumPeriodo());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getIdeSolicitud()",attDeclaracionContribucionParafiscal.getIdeSolicitud());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getIdeTipoDocumento()",attDeclaracionContribucionParafiscal.getIdeTipoDocumento());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getNumIdentificacion()",attDeclaracionContribucionParafiscal.getNumIdentificacion());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTotalBolVendida()",attDeclaracionContribucionParafiscal.getValTotalBolVendida());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTotalDerCortesia()",attDeclaracionContribucionParafiscal.getValTotalDerCortesia());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTotalDerPatrocinio()",attDeclaracionContribucionParafiscal.getValTotalDerPatrocinio());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTotalCruces()",attDeclaracionContribucionParafiscal.getValTotalCruces());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValSubtotalRetenciones()",attDeclaracionContribucionParafiscal.getValSubtotalRetenciones());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValSubtotalDevoluciones()",attDeclaracionContribucionParafiscal.getValSubtotalDevoluciones());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTipoDeclaracion()",attDeclaracionContribucionParafiscal.getValTipoDeclaracion());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValFechaPresentacion()",attDeclaracionContribucionParafiscal.getValFechaPresentacion());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getIdeUsuarioCambio()",attDeclaracionContribucionParafiscal.getIdeUsuarioCambio());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getFecCambio()",attDeclaracionContribucionParafiscal.getFecCambio());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal",pkDeclaracionContribucionParafiscal);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		}

		if (tipoOperacion == CONSULTA_GENERICA) {
			parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscal",toDeclaracionContribucionParafiscal);
		}


		validarParametros(operacion,parametros);
		return true;
	}
}