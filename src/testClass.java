
public class testClass {
	public static void main (String[] args) {
//		v;Vounteer Name;date1;date2
		String testString = "v Vounteer Namedate1date2";
		//FIXME what if we only have one semi-colon??
		testString.split(";"); //Array length = 0 of a string if we split on semi-colon but no scs in string.
	}
}
