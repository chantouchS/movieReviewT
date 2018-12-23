package csku.movie;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Account;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefMovie {

    Account account;
    boolean check;

    @Before
    public void init(){
        account = new Account();
    }
    @Given("a manager with id (.*) and password (.*)")
    public void a_manager_with_id_and_password(String username,String password) {
        account.setUsername(username);
        account.setPassword(password);
    }
    @When("I login to Account with id (.*) and password (.*)")
    public void i_login_to_Account_with_id_and_password(String username,String password){
        check = account.check(username,password);
    }
    @Then("I can add new movie")
    public void i_can_add_new_movie(){
        assertTrue(check);
    }
    @Then("I cannot add new movie")
    public void I_cannot_add_new_movie(){
        assertFalse(check);
    }

}
