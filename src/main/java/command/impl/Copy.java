package command.impl;

import command.Command;
import copie.Clipboard;
import copie.strategie.StrategieCopie;
import mvc.Controller;
import mvc.modele.Perspective;

/******************************************************
                        Copy
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Represente la commande "Copier". Copie les élements d'une perspective selon
 * une strategie donnee.
 */
public class Copy implements Command {
    private int side;
    private StrategieCopie<Perspective> strategieCopie;
    private Clipboard<Perspective> oldClipboard;
    private Clipboard<Perspective> newClipboard;

    /**
     * Constructeur de la classe "Copy"
     * @param side l'index du panneau ou il faut copier la perspective
     * @param strategieCopie la strategie de copie a appliquer
     */
    public Copy(int side, StrategieCopie<Perspective> strategieCopie){
        this.side = side;
        this.strategieCopie = strategieCopie;
    }

    /**
     * Change le "clipboard" courant par un nouveau "clipboard" avec la perspective a copier
     * et la strategie de copie a lui appliquer.
     * @param controller Controleur de l'application
     */
    @Override
    public void execute(Controller controller) {
        Perspective currPerspective = controller.getModele().getPerspective(side);
        this.oldClipboard = controller.getClipboard();
        this.newClipboard = new Clipboard<>(currPerspective.clone(), strategieCopie);
        controller.setClipBoard(newClipboard);
    }

    /**
     * Annule la commande "execute". Rechange le nouveau "clipboard" par l'ancien.
     * @param controller Controleur de l'application
     */
    @Override
    public void revert(Controller controller) {
        controller.setClipBoard(oldClipboard);
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
