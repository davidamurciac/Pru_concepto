/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRetContrArtEscenicsPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRetContrArtEscenicsTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRetenContribArtEscenicsPKTO;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para consultar objetos
 * DetRetContrArtEscenics.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: DIAN
 * </p>
 * 
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * 
 *          <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsLstDetRetContrArtEscenics extends
		DComandoServicioInterno {
	private static final long serialVersionUID = 1600011730L;

	public static final int CONSULTAR_POR_RETENCONTRIBARTESCENICS = 0;
	public static final int CONSULTA_GENERICA = 1;
	public static final int CONSULTAR_POR_DOCUMENTO_CARGA = 2;

	/** Llave primaria de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics;

	protected DDetRetContrArtEscenicsTO toDetRetContrArtEscenics;

	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	/** Colección de objetos DDetRetContrArtEscenicsTO */
	protected Collection<DDetRetContrArtEscenicsTO> objetosDetRetContrArtEscenics;
	/** colección de objetos DDetRetContrArtEscenicsTO */
	protected Collection<DDetRetContrArtEscenicsPKTO> objetosPkDetRetContrArtEscenics;

	/** Identificador de documento de carga */
	protected Long ideDocumentoCarga;
	/** Número de repetición de documento de carga */
	protected Integer numRepeticionCarga;

	/**
	 * Para copiar el contenido del comando actual al comando enviado como
	 * parámetro.
	 * 
	 * @param comando
	 *            Comando sobre el cual copiar
	 */
	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvConsLstDetRetContrArtEscenics) {
			final DCmdSrvConsLstDetRetContrArtEscenics copia = (DCmdSrvConsLstDetRetContrArtEscenics) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
			copia.objetosDetRetContrArtEscenics = objetosDetRetContrArtEscenics;
			copia.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
			copia.ideDocumentoCarga = ideDocumentoCarga;
			copia.numRepeticionCarga = numRepeticionCarga;
			copia.objetosPkDetRetContrArtEscenics = objetosPkDetRetContrArtEscenics;
		}
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * 
	 * @return Un Object con la copia del comando
	 */
	@Override
	public Object clonar() {
		return new DCmdSrvConsLstDetRetContrArtEscenics();
	}

	/**
	 * Ejecuta el comando de servicio.
	 * 
	 * @throws DExcepcion
	 *             Si ocurre algún error al realizar la la consulta de
	 *             DetRetContrArtEscenics
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Devuelve la colección de objetos DetRetContrArtEscenics que se hayan
	 * consultado.
	 * 
	 * @return Un Collection con objetos DDetRetContrArtEscenicsTO
	 */
	public Collection<DDetRetContrArtEscenicsTO> getColeccionDetRetContrArtEscenics() {
		return objetosDetRetContrArtEscenics;
	}

	/**
	 * 
	 * @return Un Collection con objetos DDetRetContrArtEscenicsPKTO
	 */
	public Collection<DDetRetContrArtEscenicsPKTO> getColeccionPkDetRetContrArtEscenics() {
		return objetosPkDetRetContrArtEscenics;
	}

	/**
	 * Obtiene la descripción del comando.
	 * 
	 * @return Un String con la descripción del comando
	 */
	@Override
	public String getDescripcion() {
		return "Permite consultar objetos DetRetContrArtEscenics";
	}

	/**
	 * Inicializa la consulta genérica de DetRetContrArtEscenics.
	 * 
	 * @param attDetRetContrArtEscenics
	 *            Atributos de DetRetContrArtEscenics
	 */
	public void inicializarConsultaGenerica(
			DDetRetContrArtEscenicsTO toDetRetContrArtEscenics) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
	}

	/**
	 * Inicializa la consulta genérica de RetenContribArtEscenics.
	 * 
	 * @param attRetenContribArtEscenics
	 *            Atributos de RetenContribArtEscenics
	 */
	public void inicializarConsultarPorDocumentoCarga(Long ideDocumentoCarga,
			Integer numRepeticionCarga) {
		tipoOperacion = CONSULTAR_POR_DOCUMENTO_CARGA;
		this.ideDocumentoCarga = ideDocumentoCarga;
		this.numRepeticionCarga = numRepeticionCarga;
	}

	/**
	 * Inicializa la consulta por RetenContribArtEscenics.
	 * 
	 * @param pkRetenContribArtEscenics
	 *            Llave primaria de RetenContribArtEscenics
	 */
	public void inicializarConsultarPorRetenContribArtEscenics(
			DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics) {
		tipoOperacion = CONSULTAR_POR_RETENCONTRIBARTESCENICS;
		this.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
		objetosDetRetContrArtEscenics = null;
	}

	/**
	 * Indica si el comando es auditable.
	 * 
	 * @return true si el comando es auditable; false de lo contrario
	 */
	@Override
	public boolean isAuditable() {
		return true;
	}

	/**
	 * Método para validar los parámetros inicializados, invocado previamente a
	 * la ejecución del comando.
	 * 
	 * @return true si los paretros son váidos; false de lo contrario
	 * @throws DValidarExcepcion
	 *             Si los parámetros no son váidos
	 */
	public boolean validar() throws DValidarExcepcion {
		final Map<String, Object> parametros = new HashMap<String, Object>();
		switch (tipoOperacion) {
		case CONSULTAR_POR_RETENCONTRIBARTESCENICS:
			parametros.put(this.getClass().getName()
					+ ":validar:pkRetenContribArtEscenics",
					pkRetenContribArtEscenics);
			parametros.put(this.getClass().getName()
					+ ":validar:pkRetenContribArtEscenics.getIdeDocumento()",
					pkRetenContribArtEscenics.getIdeDocumento());
			parametros.put(this.getClass().getName()
					+ ":validar:pkRetenContribArtEscenics.getNumRepeticion()",
					pkRetenContribArtEscenics.getNumRepeticion());
			break;

		case CONSULTA_GENERICA:
			parametros.put(this.getClass().getName()
					+ ":validar:toDetRetContrArtEscenics",
					toDetRetContrArtEscenics);
			break;
		case CONSULTAR_POR_DOCUMENTO_CARGA:
			parametros.put(this.getClass().getName()
					+ ":validar:ideDocumentoCarga", ideDocumentoCarga);
			parametros.put(this.getClass().getName()
					+ ":validar:numRepeticionCarga", numRepeticionCarga);
			break;

		default:
			throw new DValidarExcepcion(getMensajeGeneral("la consulta",
					"de objetos DetRetContrArtEscenics"),
					getMensajeOperInvalida());
		}
		validarParametros("Listar", parametros);
		return true;
	}
}
