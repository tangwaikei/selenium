package supplier.deliveryLimit;

import org.junit.jupiter.api.*;
import supplier.base.SupplierBaseTest;

import java.util.regex.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

class DeliverLimitListPageTest extends SupplierBaseTest {

    @Test
    void test1() {
        DeliverLimitListPage deliverLimitPage = loginPage.driverLimitListPage();
        String startTime = "2021-02-11";
        String endTime = "2021-02-11";
        deliverLimitPage.search(startTime, endTime);
        deliverLimitPage.scrollBottom();
        String totalNum = deliverLimitPage.getText(deliverLimitPage.total);
        Matcher matcher = deliverLimitPage.pattern.matcher(totalNum);
        if(matcher.matches()) {
            int num = Integer.parseInt(matcher.group(1));
            System.out.println(num);
            assertThat(num, greaterThan(0));
        }
    }

}