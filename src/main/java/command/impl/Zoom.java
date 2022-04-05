package command.impl;

import command.Command;
import mvc.Controller;
import mvc.modele.Perspective;

public class Zoom implements Command {
    public int zoomPointX;
    public int zoomPointY;
    public double zoomLevel;
    public int side;

    public Zoom(int zoomPointX, int zoomPointY, double zoomLevel, int side) {
        this.zoomPointX = zoomPointX;
        this.zoomPointY = zoomPointY;
        this.zoomLevel = zoomLevel;
        this.side = side;
    }

    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setPosX(zoomPointX);
        currPerspective.setPosY(zoomPointY);
        currPerspective.setZoomFactor(zoomLevel);
    }

    @Override
    public void revert(Controller controller) {

    }

    @Override
    public int getSide(int side) {
        return 0;
    }
}
