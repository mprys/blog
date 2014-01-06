package com.pathsf.example.account;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Secured("ROLE_USER")
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account accounts(Principal principal) {
        Assert.notNull(principal);
        Account acc = null;
        try {
        	acc = accountRepository.findByEmail(principal.getName());
		} catch (Exception e) {
			System.err.println(e);
		}
        return acc;
    }
    
    @RequestMapping(value = "accounts/current", method = RequestMethod.GET)
    public String readAccount(Principal principal, Model model){
    	
    	Account acc = null;
        try {
        	acc = accountRepository.findByEmail(principal.getName());
		} catch (Exception e) {
			System.err.println(e);
		}
        model.addAttribute("Post", acc.getPosts().iterator().next());
    	return "/post/postdetails";
    }
}
