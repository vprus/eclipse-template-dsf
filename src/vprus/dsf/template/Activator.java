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

import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.cdt.dsf.service.DsfSession.SessionStartedListener;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class Activator extends AbstractUIPlugin {

	private static Activator instance;
	
	public static Activator getDefault() {
		return instance;
	}
	
	public Activator() {
		instance = this;
		// DSF normally registers model adapters in GdbAdapterFactory.SessionAdapterSet and does
		// not provide any extension points. So, we need to do this on session creation.
		DsfSession.addSessionStartedListener(new SessionStartedListener() {
			@Override
			public void sessionStarted(DsfSession session) {
				session.registerModelAdapter(IReportDsfContextHandler.class, new ReportDsfContextHandler(session));
			}
		});
	}	
}
