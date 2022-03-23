package mvc;

import command.Command;

public interface IController {
	void handleCommand(Command command);
}
