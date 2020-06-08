package com.kdj.mlink.ezup3;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface ICommandIds {

    public static final String CMD_OPEN = "com.kdj.mlink.ezup3.open";
    public static final String CMD_OPEN_MESSAGE = "com.kdj.mlink.ezup3.openMessage";
    
}