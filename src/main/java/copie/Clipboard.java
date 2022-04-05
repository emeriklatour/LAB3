package copie;

import copie.strategie.StrategieCopie;

public class Clipboard<T>{
    private T savedState;
    private StrategieCopie<T> strategieCopie;

    public Clipboard(T state, StrategieCopie<T> strategieCopie) {
        this.savedState = state;
        this.strategieCopie = strategieCopie;
    }

    public StrategieCopie<T> getStrategieCopie() {
        return this.strategieCopie;
    }

    public void paste(T target) {
        strategieCopie.paste(savedState, target);
    }

}
