package co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.migracion;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;

public class DCmdAccFormalizarBorradorDefinitivo extends DComandoAccion {

	/**
	 *
	 */
	private static final long serialVersionUID = 8577184425704533215L;

	protected boolean exito;

	protected Integer ANIO_GRAVABLE_DESDE = 2011;
	protected Integer ANIO_GRAVABLE_HASTA = 2016;

	protected DFormalizaBorradorDefTO to;
	protected int tipoOperacion = -1;

	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccFormalizarBorradorDefinitivo) {
			final DCmdAccFormalizarBorradorDefinitivo copia = (DCmdAccFormalizarBorradorDefinitivo) comando;
			copia.exito = exito;
			copia.to = to;
			copia.tipoOperacion = tipoOperacion;
		}
	}

	@Override
	public Object clonar() {
		return new DCmdAccFormalizarBorradorDefinitivo();
	}

	@Override
	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getDescripcion() {
		return "Comando de acción que se encarga de la formalización de los borradores definitivos escritos en la tabla";
	}

	@Override
	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		return true;
	}

}
