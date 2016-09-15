package Control;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import Interface.ExcelInterface;

public class MainControl {
	public void eventClickButtonCost(String path, final ExcelInterface command,
			final Label result, final Text textResultKeywords,
			final int type) {
		command.execute(path, result, textResultKeywords, type);
	}
}
