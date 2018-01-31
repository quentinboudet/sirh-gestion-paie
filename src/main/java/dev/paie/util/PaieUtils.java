package dev.paie.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PaieUtils {
	/**
	 * Formate un nombre sous la forme xx.xx (exemple : 2.00, 1.90). L'arrondi se
	 * fait en mode "UP" => 1.904 devient 1.91
	 *
	 * @param decimal
	 *            nombre à formater
	 * @return le nombre formaté
	 */
	public String formaterBigDecimal(BigDecimal decimal) {
		DecimalFormat df = new DecimalFormat();
		// forcer le séparateur "." même sur un poste en français
		df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.UK));
		df.setMaximumFractionDigits(2);
		df.setRoundingMode(RoundingMode.UP);
		df.setMinimumFractionDigits(2);
		df.setGroupingUsed(false);
		return df.format(decimal);
	}
}
