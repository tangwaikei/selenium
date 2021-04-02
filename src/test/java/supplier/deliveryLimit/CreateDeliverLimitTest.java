package supplier.deliveryLimit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import supplier.base.SupplierBaseTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateDeliverLimitTest extends SupplierBaseTest {
    @Test
    @Order(1)
    void testCreate() {
        String goodsIdStr = "1253609771240657221";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        String configName = "配置名称".concat(date);
        CreateDeliverLimitPage createDeliverLimitPage = loginPage
                .driverLimitListPage()
                .createDiverLimit();
        DeliverLimitListPage deliverLimitPage = createDeliverLimitPage
                .create(goodsIdStr, configName)
                .search("", "");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        By by = By.cssSelector(".el-table__row > td:nth-child(2) > div");
        assertThat(deliverLimitPage.getText(by), equalTo(configName));
    }

    @Test
    @Order(2)
    void testSearch() {
        DeliverLimitListPage deliverLimitPage = loginPage.driverLimitListPage();
        String applyIdStr = deliverLimitPage.getText(By.cssSelector(".el-table__row > td:nth-child(1) > div"));
        String typeStr = deliverLimitPage.getText(By.cssSelector(".el-table__row > td:nth-child(5) > div"));
        String statusStr = deliverLimitPage.getText(By.cssSelector(".el-table__row > td:nth-child(6) > div"));

        deliverLimitPage.sendKeys(deliverLimitPage.applyId, applyIdStr);

//        deliverLimitPage.click(type);
//        deliverLimitPage.sendKeys(type, typeStr);
//        deliverLimitPage.sendKeys(statusStr);
        deliverLimitPage.search("", "");
        String totalNum = deliverLimitPage.getText(deliverLimitPage.total);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Matcher matcher = deliverLimitPage.pattern.matcher(totalNum);
        if(matcher.matches()) {
            int num = Integer.parseInt(matcher.group(1));
            assertThat(num, equalTo(1));
        }
    }


}