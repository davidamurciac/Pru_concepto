package co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar.impl;

import co.gov.dian.muisca.diligenciamiento.acciones.documentos.DCmdAccDiligenciarDocPresentado;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar.DCmdAccActJuegoSuerteAzar;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar.DCmdAccPresentar320;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.DJuegoSuerteAzarPKTO;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDGrupoDoc;

public class DCmdAccPresentar320Impl extends DCmdAccPresentar320 {

	public void ejecutarComando() {

		try{
			if (documento != null){
				IDGrupoDoc grupo = documento.getGrupos().getGrupo(1);
				Integer annoGravable = grupo.getOcurrencia(1).getValorCasilla(1).getIntWrapper();
				Integer periodo = new Integer(1);

				if (grupo.getOcurrencia(1).getValorCasilla(3) != null) {
					periodo = grupo.getOcurrencia(1).getValorCasilla(3).getIntWrapper();
				}

				idDocumento = documento.getId();
				numRepeticion = documento.getRepeticion();
				formato = documento.getIdFormato();
				version = documento.getVersionFormato();
				anno = annoGravable;
			}
			DCmdAccDiligenciarDocPresentado presentarDocumento = (DCmdAccDiligenciarDocPresentado)
				getAccion("diligenciamiento.documentos.DCmdAccDiligenciarDocPresentado");

			presentarDocumento.inicializarDiligenciamiento(idDocumento,
					numRepeticion, formato, version, anno, periodo, nit);
			presentarDocumento.ejecutar();
		
			tieneFirmas = presentarDocumento.getTieneFirmasCompletas();
			presentacionExtemporanea = presentarDocumento.isPresentacionExtemporanea();
			presentado = presentarDocumento.getEsPresentado();
			pasaValidaciones = presentarDocumento.getPasaValidaciones();
			codEstado = presentarDocumento.getEstadoDoc();
			
		     if(tieneFirmas.booleanValue() && presentado.booleanValue() && pasaValidaciones.booleanValue()){

					DJuegoSuerteAzarPKTO juegosPk = new DJuegoSuerteAzarPKTO();
					juegosPk.setIdeDocumento(idDocumento);
					juegosPk.setNumRepeticion(numRepeticion);

					DCmdAccActJuegoSuerteAzar actDocumento = (DCmdAccActJuegoSuerteAzar) 
					getAccion("diligenciamientomasivo.juegosdesuerteyazar.DCmdAccActJuegoSuerteAzar");
					actDocumento.inicializarActEstado(juegosPk, IDDocumento.IDE_ESTADO_PRESENTADO);
					actDocumento.ejecutar();

					isOk = true;

			}

		}catch (Exception e) {
			e.printStackTrace();
			isOk=false;
		}

	}

}
