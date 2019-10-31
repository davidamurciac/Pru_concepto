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

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para eliminar un objeto
 * DetRetContrArtEscenics.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
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
public class DCmdSrvElimDetRetContrArtEscenics extends DComandoServicioInterno {
	private static final long serialVersionUID = 2118727533L;

	/** Llave primaria de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics;

	public static final int ELIMINAR = 0;
	public static final int ELIMINAR_EN_BATCH = 1;

	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;

	protected Collection<DDetRetContrArtEscenicsPKTO> objetosPkDetRetContrArtEscenics;

	protected int registrosEliminados;

	/**
	 * Para copiar el contenido del comando actual al comando enviado como
	 * parámetro.
	 * 
	 * @param comando
	 *            Comando sobre el cual copiar
	 */
	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvElimDetRetContrArtEscenics) {
			final DCmdSrvElimDetRetContrArtEscenics copia = (DCmdSrvElimDetRetContrArtEscenics) comando;
			copia.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
			copia.objetosPkDetRetContrArtEscenics = objetosPkDetRetContrArtEscenics;
			copia.tipoOperacion = tipoOperacion;
			copia.registrosEliminados = registrosEliminados;
		}
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * 
	 * @return Un Object con la copia del comando
	 */
	@Override
	public Object clonar() {
		return new DCmdSrvElimDetRetContrArtEscenics();
	}

	/**
	 * Ejecuta el comando de servicio.
	 * 
	 * @throws DExcepcion
	 *             Si ocurre algún error al realizar la la eliminación de
	 *             DetRetContrArtEscenics
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene la descripción del comando.
	 * 
	 * @return Un String con la descripción del comando
	 */
	@Override
	public String getDescripcion() {
		return "Permite eliminar un objeto DetRetContrArtEscenics";
	}

	public int getRegistrosEliminados() {
		return registrosEliminados;
	}

	/**
	 * Inicializa la eliminación de DetRetContrArtEscenics.
	 * 
	 * @param pkDetRetContrArtEscenics
	 *            Llave primaria de DetRetContrArtEscenics
	 */
	public void inicializar(DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics) {
		tipoOperacion = ELIMINAR;
		this.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
	}

	/**
	 * Inicializa la eliminación en batch de DetRetContrArtEscenics.
	 * 
	 * @param Collection
	 *            <DDetRetContrArtEscenicsPKTO> lista de Objetos pk de
	 *            DetRetContrArtEscenics
	 */
	public void inicializarEliminarEnBatch(
			Collection<DDetRetContrArtEscenicsPKTO> objetosPkDetRetContrArtEscenics) {
		tipoOperacion = ELIMINAR_EN_BATCH;
		this.objetosPkDetRetContrArtEscenics = objetosPkDetRetContrArtEscenics;
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
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion
	 *             Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		final Map<String, Object> parametros = new HashMap<String, Object>();
		switch (tipoOperacion) {
		case ELIMINAR:
			parametros.put(this.getClass().getName()
					+ ":validar:pkDetRetContrArtEscenics",
					pkDetRetContrArtEscenics);
			parametros.put(this.getClass().getName()
					+ ":validar:pkDetRetContrArtEscenics.getIdeDocumento()",
					pkDetRetContrArtEscenics.getIdeDocumento());
			parametros.put(this.getClass().getName()
					+ ":validar:pkDetRetContrArtEscenics.getNumRepeticion()",
					pkDetRetContrArtEscenics.getNumRepeticion());
			parametros.put(this.getClass().getName()
					+ ":validar:pkDetRetContrArtEscenics.getNumOcurrencia()",
					pkDetRetContrArtEscenics.getNumOcurrencia());
			parametros.put(this.getClass().getName()
					+ ":validar:pkDetRetContrArtEscenics.getNumItem()",
					pkDetRetContrArtEscenics.getNumItem());
			break;
		case ELIMINAR_EN_BATCH:
			if (objetosPkDetRetContrArtEscenics == null
					|| objetosPkDetRetContrArtEscenics.size() <= 0) {
				throw new DValidarExcepcion(
						"La lista de Pks de DetRetContrArtEscenics a eliminar no puede ser nula o estar vacía.",
						null);
			}
			break;
		default:
			throw new DValidarExcepcion(getMensajeGeneral("la eliminación",
					"de objetos DetRetContrArtEscenics"),
					getMensajeOperInvalida());
		}
		validarParametros("Eliminar", parametros);
		return true;
	}
}