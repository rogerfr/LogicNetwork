import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class LogicNetwork{

	public static int ANDid = 0;
	public static int ORid = 0;
	public static int NOTid = 0;
	public static int sigid = 0;
		
	public static void main(String args[]){
		Scanner scanner = new Scanner( System.in );
			
		ArrayList<SigGen> gateList = new ArrayList<SigGen>();
		ArrayList<Signal> signalList = new ArrayList<Signal>();
		ArrayList<Gate> logicList = new ArrayList<Gate>();
		
		boolean running = true;
		
		Gate gate = null;
		int n = 0;
		
		System.out.println();
		System.out.println("\t\t+-------------------+");
		System.out.println("\t\t|                   |");
		System.out.println("\t\t|   Logic Network   |");
		System.out.println("\t\t|                   |");
		System.out.println("\t\t+-------------------+");
		
		do{
			System.out.println("\nWhat would you like to do?");
			System.out.println("--------------------------");
			System.out.println("1: Add AND gate");
			System.out.println("2: Add OR  gate");
			System.out.println("3: Add NOT gate");
			System.out.println("4: Print truth table");
			System.out.println("5: Clear Network");
			System.out.println("0: Quit program");
			System.out.print("\n> ");
			
			switch (Integer.parseInt(scanner.nextLine())){
			case 1:
				System.out.println("\n How many inputs?");
				System.out.print("\n> ");
				n = Integer.parseInt(scanner.nextLine());
				while (n < 2){
					System.out.println("\nThe AND gate needs at least 2 inputs");
					System.out.print("\n> ");
					n = Integer.parseInt(scanner.nextLine());
				}
				gate = new ANDGate(n);
				gate.setId(ANDid++);
				connect(gateList, gate, n);
				gateList.add(gate);
				System.out.println("\nAdded " + gate.getType() + " " + gate.getId());
				break;
			case 2:
				System.out.println("\nHow many inputs?");
				System.out.print("\n> ");
				n = Integer.parseInt(scanner.nextLine());
				while (n < 2){
					System.out.println("\nThe OR gate needs at least 2 inputs");
					System.out.print("\n> ");
					n = Integer.parseInt(scanner.nextLine());
				}
				gate = new ORGate(n);
				gate.setId(ORid++);
				connect(gateList, gate, n);
				gateList.add(gate);
				System.out.println("\nAdded " + gate.getType() + " " + gate.getId());
				break;
			case 3:
				gate = new NOTGate(1);
				gate.setId(NOTid++);
				connect(gateList, gate, 1);
				gateList.add(gate);
				System.out.println("\nAdded " + gate.getType() + " " + gate.getId());
				break;
			case 4:
				if (gateList.size() == 0){
					System.out.println("\nYou need to add a gate first");
					break;
				}
				
				signalList.clear();
				
				for(int i = 0, j = 0, k = 0; i < gateList.size(); i++){
					if ((((gateList.get(i)).getType()).compareTo("Sig")) == 0){
						signalList.add(j++, (Signal)(gateList.get(i)));
					} else {
						logicList.add(k++, (Gate)(gateList.get(i)));
					}
				}
				System.out.println();
				for(int i = 0; i < signalList.size(); i++){
					System.out.print(signalList.get(i).getType() + " " + (signalList.get(i)).getId() + "\t");
				}
				for(int i = 0; i < logicList.size(); i++){
					System.out.print(logicList.get(i).getType() + " " + logicList.get(i).getId() + "\t");
				}
				System.out.println();
				
				for (int i = 0; i<(int)Math.pow(2.0, (double)(signalList.size())); i++){
					for(int j = 0; j < signalList.size(); j++){
						(signalList.get(j)).setSignal((i & (int)(Math.pow(2.0, (double)(signalList.size())) / Math.pow(2.0, (double)(j + 1)))) != 0);
						System.out.print(signalList.get(j).generate() + "\t");
					}
					for(int j = 0; j < logicList.size(); j++){
						System.out.print(logicList.get(j).generate() + "\t");
					}
					System.out.println();
					//System.out.println(gateList.get(n).generate());
				}
				break;
			case 5:
				gateList.clear();
				signalList.clear();
				ANDid = 0;
				ORid = 0;
				NOTid = 0;
				sigid = 0;
				break;
			default:
				running = false;
				break;
			}
			
			//System.out.println(gateList.toString());
		} while (running);
	}
	
	public static void connect(ArrayList<SigGen> gateList, Gate gate, int n){
		Scanner scanner = new Scanner( System.in );
		
		int maxlvl = gate.getLevel();
		for (int i = 0; i < n; i++){
			System.out.println("\nConnect input " + (i + 1) + " to where?");
			System.out.println("-------------------------");
			System.out.println("0: New signal");
			for (int j = 0; j < gateList.size(); j++){
				System.out.println((j + 1) + ": " + (gateList.get(j)).getType() + " "
				+ (gateList.get(j)).getId() + ", Level: " + (gateList.get(j)).getLevel());
			}
			
			System.out.print("\n> ");
			int in = Integer.parseInt(scanner.nextLine());
			
			while ((in < 0) || (in > gateList.size())){
				System.out.println("\nChoose a value between 0 and " + gateList.size());
				System.out.print("\n> ");
				in = Integer.parseInt(scanner.nextLine());
			}
			
			switch (in){
			case 0:
				SigGen signal = new Signal();
				signal.setId(sigid++);
				gate.connect(signal, i);
				gateList.add(signal);
				System.out.println("\nAdded " + signal.getType() + " " + signal.getId());
				break;
			default:
				in--;
				gate.connect(gateList.get(in), i);
				break;
			}
			
			if (gateList.get(in).getLevel() > maxlvl){
					maxlvl = gateList.get(in).getLevel();
			}
		}
		maxlvl++;
		gate.setLevel(maxlvl);
	}
}