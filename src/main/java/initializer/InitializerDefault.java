package initializer;

import mvc.modele.Image;
import mvc.modele.Modele;
import mvc.modele.Perspective;
import mvc.vue.Panneau;
import mvc.vue.Vignette;
import javax.swing.*;
import java.util.*;
import java.util.stream.IntStream;

/******************************************************
                    InitializerDefault
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe hérite de la classe template Initializer et est l'initialiseur par
 * défaut pour notre application.
 */
public class InitializerDefault extends Initializer {
    /**
     * Permet de creer le Modele. Dans cet implementation, il n'y a que
     * 2 perspectives, tel que demandé pour le laboratoire.
     * @return Le modele cree
     */
    @Override
    public Modele createModele() {
        return new Modele(
                new Image(),
                new Perspective[]{
                        new Perspective(),
                        new Perspective()
                }
        );
    }

    /**
     * Permet de créer chaque vue. Dans cet implementation, il y a une vignette pour le thumbnail, ainsi
     * que le meme nombre de panneau que le nombre de perspective du modele (2 dans ce cas ci), tel que
     * demandé dans le laboratoire.
     * @param m Le modele de l'application
     * @return Une liste des panneaux créer. Cela permettera de les ajouter a la fenetre principale.
     */
    @Override
    public ArrayList<JPanel> createVues(Modele m) {
        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(new Vignette(m.getImage()));
        IntStream.range(0, m.getNbPerspective())
                .forEach(s ->
                    panels.add(
                            new Panneau(
                                    s,
                                    m.getImage(),
                                    m.getPerspective(s)
                            )
                    )
                );
        return panels;
    }
}
