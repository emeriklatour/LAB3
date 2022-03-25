package command;

import mvc.Controller;
import utils.Side;

public interface Command {
	void execute(Controller controller);

	void revert(Controller controller);

	Side getSide();
}
