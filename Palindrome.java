	//����� �������� �����,� ����� ���������, ����� �� ��� �������� ������������

public class Palindrome {
	// ����� �������� ������ ���� � ����� ������ ����� ����������� � ������,���������������� ������, ��������� ������� reverseString(), � ���������� ������������ ������ � ����������.

public static void main(String[] args) {
	
	for (int i = 0; i < args.length; i++) {
		String s = args[i];
		String s1=reverseString(s);
	if (s1.equals(s)) System.out.println("����� "+s+" - ���������");
	else System.out.println("����� "+s+" - �� ���������");
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