package command;

import mvc.Controller;

public interface Command {
	void execute(Controller controller);

	void revert(Controller controller);

	int getSide();
}
