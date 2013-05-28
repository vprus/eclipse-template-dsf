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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.cdt.dsf.concurrent.DataRequestMonitor;
import org.eclipse.cdt.dsf.concurrent.Query;
import org.eclipse.cdt.dsf.datamodel.DMContexts;
import org.eclipse.cdt.dsf.datamodel.IDMContext;
import org.eclipse.cdt.dsf.debug.service.IProcesses;
import org.eclipse.cdt.dsf.debug.service.IProcesses.IProcessDMContext;
import org.eclipse.cdt.dsf.debug.service.IProcesses.IThreadDMData;
import org.eclipse.cdt.dsf.gdb.internal.ui.commands.RefreshableDebugCommand;
import org.eclipse.cdt.dsf.service.DsfServicesTracker;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.cdt.dsf.ui.viewmodel.datamodel.IDMVMContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.IRequest;
import org.eclipse.debug.core.commands.IEnabledStateRequest;

/* Actual implementation
 * 
 * This command can be invoked on any element in the debug view, and it tries
 * to locate process element that is parent of the selected element. It then
 * reports pid of that process, and waits for 10 seconds, during which time
 * the command remain disabled when any other children of the same process
 * is selected. 
 */
@SuppressWarnings("restriction")
public class ReportDsfContextHandler extends RefreshableDebugCommand implements IReportDsfContextHandler {
	
	private DsfSession session;
	private Set<Object> active = new HashSet<Object>();
	
	public ReportDsfContextHandler(DsfSession session)
	{
		this.session = session;
	}
		
	@Override
	protected void doExecute(Object[] targets, IProgressMonitor monitor, IRequest request) throws CoreException { 
		
		active.add(targets[0]);
				
		final IProcessDMContext context = (IProcessDMContext)targets[0];
		final DsfServicesTracker tracker = new DsfServicesTracker(Activator.getDefault().getBundle().getBundleContext(), context.getSessionId());
							
		Query<IThreadDMData> query = new Query<IThreadDMData>() {
			@Override
			protected void execute(DataRequestMonitor<IThreadDMData> rm) {
				IProcesses processes = tracker.getService(IProcesses.class);		
				processes.getExecutionData(context, rm);
			}
		};
		
		session.getExecutor().submit(query);
		
		try {
			IThreadDMData d = query.get();
			System.out.println("PID is " + d.getId());			
			Thread.sleep(10*1000);
			System.out.println("Done");
			updateEnablement();
		} catch (Exception e) {
			System.out.println("PID is unknown");
		}
		
		active.remove(targets[0]);
		updateEnablement();
		request.done();
	}

	@Override
	protected boolean isExecutable(Object[] targets, IProgressMonitor monitor, IEnabledStateRequest request) throws CoreException {
		if (targets.length != 1)
			return false;
		// Don't allow to invoke a command on one element until we're done previous processing.
		if (active.contains(targets[0]))
			return false;		
		return true;
	}

	@Override
	protected Object getTarget(Object element) {
		
		if (element instanceof IDMVMContext) {
			IDMContext dmContext = ((IDMVMContext)element).getDMContext();
			IProcessDMContext processContext = DMContexts.getAncestorOfType(dmContext, IProcessDMContext.class);
			return processContext;
		}
		
		return null;
	}
}
