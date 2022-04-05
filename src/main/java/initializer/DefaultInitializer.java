package initializer;

import mvc.modele.Image;
import mvc.modele.Modele;
import mvc.modele.Perspective;
import mvc.vue.Panneau;
import mvc.vue.Vignette;

import javax.swing.*;
import java.util.*;
import java.util.stream.IntStream;

public class DefaultInitializer extends Initializer {
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

    @Override
    public ArrayList<JPanel> createVues(Modele m) {
        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(new Vignette(m.getImage()));
        IntStream.range(0, m.getNbPerspective())
                .forEach(s -> {
                    panels.add(
                            new Panneau(
                                    s,
                                    m.getImage(),
                                    m.getPerspective(s)
                            )
                    );
                    Panneau currPanneau = (Panneau) panels.get(s + 1);
                    currPanneau.setCurrPanel(panels.get(s + 1));
                    panels.set(s + 1, currPanneau);
                });
        return panels;
    }
}
