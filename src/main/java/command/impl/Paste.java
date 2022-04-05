package command.impl;

import command.Command;
import copie.Clipboard;
import mvc.Controller;
import mvc.modele.Perspective;

public class Paste extends Clipboard implements Command {
    public Perspective currPerspective;
    public Clipboard<Perspective> usedClipboard;

    public Paste(Perspective currPerspective, Clipboard<Perspective> usedClipboard) {
        this.currPerspective = currPerspective;
        this.usedClipboard = usedClipboard;
    }

    @Override
    public void execute(Controller controller) {
       // currPerspective = controller.getModele().getPerspective(getSide(0));
        Perspective perspectiveToCopy = new Perspective();
        //usedClipboard = controller.getClipboard();
        perspectiveToCopy = usedClipboard.getSavedState();
        usedClipboard.getStrategieCopie().paste(perspectiveToCopy, currPerspective);
    }

    @Override
    public void revert(Controller controller) {

    }

    @Override
    public int getSide(int side) {
        return 0;
    }
}
