package co.gov.dian.muisca.diligenciamientomasivo.recursos;

import java.util.ResourceBundle;

import co.gov.dian.muisca.arquitectura.web.IDConstantesWeb;

public class DAdminRecursosDilMasivo {
	private static ResourceBundle rb;

	/**
	 * 
	 * @return
	 */
	private static ResourceBundle getRsBundle() {
		ResourceBundle  rsBoundle = ResourceBundle.getBundle(IDConstantesWeb.MSG_BUNDLE_DIM);
		return rsBoundle;
	}
	
	/**
	 * 
	 * @param recurso
	 * @return
	 */
	public static String obtenerRecurso(String recurso){
		rb = getRsBundle();
		return rb.getString(recurso);
	}
}