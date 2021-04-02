package supplier.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SupplierBaseTest {
    public static SupplierLoginPage loginPage;

    @BeforeAll
    static void setUp() {
        loginPage = new SupplierLoginPage();
    }

    @AfterAll
    static void tearDown() {
        loginPage.quit();
    }
}
