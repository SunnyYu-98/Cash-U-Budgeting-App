package com.cashu.budgetapp.dao;
import com.cashu.budgetapp.model.Expenses;

public interface ExpensesDao {

    // delete expense and save and find
  // Expenses findExpenseById(int Id);
    void deleteExpense(int ExpensesId);
    void saveExpense( Expenses expenses);






}
