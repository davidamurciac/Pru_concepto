package co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.arquitectura.mensajes.DManipuladorMensaje;
import co.gov.dian.muisca.arquitectura.mensajes.IDArqMensajes;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionMinculturaTO;


public class DCmdSrvConsLstDeclaracionMincultura extends DComandoServicioInterno {

	private static final long										serialVersionUID				= -4824469774360074742L;
	protected static final int										CONSULTA_FORMATO_FECHA_ACUSE	= 1;

	protected int													tipoOperacion					= -1;
	protected Integer												dia;
	protected String												formatos;
	protected Map<Long, List<DSolicitudDeclaracionMinculturaTO>>	declaracionesPorNit;

	public void inicializarConsultaFormatoFechaAcuse(Integer dia, String formatos) {
		this.tipoOperacion = CONSULTA_FORMATO_FECHA_ACUSE;

		this.dia = dia;
		this.formatos = formatos;
	}

	public Map<Long, List<DSolicitudDeclaracionMinculturaTO>> getDeclaracionesPorNit() {
		return declaracionesPorNit;
	}

	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
		Map<String, Object> parametros = new HashMap<String, Object>();
		String operacion = null;

		switch (tipoOperacion) {
		case CONSULTA_FORMATO_FECHA_ACUSE:
			operacion = "La consulta";

			parametros.put(this.getClass().getName() + ":validar:dia", dia);
			parametros.put(this.getClass().getName() + ":validar:formatos", formatos);

			break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		validarParametros(operacion, parametros);

		return true;
	}

	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvConsLstDeclaracionMincultura) {
			DCmdSrvConsLstDeclaracionMincultura copia = (DCmdSrvConsLstDeclaracionMincultura) comando;

			copia.tipoOperacion = tipoOperacion;
			copia.dia = dia;
			copia.formatos = formatos;
			copia.declaracionesPorNit = declaracionesPorNit;
		}
	}

	@Override
	public Object clonar() {
		return new DCmdSrvConsLstDeclaracionMincultura();
	}

	@Override
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getDescripcion() {
		return "Consulta la lista de solicitudes e identificadores de documento relacionados con las declaraciones de Mincultura";
	}

	@Override
	public boolean isAuditable() {
		return true;
	}

}
