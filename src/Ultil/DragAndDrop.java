package Ultil;

import Control.ExcelControl;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import GUI.Main;
import Interface.ExcelInterface;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.io.*;

public class DragAndDrop {

    public void pathFileAfterDragAndDrop(final Text tbPathFile,
            Label lblFramePosition, final ExcelInterface command,
            final Label result, final Text textResultKeywords,
            final int type) {
        DropTarget dt = new DropTarget(lblFramePosition, DND.DROP_COPY
                | DND.DROP_MOVE | DND.DROP_LINK);
        dt.setTransfer(new Transfer[]{FileTransfer.getInstance()});
        dt.addDropListener(new DropTargetAdapter() {
            public void drop(DropTargetEvent event) {
                String fileList[] = null;
                FileTransfer ft = FileTransfer.getInstance();
                if (ft.isSupportedType(event.currentDataType)) {
                    fileList = (String[]) event.data;
                    Main.pathFile = String.valueOf((Object) fileList[0]);
                    tbPathFile.setText(String.valueOf((Object) fileList[0]));
                }
                command.execute((Object) fileList[0], result,
                        textResultKeywords, type);
            }
        });

    }

    public void doActionDragAndDrop(final JTextField tbPathFile, JLabel lblDrapAndDrop, final JLabel result, final JTextArea textResultKeywords, final int typeKeyWord, final JLabel lblThongBao) {
        lblDrapAndDrop.setDropTarget(new java.awt.dnd.DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt
                            .getTransferable().getTransferData(
                                    DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        tbPathFile.setText(file.getAbsolutePath());
                        for (int i = 0; i <= Constant.Constant.FILE_EXT.length; i++) {
                            if (Constant.Constant.FILE_EXT[i].contains(FilenameUtils.getExtension(file.getAbsolutePath()))) {
                                ExcelControl ec = new ExcelControl();
                                ec.executeSwing(file.getAbsolutePath(), result, textResultKeywords, typeKeyWord);
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
