package threads;
public class Threads {
    public static void main(String[] args) {
        // TODO code application logic here
        Vowel v=new Vowel();
        Digit d=new Digit();
        Thread t=new Thread(d);
        v.start();
        t.start();
    }
    
}
