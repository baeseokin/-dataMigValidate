package koreanre.batch.dto;

public class BizTb extends BaseDto{

	 private String c1;
	 private String c2;
	 private String c3;
	 
	 @Override
	 public boolean equals(Object o) {
	     if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     BizTb that = (BizTb) o;
	     return c1.equals(that.c1) &&
	    		 c2.equals(that.c2) &&
	    		 c3.equals(that.c3);
	 }

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	@Override
	public String toString() {
		return "BizTb [c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + "]";
	}
	
}