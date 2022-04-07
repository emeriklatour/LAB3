package copie.strategie.impl;

import mvc.modele.Perspective;
import copie.strategie.StrategieCopie;

/******************************************************
 CopyBoth
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette strategie copie le zoom de la perspective seulement
 */
public class CopyZoom implements StrategieCopie<Perspective> {
    /**
     * Copie le zoom de la perspective "a copier" vers la perspective "cible"
     * @param toCopy objet "a copier"
     * @param target objet "cible"
     */
    @Override
    public void paste(Perspective toCopy, Perspective target) {
        target.setZoomFactor(toCopy.getZoomFactor());
    }
}
