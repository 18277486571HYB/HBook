package www.hyb.untils;

public class Parse {

    public static Integer StringParseInteger(String s,Integer defaultValue){
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
