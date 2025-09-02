public class test {
    private static int count = 0; // This is shared by ALL Counter objects

    public test() {
        count++; // Every time we create a new Counter, this shared number goes up
    }

    public static int getCount() {
        System.out.println(count);
        return count;
    }
    public static void main(String[] args) {
        test test1 = new test();
        test test = new test();
        test.getCount();
    }
}
