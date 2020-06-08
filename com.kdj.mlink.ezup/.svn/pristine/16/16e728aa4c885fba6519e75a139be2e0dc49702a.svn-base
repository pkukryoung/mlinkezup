package com.kdj.mlink.ezup.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class ExitHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		HandlerUtil.getActiveWorkbenchWindow(event).close();
//		return null;
		
		boolean exitFlag = MessageDialog.openQuestion(null, "시스템종료",  "프로그램을 종료하시겠습니까?");
		if(exitFlag) {
			HandlerUtil.getActiveWorkbenchWindow(event).close();	
		}
		
		return null;
	}

}
