# Nextneo Bank Project
Nextneo Bank Project

Java SOA project that provides a bank service.

- Web module was used: JSF 2.2, Primefaces 6, Spring 5 running on Tomcat 
- Service modules were used: EJB 3.2, JPA 2 running on JBoss Wildfly

<img src="../docs/images/software_design.png" alt="Software Design" height="300" />

## Requirements

- JDK 8
- MySQL
- Tomcat 9 (web module)
- Application Server (JBoss Wildfly or similar)

## Configure

###Database

- Run <a href="../docs/scripts/dump.sql">script</a>

###JBoss Wildfly

- go to /modules/system/layers/base/com/
- Create a folder /mysql/main/
- add mysql connector jar

- create a file module.xml with:

<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.0" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.24-bin.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
  </dependencies>
</module>

- modify the standalone.xml file in /standalone/configuration/ with:

<datasource jndi-name="java:/bank" pool-name="bank" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/bank</connection-url>
    <driver>com.mysql</driver>
        <pool>
            <min-pool-size>10</min-pool-size>
            <max-pool-size>100</max-pool-size>
            <prefill>true</prefill>
        </pool>
        <security>
            <user-name>root</user-name>
            <password>root</password>
        </security>
</datasource>


## Info

### API Services - Customer Service

#### /bank-customer-ws/customer

##### request
{"name": "Milton", "lastName":"Friedman", "document": "50300007124"}

##### response
{
    "id": 3,
    "name": "Milton",
    "lastName": "Friedman",
    "document": "50300007124",
    "user": {
        "id": 3,
        "lastAccess": null,
        "login": "50300007124",
        "password": "4CE74kEbxjt5hmE3o/+jfQ==",
        "type": "CUSTOMER",
        "groupRoles": null
    }
}

#### /bank-customer-ws/account/addAccount

##### request
{"branchNumber": "1618", "accountDigit": "5", "status": "ACTIVE", "type": "CHECKING_ACCOUNT", "currencyId": 1, "usersId": [1]}

##### response
{
    "id": 1,
    "branchNumber": "1618",
    "accountNumber": "001000",
    "accountDigit": "5",
    "status": "ACTIVE",
    "type": "CHECKING_ACCOUNT",
    "currencyId": 1,
    "usersId": [
        1
    ]
}

#### /bank-customer-ws/account-movement/addAccountMovement

##### request
{"accountId":1, "value": "55.00", "description": "TEST", "type":"CREDIT"}

##### response
{
    "launchDate": 1521254177646,
    "value": 55,
    "auth": "835755570",
    "description": "TEST",
    "accountBalance": 240,
    "type": "CREDIT",
    "accountId": 1
}