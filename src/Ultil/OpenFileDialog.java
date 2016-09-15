package Ultil;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenFileDialog {
	public String openFileDialog(String[] ext, Shell shell) {
		FileDialog dlg = new FileDialog(shell, SWT.MULTI);
		StringBuffer buf = new StringBuffer();
		dlg.setFilterExtensions(ext);
		String fn = dlg.open();
		if (fn != null) {
			// Append all the selected files. Since getFileNames() returns only
			// the names, and not the path, prepend the path, normalizing
			// if necessary
			
			String[] files = dlg.getFileNames();
			for (int i = 0, n = files.length; i < n; i++) {
				buf.append(dlg.getFilterPath());
				if (buf.charAt(buf.length() - 1) != File.separatorChar) {
					buf.append(File.separatorChar);
				}
				buf.append(files[i]);
				buf.append(" ");
			}
			// fileName.setText(buf.toString());
		}
		//tra ve path file
		return buf.toString();
	}
}
