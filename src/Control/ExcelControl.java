package Control;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import Constant.Constant;
import Interface.ExcelInterface;
import Model.Keyword;
import Ultil.ReadWriteExcel;
import Ultil.ValueFormat;

public class ExcelControl implements ExcelInterface {

	public void execute(Object data, Label lblResult, Text textResultKeywords,
			int type) {
		textResultKeywords.setText("");
		System.out.println(data.toString());
		List<String> cells = null;
		List<Keyword> lstKeyWord = new ArrayList<Keyword>();
		ReadWriteExcel rwExcel = new ReadWriteExcel();
		List<List<String>> lstValueExcel = null;
		lstValueExcel = rwExcel.readExcelCSV(data.toString());
		int count = 1;
		int total = 0;
		for (int i = 0; i < lstValueExcel.size(); i++) {
			cells = new ArrayList<String>();
			cells = lstValueExcel.get(i);
			Keyword kw = new Keyword();
			Boolean check = false;
			if (cells.get(0).contains("Seed")) {
				kw.setTypeKey(1);
				check = true;
			} else {
				for (int j = 0; j < lstKeyWord.size(); j++) {
					String pattern = "\\b" + lstKeyWord.get(j).getNameKey()
							+ "\\b";
					Pattern p = Pattern.compile(pattern);
					if (lstKeyWord.get(j).getTypeKey() == 1) {
						Matcher m = p.matcher(cells.get(1));
						if (m.find()) {
							check = true;
							break;
						} else
							check = false;
					}
				}
				kw.setTypeKey(0);
			}
			if (check) {
				kw.setNameKey(cells.get(1));
				kw.setUnit(cells.get(2));
				if (!cells.get(3).isEmpty())
					kw.setAvgMonthlySearches(Integer.parseInt(cells.get(3)));
				else
					kw.setAvgMonthlySearches(0);
				if (!cells.get(4).isEmpty())
					kw.setTotalSearchMonth1(Integer.parseInt(cells.get(4)));
				else
					kw.setTotalSearchMonth1(0);
				if (!cells.get(5).isEmpty())
					kw.setTotalSearchMonth2(Integer.parseInt(cells.get(5)));
				else
					kw.setTotalSearchMonth2(0);
				if (!cells.get(6).isEmpty())
					kw.setTotalSearchMonth3(Integer.parseInt(cells.get(6)));
				else
					kw.setTotalSearchMonth3(0);
				if (!cells.get(7).isEmpty())
					kw.setTotalSearchMonth4(Integer.parseInt(cells.get(7)));
				else
					kw.setTotalSearchMonth4(0);
				if (!cells.get(8).isEmpty())
					kw.setTotalSearchMonth5(Integer.parseInt(cells.get(8)));
				else
					kw.setTotalSearchMonth5(0);
				if (!cells.get(9).isEmpty())
					kw.setTotalSearchMonth6(Integer.parseInt(cells.get(9)));
				else
					kw.setTotalSearchMonth6(0);
				if (!cells.get(10).isEmpty())
					kw.setTotalSearchMonth7(Integer.parseInt(cells.get(10)));
				else
					kw.setTotalSearchMonth7(0);
				if (!cells.get(11).isEmpty())
					kw.setTotalSearchMonth8(Integer.parseInt(cells.get(11)));
				else
					kw.setTotalSearchMonth8(0);
				if (!cells.get(12).isEmpty())
					kw.setTotalSearchMonth9(Integer.parseInt(cells.get(12)));
				else
					kw.setTotalSearchMonth9(0);
				if (!cells.get(13).isEmpty())
					kw.setTotalSearchMonth10(Integer.parseInt(cells.get(13)));
				else
					kw.setTotalSearchMonth10(0);
				if (!cells.get(14).isEmpty())
					kw.setTotalSearchMonth11(Integer.parseInt(cells.get(14)));
				else
					kw.setTotalSearchMonth11(0);
				if (!cells.get(15).isEmpty())
					kw.setTotalSearchMonth12(Integer.parseInt(cells.get(15)));
				else
					kw.setTotalSearchMonth12(0);
				if (!cells.get(16).isEmpty())
                                    if(cells.get(16).contains(",")){
                                        cells.get(16).toString().replaceAll(",", ".");
					kw.setRatioCompetition(Float.parseFloat(cells.get(16)));
                                    }
				else
					kw.setRatioCompetition(0);
                                
				if (!cells.get(17).isEmpty())
                                    
                                        kw.setSuggestedBid(Integer.parseInt(cells.get(17)));
                                    
				else
					kw.setSuggestedBid(0);
				Double t = (kw.getTotalSearchMonth8()
						+ kw.getTotalSearchMonth9()
						+ kw.getTotalSearchMonth10()
						+ kw.getTotalSearchMonth11() + kw
						.getTotalSearchMonth12()) / 150d;
				int avg = (int) Math.floor(t + 0.5f);
				kw.setAvgDaySearch(avg);
				if (i == 0) {
					kw.setRatio(kw.getAvgDaySearch() / 15);
				} else {
					if (kw.getAvgDaySearch() > 199) {
						Double d = kw.getAvgDaySearch() * 0.3;
						kw.setRatio(d.intValue());
					} else if (kw.getAvgDaySearch() > 149
							&& kw.getAvgDaySearch() <= 199) {
						kw.setRatio(5);
					} else if (kw.getAvgDaySearch() > 99
							&& kw.getAvgDaySearch() <= 149) {
						kw.setRatio(4);
					} else if (kw.getAvgDaySearch() > 49
							&& kw.getAvgDaySearch() <= 99) {
						kw.setRatio(3);
					} else if (kw.getAvgDaySearch() > 19
							&& kw.getAvgDaySearch() <= 49) {
						kw.setRatio(2);
					} else if (kw.getAvgDaySearch() > 9
							&& kw.getAvgDaySearch() <= 19) {
						kw.setRatio(1);
					} else
						kw.setRatio(0);
				}
				Double d = 0d;
				if (i == 0) {
					d = kw.getSuggestedBid() * t / 15
							* kw.getRatioCompetition() * 1.5d;
				} else
					d = kw.getSuggestedBid() * kw.getRatio()
							* kw.getRatioCompetition() * 1.5d;
				kw.setCost((int) Math.floor(d + 0.5f));
				if (type == Constant.EXACTLY) {
					if (kw.getTypeKey() == 1) {
						lstKeyWord.add(kw);
						textResultKeywords.append("--" + count
								+ ".[Từ Khóa Chính] " + kw.getNameKey());
						textResultKeywords.append("\n");
					}
					count++;
				} else if (type == Constant.EXPAND) {
					lstKeyWord.add(kw);
					if (kw.getTypeKey() == 1)
						textResultKeywords.append("--" + count
								+ ".[Từ Khóa Chính] " + kw.getNameKey());
					else
						textResultKeywords.append("--" + count
								+ ".[Từ Khóa Mở Rộng] " + kw.getNameKey());
					textResultKeywords.append("\n");
					count++;
				} else if (type == Constant.NOTEXPAND) {
					if (kw.getTypeKey() == 0) {
						lstKeyWord.add(kw);
						textResultKeywords.append("--" + count
								+ ".[Từ Khóa Mở Rộng] " + kw.getNameKey());
						textResultKeywords.append("\n");
						count++;
					} else
						lstKeyWord.add(kw);
				}

			}
		}
		if (type == Constant.NOTEXPAND) {
			for (int i = 0; i < lstKeyWord.size(); i++) {
				if (lstKeyWord.get(i).getTypeKey() == 0)
					total += lstKeyWord.get(i).getCost();
			}
		} else {
			for (int i = 0; i < lstKeyWord.size(); i++) {
				total += lstKeyWord.get(i).getCost();
			}
		}
		int settings = ValueFormat.COMMAS;
		lblResult
				.setText(ValueFormat.format(Long.valueOf(total * 30), settings));
	}

}
