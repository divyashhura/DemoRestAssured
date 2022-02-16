//package Pages;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class AccessJson {
//    public static void main(String[] args) throws IOException {
//        byte[] jsonData= Files.readAllBytes(Paths.get("C:\\Users\\DivyashSinghHura" +
//                "\\IdeaProjects\\DemoRestAssured\\Files\\Details.json"));
//        ObjectMapper mapper=new ObjectMapper();
//        Data d=mapper.readValue(jsonData, Data.class);
//        System.out.println(d.getEmail());
//        System.out.println(d.getAddress().getAdd());
//    }
//}
