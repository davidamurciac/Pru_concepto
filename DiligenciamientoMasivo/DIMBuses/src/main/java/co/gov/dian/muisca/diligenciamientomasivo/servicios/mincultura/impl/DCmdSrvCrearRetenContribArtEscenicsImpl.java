/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.impl;

import java.util.Collection;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRetContrArtEscenicsPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRetenContribArtEscenicsTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvConsLstDetRetContrArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvConsLstRetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvCrearDetRetContrArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvCrearRetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvElimDetRetContrArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvElimRetenContribArtEscenics;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para crear un objeto
 * RetenContribArtEscenics.
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
public class DCmdSrvCrearRetenContribArtEscenicsImpl extends
		DCmdSrvCrearRetenContribArtEscenics {

	private static final long serialVersionUID = 1935699430L;

	/**
	 * Ejecuta el comando de servicio.
	 * 
	 * @throws DExcepcion
	 *             Si ocurre algn error al realizar la creación de
	 *             RetenContribArtEscenics
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		final IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			final IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			final IDDAORetenContribArtEscenics dao = fabrica
					.getDaoRetenContribArtEscenics();

			switch (tipoOperacion) {
			case CREAR:
				toRetenContribArtEscenics = new DRetenContribArtEscenicsTO();
				toRetenContribArtEscenics.setPK(pkRetenContribArtEscenics);
				toRetenContribArtEscenics.setAtt(attRetenContribArtEscenics);
				dao.inicializarCrear(toRetenContribArtEscenics);
				admin.guardar(dao);
				break;
			case CREAR_CON_DETALLE:
				final DCmdSrvConsLstDetRetContrArtEscenics srvConsLstDetalle = (DCmdSrvConsLstDetRetContrArtEscenics) getServicio("diligenciamientomasivo.mincultura.DCmdSrvConsLstDetRetContrArtEscenics");
				srvConsLstDetalle.inicializarConsultarPorDocumentoCarga(
						toRetenContribArtEscenics.getAtt()
								.getIdeDocumentoCarga(),
						toRetenContribArtEscenics.getAtt()
								.getNumRepeticionCarga());
				srvConsLstDetalle.ejecutar();

				final Collection<DDetRetContrArtEscenicsPKTO> objetosPkDetRetContrArtEscenics = srvConsLstDetalle
						.getColeccionPkDetRetContrArtEscenics();

				if (objetosPkDetRetContrArtEscenics != null
						&& objetosPkDetRetContrArtEscenics.size() > 0) {
					final DCmdSrvElimDetRetContrArtEscenics srvElimDetalle = (DCmdSrvElimDetRetContrArtEscenics) getServicio("diligenciamientomasivo.mincultura.DCmdSrvElimDetRetContrArtEscenics");
					srvElimDetalle
							.inicializarEliminarEnBatch(objetosPkDetRetContrArtEscenics);
					srvElimDetalle.ejecutar();
					final int registrosEliminados = srvElimDetalle
							.getRegistrosEliminados();
					if (registrosEliminados > 0) {
						final DRetenContribArtEscenicsTO toReten = new DRetenContribArtEscenicsTO();
						toReten.getAtt().setIdeDocumentoCarga(
								toRetenContribArtEscenics.getAtt()
										.getIdeDocumentoCarga());
						toReten.getAtt().setNumRepeticionCarga(
								toRetenContribArtEscenics.getAtt()
										.getNumRepeticionCarga());
						final DCmdSrvConsLstRetenContribArtEscenics srvConsLst = (DCmdSrvConsLstRetenContribArtEscenics) getServicio("diligenciamientomasivo.mincultura.DCmdSrvConsLstRetenContribArtEscenics");
						srvConsLst.inicializarConsultaGenerica(toReten);
						srvConsLst.ejecutar();

						final Collection<DRetenContribArtEscenicsTO> colRetenContArtEsc = srvConsLst
								.getColeccionRetenContribArtEscenics();

						if (colRetenContArtEsc != null
								&& colRetenContArtEsc.size() > 0) {
							final DCmdSrvElimRetenContribArtEscenics srvElim = (DCmdSrvElimRetenContribArtEscenics) getServicio("diligenciamientomasivo.mincultura.DCmdSrvElimRetenContribArtEscenics");
							srvElim.inicializar(colRetenContArtEsc.iterator()
									.next().getPK());
							srvElim.ejecutar();
						}
					}
				}
				dao.inicializarCrear(toRetenContribArtEscenics);
				admin.guardar(dao);

				final DCmdSrvCrearDetRetContrArtEscenics srvCrearDetalle = (DCmdSrvCrearDetRetContrArtEscenics) getServicio("diligenciamientomasivo.mincultura.DCmdSrvCrearDetRetContrArtEscenics");
				srvCrearDetalle
						.inicializarCrearEnBatch(lstDetRetContrArtEscenics);
				srvCrearDetalle.ejecutar();
				break;
			default:
				throw new DValidarExcepcion(getMensajeGeneral("la creación",
						"de objetos DetRetContrArtEscenics"),
						getMensajeOperInvalida());
			}

		} finally {
			admin.cerrarSesion();
		}
	}
}