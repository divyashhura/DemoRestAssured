package RestAssuredDataDriven;

import io.restassured.response.Response;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class RestAssuredData {
    public static String url="https://api.stripe.com/v1/customers";
    static String key="sk_test_51KTidySI0qvV4HQF1uPjvXbR8SvosKzGMYfcUKmYpyHoYzRl4trnNUESIyLfixltCTQHTMh1ehjZbASKWvu9tG1s000jTufouw";

    @Test
    public void ApiData() throws IOException {
        String path = System.getProperty("user.dir") + "/Files/UserDataApi.xlsx";
        System.out.println(path);
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(0);
        String email = sh.getRow(1).getCell(0).getStringCellValue();
        String name = sh.getRow(1).getCell(1).getStringCellValue();
        String phone =sh.getRow(1).getCell(2).getStringCellValue();
        Response resp = given().auth().basic(key, "").formParam("email", email).
                formParam("name", name).formParam("phone",phone).when().post(url);

        int respcode = resp.getStatusCode();
        System.out.println("Response code is: " + respcode);

        String respbody = resp.getBody().asString();
        System.out.println(respbody);
    }
}
