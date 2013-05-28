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

import org.eclipse.debug.core.commands.IDebugCommandHandler;

/* Interface for the 'Report DSF Context' command. Does not do anything,
 * only used to look up the actual implementation at runtime.
 */
public interface IReportDsfContextHandler extends IDebugCommandHandler {

}
