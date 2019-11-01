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
 * <p>Descripcion: Objeto de acceso a datos para DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAODeclaracionContribucionParafiscalEvento extends DDAO implements IDDAODeclaracionContribucionParafiscalEvento {
	/** colección de objetos DDeclaracionContribucionParafiscalEventoTO */
	private Collection<DDeclaracionContribucionParafiscalEventoTO> objetosDeclaracionContribucionParafiscalEvento;
	/** Objeto de transporte de DeclaracionContribucionParafiscalEvento */
	private DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento;
	/** Llave primaria de DeclaracionContribucionParafiscalEvento */
	private DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento;
	/** Atributos de DeclaracionContribucionParafiscalEvento */
	private DDeclaracionContribucionParafiscalEventoAttTO attDeclaracionContribucionParafiscalEvento;
	/** Llave primaria de DeclaracionContribucionParafiscal */
	private DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDeclaracionContribucionParafiscalEvento Llave primaria de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializarConsultarPorPK(DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
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
	 * Inicializa la creación de DeclaracionContribucionParafiscalEvento con colección.
	 * @param tosDeclaracionContribucionParafiscalEvento Collection de Objetos de Transporte de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializarCrear(Collection<DDeclaracionContribucionParafiscalEventoTO> tosDeclaracionContribucionParafiscalEvento) {
		setTipoOperacion(CREAR_CON_COLECCION);
		this.objetosDeclaracionContribucionParafiscalEvento = tosDeclaracionContribucionParafiscalEvento;
	}
	
	/**
	 * Inicializa la creaciónn de DeclaracionContribucionParafiscalEvento.
	 * @param toDeclaracionContribucionParafiscalEvento Objeto de Transporte de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializarCrear(DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento) {
		setTipoOperacion(CREAR);
		this.toDeclaracionContribucionParafiscalEvento = toDeclaracionContribucionParafiscalEvento;
		if (toDeclaracionContribucionParafiscalEvento != null) {
			pkDeclaracionContribucionParafiscalEvento = this.toDeclaracionContribucionParafiscalEvento.getPK();
			attDeclaracionContribucionParafiscalEvento = this.toDeclaracionContribucionParafiscalEvento.getAtt();
		}
	}

	/**
	 * Inicializa la actualización de DeclaracionContribucionParafiscalEvento.
	 * @param toDeclaracionContribucionParafiscalEvento Objeto de Transporte de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializarActualizar(DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento) {
		setTipoOperacion(ACTUALIZAR);
		this.toDeclaracionContribucionParafiscalEvento = toDeclaracionContribucionParafiscalEvento;
		if (toDeclaracionContribucionParafiscalEvento != null) {
			pkDeclaracionContribucionParafiscalEvento = this.toDeclaracionContribucionParafiscalEvento.getPK();
			attDeclaracionContribucionParafiscalEvento = this.toDeclaracionContribucionParafiscalEvento.getAtt();
		}
	}

	/**
	 * Inicializa la eliminación de DeclaracionContribucionParafiscalEvento.
	 * @param pkDeclaracionContribucionParafiscalEvento Llave primaria de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializarEliminar(DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento) {
		setTipoOperacion(ELIMINAR);
		this.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
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
	 * Inicializa la consulta genérica de DeclaracionContribucionParafiscalEvento.
	 * @param attDeclaracionContribucionParafiscalEvento Atributos de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toDeclaracionContribucionParafiscalEvento = toDeclaracionContribucionParafiscalEvento;
		if (toDeclaracionContribucionParafiscalEvento != null) {
			pkDeclaracionContribucionParafiscalEvento = this.toDeclaracionContribucionParafiscalEvento.getPK();
			attDeclaracionContribucionParafiscalEvento = this.toDeclaracionContribucionParafiscalEvento.getAtt();
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
				sql.append("insert into DIL_DECLA_CONTRIB_EVENTOS")
					.append(" (IDE_DOCUMENTO_CONT_PARAF, NUM_REPETICION_DOC_CONT_PARF, IDE_OCURRENCIA, IDE_ITEM, VAL_NUM_EVENTO, IDE_MUNICIPIO_EVENTO, IDE_DEPARTAMENTO_EVENTO, VAL_NOMB_LUGAR_EVENTO, FEC_REALIZACION_EVENTO, NUM_BOLETAS_VENDIDAS, VAL_BOLETAS_VENDIDAS, NUM_BOL_VENDIDAS_INF_3UVT, VAL_BOL_VENDIDAS_INF_3UVT, NUM_BOL_VENDIDAS_SUP_3UVT, VAL_BOL_VENDIDAS_SUP_3UVT, NUM_CORTESIAS_ENTREGADAS, VAL_CORTESIAS_ENTREGADAS, NUM_CORT_ENTREG_INF_3UVT, VAL_CORT_ENTREG_INF_3UVT, NUM_CORT_ENTREG_SUP_3UVT, VAL_CORT_ENTREG_SUP_3UVT, NUM_BOLETAS_PATROCINIO, VAL_BOLETAS_PATROCINIO, NUM_BOL_PATROC_INF_3UVT, VAL_BOL_PATROC_INF_3UVT, NUM_BOL_PATROC_SUP_3UVT, VAL_BOL_PATROC_SUP_3UVT, VAL_BASE_GRAVABLE_CP, VAL_CONTRIB_PARAFISCAL, VAL_RETENCION_CP, VAL_PRIMER_APELLIDO_OP, VAL_SEGUNDO_APELLIDO_OP, VAL_PRIMER_NOMBRE_OP, VAL_OTROS_NOMBRES_OP, VAL_RAZON_SOCIAL_OP, NUM_BOLETAS_PAGO_EXCESO, VAL_BOLETAS_PAGO_EXCESO, IDE_USUARIO_CAMBIO, FEC_CAMBIO)")
					.append(" VALUES (:IDE_DOCUMENTO_CONT_PARAF, :NUM_REPETICION_DOC_CONT_PARF, :IDE_OCURRENCIA, :IDE_ITEM, :VAL_NUM_EVENTO, :IDE_MUNICIPIO_EVENTO, :IDE_DEPARTAMENTO_EVENTO, :VAL_NOMB_LUGAR_EVENTO, :FEC_REALIZACION_EVENTO, :NUM_BOLETAS_VENDIDAS, :VAL_BOLETAS_VENDIDAS, :NUM_BOL_VENDIDAS_INF_3UVT, :VAL_BOL_VENDIDAS_INF_3UVT, :NUM_BOL_VENDIDAS_SUP_3UVT, :VAL_BOL_VENDIDAS_SUP_3UVT, :NUM_CORTESIAS_ENTREGADAS, :VAL_CORTESIAS_ENTREGADAS, :NUM_CORT_ENTREG_INF_3UVT, :VAL_CORT_ENTREG_INF_3UVT, :NUM_CORT_ENTREG_SUP_3UVT, :VAL_CORT_ENTREG_SUP_3UVT, :NUM_BOLETAS_PATROCINIO, :VAL_BOLETAS_PATROCINIO, :NUM_BOL_PATROC_INF_3UVT, :VAL_BOL_PATROC_INF_3UVT, :NUM_BOL_PATROC_SUP_3UVT, :VAL_BOL_PATROC_SUP_3UVT, :VAL_BASE_GRAVABLE_CP, :VAL_CONTRIB_PARAFISCAL, :VAL_RETENCION_CP, :VAL_PRIMER_APELLIDO_OP, :VAL_SEGUNDO_APELLIDO_OP, :VAL_PRIMER_NOMBRE_OP, :VAL_OTROS_NOMBRES_OP, :VAL_RAZON_SOCIAL_OP, :NUM_BOLETAS_PAGO_EXCESO, :VAL_BOLETAS_PAGO_EXCESO, :IDE_USUARIO_CAMBIO, :FEC_CAMBIO)");
				m.put("sentencia1", sql.toString());
				break;
			case ACTUALIZAR:
				sql.append("update DIL_DECLA_CONTRIB_EVENTOS")
					.append(" set VAL_NUM_EVENTO=:VAL_NUM_EVENTO, IDE_MUNICIPIO_EVENTO=:IDE_MUNICIPIO_EVENTO, IDE_DEPARTAMENTO_EVENTO=:IDE_DEPARTAMENTO_EVENTO, VAL_NOMB_LUGAR_EVENTO=:VAL_NOMB_LUGAR_EVENTO, FEC_REALIZACION_EVENTO=:FEC_REALIZACION_EVENTO, NUM_BOLETAS_VENDIDAS=:NUM_BOLETAS_VENDIDAS, VAL_BOLETAS_VENDIDAS=:VAL_BOLETAS_VENDIDAS, NUM_BOL_VENDIDAS_INF_3UVT=:NUM_BOL_VENDIDAS_INF_3UVT, VAL_BOL_VENDIDAS_INF_3UVT=:VAL_BOL_VENDIDAS_INF_3UVT, NUM_BOL_VENDIDAS_SUP_3UVT=:NUM_BOL_VENDIDAS_SUP_3UVT, VAL_BOL_VENDIDAS_SUP_3UVT=:VAL_BOL_VENDIDAS_SUP_3UVT, NUM_CORTESIAS_ENTREGADAS=:NUM_CORTESIAS_ENTREGADAS, VAL_CORTESIAS_ENTREGADAS=:VAL_CORTESIAS_ENTREGADAS, NUM_CORT_ENTREG_INF_3UVT=:NUM_CORT_ENTREG_INF_3UVT, VAL_CORT_ENTREG_INF_3UVT=:VAL_CORT_ENTREG_INF_3UVT, NUM_CORT_ENTREG_SUP_3UVT=:NUM_CORT_ENTREG_SUP_3UVT, VAL_CORT_ENTREG_SUP_3UVT=:VAL_CORT_ENTREG_SUP_3UVT, NUM_BOLETAS_PATROCINIO=:NUM_BOLETAS_PATROCINIO, VAL_BOLETAS_PATROCINIO=:VAL_BOLETAS_PATROCINIO, NUM_BOL_PATROC_INF_3UVT=:NUM_BOL_PATROC_INF_3UVT, VAL_BOL_PATROC_INF_3UVT=:VAL_BOL_PATROC_INF_3UVT, NUM_BOL_PATROC_SUP_3UVT=:NUM_BOL_PATROC_SUP_3UVT, VAL_BOL_PATROC_SUP_3UVT=:VAL_BOL_PATROC_SUP_3UVT, VAL_BASE_GRAVABLE_CP=:VAL_BASE_GRAVABLE_CP, VAL_CONTRIB_PARAFISCAL=:VAL_CONTRIB_PARAFISCAL, VAL_RETENCION_CP=:VAL_RETENCION_CP, VAL_PRIMER_APELLIDO_OP=:VAL_PRIMER_APELLIDO_OP, VAL_SEGUNDO_APELLIDO_OP=:VAL_SEGUNDO_APELLIDO_OP, VAL_PRIMER_NOMBRE_OP=:VAL_PRIMER_NOMBRE_OP, VAL_OTROS_NOMBRES_OP=:VAL_OTROS_NOMBRES_OP, VAL_RAZON_SOCIAL_OP=:VAL_RAZON_SOCIAL_OP, NUM_BOLETAS_PAGO_EXCESO=:NUM_BOLETAS_PAGO_EXCESO, VAL_BOLETAS_PAGO_EXCESO=:VAL_BOLETAS_PAGO_EXCESO, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO, FEC_CAMBIO=:FEC_CAMBIO")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF and IDE_OCURRENCIA=:IDE_OCURRENCIA and IDE_ITEM=:IDE_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR:
				sql.append("delete from DIL_DECLA_CONTRIB_EVENTOS")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF and IDE_OCURRENCIA=:IDE_OCURRENCIA and IDE_ITEM=:IDE_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL:
				sql.append("delete from DIL_DECLA_CONTRIB_EVENTOS")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF ");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_PK:
				sql.append("select * from DIL_DECLA_CONTRIB_EVENTOS")
					.append(" where IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF and NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF and IDE_OCURRENCIA=:IDE_OCURRENCIA and IDE_ITEM=:IDE_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTA_GENERICA:
				sql.append("select * from DIL_DECLA_CONTRIB_EVENTOS where ")
					.append(generarFiltroGenerico());
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL:
				sql.append("select * from DIL_DECLA_CONTRIB_EVENTOS")
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

		if (pkDeclaracionContribucionParafiscalEvento != null) {

			if (pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO_CONT_PARAF=:IDE_DOCUMENTO_CONT_PARAF");
				append = true;

			}
			if (pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION_DOC_CONT_PARF=:NUM_REPETICION_DOC_CONT_PARF");
				append = true;

			}
			if (pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_OCURRENCIA=:IDE_OCURRENCIA");
				append = true;

			}
			if (pkDeclaracionContribucionParafiscalEvento.getIdeItem() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_ITEM=:IDE_ITEM");
				append = true;

			}
		}

		if (attDeclaracionContribucionParafiscalEvento != null) {

			if (attDeclaracionContribucionParafiscalEvento.getValNumEvento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_NUM_EVENTO=:VAL_NUM_EVENTO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getIdeMunicipioEvento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_MUNICIPIO_EVENTO=:IDE_MUNICIPIO_EVENTO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getIdeDepartamentoEvento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DEPARTAMENTO_EVENTO=:IDE_DEPARTAMENTO_EVENTO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValNombLugarEvento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_NOMB_LUGAR_EVENTO=:VAL_NOMB_LUGAR_EVENTO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getFecRealizacionEvento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_REALIZACION_EVENTO=:FEC_REALIZACION_EVENTO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBoletasVendidas() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOLETAS_VENDIDAS=:NUM_BOLETAS_VENDIDAS");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValBoletasVendidas() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BOLETAS_VENDIDAS=:VAL_BOLETAS_VENDIDAS");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBolVendidasInf3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOL_VENDIDAS_INF_3UVT=:NUM_BOL_VENDIDAS_INF_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValBolVendidasInf3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BOL_VENDIDAS_INF_3UVT=:VAL_BOL_VENDIDAS_INF_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBolVendidasSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOL_VENDIDAS_SUP_3UVT=:NUM_BOL_VENDIDAS_SUP_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValBolVendidasSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BOL_VENDIDAS_SUP_3UVT=:VAL_BOL_VENDIDAS_SUP_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumCortesiasEntregadas() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_CORTESIAS_ENTREGADAS=:NUM_CORTESIAS_ENTREGADAS");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValCortesiasEntregadas() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_CORTESIAS_ENTREGADAS=:VAL_CORTESIAS_ENTREGADAS");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumCortEntregInf3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_CORT_ENTREG_INF_3UVT=:NUM_CORT_ENTREG_INF_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValCortEntregInf3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_CORT_ENTREG_INF_3UVT=:VAL_CORT_ENTREG_INF_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumCortEntregSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_CORT_ENTREG_SUP_3UVT=:NUM_CORT_ENTREG_SUP_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValCortEntregSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_CORT_ENTREG_SUP_3UVT=:VAL_CORT_ENTREG_SUP_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBoletasPatrocinio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOLETAS_PATROCINIO=:NUM_BOLETAS_PATROCINIO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValBoletasPatrocinio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BOLETAS_PATROCINIO=:VAL_BOLETAS_PATROCINIO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBolPatrocInf3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOL_PATROC_INF_3UVT=:NUM_BOL_PATROC_INF_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValBolPatrocInf3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BOL_PATROC_INF_3UVT=:VAL_BOL_PATROC_INF_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBolPatrocSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOL_PATROC_SUP_3UVT=:NUM_BOL_PATROC_SUP_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValBolPatrocSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BOL_PATROC_SUP_3UVT=:VAL_BOL_PATROC_SUP_3UVT");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValBaseGravableCp() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BASE_GRAVABLE_CP=:VAL_BASE_GRAVABLE_CP");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValContribParafiscal() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_CONTRIB_PARAFISCAL=:VAL_CONTRIB_PARAFISCAL");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValRetencionCp() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_RETENCION_CP=:VAL_RETENCION_CP");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValPrimerApellidoOp() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_PRIMER_APELLIDO_OP=:VAL_PRIMER_APELLIDO_OP");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValSegundoApellidoOp() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_SEGUNDO_APELLIDO_OP=:VAL_SEGUNDO_APELLIDO_OP");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValPrimerNombreOp() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_PRIMER_NOMBRE_OP=:VAL_PRIMER_NOMBRE_OP");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValOtrosNombresOp() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_OTROS_NOMBRES_OP=:VAL_OTROS_NOMBRES_OP");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValRazonSocialOp() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_RAZON_SOCIAL_OP=:VAL_RAZON_SOCIAL_OP");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBoletasPagoExceso() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOLETAS_PAGO_EXCESO=:NUM_BOLETAS_PAGO_EXCESO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getValBoletasPagoExceso() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BOLETAS_PAGO_EXCESO=:VAL_BOLETAS_PAGO_EXCESO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getIdeUsuarioCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
				append = true;

			}
			if (attDeclaracionContribucionParafiscalEvento.getFecCambio() != null) {
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
		if (pkDeclaracionContribucionParafiscalEvento != null) {
			if (pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf());
			}
			if (pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf() != null) {
				unaSentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf());
			}
			if (pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia() != null) {
				unaSentencia.setInt("IDE_OCURRENCIA", pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia());
			}
			if (pkDeclaracionContribucionParafiscalEvento.getIdeItem() != null) {
				unaSentencia.setInt("IDE_ITEM", pkDeclaracionContribucionParafiscalEvento.getIdeItem());
			}
		}

		if (attDeclaracionContribucionParafiscalEvento != null) {
			if (attDeclaracionContribucionParafiscalEvento.getValNumEvento() != null) {
				unaSentencia.setString("VAL_NUM_EVENTO", attDeclaracionContribucionParafiscalEvento.getValNumEvento());
			}
			if (attDeclaracionContribucionParafiscalEvento.getIdeMunicipioEvento() != null) {
				unaSentencia.setInt("IDE_MUNICIPIO_EVENTO", attDeclaracionContribucionParafiscalEvento.getIdeMunicipioEvento());
			}
			if (attDeclaracionContribucionParafiscalEvento.getIdeDepartamentoEvento() != null) {
				unaSentencia.setInt("IDE_DEPARTAMENTO_EVENTO", attDeclaracionContribucionParafiscalEvento.getIdeDepartamentoEvento());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValNombLugarEvento() != null) {
				unaSentencia.setString("VAL_NOMB_LUGAR_EVENTO", attDeclaracionContribucionParafiscalEvento.getValNombLugarEvento());
			}
			if (attDeclaracionContribucionParafiscalEvento.getFecRealizacionEvento() != null) {
				unaSentencia.setInt("FEC_REALIZACION_EVENTO", attDeclaracionContribucionParafiscalEvento.getFecRealizacionEvento());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBoletasVendidas() != null) {
				unaSentencia.setInt("NUM_BOLETAS_VENDIDAS", attDeclaracionContribucionParafiscalEvento.getNumBoletasVendidas());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValBoletasVendidas() != null) {
				unaSentencia.setLong("VAL_BOLETAS_VENDIDAS", attDeclaracionContribucionParafiscalEvento.getValBoletasVendidas());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBolVendidasInf3uvt() != null) {
				unaSentencia.setInt("NUM_BOL_VENDIDAS_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getNumBolVendidasInf3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValBolVendidasInf3uvt() != null) {
				unaSentencia.setLong("VAL_BOL_VENDIDAS_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getValBolVendidasInf3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBolVendidasSup3uvt() != null) {
				unaSentencia.setInt("NUM_BOL_VENDIDAS_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getNumBolVendidasSup3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValBolVendidasSup3uvt() != null) {
				unaSentencia.setLong("VAL_BOL_VENDIDAS_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getValBolVendidasSup3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumCortesiasEntregadas() != null) {
				unaSentencia.setInt("NUM_CORTESIAS_ENTREGADAS", attDeclaracionContribucionParafiscalEvento.getNumCortesiasEntregadas());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValCortesiasEntregadas() != null) {
				unaSentencia.setLong("VAL_CORTESIAS_ENTREGADAS", attDeclaracionContribucionParafiscalEvento.getValCortesiasEntregadas());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumCortEntregInf3uvt() != null) {
				unaSentencia.setInt("NUM_CORT_ENTREG_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getNumCortEntregInf3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValCortEntregInf3uvt() != null) {
				unaSentencia.setLong("VAL_CORT_ENTREG_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getValCortEntregInf3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumCortEntregSup3uvt() != null) {
				unaSentencia.setInt("NUM_CORT_ENTREG_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getNumCortEntregSup3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValCortEntregSup3uvt() != null) {
				unaSentencia.setLong("VAL_CORT_ENTREG_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getValCortEntregSup3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBoletasPatrocinio() != null) {
				unaSentencia.setInt("NUM_BOLETAS_PATROCINIO", attDeclaracionContribucionParafiscalEvento.getNumBoletasPatrocinio());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValBoletasPatrocinio() != null) {
				unaSentencia.setLong("VAL_BOLETAS_PATROCINIO", attDeclaracionContribucionParafiscalEvento.getValBoletasPatrocinio());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBolPatrocInf3uvt() != null) {
				unaSentencia.setInt("NUM_BOL_PATROC_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getNumBolPatrocInf3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValBolPatrocInf3uvt() != null) {
				unaSentencia.setLong("VAL_BOL_PATROC_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getValBolPatrocInf3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBolPatrocSup3uvt() != null) {
				unaSentencia.setInt("NUM_BOL_PATROC_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getNumBolPatrocSup3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValBolPatrocSup3uvt() != null) {
				unaSentencia.setLong("VAL_BOL_PATROC_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getValBolPatrocSup3uvt());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValBaseGravableCp() != null) {
				unaSentencia.setLong("VAL_BASE_GRAVABLE_CP", attDeclaracionContribucionParafiscalEvento.getValBaseGravableCp());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValContribParafiscal() != null) {
				unaSentencia.setLong("VAL_CONTRIB_PARAFISCAL", attDeclaracionContribucionParafiscalEvento.getValContribParafiscal());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValRetencionCp() != null) {
				unaSentencia.setLong("VAL_RETENCION_CP", attDeclaracionContribucionParafiscalEvento.getValRetencionCp());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValPrimerApellidoOp() != null) {
				unaSentencia.setString("VAL_PRIMER_APELLIDO_OP", attDeclaracionContribucionParafiscalEvento.getValPrimerApellidoOp());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValSegundoApellidoOp() != null) {
				unaSentencia.setString("VAL_SEGUNDO_APELLIDO_OP", attDeclaracionContribucionParafiscalEvento.getValSegundoApellidoOp());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValPrimerNombreOp() != null) {
				unaSentencia.setString("VAL_PRIMER_NOMBRE_OP", attDeclaracionContribucionParafiscalEvento.getValPrimerNombreOp());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValOtrosNombresOp() != null) {
				unaSentencia.setString("VAL_OTROS_NOMBRES_OP", attDeclaracionContribucionParafiscalEvento.getValOtrosNombresOp());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValRazonSocialOp() != null) {
				unaSentencia.setString("VAL_RAZON_SOCIAL_OP", attDeclaracionContribucionParafiscalEvento.getValRazonSocialOp());
			}
			if (attDeclaracionContribucionParafiscalEvento.getNumBoletasPagoExceso() != null) {
				unaSentencia.setInt("NUM_BOLETAS_PAGO_EXCESO", attDeclaracionContribucionParafiscalEvento.getNumBoletasPagoExceso());
			}
			if (attDeclaracionContribucionParafiscalEvento.getValBoletasPagoExceso() != null) {
				unaSentencia.setLong("VAL_BOLETAS_PAGO_EXCESO", attDeclaracionContribucionParafiscalEvento.getValBoletasPagoExceso());
			}			
			if (attDeclaracionContribucionParafiscalEvento.getIdeUsuarioCambio() != null) {
				unaSentencia.setLong("IDE_USUARIO_CAMBIO", attDeclaracionContribucionParafiscalEvento.getIdeUsuarioCambio());
			}
			if (attDeclaracionContribucionParafiscalEvento.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attDeclaracionContribucionParafiscalEvento.getFecCambio());
			}
		}
	}

	/**
	 * Guarda los datos de DeclaracionContribucionParafiscalEvento.
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
	 * Elimina registros de DeclaracionContribucionParafiscalEvento.
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
	 * Consulta los datos de DeclaracionContribucionParafiscalEvento.
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
	 * Crea un registro de DeclaracionContribucionParafiscalEvento.
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
     * Crea los registros de DeclaracionContribucionParafiscalEvento con colección.
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
	 * Actualiza los datos de DeclaracionContribucionParafiscalEvento.
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
	 * Actualiza los datos de DeclaracionContribucionParafiscalEvento.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarDeclaracionContribucionParafiscalEvento(resultado);
		return resultado;
	}
	
	private void eliminarPorDeclaracionContribucionParafiscal() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
		sentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		sentencia.ejecutar();		
	}
	
	/**
	 * Consulta genérica de los datos de DeclaracionContribucionParafiscalEvento.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosDeclaracionContribucionParafiscalEvento(resultado);
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
		cargarObjetosDeclaracionContribucionParafiscalEvento(resultado);
		return resultado;
	}

	/**
   * Asigna todos los valores de una lista de DDocumentoSoporteTO.
   * @param unaSentencia Sentencia para asignación
   * @throws SQLException Si ocurre un error al asignar los valores
   */
  private void asignarValoresBatch(DSentenciaSQLBatch unaSentenciaBatch) throws SQLException {
      for(DDeclaracionContribucionParafiscalEventoTO itemTO : objetosDeclaracionContribucionParafiscalEvento){
    	  	unaSentenciaBatch.setLong("IDE_DOCUMENTO_CONT_PARAF", itemTO.getPK().getIdeDocumentoContParaf());
    	  	unaSentenciaBatch.setInt("NUM_REPETICION_DOC_CONT_PARF", itemTO.getPK().getNumRepeticionDocContParf());
    	  	unaSentenciaBatch.setInt("IDE_OCURRENCIA", itemTO.getPK().getIdeOcurrencia());
    	  	unaSentenciaBatch.setInt("IDE_ITEM", itemTO.getPK().getIdeItem());
    	  	unaSentenciaBatch.setString("VAL_NUM_EVENTO", itemTO.getAtt().getValNumEvento());
  			unaSentenciaBatch.setInt("IDE_MUNICIPIO_EVENTO", itemTO.getAtt().getIdeMunicipioEvento());
  			unaSentenciaBatch.setInt("IDE_DEPARTAMENTO_EVENTO", itemTO.getAtt().getIdeDepartamentoEvento());
  			unaSentenciaBatch.setString("VAL_NOMB_LUGAR_EVENTO", itemTO.getAtt().getValNombLugarEvento());
  			unaSentenciaBatch.setInt("FEC_REALIZACION_EVENTO", itemTO.getAtt().getFecRealizacionEvento());
  			unaSentenciaBatch.setInt("NUM_BOLETAS_VENDIDAS", itemTO.getAtt().getNumBoletasVendidas());
  			unaSentenciaBatch.setLong("VAL_BOLETAS_VENDIDAS", itemTO.getAtt().getValBoletasVendidas());
  			unaSentenciaBatch.setInt("NUM_BOL_VENDIDAS_INF_3UVT", itemTO.getAtt().getNumBolVendidasInf3uvt());
  			unaSentenciaBatch.setLong("VAL_BOL_VENDIDAS_INF_3UVT", itemTO.getAtt().getValBolVendidasInf3uvt());
	  		unaSentenciaBatch.setInt("NUM_BOL_VENDIDAS_SUP_3UVT", itemTO.getAtt().getNumBolVendidasSup3uvt());
	  		unaSentenciaBatch.setLong("VAL_BOL_VENDIDAS_SUP_3UVT", itemTO.getAtt().getValBolVendidasSup3uvt());
	  		unaSentenciaBatch.setInt("NUM_CORTESIAS_ENTREGADAS", itemTO.getAtt().getNumCortesiasEntregadas());
	  		unaSentenciaBatch.setLong("VAL_CORTESIAS_ENTREGADAS", itemTO.getAtt().getValCortesiasEntregadas());
	  		unaSentenciaBatch.setInt("NUM_CORT_ENTREG_INF_3UVT", itemTO.getAtt().getNumCortEntregInf3uvt());
	  		unaSentenciaBatch.setLong("VAL_CORT_ENTREG_INF_3UVT", itemTO.getAtt().getValCortEntregInf3uvt());
	  		unaSentenciaBatch.setInt("NUM_CORT_ENTREG_SUP_3UVT", itemTO.getAtt().getNumCortEntregSup3uvt());
	  		unaSentenciaBatch.setLong("VAL_CORT_ENTREG_SUP_3UVT", itemTO.getAtt().getValCortEntregSup3uvt());
	  		unaSentenciaBatch.setInt("NUM_BOLETAS_PATROCINIO", itemTO.getAtt().getNumBoletasPatrocinio());
	  		unaSentenciaBatch.setLong("VAL_BOLETAS_PATROCINIO", itemTO.getAtt().getValBoletasPatrocinio());
	  		unaSentenciaBatch.setInt("NUM_BOL_PATROC_INF_3UVT", itemTO.getAtt().getNumBolPatrocInf3uvt());
	  		unaSentenciaBatch.setLong("VAL_BOL_PATROC_INF_3UVT", itemTO.getAtt().getValBolPatrocInf3uvt());
	  		unaSentenciaBatch.setInt("NUM_BOL_PATROC_SUP_3UVT", itemTO.getAtt().getNumBolPatrocSup3uvt());
	  		unaSentenciaBatch.setLong("VAL_BOL_PATROC_SUP_3UVT", itemTO.getAtt().getValBolPatrocSup3uvt());
	  		unaSentenciaBatch.setLong("VAL_BASE_GRAVABLE_CP", itemTO.getAtt().getValBaseGravableCp());
	  		unaSentenciaBatch.setLong("VAL_CONTRIB_PARAFISCAL", itemTO.getAtt().getValContribParafiscal());
	  		unaSentenciaBatch.setLong("VAL_RETENCION_CP", itemTO.getAtt().getValRetencionCp());
	  		unaSentenciaBatch.setString("VAL_PRIMER_APELLIDO_OP", itemTO.getAtt().getValPrimerApellidoOp());
	  		unaSentenciaBatch.setString("VAL_SEGUNDO_APELLIDO_OP", itemTO.getAtt().getValSegundoApellidoOp());
	  		unaSentenciaBatch.setString("VAL_PRIMER_NOMBRE_OP", itemTO.getAtt().getValPrimerNombreOp());
	  		unaSentenciaBatch.setString("VAL_OTROS_NOMBRES_OP", itemTO.getAtt().getValOtrosNombresOp());
	  		unaSentenciaBatch.setString("VAL_RAZON_SOCIAL_OP", itemTO.getAtt().getValRazonSocialOp());
	  		unaSentenciaBatch.setInt("NUM_BOLETAS_PAGO_EXCESO", itemTO.getAtt().getNumBoletasPagoExceso());
	  		unaSentenciaBatch.setLong("VAL_BOLETAS_PAGO_EXCESO", itemTO.getAtt().getValBoletasPagoExceso());
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
		unaSentencia.setLong("IDE_DOCUMENTO_CONT_PARAF", pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf());
		unaSentencia.setInt("NUM_REPETICION_DOC_CONT_PARF", pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf());
		unaSentencia.setInt("IDE_OCURRENCIA", pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia());
		unaSentencia.setInt("IDE_ITEM", pkDeclaracionContribucionParafiscalEvento.getIdeItem());
	}

	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setString("VAL_NUM_EVENTO", attDeclaracionContribucionParafiscalEvento.getValNumEvento());
		unaSentencia.setInt("IDE_MUNICIPIO_EVENTO", attDeclaracionContribucionParafiscalEvento.getIdeMunicipioEvento());
		unaSentencia.setInt("IDE_DEPARTAMENTO_EVENTO", attDeclaracionContribucionParafiscalEvento.getIdeDepartamentoEvento());
		unaSentencia.setString("VAL_NOMB_LUGAR_EVENTO", attDeclaracionContribucionParafiscalEvento.getValNombLugarEvento());
		unaSentencia.setInt("FEC_REALIZACION_EVENTO", attDeclaracionContribucionParafiscalEvento.getFecRealizacionEvento());
		unaSentencia.setInt("NUM_BOLETAS_VENDIDAS", attDeclaracionContribucionParafiscalEvento.getNumBoletasVendidas());
		unaSentencia.setLong("VAL_BOLETAS_VENDIDAS", attDeclaracionContribucionParafiscalEvento.getValBoletasVendidas());
		unaSentencia.setInt("NUM_BOL_VENDIDAS_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getNumBolVendidasInf3uvt());
		unaSentencia.setLong("VAL_BOL_VENDIDAS_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getValBolVendidasInf3uvt());
		unaSentencia.setInt("NUM_BOL_VENDIDAS_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getNumBolVendidasSup3uvt());
		unaSentencia.setLong("VAL_BOL_VENDIDAS_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getValBolVendidasSup3uvt());
		unaSentencia.setInt("NUM_CORTESIAS_ENTREGADAS", attDeclaracionContribucionParafiscalEvento.getNumCortesiasEntregadas());
		unaSentencia.setLong("VAL_CORTESIAS_ENTREGADAS", attDeclaracionContribucionParafiscalEvento.getValCortesiasEntregadas());
		unaSentencia.setInt("NUM_CORT_ENTREG_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getNumCortEntregInf3uvt());
		unaSentencia.setLong("VAL_CORT_ENTREG_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getValCortEntregInf3uvt());
		unaSentencia.setInt("NUM_CORT_ENTREG_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getNumCortEntregSup3uvt());
		unaSentencia.setLong("VAL_CORT_ENTREG_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getValCortEntregSup3uvt());
		unaSentencia.setInt("NUM_BOLETAS_PATROCINIO", attDeclaracionContribucionParafiscalEvento.getNumBoletasPatrocinio());
		unaSentencia.setLong("VAL_BOLETAS_PATROCINIO", attDeclaracionContribucionParafiscalEvento.getValBoletasPatrocinio());
		unaSentencia.setInt("NUM_BOL_PATROC_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getNumBolPatrocInf3uvt());
		unaSentencia.setLong("VAL_BOL_PATROC_INF_3UVT", attDeclaracionContribucionParafiscalEvento.getValBolPatrocInf3uvt());
		unaSentencia.setInt("NUM_BOL_PATROC_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getNumBolPatrocSup3uvt());
		unaSentencia.setLong("VAL_BOL_PATROC_SUP_3UVT", attDeclaracionContribucionParafiscalEvento.getValBolPatrocSup3uvt());
		unaSentencia.setLong("VAL_BASE_GRAVABLE_CP", attDeclaracionContribucionParafiscalEvento.getValBaseGravableCp());
		unaSentencia.setLong("VAL_CONTRIB_PARAFISCAL", attDeclaracionContribucionParafiscalEvento.getValContribParafiscal());
		unaSentencia.setLong("VAL_RETENCION_CP", attDeclaracionContribucionParafiscalEvento.getValRetencionCp());
		unaSentencia.setString("VAL_PRIMER_APELLIDO_OP", attDeclaracionContribucionParafiscalEvento.getValPrimerApellidoOp());
		unaSentencia.setString("VAL_SEGUNDO_APELLIDO_OP", attDeclaracionContribucionParafiscalEvento.getValSegundoApellidoOp());
		unaSentencia.setString("VAL_PRIMER_NOMBRE_OP", attDeclaracionContribucionParafiscalEvento.getValPrimerNombreOp());
		unaSentencia.setString("VAL_OTROS_NOMBRES_OP", attDeclaracionContribucionParafiscalEvento.getValOtrosNombresOp());
		unaSentencia.setString("VAL_RAZON_SOCIAL_OP", attDeclaracionContribucionParafiscalEvento.getValRazonSocialOp());
		unaSentencia.setInt("NUM_BOLETAS_PAGO_EXCESO", attDeclaracionContribucionParafiscalEvento.getNumBoletasPagoExceso());
		unaSentencia.setLong("VAL_BOLETAS_PAGO_EXCESO", attDeclaracionContribucionParafiscalEvento.getValBoletasPagoExceso());
		unaSentencia.setLong("IDE_USUARIO_CAMBIO", attDeclaracionContribucionParafiscalEvento.getIdeUsuarioCambio());
		unaSentencia.setTimestamp("FEC_CAMBIO", attDeclaracionContribucionParafiscalEvento.getFecCambio());
	}

	/**
	 * Construye un objeto DeclaracionContribucionParafiscalEvento con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarDeclaracionContribucionParafiscalEvento(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toDeclaracionContribucionParafiscalEvento = completarDeclaracionContribucionParafiscalEvento(resultado);
	}

	/**
	 * Construye objetos DeclaracionContribucionParafiscalEvento con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosDeclaracionContribucionParafiscalEvento(IDDataSet resultado) throws SQLException {
		objetosDeclaracionContribucionParafiscalEvento = new ArrayList<DDeclaracionContribucionParafiscalEventoTO>();
		toDeclaracionContribucionParafiscalEvento = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DDeclaracionContribucionParafiscalEventoTO objeto = completarDeclaracionContribucionParafiscalEvento(resultado);
			objetosDeclaracionContribucionParafiscalEvento.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto DeclaracionContribucionParafiscalEvento con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un DeclaracionContribucionParafiscalEvento
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DDeclaracionContribucionParafiscalEventoTO completarDeclaracionContribucionParafiscalEvento(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DDeclaracionContribucionParafiscalEventoPKTO pk = new DDeclaracionContribucionParafiscalEventoPKTO();
		//java.lang.Long
		pk.setIdeDocumentoContParaf(resultado.getLongWrapper("IDE_DOCUMENTO_CONT_PARAF"));
		//java.lang.Integer
		pk.setNumRepeticionDocContParf(resultado.getIntWrapper("NUM_REPETICION_DOC_CONT_PARF"));
		//java.lang.Integer
		pk.setIdeOcurrencia(resultado.getIntWrapper("IDE_OCURRENCIA"));
		//java.lang.Integer
		pk.setIdeItem(resultado.getIntWrapper("IDE_ITEM"));

		// Conformar el objeto Att
		DDeclaracionContribucionParafiscalEventoAttTO att = new DDeclaracionContribucionParafiscalEventoAttTO();
		//java.lang.Long
		att.setValNumEvento(resultado.getString("VAL_NUM_EVENTO"));
		//java.lang.Integer
		att.setIdeMunicipioEvento(resultado.getIntWrapper("IDE_MUNICIPIO_EVENTO"));
		//java.lang.Integer
		att.setIdeDepartamentoEvento(resultado.getIntWrapper("IDE_DEPARTAMENTO_EVENTO"));
		//java.lang.String
		att.setValNombLugarEvento(resultado.getString("VAL_NOMB_LUGAR_EVENTO"));
		//java.lang.Integer
		att.setFecRealizacionEvento(resultado.getIntWrapper("FEC_REALIZACION_EVENTO"));
		//java.lang.Integer
		att.setNumBoletasVendidas(resultado.getIntWrapper("NUM_BOLETAS_VENDIDAS"));
		//java.lang.Long
		att.setValBoletasVendidas(resultado.getLongWrapper("VAL_BOLETAS_VENDIDAS"));
		//java.lang.Integer
		att.setNumBolVendidasInf3uvt(resultado.getIntWrapper("NUM_BOL_VENDIDAS_INF_3UVT"));
		//java.lang.Long
		att.setValBolVendidasInf3uvt(resultado.getLongWrapper("VAL_BOL_VENDIDAS_INF_3UVT"));
		//java.lang.Integer
		att.setNumBolVendidasSup3uvt(resultado.getIntWrapper("NUM_BOL_VENDIDAS_SUP_3UVT"));
		//java.lang.Long
		att.setValBolVendidasSup3uvt(resultado.getLongWrapper("VAL_BOL_VENDIDAS_SUP_3UVT"));
		//java.lang.Integer
		att.setNumCortesiasEntregadas(resultado.getIntWrapper("NUM_CORTESIAS_ENTREGADAS"));
		//java.lang.Long
		att.setValCortesiasEntregadas(resultado.getLongWrapper("VAL_CORTESIAS_ENTREGADAS"));
		//java.lang.Integer
		att.setNumCortEntregInf3uvt(resultado.getIntWrapper("NUM_CORT_ENTREG_INF_3UVT"));
		//java.lang.Long
		att.setValCortEntregInf3uvt(resultado.getLongWrapper("VAL_CORT_ENTREG_INF_3UVT"));
		//java.lang.Integer
		att.setNumCortEntregSup3uvt(resultado.getIntWrapper("NUM_CORT_ENTREG_SUP_3UVT"));
		//java.lang.Long
		att.setValCortEntregSup3uvt(resultado.getLongWrapper("VAL_CORT_ENTREG_SUP_3UVT"));
		//java.lang.Integer
		att.setNumBoletasPatrocinio(resultado.getIntWrapper("NUM_BOLETAS_PATROCINIO"));
		//java.lang.Long
		att.setValBoletasPatrocinio(resultado.getLongWrapper("VAL_BOLETAS_PATROCINIO"));
		//java.lang.Integer
		att.setNumBolPatrocInf3uvt(resultado.getIntWrapper("NUM_BOL_PATROC_INF_3UVT"));
		//java.lang.Long
		att.setValBolPatrocInf3uvt(resultado.getLongWrapper("VAL_BOL_PATROC_INF_3UVT"));
		//java.lang.Integer
		att.setNumBolPatrocSup3uvt(resultado.getIntWrapper("NUM_BOL_PATROC_SUP_3UVT"));
		//java.lang.Long
		att.setValBolPatrocSup3uvt(resultado.getLongWrapper("VAL_BOL_PATROC_SUP_3UVT"));
		//java.lang.Long
		att.setValBaseGravableCp(resultado.getLongWrapper("VAL_BASE_GRAVABLE_CP"));
		//java.lang.Long
		att.setValContribParafiscal(resultado.getLongWrapper("VAL_CONTRIB_PARAFISCAL"));
		//java.lang.Long
		att.setValRetencionCp(resultado.getLongWrapper("VAL_RETENCION_CP"));
		//java.lang.String
		att.setValPrimerApellidoOp(resultado.getString("VAL_PRIMER_APELLIDO_OP"));
		//java.lang.String
		att.setValSegundoApellidoOp(resultado.getString("VAL_SEGUNDO_APELLIDO_OP"));
		//java.lang.String
		att.setValPrimerNombreOp(resultado.getString("VAL_PRIMER_NOMBRE_OP"));
		//java.lang.String
		att.setValOtrosNombresOp(resultado.getString("VAL_OTROS_NOMBRES_OP"));
		//java.lang.String
		att.setValRazonSocialOp(resultado.getString("VAL_RAZON_SOCIAL_OP"));
		//java.lang.Integer
if (resultado.getValorPorNombre("NUM_BOLETAS_PAGO_EXCESO") != null) {
			att.setNumBoletasPagoExceso(resultado.getIntWrapper("NUM_BOLETAS_PAGO_EXCESO"));
		}
		//java.lang.Long
if (resultado.getValorPorNombre("VAL_BOLETAS_PAGO_EXCESO") != null) {
			att.setValBoletasPagoExceso(resultado.getLongWrapper("VAL_BOLETAS_PAGO_EXCESO"));
		}
		
		//java.lang.Long
		att.setIdeUsuarioCambio(resultado.getLongWrapper("IDE_USUARIO_CAMBIO"));
		//java.sql.Timestamp
		att.setFecCambio((Timestamp)resultado.getValorPorNombre("FEC_CAMBIO"));

		// Conformar el TO
		DDeclaracionContribucionParafiscalEventoTO to = new DDeclaracionContribucionParafiscalEventoTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}

	/**
	 * Devuelve el objeto DeclaracionContribucionParafiscalEvento que se haya consultado.
	 * @return Un objeto DDeclaracionContribucionParafiscalEventoTO
	 */
	public DDeclaracionContribucionParafiscalEventoTO getDeclaracionContribucionParafiscalEvento() {
		return toDeclaracionContribucionParafiscalEvento;
	}

	/**
	 * Devuelve la colección de objetos DeclaracionContribucionParafiscalEvento que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalEventoTO
	 */
	public Collection<DDeclaracionContribucionParafiscalEventoTO> getColeccionDeclaracionContribucionParafiscalEvento() {
		return objetosDeclaracionContribucionParafiscalEvento;
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
			parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalEvento",toDeclaracionContribucionParafiscalEvento);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento",pkDeclaracionContribucionParafiscalEvento);
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento",attDeclaracionContribucionParafiscalEvento);

			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeItem()",pkDeclaracionContribucionParafiscalEvento.getIdeItem());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValNumEvento()",attDeclaracionContribucionParafiscalEvento.getValNumEvento());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getIdeMunicipioEvento()",attDeclaracionContribucionParafiscalEvento.getIdeMunicipioEvento());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getIdeDepartamentoEvento()",attDeclaracionContribucionParafiscalEvento.getIdeDepartamentoEvento());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValNombLugarEvento()",attDeclaracionContribucionParafiscalEvento.getValNombLugarEvento());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getFecRealizacionEvento()",attDeclaracionContribucionParafiscalEvento.getFecRealizacionEvento());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBoletasVendidas()",attDeclaracionContribucionParafiscalEvento.getNumBoletasVendidas());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBoletasVendidas()",attDeclaracionContribucionParafiscalEvento.getValBoletasVendidas());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBolVendidasInf3uvt()",attDeclaracionContribucionParafiscalEvento.getNumBolVendidasInf3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBolVendidasInf3uvt()",attDeclaracionContribucionParafiscalEvento.getValBolVendidasInf3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBolVendidasSup3uvt()",attDeclaracionContribucionParafiscalEvento.getNumBolVendidasSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBolVendidasSup3uvt()",attDeclaracionContribucionParafiscalEvento.getValBolVendidasSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumCortesiasEntregadas()",attDeclaracionContribucionParafiscalEvento.getNumCortesiasEntregadas());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValCortesiasEntregadas()",attDeclaracionContribucionParafiscalEvento.getValCortesiasEntregadas());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumCortEntregInf3uvt()",attDeclaracionContribucionParafiscalEvento.getNumCortEntregInf3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValCortEntregInf3uvt()",attDeclaracionContribucionParafiscalEvento.getValCortEntregInf3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumCortEntregSup3uvt()",attDeclaracionContribucionParafiscalEvento.getNumCortEntregSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValCortEntregSup3uvt()",attDeclaracionContribucionParafiscalEvento.getValCortEntregSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBoletasPatrocinio()",attDeclaracionContribucionParafiscalEvento.getNumBoletasPatrocinio());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBoletasPatrocinio()",attDeclaracionContribucionParafiscalEvento.getValBoletasPatrocinio());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBolPatrocInf3uvt()",attDeclaracionContribucionParafiscalEvento.getNumBolPatrocInf3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBolPatrocInf3uvt()",attDeclaracionContribucionParafiscalEvento.getValBolPatrocInf3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBolPatrocSup3uvt()",attDeclaracionContribucionParafiscalEvento.getNumBolPatrocSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBolPatrocSup3uvt()",attDeclaracionContribucionParafiscalEvento.getValBolPatrocSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBaseGravableCp()",attDeclaracionContribucionParafiscalEvento.getValBaseGravableCp());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValContribParafiscal()",attDeclaracionContribucionParafiscalEvento.getValContribParafiscal());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValRetencionCp()",attDeclaracionContribucionParafiscalEvento.getValRetencionCp());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getIdeUsuarioCambio()",attDeclaracionContribucionParafiscalEvento.getIdeUsuarioCambio());
			parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getFecCambio()",attDeclaracionContribucionParafiscalEvento.getFecCambio());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento",pkDeclaracionContribucionParafiscalEvento);
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeItem()",pkDeclaracionContribucionParafiscalEvento.getIdeItem());
		}

		if (tipoOperacion == CONSULTA_GENERICA) {
			parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalEvento",toDeclaracionContribucionParafiscalEvento);
		}

		if (tipoOperacion == CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL || tipoOperacion == ELIMINAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL) {
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
			parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		}
		
		if(tipoOperacion == CREAR_CON_COLECCION){
	        parametros.put(this.getClass().getName()+":validar:objetosDeclaracionContribucionParafiscalEvento",objetosDeclaracionContribucionParafiscalEvento);

	    }

		validarParametros(operacion,parametros);
		return true;
	}
}
