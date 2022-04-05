package command.impl;

import command.Command;
import copie.Clipboard;
import copie.strategie.StrategieCopie;
import mvc.Controller;
import mvc.modele.Perspective;

public class Copy implements Command {
    private int side;
    private StrategieCopie<Perspective> strategieCopie;
    private Clipboard<Perspective> oldClipboard;
    private Clipboard<Perspective> newClipboard;

    public Copy(int side, StrategieCopie<Perspective> strategieCopie){
        this.side = side;
        this.strategieCopie = strategieCopie;
    }

    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        this.oldClipboard = controller.getClipboard();
        this.newClipboard = new Clipboard<>(currPerspective, strategieCopie);
        controller.setClipBoard(newClipboard);
    }

    @Override
    public void revert(Controller controller) {
        controller.setClipBoard(oldClipboard);
    }

    @Override
    public int getSide(int side) {
        return side;
    }
}
