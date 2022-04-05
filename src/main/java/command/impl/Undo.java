package command.impl;

import command.Command;
import mvc.Controller;

public class Undo implements Command {
    private int side;
    private Command revertedCommand;

    public Undo(int side){
        this.side = side;
    }

    @Override
    public void execute(Controller controller) {
        this.revertedCommand = controller.getExecutedCommands(side).previous();
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
}
