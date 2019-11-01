package co.gov.dian.muisca.diligenciamientomasivo.liquidacion;

import co.gov.dian.muisca.entradasalida.formatos.IDCasillasEncabezadoFormularios;

/**
 *
 * <p>Title: </p>
 * <p>Description: Definición de IDs de los valores recibidos para el Formulario 690.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Rb2
 * @version 1.0
 */
public interface IDCasillasFormulario690v7 extends IDCasillasEncabezadoFormularios {
    int TIPO_DECLARANTE = 0;

    int ARANCEL = 203;
    int IVA = 152;
    int SALVAGUARDIA = 588;
    int DEREC_COMPENSATORIOS = 590;
    int DEREC_ANIDUMPING = 592;
    int SANCION = 498;
    int RESCATE = 594;
    int INTERESES_MORA = 460;
    int NUMERO_FORMULARIO_DECLARACION = 28;
}
