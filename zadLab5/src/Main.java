public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length != 4){
            throw new Exception("Invalid number of arguments !!");
        }
        try{
            SubtitlesEditor.delay(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Editing finished successfully!!");
    }
}
