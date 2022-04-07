package copie.strategie.impl;

import mvc.modele.Perspective;
import copie.strategie.StrategieCopie;

/******************************************************
                    CopyNone
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette strategie ne copie rien. Elle est implemente pour la coherence
 */
public class CopyNone implements StrategieCopie<Perspective> {

    /**
     *
     * @param toCopy objet "a copier"
     * @param target objet "cible"
     */
    @Override
    public void paste(Perspective toCopy, Perspective target) {

    }
}
