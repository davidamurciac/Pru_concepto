package co.gov.dian.muisca.diligenciamientomasivo.liquidacion.impl;

import java.util.ArrayList;
import java.util.Collection;

import co.gov.dian.muisca.diligenciamientomasivo.liquidacion.IDCasillasFormulario690;
import co.gov.dian.muisca.entradasalida.liquidacion.IDDefinicionLiquidacion;
import co.gov.dian.muisca.diligenciamiento.liquidacion.impl.AbstractDefinicionLiq;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Rb2
 * @version 1.0
 */
public class DDefinicionLiqF690V6 extends AbstractDefinicionLiq implements IDDefinicionLiquidacion {

    /**
     * Retorna una lista con los IDs de las Casillas cuyos valores son los finales.
     * @return Collection - Lista de Integers.
     */
    public Collection getIdsCasillas() {
        ArrayList listaCasillas = new ArrayList();
        listaCasillas.add(new Integer(IDCasillasFormulario690.ANNO));
        listaCasillas.add(new Integer(IDCasillasFormulario690.CONCEPTO));
        listaCasillas.add(new Integer(IDCasillasFormulario690.PERIODO));
        return listaCasillas;
    }
}
