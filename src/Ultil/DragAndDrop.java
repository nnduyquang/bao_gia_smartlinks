package Ultil;

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

public class DragAndDrop {
	public void pathFileAfterDragAndDrop(final Text tbPathFile,
			Label lblFramePosition, final ExcelInterface command,
			final Label result, final Text textResultKeywords,
			final int type) {
		DropTarget dt = new DropTarget(lblFramePosition, DND.DROP_COPY
				| DND.DROP_MOVE | DND.DROP_LINK);
		dt.setTransfer(new Transfer[] { FileTransfer.getInstance() });
		dt.addDropListener(new DropTargetAdapter() {
			public void drop(DropTargetEvent event) {
				String fileList[] = null;
				FileTransfer ft = FileTransfer.getInstance();
				if (ft.isSupportedType(event.currentDataType)) {
					fileList = (String[]) event.data;
					Main.pathFile=String.valueOf((Object) fileList[0]);
					tbPathFile.setText(String.valueOf((Object) fileList[0]));
				}
				command.execute((Object) fileList[0], result,
						textResultKeywords, type);
			}
		});

	}
}
