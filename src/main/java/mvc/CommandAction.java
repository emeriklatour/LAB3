package mvc;

import command.impl.Copy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CommandAction extends AbstractAction {
    private String commandName;
    private int side;

    public CommandAction(String commandName, int side) {
        super(commandName);
        this.commandName = commandName;
        this.side = side;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (commandName){
            case "Copy":
                GestionnaireSauvegarde.getInstance().createCopyMenu(side);
                break;
            case "Paste":
                GestionnaireSauvegarde.getInstance().paste(side);
                break;
            case "Undo":
                break;
            case "Redo":
                break;
        }
    }
}