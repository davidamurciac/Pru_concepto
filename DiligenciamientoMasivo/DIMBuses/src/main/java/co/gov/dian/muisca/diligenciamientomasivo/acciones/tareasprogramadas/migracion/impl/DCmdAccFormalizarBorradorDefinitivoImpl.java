package co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.migracion.impl;

import java.util.Collection;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.migracion.DCmdAccFormalizarBorradorDefinitivo;
import co.gov.dian.muisca.diligenciamientomasivo.buses.DBusServiciosDelegateDIMB;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.DCmdSrvConsLstFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.DCmdSrvFormalizarBorradorDefinitivo;

public class DCmdAccFormalizarBorradorDefinitivoImpl extends DCmdAccFormalizarBorradorDefinitivo {

	/**
	 *
	 */
	private static final long serialVersionUID = -1877056162991475542L;
	private static final Logger log = Logger.getLogger(DCmdAccFormalizarBorradorDefinitivoImpl.class);

	/**
	 * Método que se ejecuta en ambiente transaccional
	 */
	@Override
	protected void ejecutarComando() {
		log.info("--->>>> DCmdAccFormalizarBorradorDefinitivo: Ejecución " + (exito ? "exitosa" : "con errores"));
		isOk = exito;
	}

	@Override
	protected void ejecutarComandoSinTransaccion() throws DExcepcion {
		isOk = true;
		exito = true;

		try {
			final DCmdSrvConsLstFormalizaBorradorDef consLstFormalizaBorradorDef = (DCmdSrvConsLstFormalizaBorradorDef) getServicio(
					"diligenciamientomasivo.migracion.DCmdSrvConsLstFormalizaBorradorDef");
			consLstFormalizaBorradorDef.inicializarConsultaTodos();
			consLstFormalizaBorradorDef.ejecutar();

			final Collection<DFormalizaBorradorDefTO> colDocsFormalizarDef = consLstFormalizaBorradorDef
					.getColeccionFormalizaBorradorDef();

			if (!colDocsFormalizarDef.isEmpty()) {
				for (final DFormalizaBorradorDefTO docFormalizar : colDocsFormalizarDef) {

					final DBusServiciosDelegateDIMB delegado = new DBusServiciosDelegateDIMB();
					delegado.setTransaccional(true);
					delegado.setContextoSeguridad(getContexto().getContextoSeguridad());
					DCmdSrvFormalizarBorradorDefinitivo srvFormalizarBorradorDefinitivo;
					srvFormalizarBorradorDefinitivo = (DCmdSrvFormalizarBorradorDefinitivo) delegado
							.getComando("diligenciamientomasivo.migracion.DCmdSrvFormalizarBorradorDefinitivo");
					srvFormalizarBorradorDefinitivo.inicializar(docFormalizar);
					srvFormalizarBorradorDefinitivo.ejecutar();
				}
			} else {
				log.info("No hay documentos para ser formalizados");
			}

		} catch (final DExcepcion e) {
			mensajeError = e.getMessage();
			mensajeErrorDetallado = e.getMensajeDetallado();

			isOk = false;
			exito = false;
		} catch (final Exception e) {
			mensajeError = e.getMessage();
			mensajeErrorDetallado = e.getMessage();

			isOk = false;
			exito = false;
		}

	}
}