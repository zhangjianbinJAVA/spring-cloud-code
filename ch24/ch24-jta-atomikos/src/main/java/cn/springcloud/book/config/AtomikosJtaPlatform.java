package cn.springcloud.book.config;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * 对  AbstractJtaPlatform 的支持
 * <p>
 * AbstractJtaPlatform是Spring提供事务支持的核心处理类
 * 它的功能可大致划分
 * 处理传播行为
 * 处理挂起与恢复
 * 检查只读标志，处理回滚
 * 处理回调
 * <p>
 * <p>
 * Created by caibosi on 2018-07-25.
 */
public class AtomikosJtaPlatform extends AbstractJtaPlatform {

    private static TransactionManager transactionManager;

    private static UserTransaction userTransaction;

    public static void setTransactionManager(TransactionManager transactionManager) {
        AtomikosJtaPlatform.transactionManager = transactionManager;
    }

    public static void setUserTransaction(UserTransaction userTransaction) {
        AtomikosJtaPlatform.userTransaction = userTransaction;
    }

    @Override
    protected TransactionManager locateTransactionManager() {
        return transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return userTransaction;
    }
}
