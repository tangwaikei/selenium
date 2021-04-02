package coupon;

import base.BaseTest;
import org.junit.jupiter.api.AfterAll;
import pageObject.LoginsPage;
import pageObject.coupon.BlankCouponPage;
import pageObject.coupon.CouponPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CouponTest extends BaseTest {
    static CouponPage couponPage;
    @BeforeAll
    static void before() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        couponPage = (CouponPage) new LoginsPage().login(Class.forName("pageObject.coupon.CouponPage"), CouponPage.url);
    }

    @Test
    void testCreate() {
        couponPage.createACoupon();
    }

    @Test
    void testBlankCreate() {
        BlankCouponPage blankCouponPage = couponPage.createBlankCoupon();
        assertThat(blankCouponPage.getBlankErrorFromCouponName(), equalTo("不能为空"));
        assertThat(blankCouponPage.getBlankErrorFromTotalNum(), equalTo("不能为空"));
    }


    @AfterAll
    static void quit() throws InterruptedException {
        Thread.sleep(2000);
        couponPage.quit();
    }

}
