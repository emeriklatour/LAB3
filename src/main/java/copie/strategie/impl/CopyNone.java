package copie.strategie.impl;

import mvc.modele.Perspective;
import copie.strategie.StrategieCopie;

public class CopyNone implements StrategieCopie<Perspective> {
    @Override
    public void paste(Perspective toCopy, Perspective target) {

    }
}
