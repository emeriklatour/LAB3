package copie.strategie;

public interface StrategieCopie<T>{

    void paste(T toCopy, T target);
}
