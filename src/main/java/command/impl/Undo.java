package command.impl;

import command.Command;
import mvc.Controller;

import java.util.ArrayList;

/******************************************************
                        Undo
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Permet de "defaire" une commande
 */
public class Undo implements Command {
    private int side;
    private Command revertedCommand;

    /**
     * Constructeur de la classe Undo
     * @param side l'index du panneau sur lequel la commande doit etre refaite
     */
    public Undo(int side){
        this.side = side;
    }

    /**
     * Saisi la derniere commande executee et appelle simplement sa methode
     * "revert" afin de defaire la commande
     * @param controller Controleur de l'application
     */
    @Override
    public void execute(Controller controller) {
        revertedCommand = getLastCommand(controller);
        revertedCommand.revert(controller);
    }

    /**
     * Annule la commande executee. Utilise l'ancienne commande et l'execute
     * @param controller Controleur de l'application
     */
    @Override
    public void revert(Controller controller) {
        revertedCommand.execute(controller);
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

    /**
     *
     * @param controller Controleur de l'application
     * @return La derniere commande executee
     */
    private Command getLastCommand(Controller controller) {
        ArrayList<Command> commands = new ArrayList<>();
        controller.getExecutedCommands(side).forEachRemaining(
                (command) -> {
                    if (command instanceof Undo) {
                        commands.remove(((Undo) command).revertedCommand);
                    } else {
                        commands.add(command);
                    }
                }
        );
        return commands.get(commands.size() - 1);
    }
}
