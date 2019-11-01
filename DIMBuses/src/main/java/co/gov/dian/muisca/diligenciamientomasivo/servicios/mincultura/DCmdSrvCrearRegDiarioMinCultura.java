/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRegDiaMinculturaAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRegDiarioMinCulturaAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRegDiarioMinCulturaPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRegDiarioMinCulturaTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * 
 * <pre>$Log[10]:$</pre>
 */
public class DCmdSrvCrearRegDiarioMinCultura extends DComandoServicioInterno {

	private static final long					serialVersionUID	= 480890923487396875L;
	/** Objeto de transporte de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaTO			toRegDiarioMinCultura;
	/** Llave primaria de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaPKTO			pkRegDiarioMinCultura;
	/** Atributos de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaAttTO			attRegDiarioMinCultura;
	/** Atributos del detalle de registro diario */
	protected List<DDetRegDiaMinculturaAttTO>	lstAttDetRegDiario;

	/** Tipo de operación de consulta */
	protected int								tipoOperacion		= -1;

	public static final int						CREAR				= 0;
	public static final int						CREAR_CON_DETALLE	= 1;

	public String								numSecuencia;

	/**
	 * Inicializa la creación de RegDiarioMinCultura.
	 * 
	 * @param toRegDiarioMinCultura
	 *            Objeto de Transporte de RegDiarioMinCultura
	 */
	public void inicializar(DRegDiarioMinCulturaTO toRegDiarioMinCultura) {
		tipoOperacion = CREAR;
		this.toRegDiarioMinCultura = toRegDiarioMinCultura;

		if (toRegDiarioMinCultura != null) {
			pkRegDiarioMinCultura = this.toRegDiarioMinCultura.getPK();
			attRegDiarioMinCultura = this.toRegDiarioMinCultura.getAtt();
		}
	}

	/**
	 * 
	 * @param toRegDiarioMinCultura
	 * @param toDetRegDiario
	 */
	public void inicializar(DRegDiarioMinCulturaTO toRegDiarioMinCultura, List<DDetRegDiaMinculturaAttTO> lstAttDetRegDiario) {
		tipoOperacion = CREAR_CON_DETALLE;
		this.toRegDiarioMinCultura = toRegDiarioMinCultura;

		if (toRegDiarioMinCultura != null) {
			pkRegDiarioMinCultura = this.toRegDiarioMinCultura.getPK();
			attRegDiarioMinCultura = this.toRegDiarioMinCultura.getAtt();
		}

		if (lstAttDetRegDiario != null) {
			this.lstAttDetRegDiario = lstAttDetRegDiario;
		}
	}

	/**
	 * Ejecuta el comando de servicio.
	 * 
	 * @throws DExcepcion
	 *             Si ocurre algún error al realizar la la creación de RegDiarioMinCultura
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * 
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvCrearRegDiarioMinCultura();
	}

	/**
	 * Indica si el comando es auditable.
	 * 
	 * @return true si el comando es auditable; false de lo contrario
	 */
	public boolean isAuditable() {
		return true;
	}

	/**
	 * Obtiene la descripción del comando.
	 * 
	 * @return Un String con la descripción del comando
	 */
	public String getDescripcion() {
		return "Permite crear un objeto RegDiarioMinCultura";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado previamente a la ejecucióndel comando.
	 * 
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion
	 *             Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros = new HashMap<String, Object>();
		switch (tipoOperacion) {
		case CREAR:
			parametros.put(this.getClass().getName() + ":validar:toRegDiarioMinCultura", toRegDiarioMinCultura);
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura", attRegDiarioMinCultura);
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getFecRegistro()", attRegDiarioMinCultura.getFecRegistro());
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getCodEstado()", attRegDiarioMinCultura.getCodEstado());
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getDirServidorProceso()", attRegDiarioMinCultura.getDirServidorProceso());
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getValDescripcionProceso()", attRegDiarioMinCultura.getValDescripcionProceso());
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getFecCambio()", attRegDiarioMinCultura.getFecCambio());
			
			break;
		case CREAR_CON_DETALLE:
			parametros.put(this.getClass().getName() + ":validar:toRegDiarioMinCultura", toRegDiarioMinCultura);
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura", attRegDiarioMinCultura);
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getFecRegistro()", attRegDiarioMinCultura.getFecRegistro());
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getCodEstado()", attRegDiarioMinCultura.getCodEstado());
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getDirServidorProceso()", attRegDiarioMinCultura.getDirServidorProceso());
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getValDescripcionProceso()", attRegDiarioMinCultura.getValDescripcionProceso());
			parametros.put(this.getClass().getName() + ":validar:attRegDiarioMinCultura.getFecCambio()", attRegDiarioMinCultura.getFecCambio());
			parametros.put(this.getClass().getName() + ":validar:lstAttDetRegDiario", lstAttDetRegDiario);

			break;

		default:
			throw new DValidarExcepcion(getMensajeGeneral("la creación", "de objetos RegDiarioMinCultura"), getMensajeOperInvalida());
		}

		validarParametros("Crear", parametros);
		
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * 
	 * @param comando
	 *            Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvCrearRegDiarioMinCultura) {
			DCmdSrvCrearRegDiarioMinCultura copia = (DCmdSrvCrearRegDiarioMinCultura) comando;
			copia.toRegDiarioMinCultura = toRegDiarioMinCultura;
			copia.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
			copia.attRegDiarioMinCultura = attRegDiarioMinCultura;
			copia.tipoOperacion = tipoOperacion;
			copia.lstAttDetRegDiario = lstAttDetRegDiario;
		}
	}
}
