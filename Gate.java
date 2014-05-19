import java.util.Scanner;
import java.util.ArrayList;

public abstract class Gate implements SigGen{
	String type = "Undefined";
	int  id = 0;
	protected SigGen inputs[];
	private int level = 0;
	
	Scanner scanner = new Scanner( System.in );
	
	public Gate(int n){
		inputs = new SigGen[n];
		for (int i = 0; i > n; i++){
			inputs[i] = null;
		}
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void connect(SigGen sigGen, int n){
		this.inputs[n] = sigGen;
	}
	
	public boolean generate(){
		return inputs[0].generate();
	}
}