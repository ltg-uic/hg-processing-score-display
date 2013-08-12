package ltg.foraging.model;

public class FoodPatch {
	
	public String jid;
	public float feedRatio;
	public int headCount;
	
	public FoodPatch(String jid, int feedRatio) {
		super();
		this.jid = jid;
		this.feedRatio = feedRatio;
		this.headCount = 0;
	}

}
