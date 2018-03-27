	// ласс получает слова,а затем провер€ет, какие из них €вл€ютс€ палиндромами

public class Palindrome {
	// ћетод получает массив слов и потом каждое слово сохран€етс€ в строку,разворачиваетэту строку, использу€ функцию reverseString(), и сравнивает получившуюс€ строку с оригиналом.

public static void main(String[] args) {
	
	for (int i = 0; i < args.length; i++) {
		String s = args[i];
		String s1=reverseString(s);
	if (s1.equals(s)) System.out.println("—лово "+s+" - палиндром");
	else System.out.println("—лово "+s+" - не палиндром");
	} 
}
	
public static String reverseString(String s) {
		
	String rev="";
	for (int i = s.length()-1; i >=0; i--) {
	rev+=s.charAt(i);
	}
	return rev;
	}
}