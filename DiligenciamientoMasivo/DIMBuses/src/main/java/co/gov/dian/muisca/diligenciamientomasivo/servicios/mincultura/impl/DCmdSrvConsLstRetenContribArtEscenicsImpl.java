/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.impl;


import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvConsLstRetenContribArtEscenics;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para consultar objetos RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsLstRetenContribArtEscenicsImpl extends DCmdSrvConsLstRetenContribArtEscenics {

	private static final long serialVersionUID = 895728753L; 

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * consulta de objetos RetenContribArtEscenics
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAORetenContribArtEscenics dao = fabrica.getDaoRetenContribArtEscenics();

			switch (tipoOperacion) {

				case CONSULTA_GENERICA:
					dao.inicializarConsultaGenerica(toRetenContribArtEscenics);
					break;

				default:
					throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos RetenContribArtEscenics"), getMensajeOperInvalida());
			}

			// Consultar
			admin.buscar(dao);
			objetosRetenContribArtEscenics = dao.getColeccionRetenContribArtEscenics();
		}
		finally {
			admin.cerrarSesion();
		}
	}
}
