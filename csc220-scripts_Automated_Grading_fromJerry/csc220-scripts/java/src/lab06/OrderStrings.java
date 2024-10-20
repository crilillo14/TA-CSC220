package lab06;

import java.util.Comparator;

public class OrderStrings implements Comparator<String> {
	  public int compare(String lhs, String rhs){
		  return lhs.compareTo(rhs);
	  }
}