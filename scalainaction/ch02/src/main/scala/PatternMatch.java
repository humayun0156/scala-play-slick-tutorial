/**
 * @author Humayun
 */
public class PatternMatch {
    public static void main(String[] args) {
        ordinal(3);
    }

    static void ordinal(int num) {
        switch (num) {
            case 1: System.out.println("1st"); break;
            case 2:
                System.out.println("2nd"); break;
            case 3:
                System.out.println("3rd"); break;
            default:
                System.out.println("other"); break;
        }
    }
}
