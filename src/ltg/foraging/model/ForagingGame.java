package ltg.foraging.model;

import java.util.List;
import java.util.Map;

public class ForagingGame {
	public static final float MAX_SCORE = 1200;	// 20c/s * 60 s/min * 5 min
	private static final boolean DEBUG = false;
	
	
	private List<RFIDTag> tags = null;
	private Map<String, FoodPatch> patches = null;
	
	
	public ForagingGame() {
	}
	
	
	public synchronized void resetGame(Map<String, FoodPatch> patches, List<RFIDTag> tags) {
		this.patches = patches;
		this.tags = tags;
	}
	
	
	public synchronized void printScores() {
		if (!isInitialized())
			return;
		System.out.println("===Scores===");
		String tag_ids = "|";
		for (RFIDTag t : tags) {
			tag_ids += (t.id + "\t|");
		}
		System.out.println(tag_ids);
		String scores = "|";
		for (RFIDTag t : tags) {
			scores += (t.score + "      \t|");
		}
		System.out.println(scores);
	}


	public synchronized void updateScores() {
		if (!isInitialized())
			return;
		for (RFIDTag t: tags) {
			if (!t.penalty) {
				if (t.currentPatch==null)
					t.score += t.rate;
				else {
					float headCount = (float) patches.get(t.currentPatch).headCount;
					t.score += (t.rate / headCount);
				}
			}
		}
	}
	
	
	public synchronized void updateCurrentPatch(String tag, String patch) {
		for (RFIDTag t : tags) {
			if (t.id.equals(tag)) {
				t.currentPatch = patch;
				if (patch!=null)
					t.rate = patches.get(patch).feedRatio;
				else
					t.rate = 0;
			}
		}
	}
	
	
	public synchronized void increasePatchCount(String dest) {
		patches.get(dest).headCount++;
	}


	public synchronized void decreasePatchCount(String dest) {
		patches.get(dest).headCount--;
	}

	
	
	public synchronized float getNormalizedTagScore(int tagIndex) {
		if (DEBUG) 
			return (float) Math.random();
		return ((float) tags.get(tagIndex-1).score) / MAX_SCORE;
	}
	
	
	public synchronized String getTagColor(int tagIndex) {
		return "ff"+tags.get(tagIndex-1).color.substring(1);
	}


	public synchronized boolean isInitialized() {
		if (tags!=null && patches!=null)
			return true;
		return false;
	}


	public synchronized void killBunny(String tag, boolean decision) {
		for (RFIDTag t : tags)
			if (t.id.equals(tag))
				t.penalty = decision;
	}

	
}
