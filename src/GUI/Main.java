package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Control.ExcelControl;
import Control.MainControl;
import Constant.Constant;
import Ultil.DragAndDrop;
import Ultil.OpenFileDialog;

public class Main {

	protected Shell shell;
	private static final String[] FILTER_EXTS = { "*.xls", "*.csv" };
	public static String pathFile = "";
	
	private Text txtResultListKeyWords;
	private Text tbPathFile;
	private Label lblResult;
	private Label lblDrapAnhDrop;
	private Button rbExactly;
	private Button rbExpand;
	private Button rbNotExactly;
	private Button btnCost;
	private Button btnClickMe;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		init();
		int typeSelection=1;
		
		btnClickMe.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				OpenFileDialog openFile = new OpenFileDialog();
				String pathfile = openFile.openFileDialog(FILTER_EXTS, shell);
				tbPathFile.setText(pathfile);
				pathFile = pathfile;
			}
		});


		if (rbExactly.getSelection())
			typeSelection = Constant.EXACTLY;
		else if (rbExpand.getSelection())
			typeSelection = Constant.EXPAND;
		else
			typeSelection = Constant.NOTEXPAND;

		btnCost.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MainControl mc = new MainControl();
				if (!pathFile.trim().isEmpty()) {
					if (rbExactly.getSelection())
						mc.eventClickButtonCost(pathFile, new ExcelControl(),
								lblResult, txtResultListKeyWords,
								Constant.EXACTLY);
					else if (rbExpand.getSelection())
						mc.eventClickButtonCost(pathFile, new ExcelControl(),
								lblResult, txtResultListKeyWords,
								Constant.EXPAND);
					else
						mc.eventClickButtonCost(pathFile, new ExcelControl(),
								lblResult, txtResultListKeyWords,
								Constant.NOTEXPAND);
				} else
					System.out.println("Chưa có file");
			}
		});
/*
 * Xử Lý Kéo Thả File
 * 
 * */
		DragAndDrop dad = new DragAndDrop();
		dad.pathFileAfterDragAndDrop(tbPathFile, lblDrapAnhDrop,
				new ExcelControl(), lblResult, txtResultListKeyWords, typeSelection);
	}
/*End--Kéo Thả File*/

	private void init() {
		shell = new Shell();
		shell.setSize(586, 403);
		shell.setText("SMARTLINKS.VN");
		tbPathFile = new Text(shell, SWT.BORDER);
		tbPathFile.setBounds(10, 12, 514, 21);

		btnClickMe = new Button(shell, SWT.NONE);
		btnClickMe.setBounds(526, 10, 34, 25);
		btnClickMe.setText("...");

		lblDrapAnhDrop = new Label(shell, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		lblDrapAnhDrop.setBounds(10, 41, 243, 117);
		lblDrapAnhDrop.setText("Kéo File Thả Vào Đây");

		txtResultListKeyWords = new Text(shell, SWT.BORDER | SWT.V_SCROLL
				| SWT.MULTI);
		txtResultListKeyWords.setBounds(10, 220, 550, 133);
		
		lblResult = new Label(shell, SWT.BORDER | SWT.CENTER);
		lblResult.setFont(SWTResourceManager
				.getFont("Segoe UI", 34, SWT.NORMAL));
		lblResult.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblResult.setBounds(259, 42, 301, 116);
		lblResult.setText("Giá Tiền");
		
		rbExactly = new Button(shell, SWT.RADIO);
		rbExactly.setSelection(true);
		rbExactly.setBounds(46, 183, 90, 16);
		rbExactly.setText("Chính Xác");

		rbExpand = new Button(shell, SWT.RADIO);
		rbExpand.setBounds(142, 183, 90, 16);
		rbExpand.setText("Mở Rộng");

		rbNotExactly = new Button(shell, SWT.RADIO);
		rbNotExactly.setBounds(229, 183, 121, 16);
		rbNotExactly.setText("Không Khóa Chính");
		
		btnCost = new Button(shell, SWT.NONE);
		btnCost.setBounds(431, 164, 106, 50);
		btnCost.setText("Tính Toán");

	}
}
