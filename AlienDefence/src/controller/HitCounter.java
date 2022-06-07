package controller;

public class HitCounter {

	private int shots, hit, targets;
	private long sumReactionDiffernce;

	/**
	 * erstellt einen neuen Hitcounter
	 * 
	 * @param target
	 *            Anzahl der zu z�hlenden Ziele, �bergeben Sie die maximale Anzahl
	 *            der Ziele des Levels
	 */
	public HitCounter(int target) {
		this.reset(target);
	}

	/**
	 * gibt die Anzahl der abgegebenen Sch�sse zur�ck
	 * @return Anzahl der abgegebenen Sch�sse 
	 */
	public int getShots() {
		return shots;
	}

	/**
	 * gibt die Anzahl der Treffer zur�ck
	 * @return Anzahl der Treffer
	 */
	public int getHit() {
		return hit;
	}

	/**
	 * gibt die Anzahl der noch nicht getroffenen Targets zur�ck
	 * @return Anzahl der noch nicht getroffenen Targets
	 */
	public int getTargets() {
		return targets;
	}

	/**
	 * setzt den Wert der Target auf dem im Parameter �bergebenen Wert
	 * @param targets neue Targetanzahl nicht getroffener Ziele
	 */
	public void setTargets(int targets) {
		this.targets = targets;
	}

	public long getReactionTime() {
		if(this.getHit() == 0)
			return 0;
		else
			return this.sumReactionDiffernce / this.getHit();
	}

	public long getSumReactionDiffernce() {
		return sumReactionDiffernce;
	}
	
	public void addReactionTime(long time) {
		this.sumReactionDiffernce += time;
	}

	/**
	 * registriert einen Treffer
	 */
	public void hit() {
		this.hit++;
		this.shots++;
	}

	/**
	 * registriert einen Fehlschuss
	 */
	public void miss() {
		this.shots++;
	}

	/**
	 * berechnet die Treffergenauigkeit
	 * @return Wert zwischen 0-100% der Treffergenauigkeit auf 2 Nachkommastellen gerundet
	 */
	public double getAccuracy() {
		return Math.round((100.0 / this.shots * this.hit) * 100.0) / 100.0;
	}

	/**
	 * setzt den HitCounter zur�ck 
	 * @param target
	 */
	public void reset(int target) {
		this.targets = target;
		this.shots = 0;
		this.hit = 0;
		this.sumReactionDiffernce = 0L;
	}
}
