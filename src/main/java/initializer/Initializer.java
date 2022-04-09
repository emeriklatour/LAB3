package initializer;

import command.Command;
import display.FenetrePrincipale;
import mvc.Controller;
import mvc.GestionnaireSauvegarde;
import mvc.modele.Modele;

import javax.swing.*;
import java.util.ArrayList;

/******************************************************
                    Initializer
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Permet d'initialiser le modele, les vues et le contrôleur. Les méthodes qui ne changent
 * jamais sont dans cette classe et les autres sont déclarées "abstraites" afin de laisser
 * la liberté au développeur de les implémenter à sa façon.
 */
public abstract class Initializer {

    /**
     * La methode start est le point d'entré du projet. Elle coordonne la creation de tous les
     * éléments nécessaires au lancement de l'application (modele, vues et controleur) en appelant
     * les bonnes méthodes.
     */
    public void start(){
        Modele modele = createModele();
        FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
        for(JPanel j : createVues(modele)){
            fenetrePrincipale.addToPanneau(j);
        }
        Controller.getInstance().handleCommand(getInitCommand(modele));
    }

    /**
     *
     * @param m Modele de l'application a enregistrer dans le controleur.
     * @return La commande qui permettera au controleur de s'enregistrer au gestionnaire de sauvegarde
     */
    private Command getInitCommand(Modele m){
        return new Command() {
            @Override
            public void execute(Controller controller) {
                controller.setModele(m);
                GestionnaireSauvegarde.getInstance()
                        .registerController(controller);
            }

            @Override
            public void revert(Controller controller) {}

            @Override
            public int getSide() {
                return -1;
            }
        };
    }

    /**
     * Permet de creer le Modele. Doit etre implemente par le developpeur
     * @return Le modele cree
     */
    public abstract Modele createModele();

    /**
     * Permet de créer chaque vue. Doit être implemente par le developpeur.
     * @param m Le modele de l'application
     * @return Une liste des panneaux créer. Cela permettera de les ajouter a la fenetre principale.
     */
    public abstract ArrayList<JPanel> createVues(Modele m);
}
