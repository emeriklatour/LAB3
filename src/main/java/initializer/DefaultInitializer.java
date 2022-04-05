package initializer;

import command.impl.Copy;
import copie.strategie.StrategieCopie;
import copie.strategie.impl.CopyBoth;
import copie.strategie.impl.CopyNone;
import copie.strategie.impl.CopyTranslate;
import copie.strategie.impl.CopyZoom;
import lombok.NoArgsConstructor;
import mvc.CommandAction;
import mvc.Controller;
import mvc.GestionnaireSauvegarde;
import mvc.modele.Image;
import mvc.modele.Modele;
import mvc.modele.Perspective;
import mvc.vue.Panneau;
import mvc.vue.Vignette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.stream.IntStream;

@NoArgsConstructor
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
                            /*panels.get(s + 1).addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                                        createCommandMenu().show(panels.get(s + 1), e.getX(), e.getY());
                                    }
                                }
                            });
                        }*/
                });
        return panels;
    }
}
