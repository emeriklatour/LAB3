package command.impl;

import command.Command;
import copie.Clipboard;
import mvc.Controller;
import mvc.modele.Perspective;

/******************************************************
                        Paste
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Represente la commande "Coller". Permet de coller la perspective du clipboard
 * selon une strategie de copie choisie par l'utilisateur lors de la commande "Copier".
 */
public class Paste implements Command {
    private int side;
    private Clipboard<Perspective> usedClipboard;
    private Perspective oldPerspective;

    /**
     * Constructeur de la classe "Paste"
     * @param side Le cote de la perspective sur laquelle la commande doit etre appliquee
     */
    public Paste(int side) {
        this.side = side;
    }

    /**
     * Utilise la perspective du "clipboard" et colle ses attributs vers la perspective
     * choisie selon la strategie de copie determinee par l'utilisateur.
     * @param controller Controleur de l'application
     */
    @Override
    public void execute(Controller controller) {
        this.oldPerspective = controller.getModele().getPerspective(side).clone();
        this.usedClipboard = controller.getClipboard();
        usedClipboard.paste(controller.getModele().getPerspective(side));
    }

    /**
     * Annule la commande executee. Utilise l'ancienne perspective afin de "recoller" ses attributs et ainsi annuler
     * les changements.
     * @param controller Controleur de l'application
     */
    @Override
    public void revert(Controller controller) {
        usedClipboard.getStrategieCopie().paste(
                oldPerspective,
                controller.getModele().getPerspective(side));
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
