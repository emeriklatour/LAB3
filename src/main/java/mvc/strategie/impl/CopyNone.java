package mvc.strategie.impl;

import mvc.modele.Perspective;
import mvc.strategie.StrategieCopie;

public class CopyNone implements StrategieCopie<Perspective> {
    @Override
    public void paste(Perspective toCopy, Perspective target) {

    }
}
