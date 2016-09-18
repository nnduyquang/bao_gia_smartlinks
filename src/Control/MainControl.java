package Control;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import Interface.ExcelInterface;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MainControl {
	public void eventClickButtonCost(String path, final ExcelInterface command,
			final Label result, final Text textResultKeywords,
			final int type) {
		command.execute(path, result, textResultKeywords, type);
	}
        public void Xu_Ly_Button_Cost(String path,
			final JLabel result, final JTextArea textResultKeywords,
			final int type){
            ExcelControl ec=new ExcelControl();
            ec.executeSwing(path, result, textResultKeywords, type);
        }
}
