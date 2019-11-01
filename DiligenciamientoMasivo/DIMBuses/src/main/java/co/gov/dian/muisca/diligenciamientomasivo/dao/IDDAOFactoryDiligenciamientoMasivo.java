package co.gov.dian.muisca.diligenciamientomasivo.dao;

import co.gov.dian.muisca.arquitectura.interfaces.IDDAOFactory;
import co.gov.dian.muisca.diligenciamientomasivo.dao.etesa.IDDAOPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.dao.migracion.IDDAOFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODeclaracionContribucionParafiscal;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODeclaracionContribucionParafiscalEvento;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODeclaracionContribucionParafiscalPatrocinador;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODetRegDiaMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODetRetContrArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORegDiarioMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAOSolicitudDeclaracionMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.dao.movimientoperiodico.IDDAOMovimientoPeriodico;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.IDDAOSolicitudConciliaPago;
import co.gov.dian.muisca.diligenciamientomasivo.dao.pagocaja.IDDAOSolicitudDocConciliaPago;


public interface IDDAOFactoryDiligenciamientoMasivo extends IDDAOFactory {

	IDDAOMovimientoPeriodico getDaoMovimientoPeriodico();

	IDDAOJuegoSuerteAzar getDaoJuegoSuerteAzar();

	IDDAOPrevsABS getDaoPrevsABS();

	IDDAOSolicitudDeclaracion getDaoSolicitudDeclaracion();

	IDDAOSolicitudConciliaPago getDaoSolicitudConciliaPago();

	IDDAOSolicitudDocConciliaPago getDaoSolicitudDocConciliaPago();

	// MinComercio
	IDDAODeclaracionContribucionParafiscalEvento getDaoDeclaracionContribucionParafiscalEvento();

	IDDAODeclaracionContribucionParafiscal getDaoDeclaracionContribucionParafiscal();

	IDDAODeclaracionContribucionParafiscalPatrocinador getDaoDeclaracionContribucionParafiscalPatrocinador();

	IDDAODetRetContrArtEscenics getDaoDetRetContrArtEscenics();

	IDDAORetenContribArtEscenics getDaoRetenContribArtEscenics();

	// Mincultura
	IDDAORegDiarioMinCultura getDaoRegDiarioMinCultura();

	IDDAODetRegDiaMincultura getDaoDetRegDiaMincultura();

	IDDAOSolicitudDeclaracionMincultura getDAOSolicitudDeclaracionMincultura();
	
	IDDAOFormalizaBorradorDef getDaoFormalizaBorradorDef();

}/* fin de interface */
