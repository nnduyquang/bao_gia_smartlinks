package Interface;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public interface ExcelInterface {
	public void execute(Object data,final Label result,Text textResultKeywords,int type);
        public void executeSwing(Object data,final JLabel result,JTextArea textResultKeywords,int type);
}
