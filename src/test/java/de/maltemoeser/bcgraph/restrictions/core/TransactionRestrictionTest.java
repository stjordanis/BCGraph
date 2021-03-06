package de.maltemoeser.bcgraph.restrictions.core;

import de.maltemoeser.bcgraph.restrictions.Restriction;
import de.maltemoeser.bcgraph.testing.BitcoinTest;
import de.maltemoeser.bcgraph.testing.TestUtils;
import org.bitcoinj.core.Transaction;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TransactionRestrictionTest extends BitcoinTest {

    @Test
    public void testPaymentCodeRestriction() {
        Transaction paymentCodeTransaction = TestUtils.getPaymentCodeTransaction();
        Transaction stealthAddressTransaction = TestUtils.getStealthTransaction();
        Transaction normalTransaction = TestUtils.getP2PKHTransaction();

        Restriction<Transaction> restriction = new PaymentCodeNotificationTransactionRestriction();

        assertTrue(restriction.evaluate(paymentCodeTransaction));
        assertFalse(restriction.evaluate(stealthAddressTransaction));
        assertFalse(restriction.evaluate(normalTransaction));
    }

    @Test
    public void testStealthAddressRestriction() {
        Transaction paymentCodeTransaction = TestUtils.getPaymentCodeTransaction();
        Transaction stealthAddressTransaction = TestUtils.getStealthTransaction();
        Transaction normalTransaction = TestUtils.getP2PKHTransaction();

        Restriction<Transaction> restriction = new StealthAddressTransactionRestriction();

        assertTrue(restriction.evaluate(stealthAddressTransaction));
        assertFalse(restriction.evaluate(paymentCodeTransaction));
        assertFalse(restriction.evaluate(normalTransaction));
    }
}
