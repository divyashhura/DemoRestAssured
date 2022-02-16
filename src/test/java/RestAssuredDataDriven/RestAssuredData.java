package RestAssuredDataDriven;

import io.restassured.response.Response;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class RestAssuredData {
    public static String url="https://api.stripe.com/v1/customers";
    static Properties prop;
    static String key="sk_test_51KTidySI0qvV4HQF1uPjvXbR8SvosKzGMYfcUKmYpyHoYzRl4trnNUESIyLfixltCTQHTMh1ehjZbASKWvu9tG1s000jTufouw";

    @DataProvider(name="data")
    public Object[][] ApiData() throws IOException {
        Object[][] data=new Object[1][];
        String path = System.getProperty("user.dir") + prop.getProperty("Path");
        System.out.println(path);
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(0);
        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            data[i][0] = sh.getRow(i).getCell(0).getStringCellValue();
            data[i][1] = sh.getRow(i).getCell(1).getStringCellValue();
            data[i][2]=sh.getRow(i).getCell(2).getStringCellValue();
        }
        return data;
    }
    @Test(dataProvider = "data")
    public void postdata(String email,String name,String phone){
        Response resp = given().auth().basic(key, "").formParam("email", email).
                formParam("name", name).formParam("phone",phone).when().post(url);
        int respcode = resp.getStatusCode();
        System.out.println("Responce code is: " + respcode);
        String respbody = resp.getBody().asString();
        System.out.println(respbody);
    }
}