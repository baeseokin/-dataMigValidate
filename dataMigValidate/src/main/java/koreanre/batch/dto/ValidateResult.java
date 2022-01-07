package koreanre.batch.dto;

public class ValidateResult extends BaseDto {

	private String valDate;
	private String schemaNm;
	private String tableNm;
	private String status;
	private String srcValue;
	private String tgtValue;

	public String getValDate() {
		return valDate;
	}

	public void setValDate(String valDate) {
		this.valDate = valDate;
	}
	
	public String getSchemaNm() {
		return schemaNm;
	}

	public void setSchemaNm(String schemaNm) {
		this.schemaNm = schemaNm;
	}

	public String getTableNm() {
		return tableNm;
	}

	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSrcValue() {
		return srcValue;
	}

	public void setSrcValue(String srcValue) {
		this.srcValue = srcValue;
	}

	public String getTgtValue() {
		return tgtValue;
	}

	public void setTgtValue(String tgtValue) {
		this.tgtValue = tgtValue;
	}

	@Override
	public String toString() {
		return "ValidateResult [valDate=" + valDate + ", schemaNm=" + schemaNm + ", tableNm=" + tableNm + ", status="
				+ status + ", srcValue=" + srcValue + ", tgtValue=" + tgtValue + "]";
	}


}