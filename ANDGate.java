public class ANDGate extends Gate{
	String type = "AND";
	
	public ANDGate(int n){
		super(n);
	}
	
	public String getType(){
		return this.type;
	}
	
	public boolean generate(){
		boolean result = inputs[0].generate();
		for (int i = 1; i < inputs.length; i++){
			result = result && inputs[i].generate();
		}
		return result;
		
		//return ((inputs[0].generate()) && (inputs[1].generate()));
	}
	
	public void printConnections(){
		for (int i = 0; i < inputs.length; i++){
			System.out.println("Connected to " + inputs[i]);
			System.out.println((i + 1) + ": " + inputs[i].getType() + " "
				+ i + ", Level: " + inputs[i].getLevel());
		}
	}
}