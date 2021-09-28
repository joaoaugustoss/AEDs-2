class Q2{
    public static void main(String[] args){
        int n = MyIO.readInt();
        int x = MyIO.readInt();
        int y = MyIO.readInt();
        int z = MyIO.readInt();

        if(x + y + z <= n){
            System.out.println("3");
        } else if(x + y <= n || x + z <= n || y + z <= n){
            System.out.println("2");
        } else if(x <= n || y <= n || z <= n){
            System.out.println("1");
        }
    }
}