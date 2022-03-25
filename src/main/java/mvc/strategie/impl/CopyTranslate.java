package mvc.strategie.impl;

import mvc.modele.Perspective;
import mvc.strategie.StrategieCopie;

public class CopyTranslate implements StrategieCopie<Perspective> {
    @Override
    public void paste(Perspective toCopy, Perspective target) {
        target.setPosX(toCopy.getPosX());
        target.setPosY(toCopy.getPosY());
    }
}
