package MyPackage;

public class PaiR {
    private int id1;
    private int id2;
    private long timeSpent;
    public PaiR(int id, int Id, long ts){
        id1 = id;
        id2 = Id;
        timeSpent = ts;
    }
    public boolean comp(int a, int b){
        if(a==id1 && b==id2){
            return true;
        }
        else{
            return false;
        }
    }
    public void plus(long a){
        timeSpent += a;
    }
	public int getId1() {
		return id1;
	}
	public void setId1(int id1) {
		this.id1 = id1;
	}
	public int getId2() {
		return id2;
	}
	public void setId2(int id2) {
		this.id2 = id2;
	}
	public long getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}
}
