package src;
public class Timer{
    public static void main(String[] args){
        long x = System.nanoTime();
        while (System.nanoTime() - x < 5 * Math.pow(10, 9)){
        }
        System.out.println("done");
    }
}