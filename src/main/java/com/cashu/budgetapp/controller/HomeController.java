package com.cashu.budgetapp.controller;

import com.cashu.budgetapp.model.Expenses;
import com.cashu.budgetapp.model.User;
import com.cashu.budgetapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HomeController {
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String showHomepage(HttpServletRequest request, HttpServletResponse response, Model model){
        addLoggedInUserToModel(model);
        return "home";
    }

    @RequestMapping(value = "/budget", method = RequestMethod.GET)
    public String showBudgetPage(HttpServletRequest request, HttpServletResponse response, Model model){
        addLoggedInUserToModel(model);
        User user = userService.getCurrentLoggedInUser();
        List<Expenses> expenses = user.getAllExpenses();
        //Collections.sort(expenses);
        double totalIncome = 0.0;
        double totalExpense = 0.0;

        for(Expenses exp : expenses) {
            if(exp.getAmount() > 0)
                totalIncome += exp.getAmount();
            else
                totalExpense += exp.getAmount();
        }

        model.addAttribute("expenses", expenses);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("availableBudget", (totalIncome + totalExpense));

        return "budget";
    }

    @RequestMapping(value = "/privacy", method = RequestMethod.GET)
    public String showPrivacyPage(HttpServletRequest request, HttpServletResponse response, Model model){
        addLoggedInUserToModel(model);
        return "privacy";
    }

    @RequestMapping(value = "/terms", method = RequestMethod.GET)
    public String showTermsPage(HttpServletRequest request, HttpServletResponse response, Model model){
        addLoggedInUserToModel(model);
        return "terms";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String showContactPage(HttpServletRequest request, HttpServletResponse response, Model model){
        addLoggedInUserToModel(model);
        return "contact";
    }


    public void addLoggedInUserToModel(Model model){
        //user is already logged in
        User loggedInUser = userService.getCurrentLoggedInUser();
        model.addAttribute("user", loggedInUser);
    }
}
