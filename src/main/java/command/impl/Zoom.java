package command.impl;

import command.Command;
import mvc.Controller;
import mvc.modele.Perspective;

/******************************************************
                        Zoom
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Permet de "zoom" ou "dezoom" une perspective
 */
public class Zoom implements Command {
    private int side;
    private double zoomLevel;

    /**
     * Constructeur de la classe zoom
     * @param side L'index du panneau ou le zoom doit etre applique sur la perspective
     * @param zoomLevel L'ampleur du zoom
     */
    public Zoom(int side, double zoomLevel) {
        this.side = side;
        this.zoomLevel = zoomLevel;
    }

    /**
     * Saisie de la bonne perspective et application du zoom
     * @param controller Controleur de l'application
     */
    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setZoomFactor(currPerspective.getZoomFactor() + zoomLevel);
    }

    /**
     * Saisie de la bonne perspective et annulation du zoom effectue par la methode execute()
     * @param controller Controleur de l'application
     */
    @Override
    public void revert(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setZoomFactor(currPerspective.getZoomFactor() - zoomLevel);
    }

    /**
     * Retourne l'index d'un des panneaux. Sert a determiner sur quel panneau
     * la commande a ete effectuee.
     * @return l'index du panneau sur lequel la commande a ete effectuee.
     */
    @Override
    public int getSide() {
        return side;
    }
}
