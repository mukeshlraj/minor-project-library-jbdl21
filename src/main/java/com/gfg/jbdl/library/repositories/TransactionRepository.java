package com.gfg.jbdl.library.repositories;

import com.gfg.jbdl.library.models.TransactionStatus;
import com.gfg.jbdl.library.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    @Query("select t from Transactions t where t.student.id = :studentId and t.book.id = :bookId and t.transactionStatus=:status order by t.transactionTime")
    List<Transactions> getTransactions(int studentId, int bookId, TransactionStatus status);
}
