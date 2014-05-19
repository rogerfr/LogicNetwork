public class Signal implements SigGen{
	boolean signal = false;
	int id = 0;
	
	public String getType(){
		return "Sig";
	}
	public int getId(){
		return this.id;
	}
	public int getLevel(){
		return 0;
	}
	
	public void setId(int id){
		this.id = id;
	}	
	
	public void setSignal(boolean signal){
		this.signal = signal;
	}
	
	public boolean generate(){
		return signal;
	}
}