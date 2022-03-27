package Initializer;

import command.Command;
import display.FenetrePrincipale;
import lombok.NoArgsConstructor;
import mvc.Controller;
import mvc.GestionnaireSauvegarde;
import mvc.modele.Modele;
import mvc.vue.Vignette;
import utils.Side;

import javax.swing.*;
import java.util.ArrayList;

@NoArgsConstructor
public abstract class ViewInitializer {

    public void start(){
        Modele modele = createModele();
        FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
        for(JPanel j : createVues(modele)){
            fenetrePrincipale.addToPanneau(j);
        }
        Controller.getInstance().handleCommand(getInitCommand(modele));
    }

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
            public Side getSide() {
                return null;
            }
        };
    }

    public abstract Modele createModele();

    public abstract ArrayList<JPanel> createVues(Modele m);
}
