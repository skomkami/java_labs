import javafx.application.Application;

public class Main extends ImageChooser{

    public Main() throws Exception {
            super(4, 200);
    }

    public static void main(String[] args) {
        try{
            Application.launch();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
