public class NOTGate extends Gate{
	String type = "NOT";
	
	public NOTGate(int n){
		super(n);
	}
	
	public String getType(){
		return this.type;
	}
	
	public boolean generate(){
		return !(inputs[0].generate());
	}
	
	public void printConnections(){
		for (int i = 0; i < inputs.length; i++){
			System.out.println("Connected to " + inputs[i]);
			System.out.println((i + 1) + ": " + inputs[i].getType() + " "
				+ i + ", Level: " + inputs[i].getLevel());
		}
	}
}