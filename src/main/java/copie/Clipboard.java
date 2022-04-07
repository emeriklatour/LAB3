package copie;
import copie.strategie.StrategieCopie;

/******************************************************
                        Clipboard
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/
/*
La classe Clipboard permet de sauvegarder le type donne en parametre, ainsi que
la strategie de copie à lui appliquer.
 */
public class Clipboard<T>{
    private T savedState;
    private StrategieCopie<T> strategieCopie;

    /**
     * Constructeur de la classe Clipboard
     * @param state l'état ou l'objet sauvegardé.
     * @param strategieCopie la stratégie de copie à appliquer à l'état.
     */
    public Clipboard(T state, StrategieCopie<T> strategieCopie) {
        this.savedState = state;
        this.strategieCopie = strategieCopie;
    }

    /**
     * @return la stratégie de copie utilisé pour l'état sauvegardé.
     */
    public StrategieCopie<T> getStrategieCopie() {
        return this.strategieCopie;
    }

    /**
     * Permet de copier les attributs de l'état de l'objet sauvegardé vers un autre objet "target"
     * @param target l'objet sur lequel on doit copier les attributs de l'état sauvegarde
     */
    public void paste(T target) {
        strategieCopie.paste(savedState, target);
    }

}
