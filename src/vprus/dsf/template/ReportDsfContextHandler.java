package vprus.dsf.template;

import org.eclipse.cdt.dsf.concurrent.DataRequestMonitor;
import org.eclipse.cdt.dsf.concurrent.Query;
import org.eclipse.cdt.dsf.datamodel.DMContexts;
import org.eclipse.cdt.dsf.datamodel.IDMContext;
import org.eclipse.cdt.dsf.debug.service.IProcesses;
import org.eclipse.cdt.dsf.debug.service.IProcesses.IProcessDMContext;
import org.eclipse.cdt.dsf.debug.service.IProcesses.IThreadDMData;
import org.eclipse.cdt.dsf.service.DsfServicesTracker;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.cdt.dsf.ui.viewmodel.datamodel.IDMVMContext;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;


@SuppressWarnings("restriction")
public class ReportDsfContextHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		
	}

	@Override
	public void dispose() {
		
	}

	@SuppressWarnings("restriction")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);

		if (window == null) {
			throw new ExecutionException("No active workbench window."); //$NON-NLS-1$
		}

		ISelection selection = DebugUITools.getDebugContextManager().getContextService(window).getActiveContext();
		if (selection instanceof IStructuredSelection && isEnabled()) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object context = ss.getFirstElement();
			if (context instanceof IDMVMContext) {
				IDMContext dmContext = ((IDMVMContext)context).getDMContext();
				final IProcessDMContext processContext = DMContexts.getAncestorOfType(dmContext, IProcessDMContext.class);
				
				final DsfServicesTracker tracker = new DsfServicesTracker(Activator.getDefault().getBundle().getBundleContext(), dmContext.getSessionId());
				
				Query<IThreadDMData> query = new Query<IThreadDMData>() {
					@Override
					protected void execute(DataRequestMonitor<IThreadDMData> rm) {
						final IProcesses processes = tracker.getService(IProcesses.class);
						processes.getExecutionData(processContext, rm);
					}
				};
				
				DsfSession session = DsfSession.getSession(dmContext.getSessionId());
				session.getExecutor().submit(query);
				
				try {
					IThreadDMData d = query.get();
					System.out.println("PID is " + d.getId());
				} catch (Exception e) {
					System.out.println("PID is unknown");
				}
			}
		}

		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}
}
