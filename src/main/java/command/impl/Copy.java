package command.impl;

import command.Command;
import copie.Clipboard;
import copie.strategie.StrategieCopie;
import mvc.Controller;
import mvc.modele.Perspective;
import mvc.vue.Panneau;

public class Copy extends Clipboard implements Command {
    public Clipboard<Perspective> oldClipboard = new Clipboard<Perspective>();
    public int side;

    public Copy(StrategieCopie<?> strategieCopie, int side){
        oldClipboard.setStrategieCopie((StrategieCopie<Perspective>) strategieCopie);
        this.side = side;
    }

    public Copy(){

    }

    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
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
    }
}
