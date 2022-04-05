package command.impl;

import command.Command;
import mvc.Controller;
import mvc.modele.Perspective;

public class Translate implements Command {
    public int posX;
    public int posY;

    public Translate(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(getSide(0));
        currPerspective.setPosX(posX);
        currPerspective.setPosY(posY);
    }

    @Override
    public void revert(Controller controller) {

    }

    @Override
    public int getSide(int side) {
        return 0;
    }
}
