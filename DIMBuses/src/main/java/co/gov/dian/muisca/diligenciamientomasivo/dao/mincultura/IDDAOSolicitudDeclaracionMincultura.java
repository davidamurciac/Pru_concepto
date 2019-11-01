package co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura;

import java.util.List;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.interfaces.IDDAO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionMinculturaTO;


public interface IDDAOSolicitudDeclaracionMincultura extends IDDAO {

	static final int CONSULTA_FORMATO_FECHA_ACUSE = 1;

	void inicializarConsultaFormatoFechaAcuse(Integer dia, String formatos);

	public Map<Long, List<DSolicitudDeclaracionMinculturaTO>> getDeclaracionesPorNit();

}
