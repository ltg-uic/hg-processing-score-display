package ltg.foraging.model;

public class RFIDTag {

	public String id;
	public String cluster;
	public String color;
	public float score;
	public float rate;
	public String currentPatch;
	public boolean penalty;
	
	
	public RFIDTag(String id, String cluster, String color) {
		this.id = id;
		this.cluster = cluster;
		this.color = color;
		this.score = 0f;
		this.rate = 0f;
		this.currentPatch = null;
		this.penalty = false;
		}
		
		
		public void setRate(float rate) {
			this.rate = rate;
		}
		
		@Override
		public String toString() {
			return cluster + "|" + id;
		}

}
