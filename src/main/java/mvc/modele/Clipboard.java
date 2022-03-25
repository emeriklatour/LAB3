package mvc.modele;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.strategie.StrategieCopie;

@NoArgsConstructor
@Getter
@Setter
public class Clipboard<T>{
    T savedState;
    StrategieCopie<T> strategieCopie;
}
