package co.gov.dian.muisca.diligenciamientomasivo.dao.impl;

import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DDAOFactory;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOJuegoSuerteAzar;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOSolicitudDeclaracion;
import co.gov.dian.muisca.diligenciamientomasivo.dao.etesa.IDDAOPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.dao.etesa.impl.oracle.DDAOPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.oracle.DDAOJuegoSuerteAzar;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.oracle.DDAOSolicitudDeclaracion;
import co.gov.dian.muisca.diligenciamientomasivo.dao.migracion.IDDAOFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.dao.migracion.impl.oracle.DDAOFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODeclaracionContribucionParafiscal;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODeclaracionContribucionParafiscalEvento;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODeclaracionContribucionParafiscalPatrocinador;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODetRegDiaMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODetRetContrArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORegDiarioMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAOSolicitudDeclaracionMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle.DDAODeclaracionContribucionParafiscal;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle.DDAODeclaracionContribucionParafiscalEvento;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle.DDAODeclaracionContribucionParafiscalPatrocinador;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle.DDAODetRegDiaMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle.DDAODetRetContrArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle.DDAORegDiarioMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle.DDAORetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle.DDAOSolicitudDeclaracionMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.movimientoperiodico.IDDAOMovimientoPeriodico;
import co.gov.dian.muisca.diligenciamientomasivo.dao.movimientoperiodico.impl.oracle.DDAOMovimientoPeriodico;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.IDDAOSolicitudConciliaPago;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.IDDAOSolicitudDocConciliaPago;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.impl.oracle.DDAOSolicitudConciliaPago;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.impl.oracle.DDAOSolicitudDocConciliaPago;


public class DDAOFactoryDiligenciamientoMasivo extends DDAOFactory implements IDDAOFactoryDiligenciamientoMasivo {

	public IDDAOMovimientoPeriodico getDaoMovimientoPeriodico() {
		return new DDAOMovimientoPeriodico();
	}/* fin de getDaoMovimientoPeriodico */

	public IDDAOJuegoSuerteAzar getDaoJuegoSuerteAzar() {
		return new DDAOJuegoSuerteAzar();
	}

	public IDDAOPrevsABS getDaoPrevsABS() {
		return new DDAOPrevsABS();
	}/* fin de getDaoPrevsABS */

	public IDDAOSolicitudDeclaracion getDaoSolicitudDeclaracion() {
		return new DDAOSolicitudDeclaracion();
	}

	public IDDAOSolicitudConciliaPago getDaoSolicitudConciliaPago() {
		return new DDAOSolicitudConciliaPago();
	}

	public IDDAOSolicitudDocConciliaPago getDaoSolicitudDocConciliaPago() {
		return new DDAOSolicitudDocConciliaPago();
	}

	public IDDAODeclaracionContribucionParafiscalEvento getDaoDeclaracionContribucionParafiscalEvento() {
		return new DDAODeclaracionContribucionParafiscalEvento();
	}

	public IDDAODeclaracionContribucionParafiscal getDaoDeclaracionContribucionParafiscal() {
		return new DDAODeclaracionContribucionParafiscal();
	}

	public IDDAODeclaracionContribucionParafiscalPatrocinador getDaoDeclaracionContribucionParafiscalPatrocinador() {
		return new DDAODeclaracionContribucionParafiscalPatrocinador();
	}

	public IDDAORetenContribArtEscenics getDaoRetenContribArtEscenics() {
		return new DDAORetenContribArtEscenics();
	}

	public IDDAODetRetContrArtEscenics getDaoDetRetContrArtEscenics() {
		return new DDAODetRetContrArtEscenics();
	}

	public IDDAORegDiarioMinCultura getDaoRegDiarioMinCultura() {
		return new DDAORegDiarioMinCultura();
	}

	public IDDAODetRegDiaMincultura getDaoDetRegDiaMincultura() {
		return new DDAODetRegDiaMincultura();
	}

	public IDDAOSolicitudDeclaracionMincultura getDAOSolicitudDeclaracionMincultura() {
		return new DDAOSolicitudDeclaracionMincultura();
	}
	
	public IDDAOFormalizaBorradorDef getDaoFormalizaBorradorDef(){
		return new DDAOFormalizaBorradorDef();
	}
}
