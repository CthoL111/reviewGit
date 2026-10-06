// public class try_catch {
//     public static void main(String[] args) {
//         int result = 10 / 0;   // error!
//         System.out.println("Result: " + result);
//         System.out.println("Program finished");  // never runs
//     }
// }

public class try_catch {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("You cannot divide by zero!");
        } finally{
            System.out.println("Always run");
        }
        System.out.println("Program finished");  // this runs now
    }
}