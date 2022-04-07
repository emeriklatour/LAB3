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
 * Cette strategie copie tous les elements de la perspective
 */
public class CopyBoth implements StrategieCopie<Perspective> {
    /**
     * Transfere la position et le le zoom de la perspective
     * @param toCopy objet "a copier"
     * @param target objet "cible"
     */
    @Override
    public void paste(Perspective toCopy, Perspective target) {
        target.setPosX(toCopy.getPosX());
        target.setPosY(toCopy.getPosY());
        target.setZoomFactor(toCopy.getZoomFactor());
    }
}
