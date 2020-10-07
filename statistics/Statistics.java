package statistics;

import java.util.Arrays;

public class Statistics {
	
	/*int[0] -> wins; int[1] -> losses; int[2] -> pushes*/
	public int[] stats1 = new int[3];
	/*int[0] -> BJ Player; int[1] -> BJ Dealer*/
	public int[] stats2 = new int[2];
	/*int[0] -> initial chips; int[1] -> c chips*/
	public float[] stats3 = new float[2];
	
	public Statistics(float chips){
		Arrays.fill(stats1, 0);
		Arrays.fill(stats2, 0);
		Arrays.fill(stats3, 0);
		
		stats3[0] = chips;
	}
	
	public String N1N2(){
		
		int total = getTotal();
		
		return "BJ P/D     " + (float) stats2[0]/total + "/" + (float) stats2[1]/total;
		
	}
	
	
	public String N3(){
		
		int total = getTotal();
		
		return "Win        " + (float) stats1[0]/total;
		
	}

	public String N4(){
		
		int total = getTotal();
		
		return "Lose       " + (float) stats1[1]/total;
	
	}
	
	public String N5(){
		
		int total = getTotal();
		
		return "Push       " + (float) stats1[2]/total;
	
	}
	
	public String N6N7(){
		
		return "Balance    " + stats3[1]+"("+(((float) (stats3[1]-stats3[0])/stats3[0])*100) +" %)";
	}
	
	public int getTotal(){
		
		int total = 0;
		for(int i = 0; i < 3; i++)
			total += this.stats1[i];
		return total;
	}
}
