package mvc;

import command.impl.Copy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CommandAction extends AbstractAction {
    private String commandName;

    public CommandAction(String commandName) {
        super(commandName);
        this.commandName = commandName;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (commandName){
            case "Copy":
                GestionnaireSauvegarde.getInstance().createCopyMenu();
                break;
            case "Paste":
                GestionnaireSauvegarde.getInstance().paste();
                break;
            case "Undo":
                break;
            case "Redo":
                break;
        }
    }
}