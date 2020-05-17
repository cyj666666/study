import org.junit.Test;

/**
 * 计算n步台阶的走法，一次只能走一步或者2步
 */
public class StepTest {

    public static int f(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        System.out.print((n - 2) + ":" + f(n - 2) + " ");
        System.out.println((n - 1) + ":" + f(n - 1));
        return f(n - 2) + f(n - 1);
    }

    @Test
    public void test() {
        System.out.println("result:" + f(4));

    }

    public static void main(String[] args) {
        System.out.println("result:" + f(10));

    }
}
