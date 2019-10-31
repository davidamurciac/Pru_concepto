/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.impl;


import java.sql.Timestamp;
import java.util.Date;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORegDiarioMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRegDiaMinculturaAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRegDiaMinculturaTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvCrearDetRegDiaMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvCrearRegDiarioMinCultura;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>$Log[10]:$</pre>
 */
public class DCmdSrvCrearRegDiarioMinCulturaImpl extends DCmdSrvCrearRegDiarioMinCultura {

	private static final long serialVersionUID = -3029610611157545658L;

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion
	 *             Si ocurre algn error al realizar la creación de RegDiarioMinCultura
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAORegDiarioMinCultura dao = fabrica.getDaoRegDiarioMinCultura();

			switch (tipoOperacion) {
			case CREAR:
				// Crear
				dao.inicializarCrear(toRegDiarioMinCultura);
				admin.guardar(dao);

				break;
			case CREAR_CON_DETALLE:
				dao.inicializarCrear(toRegDiarioMinCultura);
				admin.guardar(dao);

				DCmdSrvCrearDetRegDiaMincultura srvCrearDetalle = (DCmdSrvCrearDetRegDiaMincultura) getServicio("diligenciamientomasivo.mincultura.DCmdSrvCrearDetRegDiaMincultura");

				for (DDetRegDiaMinculturaAttTO attDetRegDiario : lstAttDetRegDiario) {
					// Se asigna el número de secuencia para el detalle
					attDetRegDiario.setIdeRegistroDiario(dao.getNumSecuencia().toString());
					attDetRegDiario.setFecCambio(new Timestamp(new Date().getTime()));
					attDetRegDiario.setFecRegistro(toRegDiarioMinCultura.getAtt().getFecRegistro());

					DDetRegDiaMinculturaTO toDetRegDiaMincultura = new DDetRegDiaMinculturaTO();
					toDetRegDiaMincultura.setAtt(attDetRegDiario);

					srvCrearDetalle.inicializar(toDetRegDiaMincultura);
					srvCrearDetalle.ejecutar();
				}

				break;

			default:
				throw new DValidarExcepcion(getMensajeGeneral("la creación", "de objetos RegDiarioMinCultura"), getMensajeOperInvalida());
			}
		}
		finally {
			admin.cerrarSesion();
		}
	}

}
