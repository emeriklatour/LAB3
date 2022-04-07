package copie.strategie.impl;

import mvc.modele.Perspective;
import copie.strategie.StrategieCopie;
/******************************************************
                    CopyTranslate
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette strategie copie la position de la perspective seulement
 */
public class CopyTranslate implements StrategieCopie<Perspective> {
    /**
     * Copie la position de la perspective "a copier" vers la perspective "cible"
     * @param toCopy objet "a copier"
     * @param target objet "cible"
     */
    @Override
    public void paste(Perspective toCopy, Perspective target) {
        target.setPosX(toCopy.getPosX());
        target.setPosY(toCopy.getPosY());
    }
}
