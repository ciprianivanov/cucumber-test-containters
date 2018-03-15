package com.cucumber.container.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.OracleContainerProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectToContainerSteps {

    private ResultSet resultSet;
    private Connection oracleConnection;
    private JdbcDatabaseContainer oracleContainer;

    @Before
    public void setup() {
        OracleContainerProvider oracleContainerProvider = new OracleContainerProvider();
        oracleContainer = oracleContainerProvider.newInstance("latest");
        oracleContainer.start();
    }

    @Given("^I want to connect to a Oracle DB container$")
    public void connectToAOracleDbContainer() throws SQLException {
        oracleConnection = oracleContainer.createConnection("");
    }

    @When("^I run a test DB query$")
    public void containerIsCreated() throws SQLException {
       resultSet = oracleConnection.prepareStatement("SELECT * FROM SYS.USER$").executeQuery();
    }

    @Then("^the query outputs the result$")
    public void queryOutputsTheCorrectResult() throws SQLException {
        assert resultSet.next();
    }

    @After
    public void tearDown() throws SQLException {
        oracleConnection.close();
        oracleContainer.close();
    }
}