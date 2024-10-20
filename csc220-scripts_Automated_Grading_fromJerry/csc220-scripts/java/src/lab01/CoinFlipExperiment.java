package lab01;


public class CoinFlipExperiment {

static public int coinFlipExperiment () {
	int i = 1;
	int winnings = 0;
	while (i <= 100) {
	double flip = Math.random();
	  if (flip < 0.505) {
	    winnings++;
	  }
	  else {
	    winnings--;
	} 
	i++;
	}
	return winnings;
}

	public static void main(String[] args) {
		int amount = coinFlipExperiment ();
		System.out.println ("Winnings = $" + amount + ".00");
	}
}
