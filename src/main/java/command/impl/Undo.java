package command.impl;

import command.Command;
import mvc.Controller;

public class Undo implements Command {
    public Command revertedCommand;
    public int side;

    public Undo(Command revertedCommand, int side){
        this.revertedCommand = revertedCommand;
        this.side = side;
    }
    @Override
    public void execute(Controller controller) {

    }

    @Override
    public void revert(Controller controller) {

    }

    @Override
    public int getSide(int side) {
        return 0;
    }
}
