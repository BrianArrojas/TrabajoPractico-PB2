package ar.edu.unlam.dominio;

public enum DescuentoFechaVencimiento {

	TRES_DIAS(3, 0.50), SIETE_DIAS(7, 0.30), CATORCE_DIAS(14, 0.15), NINGUNO(0, 0.0);

	private Integer diasMax;
	private Double porcentaje;

	DescuentoFechaVencimiento(Integer diasMax, Double porcentaje) {
		this.diasMax = diasMax;
		this.porcentaje = porcentaje;
	}

	public Integer getDiasMax() {
		return diasMax;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public static DescuentoFechaVencimiento obtenerDescuentoCorrespondiente(Integer dias) {

		if (dias <= TRES_DIAS.getDiasMax()) {
			return TRES_DIAS;
		} else if (dias > TRES_DIAS.getDiasMax() && dias <= SIETE_DIAS.getDiasMax()) {
			return SIETE_DIAS;
		} else if (dias > SIETE_DIAS.getDiasMax() && dias <= CATORCE_DIAS.getDiasMax()) {
			return CATORCE_DIAS;
		} else {
			return NINGUNO;
		}
	}

}
