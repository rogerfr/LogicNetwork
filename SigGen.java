public interface SigGen{

	public String getType();
	public int getId();
	public int getLevel();
	public void setId(int id);
	public boolean generate();
}