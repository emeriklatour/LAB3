package command.impl;

import command.Command;
import copie.Clipboard;
import copie.strategie.StrategieCopie;
import mvc.Controller;
import mvc.modele.Perspective;
import mvc.vue.Panneau;

public class Copy extends Clipboard implements Command {
    public Clipboard<Perspective> oldClipboard = new Clipboard<Perspective>();

    public Copy(StrategieCopie<?> strategieCopie){
        oldClipboard.setStrategieCopie((StrategieCopie<Perspective>) strategieCopie);
    }

    public Copy(){

    }

    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(getSide(0));
        Perspective p = new Perspective();
        p.copyFrom(currPerspective);
        oldClipboard.setSavedState(p);
        controller.setClipBoard(oldClipboard);
    }

    @Override
    public void revert(Controller controller) {

    }

    @Override
    public int getSide(int side) {
        return side;
        // return controller.getModele().getNbPerspective() - 1;
    }
}
