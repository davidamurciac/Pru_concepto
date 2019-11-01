package co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAOSolicitudDeclaracionMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvConsLstDeclaracionMincultura;


public class DCmdSrvConsLstDeclaracionMinculturaImpl extends DCmdSrvConsLstDeclaracionMincultura {

	private static final long serialVersionUID = 7120584184606472395L;

	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();

		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAOSolicitudDeclaracionMincultura dao = fabrica.getDAOSolicitudDeclaracionMincultura();

			switch (tipoOperacion) {
			case CONSULTA_FORMATO_FECHA_ACUSE:
				dao.inicializarConsultaFormatoFechaAcuse(dia, formatos);
				break;
			}

			// Consultar
			admin.buscar(dao);

			declaracionesPorNit = dao.getDeclaracionesPorNit();
		}
		finally {
			admin.cerrarSesion();
		}
	}

}
