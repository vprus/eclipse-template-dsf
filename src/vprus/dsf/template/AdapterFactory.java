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

import org.eclipse.core.runtime.IAdapterFactory;

public class AdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[]{IReportDsfContextHandler.class};
	}

}
