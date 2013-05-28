/*******************************************************************************
 * Copyright (c) 2013 Mentor Graphics
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Vladimir Prus (Mentor Graphics) - initial API and implementation
 *******************************************************************************/

package vprus.dsf.template;

import org.eclipse.debug.ui.actions.DebugCommandHandler;

/* Command handler for 'Report DSF Context' command.
 * 
 * The DebugCommandHandler class implements standard eclipse command handler
 * interfaces, and delegates to instances of 'getCommandType()' obtained
 * from DSF session.
 */
public class ReportDsfContextCommandHandler extends DebugCommandHandler {

	@Override
	protected Class<?> getCommandType() {
		return IReportDsfContextHandler.class;
	}
	
	@Override
	protected boolean getInitialEnablement() {
		return false;
	}	
}
	