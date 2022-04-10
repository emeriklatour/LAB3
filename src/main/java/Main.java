import initializer.InitializerDefault;
import initializer.Initializer;

/******************************************************
						Main
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Point d'entre de l'application.
 */
public class Main {
	public static void main(String[] args) {
		Initializer initializer = new InitializerDefault();
		initializer.start();
	}
}
