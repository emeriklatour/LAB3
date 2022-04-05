package command.impl;

import command.Command;
import mvc.Controller;
import mvc.modele.Perspective;

public class Zoom implements Command {
    private int side;
    private double zoomLevel;

    public Zoom(int side, double zoomLevel) {
        this.side = side;
        this.zoomLevel = zoomLevel;
    }

    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setZoomFactor(currPerspective.getZoomFactor() + zoomLevel);
    }

    @Override
    public void revert(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setZoomFactor(currPerspective.getZoomFactor() - zoomLevel);
    }

    @Override
    public int getSide() {
        return side;
    }
}
