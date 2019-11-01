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
 * <p>Descripcion: Objeto de acceso a datos para DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAODetRetContrArtEscenics extends DDAO implements IDDAODetRetContrArtEscenics {
	/** colección de objetos DDetRetContrArtEscenicsTO */
	private Collection<DDetRetContrArtEscenicsTO> objetosDetRetContrArtEscenics;
	/** colección de objetos DDetRetContrArtEscenicsTO */
	private Collection<DDetRetContrArtEscenicsPKTO> objetosPkDetRetContrArtEscenics;
	/** Objeto de transporte de DetRetContrArtEscenics */
	private DDetRetContrArtEscenicsTO toDetRetContrArtEscenics;
	/** Llave primaria de DetRetContrArtEscenics */
	private DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics;
	/** Atributos de DetRetContrArtEscenics */
	private DDetRetContrArtEscenicsAttTO attDetRetContrArtEscenics;
	/** Llave primaria de RetenContribArtEscenics */
	private DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics;
	/** Identificador de documento de carga*/
	private Long ideDocumentoCarga;
	/** Número de repetición de documento de carga*/
	private Integer numRepeticionCarga;
	/** Indica si es ejecución libre*/
	private boolean ejecucionLibre = false;
	

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDetRetContrArtEscenics Llave primaria de DetRetContrArtEscenics
	 */
	public void inicializarConsultarPorPK(DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
	}

	/**
	 * Inicializa la consulta por RetenContribArtEscenics.
	 * @param pkRetenContribArtEscenics Llave primaria de RetenContribArtEscenics
	 */
	public void inicializarConsultarPorRetenContribArtEscenics(DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics) {
		setTipoOperacion(CONSULTAR_POR_RETENCONTRIBARTESCENICS);
		this.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
	}
	
	/**
	 * Inicializa la consulta por documento carga.
	 * @param ideDocumentoCarga identificador de documento de carga
	 * @param numRepeticionCarga numero de repetición de documento de carga
	 */
	public void inicializarConsultarPorDocumentoCarga(Long ideDocumentoCarga, Integer numRepeticionCarga){
		setTipoOperacion(CONSULTAR_POR_DOCUMENTO_CARGA);
		this.ideDocumentoCarga = ideDocumentoCarga;
		this.numRepeticionCarga = numRepeticionCarga;
	}


	/**
	 * Inicializa la creaciónn de DetRetContrArtEscenics.
	 * @param toDetRetContrArtEscenics Objeto de Transporte de DetRetContrArtEscenics
	 */
	public void inicializarCrear(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics) {
		setTipoOperacion(CREAR);
		this.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
		if (toDetRetContrArtEscenics != null) {
			pkDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getPK();
			attDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getAtt();
		}
	}
	
	/**
	 * Inicializa la creación en batch de DetRetContrArtEscenics.
	 * @param Collection<DDetRetContrArtEscenicsTO> lista de Objetos de Transporte de DetRetContrArtEscenics
	 */
	public void inicializarCrearEnBatch(Collection<DDetRetContrArtEscenicsTO> objetosDetRetContrArtEscenics){
		setTipoOperacion(CREAR_EN_BATCH);
		this.objetosDetRetContrArtEscenics = objetosDetRetContrArtEscenics;
	}

	/**
	 * Inicializa la actualización de DetRetContrArtEscenics.
	 * @param toDetRetContrArtEscenics Objeto de Transporte de DetRetContrArtEscenics
	 */
	public void inicializarActualizar(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics) {
		setTipoOperacion(ACTUALIZAR);
		this.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
		if (toDetRetContrArtEscenics != null) {
			pkDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getPK();
			attDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getAtt();
		}
	}

	/**
	 * Inicializa la eliminación de DetRetContrArtEscenics.
	 * @param pkDetRetContrArtEscenics Llave primaria de DetRetContrArtEscenics
	 */
	public void inicializarEliminar(DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics) {
		setTipoOperacion(ELIMINAR);
		this.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
	}
	
	/**
	 * Inicializa la eliminación en batch de DetRetContrArtEscenics.
	 * @param Collection<DDetRetContrArtEscenicsPKTO> lista de Objetos pk de DetRetContrArtEscenics
	 */
	public void inicializarEliminarEnBatch(Collection<DDetRetContrArtEscenicsPKTO> objetosPkDetRetContrArtEscenics){
		setTipoOperacion(ELIMINAR_EN_BATCH);
		this.objetosPkDetRetContrArtEscenics = objetosPkDetRetContrArtEscenics;
	}

	/**
	 * Inicializa la consulta genérica de DetRetContrArtEscenics.
	 * @param attDetRetContrArtEscenics Atributos de DetRetContrArtEscenics
	 */
	public void inicializarConsultaGenerica(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
		if (toDetRetContrArtEscenics != null) {
			pkDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getPK();
			attDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getAtt();
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
			case CREAR_EN_BATCH:
			case CREAR:
				sql.append("insert into DIL_DET_RET_CONTR_ART_ESCENICS")
					.append(" (IDE_DOCUMENTO, NUM_REPETICION, IDE_FORMATO, NUM_VERSION_FORMATO, NUM_OCURRENCIA, NUM_ITEM, NUM_EVENTO, NOM_ESPECTACULO, COD_MUNICIPIO_ESPECTACULO, COD_DEPARTAMENTO_ESPECTACULO, FEC_REALIZACION, IDE_PERSONA_RUT_PRODUCTOR, VAL_DIRECCION_PRODUCTOR, COD_MUNICIPIO_PRODUCTOR, COD_DEPARTAMENTO_PRODUCTOR, NUM_TELEFONO_PRODUCTOR, VAL_LUGAR_EVENTO, NUM_TOT_BOLETAS_VENDIDAS, VAL_TOT_BOLETAS_VENDIDAS, VAL_SERV_DIST_COMER_BOLETERIA, NUM_TOT_BOL_PREC_IG_SUP_3UVT, VAL_TOT_BOL_PREC_IG_SUP_3UVT, NUM_TOT_DER_AS_PRE_IG_SUP_3UVT, VAL_TOT_DER_AS_PRE_IG_SUP_3UVT, NUM_BOLETAS_RETENCION_EXCESO, VAL_BOLETAS_RETENCION_EXCESO, IDE_USUARIO_CAMBIO, FEC_CAMBIO)")
					.append(" VALUES (:IDE_DOCUMENTO, :NUM_REPETICION, :IDE_FORMATO, :NUM_VERSION_FORMATO, :NUM_OCURRENCIA, :NUM_ITEM, :NUM_EVENTO, :NOM_ESPECTACULO, :COD_MUNICIPIO_ESPECTACULO, :COD_DEPARTAMENTO_ESPECTACULO, :FEC_REALIZACION, :IDE_PERSONA_RUT_PRODUCTOR, :VAL_DIRECCION_PRODUCTOR, :COD_MUNICIPIO_PRODUCTOR, :COD_DEPARTAMENTO_PRODUCTOR, :NUM_TELEFONO_PRODUCTOR, :VAL_LUGAR_EVENTO, :NUM_TOT_BOLETAS_VENDIDAS, :VAL_TOT_BOLETAS_VENDIDAS, :VAL_SERV_DIST_COMER_BOLETERIA, :NUM_TOT_BOL_PREC_IG_SUP_3UVT, :VAL_TOT_BOL_PREC_IG_SUP_3UVT, :NUM_TOT_DER_AS_PRE_IG_SUP_3UVT, :VAL_TOT_DER_AS_PRE_IG_SUP_3UVT, :NUM_BOLETAS_RETENCION_EXCESO, :VAL_BOLETAS_RETENCION_EXCESO, :IDE_USUARIO_CAMBIO, :FEC_CAMBIO)");
				m.put("sentencia1", sql.toString());
				break;
			case ACTUALIZAR:
				sql.append("update DIL_DET_RET_CONTR_ART_ESCENICS")
					.append(" set IDE_FORMATO=:IDE_FORMATO, NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO, NUM_EVENTO=:NUM_EVENTO, NOM_ESPECTACULO=:NOM_ESPECTACULO, COD_MUNICIPIO_ESPECTACULO=:COD_MUNICIPIO_ESPECTACULO, COD_DEPARTAMENTO_ESPECTACULO=:COD_DEPARTAMENTO_ESPECTACULO, FEC_REALIZACION=:FEC_REALIZACION, IDE_PERSONA_RUT_PRODUCTOR=:IDE_PERSONA_RUT_PRODUCTOR, VAL_DIRECCION_PRODUCTOR=:VAL_DIRECCION_PRODUCTOR, COD_MUNICIPIO_PRODUCTOR=:COD_MUNICIPIO_PRODUCTOR, COD_DEPARTAMENTO_PRODUCTOR=:COD_DEPARTAMENTO_PRODUCTOR, NUM_TELEFONO_PRODUCTOR=:NUM_TELEFONO_PRODUCTOR, VAL_LUGAR_EVENTO=:VAL_LUGAR_EVENTO, NUM_TOT_BOLETAS_VENDIDAS=:NUM_TOT_BOLETAS_VENDIDAS, VAL_TOT_BOLETAS_VENDIDAS=:VAL_TOT_BOLETAS_VENDIDAS, VAL_SERV_DIST_COMER_BOLETERIA=:VAL_SERV_DIST_COMER_BOLETERIA, NUM_TOT_BOL_PREC_IG_SUP_3UVT=:NUM_TOT_BOL_PREC_IG_SUP_3UVT, VAL_TOT_BOL_PREC_IG_SUP_3UVT=:VAL_TOT_BOL_PREC_IG_SUP_3UVT, NUM_TOT_DER_AS_PRE_IG_SUP_3UVT=:NUM_TOT_DER_AS_PRE_IG_SUP_3UVT, VAL_TOT_DER_AS_PRE_IG_SUP_3UVT=:VAL_TOT_DER_AS_PRE_IG_SUP_3UVT, NUM_BOLETAS_RETENCION_EXCESO=:NUM_BOLETAS_RETENCION_EXCESO, VAL_BOLETAS_RETENCION_EXCESO=:VAL_BOLETAS_RETENCION_EXCESO, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO, FEC_CAMBIO=:FEC_CAMBIO")
					.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION and NUM_OCURRENCIA=:NUM_OCURRENCIA and NUM_ITEM=:NUM_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR_EN_BATCH:	
			case ELIMINAR:
				sql.append("delete from DIL_DET_RET_CONTR_ART_ESCENICS")
					.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION and NUM_OCURRENCIA=:NUM_OCURRENCIA and NUM_ITEM=:NUM_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_PK:
				sql.append("select * from DIL_DET_RET_CONTR_ART_ESCENICS")
					.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION and NUM_OCURRENCIA=:NUM_OCURRENCIA and NUM_ITEM=:NUM_ITEM");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTA_GENERICA:
				sql.append("select * from DIL_DET_RET_CONTR_ART_ESCENICS where ")
					.append(generarFiltroGenerico());
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_RETENCONTRIBARTESCENICS:
				sql.append("select * from DIL_DET_RET_CONTR_ART_ESCENICS")
					.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_DOCUMENTO_CARGA:
				ejecucionLibre = true;
				sql.append("SELECT DRCAE.* FROM DIL_RETEN_CONTRIB_ART_ESCENICS RCAE, DIL_DET_RET_CONTR_ART_ESCENICS DRCAE")
				.append(" where RCAE.IDE_DOCUMENTO = DRCAE.IDE_DOCUMENTO")
				.append(" and RCAE.NUM_REPETICION  = DRCAE.NUM_REPETICION ")
				.append(" and RCAE.IDE_DOCUMENTO_CARGA = :IDE_DOCUMENTO_CARGA")
				.append(" and RCAE.NUM_REPETICION_CARGA = :NUM_REPETICION_CARGA");		
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

		if (pkDetRetContrArtEscenics != null) {

			if (pkDetRetContrArtEscenics.getIdeDocumento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO=:IDE_DOCUMENTO");
				append = true;

			}
			if (pkDetRetContrArtEscenics.getNumRepeticion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION=:NUM_REPETICION");
				append = true;

			}
			if (pkDetRetContrArtEscenics.getNumOcurrencia() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_OCURRENCIA=:NUM_OCURRENCIA");
				append = true;

			}
			if (pkDetRetContrArtEscenics.getNumItem() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_ITEM=:NUM_ITEM");
				append = true;

			}
		}

		if (attDetRetContrArtEscenics != null) {

			if (attDetRetContrArtEscenics.getIdeFormato() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_FORMATO=:IDE_FORMATO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getNumVersionFormato() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getNumEvento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_EVENTO=:NUM_EVENTO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getNomEspectaculo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NOM_ESPECTACULO=:NOM_ESPECTACULO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getCodMunicipioEspectaculo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("COD_MUNICIPIO_ESPECTACULO=:COD_MUNICIPIO_ESPECTACULO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getCodDepartamentoEspectaculo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("COD_DEPARTAMENTO_ESPECTACULO=:COD_DEPARTAMENTO_ESPECTACULO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getFecRealizacion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_REALIZACION=:FEC_REALIZACION");
				append = true;

			}
			if (attDetRetContrArtEscenics.getIdePersonaRutProductor() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_PERSONA_RUT_PRODUCTOR=:IDE_PERSONA_RUT_PRODUCTOR");
				append = true;

			}
			if (attDetRetContrArtEscenics.getValDireccionProductor() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_DIRECCION_PRODUCTOR=:VAL_DIRECCION_PRODUCTOR");
				append = true;

			}
			if (attDetRetContrArtEscenics.getCodMunicipioProductor() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("COD_MUNICIPIO_PRODUCTOR=:COD_MUNICIPIO_PRODUCTOR");
				append = true;

			}
			if (attDetRetContrArtEscenics.getCodDepartamentoProductor() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("COD_DEPARTAMENTO_PRODUCTOR=:COD_DEPARTAMENTO_PRODUCTOR");
				append = true;

			}
			if (attDetRetContrArtEscenics.getNumTelefonoProductor() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_TELEFONO_PRODUCTOR=:NUM_TELEFONO_PRODUCTOR");
				append = true;

			}
			if (attDetRetContrArtEscenics.getValLugarEvento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_LUGAR_EVENTO=:VAL_LUGAR_EVENTO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getNumTotBoletasVendidas() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_TOT_BOLETAS_VENDIDAS=:NUM_TOT_BOLETAS_VENDIDAS");
				append = true;

			}
			if (attDetRetContrArtEscenics.getValTotBoletasVendidas() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_TOT_BOLETAS_VENDIDAS=:VAL_TOT_BOLETAS_VENDIDAS");
				append = true;

			}
			if (attDetRetContrArtEscenics.getValServDistComerBoleteria() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_SERV_DIST_COMER_BOLETERIA=:VAL_SERV_DIST_COMER_BOLETERIA");
				append = true;

			}			
			if (attDetRetContrArtEscenics.getNumTotBolPrecIgSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_TOT_BOL_PREC_IG_SUP_3UVT=:NUM_TOT_BOL_PREC_IG_SUP_3UVT");
				append = true;

			}
			if (attDetRetContrArtEscenics.getValTotBolPrecIgSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_TOT_BOL_PREC_IG_SUP_3UVT=:VAL_TOT_BOL_PREC_IG_SUP_3UVT");
				append = true;

			}
			if (attDetRetContrArtEscenics.getNumTotDerAsPreIgSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_TOT_DER_AS_PRE_IG_SUP_3UVT=:NUM_TOT_DER_AS_PRE_IG_SUP_3UVT");
				append = true;

			}
			if (attDetRetContrArtEscenics.getValTotDerAsPreIgSup3uvt() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_TOT_DER_AS_PRE_IG_SUP_3UVT=:VAL_TOT_DER_AS_PRE_IG_SUP_3UVT");
				append = true;

			}
			if (attDetRetContrArtEscenics.getNumBoletasRetencionExceso() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_BOLETAS_RETENCION_EXCESO=:NUM_BOLETAS_RETENCION_EXCESO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getValBoletasRetencionExceso() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_BOLETAS_RETENCION_EXCESO=:VAL_BOLETAS_RETENCION_EXCESO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getIdeUsuarioCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
				append = true;

			}
			if (attDetRetContrArtEscenics.getFecCambio() != null) {
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
		if (pkDetRetContrArtEscenics != null) {
			if (pkDetRetContrArtEscenics.getIdeDocumento() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO", pkDetRetContrArtEscenics.getIdeDocumento());
			}
			if (pkDetRetContrArtEscenics.getNumRepeticion() != null) {
				unaSentencia.setInt("NUM_REPETICION", pkDetRetContrArtEscenics.getNumRepeticion());
			}
			if (pkDetRetContrArtEscenics.getNumOcurrencia() != null) {
				unaSentencia.setShort("NUM_OCURRENCIA", pkDetRetContrArtEscenics.getNumOcurrencia());
			}
			if (pkDetRetContrArtEscenics.getNumItem() != null) {
				unaSentencia.setInt("NUM_ITEM", pkDetRetContrArtEscenics.getNumItem());
			}
		}

		if (attDetRetContrArtEscenics != null) {
			if (attDetRetContrArtEscenics.getIdeFormato() != null) {
				unaSentencia.setInt("IDE_FORMATO", attDetRetContrArtEscenics.getIdeFormato());
			}
			if (attDetRetContrArtEscenics.getNumVersionFormato() != null) {
				unaSentencia.setShort("NUM_VERSION_FORMATO", attDetRetContrArtEscenics.getNumVersionFormato());
			}
			if (attDetRetContrArtEscenics.getNumEvento() != null) {
				unaSentencia.setString("NUM_EVENTO", attDetRetContrArtEscenics.getNumEvento());
			}
			if (attDetRetContrArtEscenics.getNomEspectaculo() != null) {
				unaSentencia.setString("NOM_ESPECTACULO", attDetRetContrArtEscenics.getNomEspectaculo());
			}
			if (attDetRetContrArtEscenics.getCodMunicipioEspectaculo() != null) {
				unaSentencia.setInt("COD_MUNICIPIO_ESPECTACULO", attDetRetContrArtEscenics.getCodMunicipioEspectaculo());
			}
			if (attDetRetContrArtEscenics.getCodDepartamentoEspectaculo() != null) {
				unaSentencia.setInt("COD_DEPARTAMENTO_ESPECTACULO", attDetRetContrArtEscenics.getCodDepartamentoEspectaculo());
			}
			if (attDetRetContrArtEscenics.getFecRealizacion() != null) {
				unaSentencia.setInt("FEC_REALIZACION", attDetRetContrArtEscenics.getFecRealizacion());
			}
			if (attDetRetContrArtEscenics.getIdePersonaRutProductor() != null) {
				unaSentencia.setLong("IDE_PERSONA_RUT_PRODUCTOR", attDetRetContrArtEscenics.getIdePersonaRutProductor());
			}
			if (attDetRetContrArtEscenics.getValDireccionProductor() != null) {
				unaSentencia.setString("VAL_DIRECCION_PRODUCTOR", attDetRetContrArtEscenics.getValDireccionProductor());
			}
			if (attDetRetContrArtEscenics.getCodMunicipioProductor() != null) {
				unaSentencia.setInt("COD_MUNICIPIO_PRODUCTOR", attDetRetContrArtEscenics.getCodMunicipioProductor());
			}
			if (attDetRetContrArtEscenics.getCodDepartamentoProductor() != null) {
				unaSentencia.setInt("COD_DEPARTAMENTO_PRODUCTOR", attDetRetContrArtEscenics.getCodDepartamentoProductor());
			}
			if (attDetRetContrArtEscenics.getNumTelefonoProductor() != null) {
				unaSentencia.setLong("NUM_TELEFONO_PRODUCTOR", attDetRetContrArtEscenics.getNumTelefonoProductor());
			}
			if (attDetRetContrArtEscenics.getValLugarEvento() != null) {
				unaSentencia.setString("VAL_LUGAR_EVENTO", attDetRetContrArtEscenics.getValLugarEvento());
			}
			if (attDetRetContrArtEscenics.getNumTotBoletasVendidas() != null) {
				unaSentencia.setInt("NUM_TOT_BOLETAS_VENDIDAS", attDetRetContrArtEscenics.getNumTotBoletasVendidas());
			}
			if (attDetRetContrArtEscenics.getValTotBoletasVendidas() != null) {
				unaSentencia.setBigDecimal("VAL_TOT_BOLETAS_VENDIDAS", attDetRetContrArtEscenics.getValTotBoletasVendidas());
			}
			if (attDetRetContrArtEscenics.getValServDistComerBoleteria() != null) {
				unaSentencia.setBigDecimal("VAL_SERV_DIST_COMER_BOLETERIA", attDetRetContrArtEscenics.getValServDistComerBoleteria());
			}
			if (attDetRetContrArtEscenics.getNumTotBolPrecIgSup3uvt() != null) {
				unaSentencia.setInt("NUM_TOT_BOL_PREC_IG_SUP_3UVT", attDetRetContrArtEscenics.getNumTotBolPrecIgSup3uvt());
			}
			if (attDetRetContrArtEscenics.getValTotBolPrecIgSup3uvt() != null) {
				unaSentencia.setBigDecimal("VAL_TOT_BOL_PREC_IG_SUP_3UVT", attDetRetContrArtEscenics.getValTotBolPrecIgSup3uvt());
			}
			if (attDetRetContrArtEscenics.getNumTotDerAsPreIgSup3uvt() != null) {
				unaSentencia.setInt("NUM_TOT_DER_AS_PRE_IG_SUP_3UVT", attDetRetContrArtEscenics.getNumTotDerAsPreIgSup3uvt());
			}
			if (attDetRetContrArtEscenics.getValTotDerAsPreIgSup3uvt() != null) {
				unaSentencia.setBigDecimal("VAL_TOT_DER_AS_PRE_IG_SUP_3UVT", attDetRetContrArtEscenics.getValTotDerAsPreIgSup3uvt());
			}
			if (attDetRetContrArtEscenics.getNumBoletasRetencionExceso() != null) {
				unaSentencia.setInt("NUM_BOLETAS_RETENCION_EXCESO", attDetRetContrArtEscenics.getNumBoletasRetencionExceso());
			}
			if (attDetRetContrArtEscenics.getValBoletasRetencionExceso() != null) {
				unaSentencia.setBigDecimal("VAL_BOLETAS_RETENCION_EXCESO", attDetRetContrArtEscenics.getValBoletasRetencionExceso());
			}
			if (attDetRetContrArtEscenics.getIdeUsuarioCambio() != null) {
				unaSentencia.setLong("IDE_USUARIO_CAMBIO", attDetRetContrArtEscenics.getIdeUsuarioCambio());
			}
			if (attDetRetContrArtEscenics.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attDetRetContrArtEscenics.getFecCambio());
			}			
		}
	}

	/**
	 * Guarda los datos de DetRetContrArtEscenics.
	 * @return @return Un int con el número de registros guardados
	 * @throws SQLException Si ocurre un error de base de datos al guardar
	 */
	public int guardar() throws SQLException {
		switch (getTipoOperacion()) {
			case CREAR:
			case CREAR_EN_BATCH:	
				return crear();
			case ACTUALIZAR:
				return actualizar();
		}
		return -1;
	}

	/**
	 * Elimina registros de DetRetContrArtEscenics.
	 * @return Un int con el número de registros eliminados
	 * @throws SQLException Si ocurre un error de base de datos al eliminar
	 */
	public int eliminar() throws SQLException {
		DSentenciaSQL sentencia = null;
		switch (tipoOperacion) {
		case ELIMINAR:
			sentencia = getSentenciaSQLPreparada("sentencia1");
			asignarValoresPK(sentencia);
			break;
		case ELIMINAR_EN_BATCH:
			sentencia = getSentenciaSQLBatch("sentencia1");
			asignarValoresPkEnBatch(sentencia);			
			break;
		default:
			break;
		}
		sentencia.ejecutar();
		return sentencia.getRegistrosAfectados();
	}

	/**
	 * Consulta los datos de DetRetContrArtEscenics.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	public IDDataSet consultar() throws SQLException {
		switch (getTipoOperacion()) {
			case CONSULTAR_POR_PK:
				return consultarPorPK();
			case CONSULTAR_POR_RETENCONTRIBARTESCENICS:
				return consultarPorRetenContribArtEscenics();
			case CONSULTA_GENERICA:
				return consultaGenerica();
			case CONSULTAR_POR_DOCUMENTO_CARGA:
				return consultarPorDocumentoCarga();
		}
		return null;
	}

	/**
	 * Crea un registro de DetRetContrArtEscenics.
	 * @return Un int con el número de registros creados
	 * @throws SQLException Si ocurre un error de base de datos al crear
	 */
	private int crear() throws SQLException {
		DSentenciaSQL sentencia = null;
		switch (tipoOperacion) {
		case CREAR:
			sentencia = getSentenciaSQLPreparada("sentencia1");
			asignarValoresObjeto(sentencia);
			asignarValoresPK(sentencia);
			break;
		case CREAR_EN_BATCH:
			sentencia = getSentenciaSQLBatch("sentencia1");
			asignarValoresObjetoEnBatch(sentencia);			
			break;
		default:
			break;
		}			
		sentencia.ejecutar();
		final int resultado = sentencia.getRegistrosAfectados();
		if (resultado <= 0) {
			throw new SQLException("No se ha creado ningún registro");
		}
		if (tipoOperacion == CREAR) {
			if (resultado > 1) {
				throw new SQLException("Se intentó crear más de un registro");
			}
		}
		return resultado;
	}

	/**
	 * Actualiza los datos de DetRetContrArtEscenics.
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
	 * Actualiza los datos de DetRetContrArtEscenics.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarDetRetContrArtEscenics(resultado);
		return resultado;
	}

	/**
	 * Consulta genérica de los datos de DetRetContrArtEscenics.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosDetRetContrArtEscenics(resultado);
		return resultado;
	}

	/**
	 * Consulta por RetenContribArtEscenics.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorRetenContribArtEscenics() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.setLong("IDE_DOCUMENTO", pkRetenContribArtEscenics.getIdeDocumento());
		sentencia.setInt("NUM_REPETICION", pkRetenContribArtEscenics.getNumRepeticion());
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosDetRetContrArtEscenics(resultado);
		return resultado;
	}

	/**
	 * Consulta por documento carga.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorDocumentoCarga() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresDocumentoCarga(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarPksDetRetContrArtEscenics(resultado);
		return resultado;
	}
	
	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO", pkDetRetContrArtEscenics.getIdeDocumento());
		unaSentencia.setInt("NUM_REPETICION", pkDetRetContrArtEscenics.getNumRepeticion());
		unaSentencia.setShort("NUM_OCURRENCIA", pkDetRetContrArtEscenics.getNumOcurrencia());
		unaSentencia.setInt("NUM_ITEM", pkDetRetContrArtEscenics.getNumItem());
	}
	
	/**
	 * Asigna los valores para para consultar por documento carga en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresDocumentoCarga(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO_CARGA", ideDocumentoCarga);
		unaSentencia.setInt("NUM_REPETICION_CARGA", numRepeticionCarga);
	}	
	
	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setInt("IDE_FORMATO", attDetRetContrArtEscenics.getIdeFormato());
		unaSentencia.setShort("NUM_VERSION_FORMATO", attDetRetContrArtEscenics.getNumVersionFormato());
		unaSentencia.setString("NUM_EVENTO", attDetRetContrArtEscenics.getNumEvento());
		unaSentencia.setString("NOM_ESPECTACULO", attDetRetContrArtEscenics.getNomEspectaculo());
		unaSentencia.setInt("COD_MUNICIPIO_ESPECTACULO", attDetRetContrArtEscenics.getCodMunicipioEspectaculo());
		unaSentencia.setInt("COD_DEPARTAMENTO_ESPECTACULO", attDetRetContrArtEscenics.getCodDepartamentoEspectaculo());
		unaSentencia.setInt("FEC_REALIZACION", attDetRetContrArtEscenics.getFecRealizacion());
		unaSentencia.setLong("IDE_PERSONA_RUT_PRODUCTOR", attDetRetContrArtEscenics.getIdePersonaRutProductor());
		unaSentencia.setString("VAL_DIRECCION_PRODUCTOR", attDetRetContrArtEscenics.getValDireccionProductor());
		unaSentencia.setInt("COD_MUNICIPIO_PRODUCTOR", attDetRetContrArtEscenics.getCodMunicipioProductor());
		unaSentencia.setInt("COD_DEPARTAMENTO_PRODUCTOR", attDetRetContrArtEscenics.getCodDepartamentoProductor());
		unaSentencia.setLong("NUM_TELEFONO_PRODUCTOR", attDetRetContrArtEscenics.getNumTelefonoProductor());
		unaSentencia.setString("VAL_LUGAR_EVENTO", attDetRetContrArtEscenics.getValLugarEvento());		
		unaSentencia.setInt("NUM_TOT_BOLETAS_VENDIDAS", attDetRetContrArtEscenics.getNumTotBoletasVendidas());
		unaSentencia.setBigDecimal("VAL_TOT_BOLETAS_VENDIDAS", attDetRetContrArtEscenics.getValTotBoletasVendidas());
		unaSentencia.setBigDecimal("VAL_SERV_DIST_COMER_BOLETERIA", attDetRetContrArtEscenics.getValServDistComerBoleteria());
		unaSentencia.setInt("NUM_TOT_BOL_PREC_IG_SUP_3UVT", attDetRetContrArtEscenics.getNumTotBolPrecIgSup3uvt());
		unaSentencia.setBigDecimal("VAL_TOT_BOL_PREC_IG_SUP_3UVT", attDetRetContrArtEscenics.getValTotBolPrecIgSup3uvt());
		unaSentencia.setInt("NUM_TOT_DER_AS_PRE_IG_SUP_3UVT", attDetRetContrArtEscenics.getNumTotDerAsPreIgSup3uvt());
		unaSentencia.setBigDecimal("VAL_TOT_DER_AS_PRE_IG_SUP_3UVT", attDetRetContrArtEscenics.getValTotDerAsPreIgSup3uvt());
		unaSentencia.setInt("NUM_BOLETAS_RETENCION_EXCESO", attDetRetContrArtEscenics.getNumBoletasRetencionExceso());
		unaSentencia.setBigDecimal("VAL_BOLETAS_RETENCION_EXCESO", attDetRetContrArtEscenics.getValBoletasRetencionExceso());
		unaSentencia.setLong("IDE_USUARIO_CAMBIO", attDetRetContrArtEscenics.getIdeUsuarioCambio());
		unaSentencia.setTimestamp("FEC_CAMBIO", attDetRetContrArtEscenics.getFecCambio());
	}
	
	
	/**
	 * Asigna todos los valores en batch de un objeto detalle.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjetoEnBatch(DSentenciaSQL unaSentencia) throws SQLException {
			if(objetosDetRetContrArtEscenics != null && objetosDetRetContrArtEscenics.size()>0){
				final DSentenciaSQLBatch unaSentenciaBatch=(DSentenciaSQLBatch) unaSentencia;
				for(final DDetRetContrArtEscenicsTO toDetRetContrArtEscenics: objetosDetRetContrArtEscenics){
					final DDetRetContrArtEscenicsAttTO attDetRetContrArtEscenics = toDetRetContrArtEscenics.getAtt();
					final DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics = toDetRetContrArtEscenics.getPK();
					unaSentenciaBatch.setLong("IDE_DOCUMENTO", pkDetRetContrArtEscenics.getIdeDocumento());
					unaSentenciaBatch.setInt("NUM_REPETICION", pkDetRetContrArtEscenics.getNumRepeticion());
					unaSentenciaBatch.setShort("NUM_OCURRENCIA", pkDetRetContrArtEscenics.getNumOcurrencia());
					unaSentenciaBatch.setInt("NUM_ITEM", pkDetRetContrArtEscenics.getNumItem());
					unaSentenciaBatch.setInt("IDE_FORMATO", attDetRetContrArtEscenics.getIdeFormato());
					unaSentenciaBatch.setShort("NUM_VERSION_FORMATO", attDetRetContrArtEscenics.getNumVersionFormato());
					unaSentenciaBatch.setString("NUM_EVENTO", attDetRetContrArtEscenics.getNumEvento());
					unaSentenciaBatch.setString("NOM_ESPECTACULO", attDetRetContrArtEscenics.getNomEspectaculo());
					unaSentenciaBatch.setInt("COD_MUNICIPIO_ESPECTACULO", attDetRetContrArtEscenics.getCodMunicipioEspectaculo());
					unaSentenciaBatch.setInt("COD_DEPARTAMENTO_ESPECTACULO", attDetRetContrArtEscenics.getCodDepartamentoEspectaculo());
					unaSentenciaBatch.setInt("FEC_REALIZACION", attDetRetContrArtEscenics.getFecRealizacion());
					unaSentenciaBatch.setLong("IDE_PERSONA_RUT_PRODUCTOR", attDetRetContrArtEscenics.getIdePersonaRutProductor());
					unaSentenciaBatch.setString("VAL_DIRECCION_PRODUCTOR", attDetRetContrArtEscenics.getValDireccionProductor());
					unaSentenciaBatch.setInt("COD_MUNICIPIO_PRODUCTOR", attDetRetContrArtEscenics.getCodMunicipioProductor());
					unaSentenciaBatch.setInt("COD_DEPARTAMENTO_PRODUCTOR", attDetRetContrArtEscenics.getCodDepartamentoProductor());
					unaSentenciaBatch.setLong("NUM_TELEFONO_PRODUCTOR", attDetRetContrArtEscenics.getNumTelefonoProductor());
					unaSentenciaBatch.setString("VAL_LUGAR_EVENTO", attDetRetContrArtEscenics.getValLugarEvento());		
					unaSentenciaBatch.setInt("NUM_TOT_BOLETAS_VENDIDAS", attDetRetContrArtEscenics.getNumTotBoletasVendidas());
					unaSentenciaBatch.setBigDecimal("VAL_TOT_BOLETAS_VENDIDAS", attDetRetContrArtEscenics.getValTotBoletasVendidas());
					unaSentenciaBatch.setBigDecimal("VAL_SERV_DIST_COMER_BOLETERIA", attDetRetContrArtEscenics.getValServDistComerBoleteria());
					unaSentenciaBatch.setInt("NUM_TOT_BOL_PREC_IG_SUP_3UVT", attDetRetContrArtEscenics.getNumTotBolPrecIgSup3uvt());
					unaSentenciaBatch.setBigDecimal("VAL_TOT_BOL_PREC_IG_SUP_3UVT", attDetRetContrArtEscenics.getValTotBolPrecIgSup3uvt());
					unaSentenciaBatch.setInt("NUM_TOT_DER_AS_PRE_IG_SUP_3UVT", attDetRetContrArtEscenics.getNumTotDerAsPreIgSup3uvt());
					unaSentenciaBatch.setBigDecimal("VAL_TOT_DER_AS_PRE_IG_SUP_3UVT", attDetRetContrArtEscenics.getValTotDerAsPreIgSup3uvt());
					unaSentenciaBatch.setInt("NUM_BOLETAS_RETENCION_EXCESO", attDetRetContrArtEscenics.getNumBoletasRetencionExceso());
					unaSentenciaBatch.setBigDecimal("VAL_BOLETAS_RETENCION_EXCESO", attDetRetContrArtEscenics.getValBoletasRetencionExceso());
					unaSentenciaBatch.setLong("IDE_USUARIO_CAMBIO", attDetRetContrArtEscenics.getIdeUsuarioCambio());
					unaSentenciaBatch.setTimestamp("FEC_CAMBIO", attDetRetContrArtEscenics.getFecCambio());
					unaSentenciaBatch.nuevoRegistro();
				}
			}
	}	
	
	
	/**
	 * Asigna todos los valores en batch de un objeto detalle.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPkEnBatch(DSentenciaSQL unaSentencia) throws SQLException {
			if(objetosPkDetRetContrArtEscenics != null && objetosPkDetRetContrArtEscenics.size()>0){
				final DSentenciaSQLBatch unaSentenciaBatch=(DSentenciaSQLBatch) unaSentencia;
				for(final DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics: objetosPkDetRetContrArtEscenics){
					unaSentenciaBatch.setLong("IDE_DOCUMENTO", pkDetRetContrArtEscenics.getIdeDocumento());
					unaSentenciaBatch.setInt("NUM_REPETICION", pkDetRetContrArtEscenics.getNumRepeticion());
					unaSentenciaBatch.setShort("NUM_OCURRENCIA", pkDetRetContrArtEscenics.getNumOcurrencia());
					unaSentenciaBatch.setInt("NUM_ITEM", pkDetRetContrArtEscenics.getNumItem());
					unaSentenciaBatch.nuevoRegistro();
				}
			}
	}

	/**
	 * Construye un objeto DetRetContrArtEscenics con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarDetRetContrArtEscenics(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toDetRetContrArtEscenics = completarDetRetContrArtEscenics(resultado);
	}

	/**
	 * Construye objetos DetRetContrArtEscenics con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosDetRetContrArtEscenics(IDDataSet resultado) throws SQLException {
		objetosDetRetContrArtEscenics = new ArrayList<DDetRetContrArtEscenicsTO>();
		toDetRetContrArtEscenics = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DDetRetContrArtEscenicsTO objeto = completarDetRetContrArtEscenics(resultado);
			objetosDetRetContrArtEscenics.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}
	
	/**
	 * Construye objetos DetRetContrArtEscenicsPK con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarPksDetRetContrArtEscenics(IDDataSet resultado) throws SQLException {
		objetosPkDetRetContrArtEscenics = new ArrayList<DDetRetContrArtEscenicsPKTO>();
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DDetRetContrArtEscenicsPKTO objeto = completarPkDetRetContrArtEscenics(resultado);
			objetosPkDetRetContrArtEscenics.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto DetRetContrArtEscenics con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un DetRetContrArtEscenics
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DDetRetContrArtEscenicsTO completarDetRetContrArtEscenics(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DDetRetContrArtEscenicsPKTO pk = new DDetRetContrArtEscenicsPKTO();
		//java.lang.Long
		pk.setIdeDocumento(resultado.getLongWrapper("IDE_DOCUMENTO"));
		//java.lang.Integer
		pk.setNumRepeticion(resultado.getIntWrapper("NUM_REPETICION"));
		//java.lang.Short
		pk.setNumOcurrencia(resultado.getShortWrapper("NUM_OCURRENCIA"));
		//java.lang.Integer
		pk.setNumItem(resultado.getIntWrapper("NUM_ITEM"));

		// Conformar el objeto Att
		DDetRetContrArtEscenicsAttTO att = new DDetRetContrArtEscenicsAttTO();
		//java.lang.Integer
		att.setIdeFormato(resultado.getIntWrapper("IDE_FORMATO"));
		//java.lang.Byte
		att.setNumVersionFormato(resultado.getByteWrapper("NUM_VERSION_FORMATO"));
		//java.lang.Long
		att.setNumEvento(resultado.getString("NUM_EVENTO"));
		//java.lang.String
		att.setNomEspectaculo(resultado.getString("NOM_ESPECTACULO"));
		//java.lang.Integer
		att.setCodMunicipioEspectaculo(resultado.getIntWrapper("COD_MUNICIPIO_ESPECTACULO"));
		//java.lang.Integer
		att.setCodDepartamentoEspectaculo(resultado.getIntWrapper("COD_DEPARTAMENTO_ESPECTACULO"));
		//java.sql.Timestamp
		att.setFecRealizacion(resultado.getIntWrapper("FEC_REALIZACION"));
		//java.lang.Long
		att.setIdePersonaRutProductor(resultado.getLongWrapper("IDE_PERSONA_RUT_PRODUCTOR"));
		//java.lang.String
		att.setValDireccionProductor(resultado.getString("VAL_DIRECCION_PRODUCTOR"));
		//java.lang.Integer
		att.setCodMunicipioProductor(resultado.getIntWrapper("COD_MUNICIPIO_PRODUCTOR"));
		//java.lang.Integer
		att.setCodDepartamentoProductor(resultado.getIntWrapper("COD_DEPARTAMENTO_PRODUCTOR"));
		//java.lang.Long
		if (resultado.getValorPorNombre("NUM_TELEFONO_PRODUCTOR") != null) {
			att.setNumTelefonoProductor(resultado.getLongWrapper("NUM_TELEFONO_PRODUCTOR"));
		}
		
		//java.math.BigDecimal
		att.setValLugarEvento(resultado.getString("VAL_LUGAR_EVENTO"));
		
		//java.lang.Integer
		att.setNumTotBoletasVendidas(resultado.getIntWrapper("NUM_TOT_BOLETAS_VENDIDAS"));
		//java.math.BigDecimal
		att.setValTotBoletasVendidas(resultado.getBigDecimal("VAL_TOT_BOLETAS_VENDIDAS"));
		
		//java.lang.Integer
		att.setValServDistComerBoleteria(resultado.getBigDecimal("VAL_SERV_DIST_COMER_BOLETERIA"));
		
		//java.lang.Integer
		if (resultado.getValorPorNombre("NUM_TOT_BOL_PREC_IG_SUP_3UVT") != null) {
			att.setNumTotBolPrecIgSup3uvt(resultado.getIntWrapper("NUM_TOT_BOL_PREC_IG_SUP_3UVT"));
		}
		//java.math.BigDecimal
		att.setValTotBolPrecIgSup3uvt(resultado.getBigDecimal("VAL_TOT_BOL_PREC_IG_SUP_3UVT"));
		//java.lang.Integer
		if (resultado.getValorPorNombre("NUM_TOT_DER_AS_PRE_IG_SUP_3UVT") != null) {
			att.setNumTotDerAsPreIgSup3uvt(resultado.getIntWrapper("NUM_TOT_DER_AS_PRE_IG_SUP_3UVT"));
		}
		//java.math.BigDecimal
		att.setValTotDerAsPreIgSup3uvt(resultado.getBigDecimal("VAL_TOT_DER_AS_PRE_IG_SUP_3UVT"));
		//java.lang.Integer
		if (resultado.getValorPorNombre("NUM_BOLETAS_RETENCION_EXCESO") != null) {
			att.setNumBoletasRetencionExceso(resultado.getIntWrapper("NUM_BOLETAS_RETENCION_EXCESO"));
		}
		//java.math.BigDecimal
		att.setValBoletasRetencionExceso(resultado.getBigDecimal("VAL_BOLETAS_RETENCION_EXCESO"));

		// Conformar el TO
		DDetRetContrArtEscenicsTO to = new DDetRetContrArtEscenicsTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}
	
	
	/**
	 * Construye un objeto pk DetRetContrArtEscenics con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un DDetRetContrArtEscenicsPKTO
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DDetRetContrArtEscenicsPKTO completarPkDetRetContrArtEscenics(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DDetRetContrArtEscenicsPKTO pk = new DDetRetContrArtEscenicsPKTO();
		//java.lang.Long
		pk.setIdeDocumento(resultado.getLongWrapper("IDE_DOCUMENTO"));
		//java.lang.Integer
		pk.setNumRepeticion(resultado.getIntWrapper("NUM_REPETICION"));
		//java.lang.Short
		pk.setNumOcurrencia(resultado.getShortWrapper("NUM_OCURRENCIA"));
		//java.lang.Integer
		pk.setNumItem(resultado.getIntWrapper("NUM_ITEM"));
		
		return pk;
	}	

	/**
	 * Devuelve el objeto DetRetContrArtEscenics que se haya consultado.
	 * @return Un objeto DDetRetContrArtEscenicsTO
	 */
	public DDetRetContrArtEscenicsTO getDetRetContrArtEscenics() {
		return toDetRetContrArtEscenics;
	}

	/**
	 * Devuelve la colección de objetos DetRetContrArtEscenics que se hayan consultado.
	 * @return Un Collection con objetos DDetRetContrArtEscenicsTO
	 */
	public Collection<DDetRetContrArtEscenicsTO> getColeccionDetRetContrArtEscenics() {
		return objetosDetRetContrArtEscenics;
	}

	
	/**
	 * Devuelve la colección de Pks de DetRetContrArtEscenics que se hayan consultado.
	 * @return Un Collection con objetos DDetRetContrArtEscenicsPKTO
	 */
	public Collection<DDetRetContrArtEscenicsPKTO> getColeccionPkDetRetContrArtEscenics(){
		return objetosPkDetRetContrArtEscenics;
	}
	
	/**
	 * Indica si el DAO es de ejecución libre.
	 * @return true si es de ejecución libre; false de lo contrario
	 */
	public boolean isEjecucionLibre() {
		return ejecucionLibre;
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
			case CONSULTAR_POR_RETENCONTRIBARTESCENICS: operacion = "la consulta"; break;
			case CREAR_EN_BATCH: operacion = "la creación"; break;
			case CONSULTAR_POR_DOCUMENTO_CARGA: operacion = "la consulta"; break;
			case ELIMINAR_EN_BATCH: operacion = "la eliminación"; break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
			parametros.put(this.getClass().getName()+":validar:toDetRetContrArtEscenics",toDetRetContrArtEscenics);
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics",pkDetRetContrArtEscenics);
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics",attDetRetContrArtEscenics);

			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getIdeDocumento()",pkDetRetContrArtEscenics.getIdeDocumento());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumRepeticion()",pkDetRetContrArtEscenics.getNumRepeticion());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumOcurrencia()",pkDetRetContrArtEscenics.getNumOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumItem()",pkDetRetContrArtEscenics.getNumItem());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getIdeFormato()",attDetRetContrArtEscenics.getIdeFormato());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumVersionFormato()",attDetRetContrArtEscenics.getNumVersionFormato());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumEvento()",attDetRetContrArtEscenics.getNumEvento());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNomEspectaculo()",attDetRetContrArtEscenics.getNomEspectaculo());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodMunicipioEspectaculo()",attDetRetContrArtEscenics.getCodMunicipioEspectaculo());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodDepartamentoEspectaculo()",attDetRetContrArtEscenics.getCodDepartamentoEspectaculo());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getFecRealizacion()",attDetRetContrArtEscenics.getFecRealizacion());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getIdePersonaRutProductor()",attDetRetContrArtEscenics.getIdePersonaRutProductor());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValDireccionProductor()",attDetRetContrArtEscenics.getValDireccionProductor());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodMunicipioProductor()",attDetRetContrArtEscenics.getCodMunicipioProductor());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodDepartamentoProductor()",attDetRetContrArtEscenics.getCodDepartamentoProductor());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValLugarEvento()",attDetRetContrArtEscenics.getValLugarEvento());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumTotBoletasVendidas()",attDetRetContrArtEscenics.getNumTotBoletasVendidas());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValTotBoletasVendidas()",attDetRetContrArtEscenics.getValTotBoletasVendidas());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValServDistComerBoleteria()",attDetRetContrArtEscenics.getValServDistComerBoleteria());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics",pkDetRetContrArtEscenics);
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getIdeDocumento()",pkDetRetContrArtEscenics.getIdeDocumento());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumRepeticion()",pkDetRetContrArtEscenics.getNumRepeticion());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumOcurrencia()",pkDetRetContrArtEscenics.getNumOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumItem()",pkDetRetContrArtEscenics.getNumItem());
		}

		if (tipoOperacion == CONSULTA_GENERICA) {
			parametros.put(this.getClass().getName()+":validar:toDetRetContrArtEscenics",toDetRetContrArtEscenics);
		}

		if (tipoOperacion == CONSULTAR_POR_RETENCONTRIBARTESCENICS) {
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getIdeDocumento()",pkRetenContribArtEscenics.getIdeDocumento());
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getNumRepeticion()",pkRetenContribArtEscenics.getNumRepeticion());
		}
		
		if (tipoOperacion == CONSULTAR_POR_DOCUMENTO_CARGA) {
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getIdeDocumento()",ideDocumentoCarga);
			parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getNumRepeticion()",numRepeticionCarga);
		}
		
		if (tipoOperacion == CREAR_EN_BATCH){
			if (objetosDetRetContrArtEscenics == null
					|| objetosDetRetContrArtEscenics.size() <= 0) {
				throw new DValidarExcepcion(
						"La lista de detalle a crear no puede ser nula o estar vacía.",
						null);
			}
		}
		
		if (tipoOperacion == ELIMINAR_EN_BATCH){
			if (objetosPkDetRetContrArtEscenics == null
					|| objetosPkDetRetContrArtEscenics.size() <= 0) {
				throw new DValidarExcepcion(
						"La lista de Pks a eliminar no puede ser nula o estar vacía.",
						null);
			}
		}
		
		validarParametros(operacion,parametros);
		return true;
	}
}
