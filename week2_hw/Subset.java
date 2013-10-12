/**
 * Created with IntelliJ IDEA.
 * User: Pasha
 * Date: 9/9/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        String[] values = StdIn.readAllStrings();
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        for(int i = 0; i < values.length; i++) {
            queue.enqueue(values[i]);
        }

        while(k > 0) {
            StdOut.println(queue.dequeue());

            k--;
        }
    }
}
