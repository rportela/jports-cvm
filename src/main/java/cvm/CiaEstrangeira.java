package cvm;

import jports.text.CsvColumn;
import jports.text.CsvTable;

@CsvTable(
		separator = "\t",
		charset = "windows-1252",
		firstRowHasNames = true)
public class CiaEstrangeira extends CiaIncentivada {

	@CsvColumn(
			name = "PAIS_ORIGEM")
	public String pais;

}
