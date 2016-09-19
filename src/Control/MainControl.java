package Control;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import Interface.ExcelInterface;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import org.apache.commons.io.FilenameUtils;

public class MainControl {

    public void eventClickButtonCost(String path, final ExcelInterface command,
            final Label result, final Text textResultKeywords,
            final int type) {
        command.execute(path, result, textResultKeywords, type);
    }

    public void Xu_Ly_Button_Cost(String path,
            final JLabel result, final JTextArea textResultKeywords,
            final int type, JLabel lblThongBao) {
        ExcelControl ec = new ExcelControl();
        for (int i = 0; i <= Constant.Constant.FILE_EXT.length; i++) {
            if (Constant.Constant.FILE_EXT[i].contains(FilenameUtils.getExtension(path))) {
                ec.executeSwing(path, result, textResultKeywords, type);
                lblThongBao.setText("...");
                lblThongBao.setForeground(Color.BLACK);
                return;
            } else {
                lblThongBao.setText("File Khong Hop Le Nha !!!");
                lblThongBao.setForeground(Color.RED);
                return;
            }
        }
    }

}
