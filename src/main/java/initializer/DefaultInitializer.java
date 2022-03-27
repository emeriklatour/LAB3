package initializer;

import lombok.NoArgsConstructor;
import mvc.modele.Image;
import mvc.modele.Modele;
import mvc.modele.Perspective;
import mvc.vue.Panneau;
import mvc.vue.Vignette;
import utils.Side;
import javax.swing.*;
import java.util.*;

@NoArgsConstructor
public class DefaultInitializer extends Initializer {
    @Override
    public Modele createModele() {
        Map<Side, Perspective> perspectives = new HashMap<>();
        Arrays.stream(Side.values()).forEach(
                (s) -> perspectives.put(s, new Perspective())
        );
        return new Modele(new Image(), perspectives);
    }

    @Override
    public ArrayList<JPanel> createVues(Modele m) {
        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(new Vignette(m.getImage()));
        Arrays.stream(Side.values()).forEach(
                (s) -> panels.add(
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
