package org.geepawhill.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.geepawhill.model.EntryPoint;
import org.geepawhill.model.EntryPointSet;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private Display display;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("org.geepawhill.demo: start");
		Activator.context = bundleContext;
		
		EntryPointSet entrypoints = getEntryPoints();
		if(entrypoints==null) return;
		System.out.println("org.geepawhill.demo: Got entrypoints.");
		display = new Display();

		Shell shell = new Shell(display);
		


		// the layout manager handle the layout
		// of the widgets in the container
		shell.setLayout(new FillLayout());

		List list = new List(shell,SWT.SINGLE);
		for(EntryPoint entry : entrypoints)
		{
			list.add(entry.name());
		}
		list.pack();
		// TODO add some widgets to the Shell
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

//		context.getBundle(0).stop();

	}

	private EntryPointSet getEntryPoints() throws Exception {
		ServiceReference<?> reference = context.getServiceReference(org.geepawhill.model.EntryPointSet.class.getName());
		if(reference==null)
		{
			System.out.println("Can't find any entry point sets.");
			return null;
		}
		return (EntryPointSet)context.getService(reference);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("org.geepawhill.demo: stop");
		Activator.context = null;
		display.dispose();
	}

}
