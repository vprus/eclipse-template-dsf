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

import org.eclipse.ui.IStartup;

/* Empty class to force activation of this plugin.
 * 
 * The Activator class contains the code to register DSF model adapters,
 * and this registration should happen when DSF session is started. However,
 * no class in this project is loaded until we actually right click somewhere
 * in the debug model, and it's too later. Startup class forces activation.
 */
public class Startup implements IStartup {

	@Override
	public void earlyStartup() {
	}
}
