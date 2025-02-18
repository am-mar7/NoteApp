package My_Lib;

import java.util.Random;

public class Util {
    private static Random r  = new Random();

    private Util() {

    }

    ////////////////random_Generatiom///////////////////

    public static int RandomNumber() {
        return r.nextInt();
    }
    public static int RandomNumber(int x ,int y) {
        return r.nextInt(x,y+1);
    }
    public static double RandomNumber(double x ,double y) {
        return r.nextDouble(x, y+1);
    }

    public static boolean RandomBool () {
        return r.nextBoolean();
    }
    public static char Randomsmallchar() {
        int a = 'a' , z = 'z' +1;
        return (char)r.nextInt(a,z);
    }
    public static char RandomCapitalchar() {
        int a = 'A' , z = 'Z'+1;
        return (char)r.nextInt(a,z);
    }
    public static char Randomchar() {
        int a , z ;
        boolean flag = RandomBool();
        if(flag) {
            a = 'a' ; z ='z' +1;
        }
        else {
            a ='A' ; z = 'Z' +1;
        }
        return (char)r.nextInt(a,z);
    }
    public static String Generateword() {
        int size = r.nextInt(100)+2;
        String ret = "";
        for(int i = 0 ; i < size ; i++) {
            char c = Randomchar();
            ret += c;
        }
        return ret;
    }
    public static String Generateword(int size) {
        String ret = "";
        for(int i = 0 ; i < size ; i++) {
            char c = Randomchar();
            ret += c;
        }
        return ret;
    }

    public static void FillArrayWithRandomNumbers( int arr[],int size) {
        for(int i = 0 ; i < size ; i++) {
            arr[i]= RandomNumber();
        }
    }
    public static void FillArrayWithRandomNumbers( int arr[],int size,int l ,int r) {
        for(int i = 0 ; i < size ; i++) {
            arr[i]= RandomNumber(l,r+1);
        }
    }

    ///////////////////////////////////////////////////////

    /////////////////////////Strings///////////////////////
    public static boolean is_a_sub_string(String sub ,String org) {
        if(sub.length() > org.length())return false;
        if(sub.equals(org))return true;
        int i = 0 ,j =0;
        while(j < org.length()){
            if(i == sub.length())return true;
            if(sub.charAt(i) == org.charAt(j))i++;
            else i = (sub.charAt(i) == org.charAt(j)) ? 1 : 0;
            j++;
        }
        return i == sub.length();//
    }
    public static String[] Split(int size,String Line, String seprator) {
        int ptr = 0;
        String tmp ="";
        String[] Data =new String [size];
        for(int i = 0 ; i < Line.length(); i++) {
            if(Line.charAt(i) == seprator.charAt(0)) {
                i+= seprator.length() -1;
                Data[ptr++] = tmp;
                tmp ="";
            }
            else tmp += Line.charAt(i);
        }
        Data[ptr++] = tmp;
        return Data;
    }
    ////////////////////////////////////////////


    //////////////////converts/////////////////

    public static int to_int(String s){
        int x = 0;
        for(int i  = 0; i < s.length(); i++){
            int dig = s.charAt(i) - '0';
            x*=10;
            x+= dig;
        }
        return x;
    }
    public static double to_double (String val) {
//		if(val.equals("0"))return  0.0;
        double ret = 0;int cnt = 0 , len = val.length();
        for(int i = 0 ; i < len ; i++) {
            if(val.charAt(i) == '.') {
                cnt++;
                continue;
            }
            if(cnt > 0)cnt*=10;
            int digit = val.charAt(i) - '0';
            ret = ret*10 + digit;
        }
        return ret/cnt;
    }
    public static String convert_to_string(int val) {
        if(val == 0)return "0";
        String ret ="";
        while(val > 0) {
            char idx = (char) ((char)val%10 + '0');
            ret += idx;
            val /= 10;

        }
        ret = reverse(ret);
        return ret;
    }
    public static String convert_to_string(double val) {
        int first = (int) val;
        double sec = val - first;
        int cnt = 8;
        while(sec != (int)sec && cnt> 0) {
            sec*=10;cnt--;
        }
        if(sec == 0)return (convert_to_string(first)+".00");
        return convert_to_string(first)+"."+convert_to_string((int)sec);
    }
    ///////////////////reverse////////////////////////

    public static String reverse(String s) {
        String ret ="";
        for(int i  = s.length()-1; i >= 0; i--)
            ret += s.charAt(i);
        return ret;
    }
    public static void reverse(int arr[]) {
        int [] rev = new int[arr.length];
        for(int i = arr.length -1; i >= 0; i--) {
            rev[i] = arr[i];
        }
        arr = rev;
    }
    public static void reverse(double arr[]) {
        double [] rev = new double[arr.length];
        for(int i = arr.length -1; i >= 0; i--) {
            rev[i] = arr[i];
        }
        arr = rev;
    }
    public static void reverse(String arr[]) {
        String [] rev = new String[arr.length];
        for(int i = arr.length -1; i >= 0; i--) {
            rev[i] = arr[i];
        }
        arr = rev;
    }
    public static void reverse(char arr[]) {
        char [] rev = new char[arr.length];
        for(int i = arr.length -1; i >= 0; i--) {
            rev[i] = arr[i];
        }
        arr = rev;
    }
    public static void reverse(long arr[]) {
        long [] rev = new long[arr.length];
        for(int i = arr.length -1; i >= 0; i--) {
            rev[i] = arr[i];
        }
        arr = rev;
    }
    public static void reverse(float arr[]) {
        float [] rev = new float[arr.length];
        for(int i = arr.length -1; i >= 0; i--) {
            rev[i] = arr[i];
        }
        arr = rev;
    }

    ///////////////////////////////////////////////

}
