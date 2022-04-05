package command.impl;

import command.Command;
import mvc.Controller;
import mvc.modele.Perspective;

public class Translate implements Command {
    public int posX;
    public int posY;
    public int side;

    public Translate(int posX, int posY, int side) {
        this.posX = posX;
        this.posY = posY;
        this.side = side;
    }

    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setPosX(currPerspective.getPosX() + posX);
        currPerspective.setPosY(currPerspective.getPosY() + posY);
    }

    @Override
    public void revert(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setPosX(currPerspective.getPosX() - posX);
        currPerspective.setPosY(currPerspective.getPosY() - posY);
    }

    @Override
    public int getSide() {
        return side;
    }
}
