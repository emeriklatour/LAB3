package command.impl;

import command.Command;
import mvc.Controller;
import mvc.modele.Perspective;

/******************************************************
                    Translate
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Commande nous permettant de bouger une perspective d'un endroit a un autre dans le panneau
 */
public class Translate implements Command {
    private int posX;
    private int posY;
    private int side;

    /**
     * Constructeur de la classe Tranlate
     * @param posX La position de la perspective sur l'axe X
     * @param posY La position de la perspective sur l'axe Y
     * @param side l'index du panneau sur lequel la commande est executee
     */
    public Translate(int posX, int posY, int side) {
        this.posX = posX;
        this.posY = posY;
        this.side = side;
    }

    /**
     * Saisie de la perspective selon le panneau ou la translation doit etre appliquee
     * application de la difference de deplacement sur les 2 axes.
     * @param controller Controleur de l'application
     */
    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setPosX(currPerspective.getPosX() + posX);
        currPerspective.setPosY(currPerspective.getPosY() + posY);
    }

    /**
     * Annulation de la difference de deplacement appliquee par la methode
     * "execute()" sur les 2 axes
     * @param controller Controleur de l'application
     */
    @Override
    public void revert(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        currPerspective.setPosX(currPerspective.getPosX() - posX);
        currPerspective.setPosY(currPerspective.getPosY() - posY);
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
