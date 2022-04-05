package command.impl;

import command.Command;
import copie.Clipboard;
import mvc.Controller;
import mvc.modele.Perspective;

public class Paste implements Command {
    private int side;
    private Clipboard<Perspective> usedClipboard;
    private Perspective oldPerspective;

    public Paste(int side) {
        this.side = side;
    }

    @Override
    public void execute(Controller controller) {
        this.oldPerspective = controller.getModele().getPerspective(side).clone();
        this.usedClipboard = controller.getClipboard();
        usedClipboard.paste(controller.getModele().getPerspective(side));
    }

    @Override
    public void revert(Controller controller) {
        usedClipboard.getStrategieCopie().paste(
                oldPerspective,
                controller.getModele().getPerspective(side));
    }

    @Override
    public int getSide() {
        return side;
    }
}
