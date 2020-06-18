package gogogo.singleton;

public class Test3 {
    public static void main(String[] args) {
        int i = 1;//i = 1
        i = i++; //由2到1 =》 i = 1
        int j = i++; //j = 1,i = 2
        int k = i + ++i * i++ + i++ + ++i; //2 + 3 * 3 + 4 + 6 = 11,i = 4,j = 1,k = 11
        System.out.println(i); //
        System.out.println(j); //
        System.out.println(k); //


    }
}
