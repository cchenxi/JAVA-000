package io.github.cchenxi.w7.db.fx02.service;

import io.github.cchenxi.w7.db.fx02.exception.RollbackException;

/**
 * Date: 2020-12-02
 *
 * @author chenxi
 */
public interface CustomerService {
    void clear();
    void addCustomer();
    void addCustomerRollback() throws RollbackException;
    long getCustomersCount();
}
