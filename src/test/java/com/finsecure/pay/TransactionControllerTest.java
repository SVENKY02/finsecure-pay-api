package com.finsecure.pay;

import com.finsecure.pay.controller.TransactionController;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionControllerTest {

    private final TransactionController controller = new TransactionController();

    @Test
    public void testHealthCheck() {
        Map<String, String> response = controller.healthCheck();
        assertEquals("UP", response.get("status"));
        assertEquals("FinSecure Pay API", response.get("service"));
    }

    @Test
    public void testValidateTransactionApproved() {
        Map<String, Object> tx = new HashMap<>();
        tx.put("amount", 500.0);
        tx.put("currency", "USD");

        Map<String, Object> response = controller.validateTransaction(tx);
        assertEquals("APPROVED", response.get("status"));
        assertEquals("USD", response.get("currency"));
        assertEquals(500.0, response.get("amount"));
    }
}
