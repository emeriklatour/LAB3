package copie.strategie;


/******************************************************
                    StrategieCopie
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/
/*
Declare les methodes a utiliser pour toutes les strategie de copie.
 */
public interface StrategieCopie<T>{
    /**
     * Copie les attributs d'un objet "a copier" vers un objet "cible" selon la strategie de copie
     * @param toCopy objet "a copier"
     * @param target objet "cible"
     */
    void paste(T toCopy, T target);
}
