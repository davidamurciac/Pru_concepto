package co.gov.dian.muisca.diligenciamientomasivo.dao.etesa;

import java.util.List;

import co.gov.dian.muisca.arquitectura.interfaces.IDDAO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSTO;



/**
 * Interfaz de operaciones sobre la tabla EYS_DOCUMENTOS_BAN_SALIDA, para prevalidadores iniciales ETESA
 * 
 * @author nfontechar
 *
 */

public interface IDDAOPrevsABS extends IDDAO {
	
	
	
	
	/*
	 * MÉTODOS INICIALIZAR*
	 */
	 
	 public void inicializarCrear(DPrevalidadorABSTO to);
	 public void inicializarActualizar(DPrevalidadorABSTO to);
	 public void inicializarEliminar(DPrevalidadorABSPKTO pk);
	 public void inicializarConsultarPorPK(DPrevalidadorABSPKTO pk);
	 public void inicializarConsultarPorNIT(DPrevalidadorABSPKTO pk);
	 public void inicializarConsultarPorSolicitudYExpediente(DPrevalidadorABSPKTO pk);
	
	 
	 
	 
	 /*
	  * GETTERS y SETTERS
	  */
	 
	 public List<DPrevalidadorABSTO> getLosTOs();
	
	

}/*fin de interface*/
