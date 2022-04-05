package command.impl;

import command.Command;
import mvc.Controller;

import java.util.ArrayList;

public class Undo implements Command {
    private int side;
    private Command revertedCommand;

    public Undo(int side){
        this.side = side;
    }

    @Override
    public void execute(Controller controller) {
        revertedCommand = getLastCommand(controller);
        revertedCommand.revert(controller);
    }

    @Override
    public void revert(Controller controller) {
        revertedCommand.execute(controller);
    }

    @Override
    public int getSide(int side) {
        return side;
    }

    private Command getLastCommand(Controller controller) {
        ArrayList<Command> commands = new ArrayList<>();
        controller.getExecutedCommands(side).forEachRemaining(
                (command) -> {
                    if (command instanceof Undo) {
                        commands.remove(((Undo) command).revertedCommand);
                    } else {
                        commands.add(command);
                    }
                }
        );
        return commands.get(commands.size() - 1);
    }
}
